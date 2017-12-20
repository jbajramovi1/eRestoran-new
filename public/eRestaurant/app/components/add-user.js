import Ember from 'ember';

export default Ember.Component.extend({
  notifications: Ember.inject.service('notification-messages'),
  adminService: Ember.inject.service('admin-service'),
  adminpanel:Ember.inject.controller(),
  errorEmail:null,
  errorPassword:null,
  email:null,
  firstName:null,
  lastName:null,
  password:null,
  phone:null,
  country:null,
  city:null,
  actions:{
    addUser(){
      this.set('errorEmail',null);
      this.set('errorPassword',null);
      let errorFront=false;
      if (!this.get('email')) {
        this.set("errorEmail","Email field is required");
        errorFront=true;
      }
      if (!this.get('password')) {
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
      this.get('adminService').updateUser(this.get('firstName'),this.get('lastName'),this.get('email'),this.get('password'),this.get('phone'),this.get('country'),this.get('city'))
      .done(response => {
           this.get('notifications').success('Successfully added an user!', {
            autoClear: true,
            clearDuration: 1500
          });
          this.get('adminpanel').send('refreshModel');
          this.get('adminpanel').send('openUsers');
     })
     .fail(response => {
       var data = $.parseJSON(response.responseText);

          this.set('errorEmail',data.email);
          this.set('errorPassword',data.password);

          this.get('notifications').error("Error occured while adding an user!", {
           autoClear: true,
           clearDuration: 1500
         });
       });
    }

  }
});
