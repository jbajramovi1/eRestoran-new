import Ember from 'ember';

export default Ember.Route.extend({
    accountService: Ember.inject.service('account-service'),
    notifications: Ember.inject.service('notification-messages'),
    setupController: function(controller, model) {
      this._super(controller, model);
      this.controller.set('errorEmail',null);
      this.controller.set('errorPassword',null);
      this.controller.set('errorConfirmPassword',null);
      this.controller.set('firstName',null);
      this.controller.set('lastName',null);
      this.controller.set('email',null);
      this.controller.set('password',null);
      this.controller.set('phone',null);
      this.controller.set('country',null);
      this.controller.set('city',null);
      this.controller.set('passwordConf',null);
  },
    actions:{
    register(){
      this.controllerFor('register').send('register')
         }
    }
})
