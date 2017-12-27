import Ember from 'ember';

export default Ember.Route.extend({
  restaurantService:Ember.inject.service('restaurant-service'),
  setupController: function(controller, model) {
    this._super(controller, model);
    this.get('restaurantService').filter(6,0,"")
    .done(response=>{
      this.set('restaurants',response.data);
      this.set('totalPages',response.count);
      console.log(response);
      console.log(this.get('restaurants'));

    })
    .fail(response=>{
      this.get('notifications').error("Error while loading restaurants!", {
       autoClear: true,
       clearDuration: 1500
        });
    });
  },
  beforeModel(transition){


  },
  model(){
    return this.get('restaurants');
  }
});
