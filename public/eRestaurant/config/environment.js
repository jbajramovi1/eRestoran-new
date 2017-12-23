/* eslint-env node */

module.exports = function(environment) {
  var ENV = {
    modulePrefix: 'e-restaurant-fe',
    environment: environment,
    rootURL: '/',
    locationType: 'auto',
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. 'with-controller': true
      },
      EXTEND_PROTOTYPES: {
        // Prevent Ember Data from overriding Date.parse.
        Date: false
      }
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
    },
    CLOUDINARY: {
      CLOUD_NAME: 'dxcx9k4in', // "cloud name" in Cloudinary
      SECURE: true // use https? 
    }
  };
  ENV.googleMap = {
  apiKey: 'AIzaSyCQKiuoytD_QzAJBrVYiQtit3E-L-zya7E'
};
  if (environment === 'development') {
    // ENV.APP.LOG_RESOLVER = true;
    ENV.APP.LOG_ACTIVE_GENERATION = true;
    ENV.APP.LOG_TRANSITIONS = true;
    ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
    ENV.APP.LOG_VIEW_LOOKUPS = true;
    ENV.API_HOST = "http://localhost:9000";
    ENV.API_VERSION = "1";
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
  }

  if (environment === 'production') {
    ENV.locationType = 'hash';
    ENV.API_HOST = "https://stark-citadel-95079.herokuapp.com";
    ENV.API_VERSION = "1";
  }

  return ENV;
};
