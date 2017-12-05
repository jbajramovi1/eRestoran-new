import Ember from 'ember';

export default Ember.Component.extend({
  adminpanel:Ember.inject.controller(),
  adminService:Ember.inject.service('admin-service'),
  notifications: Ember.inject.service('notification-messages'),
  deleteEnabled:false,
  deleteId:null,
  deleteName:null,
  actions:{
    openAddRestaurant:function(){
        this.get('adminpanel').send('openAddRestaurant');
    },
    toggleModal:function(id,name){
      this.toggleProperty('deleteEnabled');
      this.set('deleteId',id);
      this.set('deleteName',name);
    },
    deleteRestaurant:function(){
      this.get('adminService').deleteRestaurant(this.get('deleteId'))
        .done(response => {
                 this.get('notifications').success("Restaurant deleted!", {
                  autoClear: true,
                  clearDuration: 1500
                   });
                   this.get('adminpanel').send('refreshModel');
                   this.get('adminpanel').send('openRestaurants');
             }
            )
          .fail(response => {
             this.get('notifications').error('Error occured while deleting restaurant', {
              autoClear: true,
              clearDuration: 1500
            });
          });

        this.toggleProperty('deleteEnabled');
    }
  }
});
