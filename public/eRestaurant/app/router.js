import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL/*,
  didTransition() {
    this._super();
    window.scrollTo(0, 0);
  }*/
});

Router.map(function() {
  this.route('home', { path: '/' });
  this.route('home');
  this.route('login');
  this.route('register');
  this.route('restaurant',{path: 'restaurant/:id'});
  this.route('adminpanel');
  this.route('restaurants');
});

export default Router;
