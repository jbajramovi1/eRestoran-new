import Ember from 'ember';

export default Ember.Component.extend({
  adminpanel:Ember.inject.controller(),
  actions:{
    openAddRestaurant:function(){
        this.get('adminpanel').send('openAddRestaurant');
    },
    openAddLocation:function(){
        this.get('adminpanel').send('openAddLocation');
    },
    openAddUser:function(){
        this.get('adminpanel').send('openAddUser');
    }
  }
});
