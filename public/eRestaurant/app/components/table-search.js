import Ember from 'ember';

export default Ember.Component.extend({
  reservationBar:Ember.inject.controller('reservation-bar'),
  actions:{
    makeNewReservation(places){
      this.sendAction('makeNewReservation',places);
    }
  }
});
