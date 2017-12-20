import Ember from 'ember';

export default Ember.Controller.extend({
  enableDashboard:true,
  enableRestaurants:false,
  enableAddRestaurant:false,
  enableLocations:false,
  enableAddLocation:false,
  enableUsers:false,
  enableAddUser:false,
  enableUpdateUser:false,
  adminService:Ember.inject.service('admin-service'),
  restaurants:[],
  users:[],
  user:null,
  userId:null,
  actions: {
        openDashboard: function() {
            this.set('enableDashboard',true);
            this.set('enableRestaurants',false);
            this.set('enableAddRestaurant',false);
            this.set('enableLocations',false);
            this.set('enableAddLocation',false);
            this.set('enableUsers',false);
            this.set('enableAddUser',false);
            this.set('enableUpdateUser',false);
         },
         openRestaurants: function() {
           this.set('enableDashboard',false);
             this.set('enableRestaurants',true);
             this.set('enableAddRestaurant',false);
             this.set('enableLocations',false);
             this.set('enableAddLocation',false);
             this.set('enableUsers',false);
             this.set('enableAddUser',false);
             this.set('enableUpdateUser',false);
          },
          openAddRestaurant: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',true);
              this.set('enableLocations',false);
              this.set('enableAddLocation',false);
              this.set('enableUsers',false);
              this.set('enableAddUser',false);
              this.set('enableUpdateUser',false);
          },
          openLocations: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',true);
              this.set('enableAddLocation',false);
              this.set('enableUsers',false);
              this.set('enableAddUser',false);
              this.set('enableUpdateUser',false);
          },
          openAddLocation: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',false);
              this.set('enableAddLocation',true);
              this.set('enableUsers',false);
              this.set('enableAddUser',false);
              this.set('enableUpdateUser',false);
          },
          openUsers: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',false);
              this.set('enableAddLocation',false);
              this.set('enableUsers',true);
              this.set('enableAddUser',false);
              this.set('enableUpdateUser',false);
          },
          openAddUser: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',false);
              this.set('enableAddLocation',false);
              this.set('enableUsers',false);
              this.set('enableAddUser',true);
              this.set('enableUpdateUser',false);
          },
          openUpdateUser: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',false);
              this.set('enableAddLocation',false);
              this.set('enableUsers',false);
              this.set('enableAddUser',false);
              this.set('enableUpdateUser',true);
          },
          refreshModel: function() {
            this.set('restaurants',this.get('adminService').getRestaurants());
            this.set('users',this.get('adminService').getUsers());
            if (this.get('userId')!=null) {
              this.get('adminService').getUser(this.get('userId'))
              .done(response => {
                       this.set('user',response);
                       this.send('refresh');
                       this.send('openUpdateUser');
              });
            }
            else{
              this.set('userId',null);
              this.send('refresh');
            }

          }
}
});
