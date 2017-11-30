import Ember from 'ember';

export default Ember.Route.extend({
    account: Ember.inject.service('account-service'),
    session: Ember.inject.service('session-service'),
    setupController: function(controller, model) {
      this._super(controller, model);
      this.controller.set('errorUsername',null);
      this.controller.set('errorPassword',null);
      this.controller.set('username',null);
      this.controller.set('password',null);
  },
    actions:{
        authenticate(){
          this.controllerFor('login').send('login',this.controller.get("username"),this.controller.get("password"));
        }
    }
});
