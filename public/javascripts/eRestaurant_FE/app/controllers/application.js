import Ember from 'ember';

export default Ember.Controller.extend({
  sessionService: Ember.inject.service('session-service'),
  accountService: Ember.inject.service('account-service'),
  notifications: Ember.inject.service('notification-messages'),
  isAuthenticated:false,
  isAdmin:false,
  user:null,
  init:function(){
    var self=this;
    this.get('accountService').getSession()
    .done(response => {
           if (response){
           this.get('sessionService').authenticate(response.email,response.password,response.id,response.role);
           self.send('authenticate');
         }
        });
  },
  actions:{
    authenticate(){
      this.set('isAuthenticated',true);
      this.set('user',this.get('sessionService').getCurrentUser());
      if (this.get('sessionService').isAdmin()) this.set('isAdmin',true);
    },
    unauthenticate(){
      this.set('isAuthenticated',false);
      this.set('user',null);
      this.set('isAdmin',false);
      this.get('sessionService').unauthenticate();
      this.get('accountService').logout()
      .done(response => {
           this.transitionToRoute('home');
         });
      }
    }

});
