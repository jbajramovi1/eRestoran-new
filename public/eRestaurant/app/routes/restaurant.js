import Ember from 'ember';
import Restaurant from '../models/restaurant';

export default Ember.Route.extend({
  restaurant: Ember.inject.service('restaurant-service'),
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
    return this.get('restaurant').getById(params.id)
    .fail(response => {
        this.transitionTo('home');
         this.get('notifications').error("Restaurant load error occured!", {
          autoClear: true,
          clearDuration: 1500
        });

    });

  }

});
