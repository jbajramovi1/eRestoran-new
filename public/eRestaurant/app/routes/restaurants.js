import Ember from 'ember';

export default Ember.Route.extend({
  restaurantService:Ember.inject.service('restaurant-service'),
  notifications: Ember.inject.service('notification-messages'),
  setupController: function(controller, model) {
    this._super(controller, model);
  },
  beforeModel(transition){
    this.get('restaurantService').filter(this.get('pageSize'),this.get('pageNumber'),this.get('name'))
    .done(response=>{
      this.set('pageNumber',response.pageNumber);
      this.set('restaurants',response.data);
      this.set('totalPages',response.count);
    })
    .fail(response=>{
      this.get('notifications').error("Error while loadin restaurants!", {
       autoClear: true,
       clearDuration: 1500
        });
    });
  },
  model(){
    return Ember.RSVP.hash({
      restaurants:this.get('restaurants')
    });
  }
});
