import Ember from 'ember';

export default Ember.Route.extend({
  sessionService:Ember.inject.service('session-service'),
  notifications: Ember.inject.service('notification-messages'),
  accountService:Ember.inject.service('account-service'),
  adminService:Ember.inject.service('admin-service'),
  setupController: function(controller, model) {
    this._super(controller, model);
  },
  beforeModel(transition) {
    /*
      this.get('accountService').getSession()
      .done(response => {
             if (!response || response.role!=="ADMIN"){
               this.get('notifications').error("You can't access admin panel without permissions!", {
                autoClear: true,
                clearDuration: 1500
                 });
               this.transitionTo('home');
           }
         })
         .fail(response => {
                if (!response || response.role!=="ADMIN"){
                  this.get('notifications').error("You can't access admin panel without permissions!", {
                   autoClear: true,
                   clearDuration: 1500
                    });
                  this.transitionTo('home');
              }
            });
      this.set('restaurants',this.get('adminService').getRestaurants());
      this.set('users',this.get('adminService').getUsers());*/
  },
  model(){
    /*
    return Ember.RSVP.hash({
      restaurants: this.get('restaurants'),
      users: this.get('users')
    });*/
  },
  actions:{
    refresh(){
      this.refresh();
    }
  }
});
