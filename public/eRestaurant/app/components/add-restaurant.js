import Ember from 'ember';
import Restaurant from '../models/restaurant';
import Menu from '../models/menu';

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
  breakfast: Ember.computed(function() {
    return [{
      name:null,
      description:null,
      price:null
    }];
  }),
  lunch:[{id:1,name:'',description:'',price:''}],
  dinner:[{id:1,name:'',description:'',price:''}],
  tables:[{id:1,people:'2',amount:''}],
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
        console.log('restaurantName');
        this.get('notifications').error("Error occured while adding restaurant", {
         autoClear: true,
         clearDuration: 1500
          });
        return;
      }

      this.get('adminService').addRestaurant(this.get('restaurantName'),this.get('restaurantDesc'),this.get('restaurantCategory'))
      .done(response => {

          var restaurant=Restaurant.create({});
          restaurant.set('id',response.id);
          var menuBreakfast=Menu.create({});
          this.get('adminService').addMenu('Breakfast',restaurant)
          .done(response =>{
             menuBreakfast.set('id',response.id);
             console.log('add');
             console.log(this.get('breakfast'));
             console.log(this.get('lunch'));
             for (item in this.get('breakfast')){
               this.get('adminService').addMenuItem(item.name,item.price,item.description,menuBreakfast)
               .fail(response => {
                 this.get('notifications').error('Error occured while adding restaurant menu item', {
                  autoClear: true,
                  clearDuration: 1500
                });
               })
             }
          })
          .fail(response => {
            this.get('notifications').error('Error occured while adding restaurant menu', {
             autoClear: true,
             clearDuration: 1500
           });
         });

           var menuLunch=Menu.create({});
           this.get('adminService').addMenu('Lunch',restaurant)
           .done(response =>{
              menuLunch.set('id',response.id);
              for (item in this.get('lunch')){
                this.get('adminService').addMenuItem(item.name,item.price,item.description,menuLunch)
                .fail(response => {
                  this.get('notifications').error('Error occured while adding restaurant menu item', {
                   autoClear: true,
                   clearDuration: 1500
                 });
                })
              }
           })
           .fail(response => {
             this.get('notifications').error('Error occured while adding restaurant menu', {
              autoClear: true,
              clearDuration: 1500
            });


          });

          var menuDinner=Menu.create({});
          this.get('adminService').addMenu('Dinner',restaurant)
          .done(response =>{
             menuDinner.set('id',response.id);
             for (item in this.get('dinner')){
               this.get('adminService').addMenuItem(item.name,item.price,item.description,menuDinner)
               .fail(response => {
                 this.get('notifications').error('Error occured while adding restaurant menu item', {
                  autoClear: true,
                  clearDuration: 1500
                });
               })
             }
          })
          .fail(response => {
            this.get('notifications').error('Error occured while adding restaurant menu', {
             autoClear: true,
             clearDuration: 1500
           });
         });
        /*
         for (item in this.get('tables')){
           this.get('adminService').addRestaurantTable(item.sittingPlaces,restaurant)
           .fail(response => {
             this.get('notifications').error('Error occured while adding restaurant tables', {
              autoClear: true,
              clearDuration: 1500
            });
           })
         }*/
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
