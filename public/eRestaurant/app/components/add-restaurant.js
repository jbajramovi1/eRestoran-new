import Ember from 'ember';

export default Ember.Component.extend({
  notifications: Ember.inject.service('notification-messages'),
  adminService: Ember.inject.service('admin-service'),
  adminpanel:Ember.inject.controller(),
  errorName:null,
  errorDesc:null,
  errorCategory:null,
  restaurantName:null,
  restaurantCategory:null,
  restaurantLocation:null,
  restaurantDesc:null,
  enableBasic:true,
  enableMenu:false,
  enableTables:false,
  
  actions:{
    addRestaurant(){
      this.set('errorName',null);
      this.set('errorDesc',null);
      this.set('errorCategory',null);
      let errorFront=false;
      if (!this.get('restaurantName')) {
        this.set("errorName","Restaurant name field is required");
        errorFront=true;
      }
      if (!this.get('restaurantDesc')) {
        this.set("errorDesc","Restaurant description field is required");
        errorFront=true;
      }
      if (!this.get('restaurantCategory')) {
        this.set("errorCategory","Restaurant category field is required");
        errorFront=true;
      }
      if (errorFront){
        this.get('notifications').error("Error occured while adding restaurant", {
         autoClear: true,
         clearDuration: 1500
          });
        return;
      }
      this.get('adminService').addRestaurant(this.get('restaurantName'),this.get('restaurantDesc'),this.get('restaurantCategory'))
      .done(response => {
           this.get('notifications').success('Successfully added a restaurant!', {
            autoClear: true,
            clearDuration: 1500
          });
          this.get('adminpanel').send('refreshModel');
          this.get('adminpanel').send('openRestaurants');
     })
       .fail(response => {
          this.get('notifications').error('Error occured while adding restaurant', {
           autoClear: true,
           clearDuration: 1500
         });
       });
    },
    cancel(){
      this.get('adminpanel').send('openRestaurants');
    },
    openBasic:function(){
      this.set('enableBasic',true);
      this.set('enableMenu',false);
      this.set('enableTables',false);
    },
    openMenu:function(){
      this.set('enableBasic',false);
      this.set('enableMenu',true);
      this.set('enableTables',false);
    },
    openTables:function(){
      this.set('enableBasic',false);
      this.set('enableMenu',false);
      this.set('enableTables',true);
    }

  }
  });
