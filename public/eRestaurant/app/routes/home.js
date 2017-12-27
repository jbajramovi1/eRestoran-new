import Ember from 'ember';

export default Ember.Route.extend({
  restaurantService:Ember.inject.service('restaurant-service'),
  model(){
    return this.get('restaurantService').filter(6,0,"");
  }
});
