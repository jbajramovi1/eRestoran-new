import Ember from 'ember';
import Account from '../models/account';

export default Ember.Controller.extend({
  errorUsername:null,
  errorPassword:null,
  errorConfirmPassword:null,
  accountService: Ember.inject.service('account-service'),
  notifications: Ember.inject.service('notification-messages'),
  actions:{
    register(){
      var account=Account.create({});
      account.setProperties({'email':this.get('email'),'password':this.get('password'),'country':this.get('country'),'city':this.get('city'),'firstName':this.get('firstName'),'lastName':this.get('lastName')});
      this.set('errorEmail',null);
      this.set('errorPassword',null);
      this.set('errorConfirmPassword',null);
      let emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      let errorFront=false;
      if (!this.get('password')) {
        this.set("errorPassword","Password field is required");
        errorFront=true;
      }
      else if (this.get('password')!=this.get('passwordConf')) {
        this.set("errorConfirmPassword","Password doesn't match");
        errorFront=true;
      }
      if (!this.get('email')) {
        this.set("errorEmail","Email field is required");
        errorFront=true;
      }
      else if (!emailRegex.test(this.get('email'))){
        this.set("errorEmail","Email format is incorrect");
        errorFront=true;
      }

      if (errorFront){
        this.get('notifications').error("Authentication error occured!", {
         autoClear: true,
         clearDuration: 1500
          });
        return;
      }

      this.get('accountService').
      userRegister(this.get('firstName'),this.get('lastName'),this.get('email'),this.get('phone'),this.get('country'),this.get('city'),this.get('password'))
                  .done(response => {
                       this.get('notifications').success('Successful registration!', {
                        autoClear: true,
                        clearDuration: 1500
                      });
                       this.transitionToRoute('home');
                 })
                   .fail(response => {
                     var data = $.parseJSON(response.responseText);

                        this.set('errorEmail',data.email);
                        this.set('errorPassword',data.password);

                        this.get('notifications').error("Authentication error occured!", {
                         autoClear: true,
                         clearDuration: 1500
                       });
                   });

    }
}
});
