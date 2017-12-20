import Ember from 'ember';

export default Ember.Component.extend({
  notifications: Ember.inject.service('notification-messages'),
  adminService: Ember.inject.service('admin-service'),
  adminpanel:Ember.inject.controller(),
  errorEmail:null,
  errorPassword:null,
  actions:{
    updateUser(){
      this.set('errorEmail',null);
      this.set('errorPassword',null);
      let emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      let errorFront=false;
      if (!this.get('model.email')) {
        this.set("errorEmail","Email field is required");
        errorFront=true;
      }
      else if (!emailRegex.test(this.get('model.email'))){
        this.set("errorEmail","Email format is incorrect");
        errorFront=true;
      }
      if (!this.get('model.password')) {
        this.set("errorPassword","Password field is required");
        errorFront=true;
      }
      if (errorFront){
        this.get('notifications').error("Error occured while updating user!", {
         autoClear: true,
         clearDuration: 1500
          });
        return;
      }
      this.get('adminService').updateUser(this.get('model.id'),this.get('model.firstName'),this.get('model.lastName'),this.get('model.email'),this.get('model.password'),this.get('model.phone'),this.get('model.country'),this.get('model.city'))
      .done(response => {
           this.get('notifications').success('Successfully updated user!', {
            autoClear: true,
            clearDuration: 1500
          });
          this.get('adminpanel').set('userId',null);
          this.get('adminpanel').send('refreshModel');
          this.get('adminpanel').send('openUsers');
     })
     .fail(response => {
       var data = $.parseJSON(response.responseText);

          this.set('errorEmail',data.email);
          this.set('errorPassword',data.password);

          this.get('notifications').error("Error occured while updating user!", {
           autoClear: true,
           clearDuration: 1500
         });
       });
    }

  }
});
