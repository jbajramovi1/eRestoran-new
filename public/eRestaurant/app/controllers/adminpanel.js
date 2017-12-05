import Ember from 'ember';

export default Ember.Controller.extend({
  enableDashboard:true,
  enableRestaurants:false,
  enableAddRestaurant:false,
  enableLocations:false,
  enableAddLocation:false,
  enableUsers:false,
  enableAddUser:false,
  adminService:Ember.inject.service('admin-service'),
  restaurants:[],
  user:[],
  actions: {
        openDashboard: function() {
            this.set('enableDashboard',true);
            this.set('enableRestaurants',false);
            this.set('enableAddRestaurant',false);
            this.set('enableLocations',false);
            this.set('enableAddLocation',false);
            this.set('enableUsers',false);
            this.set('enableAddUser',false);
         },
         openRestaurants: function() {
           this.set('enableDashboard',false);
             this.set('enableRestaurants',true);
             this.set('enableAddRestaurant',false);
             this.set('enableLocations',false);
             this.set('enableAddLocation',false);
             this.set('enableUsers',false);
             this.set('enableAddUser',false);
          },
          openAddRestaurant: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',true);
              this.set('enableLocations',false);
              this.set('enableAddLocation',false);
              this.set('enableUsers',false);
              this.set('enableAddUser',false);
          },
          openLocations: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',true);
              this.set('enableAddLocation',false);
              this.set('enableUsers',false);
              this.set('enableAddUser',false);
          },
          openAddLocation: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',false);
              this.set('enableAddLocation',true);
              this.set('enableUsers',false);
              this.set('enableAddUser',false);
          },
          openUsers: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',false);
              this.set('enableAddLocation',false);
              this.set('enableUsers',true);
              this.set('enableAddUser',false);
          },
          openAddUser: function() {
            this.set('enableDashboard',false);
              this.set('enableRestaurants',false);
              this.set('enableAddRestaurant',false);
              this.set('enableLocations',false);
              this.set('enableAddLocation',false);
              this.set('enableUsers',false);
              this.set('enableAddUser',true);
          },
          refreshModel: function() {
            this.set('restaurants',this.get('adminService').getRestaurants());
            this.set('users',this.get('adminService').getUsers());
            this.send('refresh');
          }
}
});
