import Ember from 'ember';
import Restaurant from '../models/restaurant';

export default Ember.Route.extend({
  restaurantService: Ember.inject.service('restaurant-service'),
  setupController: function(controller,model) {
    this._super(controller, model);
    window.scrollTo(0,0);
    controller.setProperties({
      lat: 34.751603,
      lng: -82.0463009,
      zoom: 14,
      markers: Ember.A([
      {
        id: 'McDonalds',
        lat: 34.751603,
        lng: -82.0463009,
        visible: true,
      }
    ]),
    });
  },

  model(params){
    console.log(this.get('tablesResponse'));
    return Ember.RSVP.hash({
    restaurant:this.get('restaurantService').getById(params.id)
                .fail(response => {
                    this.transitionTo('home');
                     this.get('notifications').error("Restaurant load error occured!", {
                      autoClear: true,
                      clearDuration: 1500
                    });

                }),
    tables:this.get('tablesResponse')
    });
  }
});
