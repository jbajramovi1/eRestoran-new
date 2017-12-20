import Ember from 'ember';

export default Ember.Component.extend({
  adminpanel:Ember.inject.controller(),
  adminService:Ember.inject.service('admin-service'),
  notifications: Ember.inject.service('notification-messages'),
  deleteEnabled:false,
  deleteId:null,
  deleteName:null,

  actions:{
    openAddUser:function(){
        this.get('adminpanel').send('openAddUser');
    },
    toggleModal:function(id,name){
      this.toggleProperty('deleteEnabled');
      this.set('deleteId',id);
      this.set('deleteName',name);
    },
    editUser:function(id){
      this.get('adminpanel').set('userId',id);
      this.get('adminpanel').send('refreshModel');

    },
    deleteUser:function(){
      this.get('adminService').deleteUser(this.get('deleteId'))
        .done(response => {
                 this.get('notifications').success("User deleted!", {
                  autoClear: true,
                  clearDuration: 1500
                   });
                   this.get('adminpanel').send('refreshModel');
                   this.get('adminpanel').send('openUsers');
             }
            )
          .fail(response => {
             this.get('notifications').error('Error occured while deleting user', {
              autoClear: true,
              clearDuration: 1500
            });
          });

        this.toggleProperty('deleteEnabled');
    }
  }
});
