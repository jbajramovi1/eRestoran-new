import Ember from 'ember';

export default Ember.Component.extend({
  setupController: function(controller,model) {
    this._super(controller, model);
    controller.setProperties({
      lat: 34.751603,
      lng: -82.0463009,
      zoom: 14,
      markers: Ember.A([
      {
        id: 'McDonalds',
        lat: 34.751603,
        lng: -82.0463009,
        //animation: google.maps.Animation.DROP,
        visible: true,
      }
    ]),
    });
  },
  model(params){

  }
});
