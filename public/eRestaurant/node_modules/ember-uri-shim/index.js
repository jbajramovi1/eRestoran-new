/* jshint node: true */
'use strict';

var Funnel = require('broccoli-funnel');
var mergeTrees = require('broccoli-merge-trees');
var defaults = require('lodash.defaults');
var path = require('path');

module.exports = {
  name: 'uri',

  included: function (app) {
    this._super.included.apply(this, arguments);

    // see: https://github.com/ember-cli/ember-cli/issues/3718
    if (typeof app.import !== 'function' && app.app) {
      app = app.app;
    }

    this.app = app;
    this.urijsOptions = this.getConfig();

    var vendor = this.treePaths.vendor;

    app.import(vendor + '/urijs/URI.min.js', { prepend: true });
  },

  treeForVendor: function (vendorTree) {
    var trees = [];
    var options = this.urijsOptions;

    if (vendorTree) {
      trees.push(vendorTree);
    }

    trees.push(new Funnel(options.urijsPath, {
      destDir: 'urijs',
      include: [new RegExp(/\.js$/)],
      exclude: ['test', 'benchmark', 'example', 'src'].map(function (key) {
        return new RegExp(key + '\.js$');
      }),
    }));

    return mergeTrees(trees);
  },

  getConfig: function () {
    var projectConfig = ((this.project.config(process.env.EMBER_ENV) || {}).urijs || {});
    var urijsPath = path.dirname(require.resolve('urijs'));

    var config = defaults(projectConfig, {
      urijsPath: urijsPath,
    });

    return config;
  },
};
