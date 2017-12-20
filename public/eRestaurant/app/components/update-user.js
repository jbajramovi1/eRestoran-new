import Ember from 'ember';

export default Ember.Component.extend({
  notifications: Ember.inject.service('notification-messages'),
  adminService: Ember.inject.service('admin-service'),
  adminpanel:Ember.inject.controller(),
  errorEmail:null,
  errorPassword:null,

  actions:{
    addUser(){
      this.set('errorEmail',null);
      this.set('errorPassword',null);
      let errorFront=false;
      if (!this.get('user.email')) {
        this.set("errorEmail","Email field is required");
        errorFront=true;
      }
      if (!this.get('user.password')) {
        this.set("errorPassword","Password field is required");
        errorFront=true;
      }
      if (errorFront){
        this.get('notifications').error("Error occured while adding an user!", {
         autoClear: true,
         clearDuration: 1500
          });
        return;
      }
      this.get('adminService').updateUser(this.get('userId'),this.get('user.firstName'),this.get('user.lastName'),this.get('user.email'),this.get('user.password'),this.get('user.phone'),this.get('user.country'),this.get('user.city'))
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
