import Ember from 'ember';

export default Ember.Controller.extend({
  errorUsername:null,
  errorPassword:null,
  accountService: Ember.inject.service('account-service'),
  sessionService:Ember.inject.service('session-service'),
  notifications: Ember.inject.service('notification-messages'),
  applicationController: Ember.inject.controller('application'),
  actions:{
    login(username,password){
      this.set('errorUsername',null);
      this.set('errorPassword',null);
      let emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      let errorFront=false;
      if (!this.get('password')) {
        this.set("errorPassword","Password field is required");
        errorFront=true;
      }
      if (!this.get('username')) {
        this.set("errorUsername","Email field is required");
        errorFront=true;
      }
      else if (!emailRegex.test(this.get('username'))){
        this.set("errorUsername","Email format is incorrect");
        errorFront=true;
      }

      if (errorFront){
        this.get('notifications').error("Authentication error occured!", {
         autoClear: true,
         clearDuration: 1500
          });
        return;
      }
      this.get('accountService').userLogin(username,password)
      .done(response => {
           this.get('notifications').success('Login successful!', {
            autoClear: true,
            clearDuration: 1500
          });
           this.get('accountService').getSession()
           .done(response => {
                  this.get('sessionService').authenticate(username,password,response.id,response.role);
                  this.get('applicationController').send('authenticate');
                  this.transitionToRoute('home');
               });

     })
     .fail(response => {
       var data = $.parseJSON(response.responseText);

          this.set('errorUsername',data.email);
          this.set('errorPassword',data.password);
          this.get('notifications').error("Authentication error occured!", {
           autoClear: true,
           clearDuration: 1500
         });

     });

    }
  }
});
