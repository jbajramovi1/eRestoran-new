import Ember from 'ember';

export default Ember.Service.extend({
  createReservation(tables,reservationDate,restaurant) {
  return $.ajax({
      method:'POST',
      url:'/api/v1/reservation',
      data: JSON.stringify({
          tables,
          reservationDate,
          restaurant
      }),
      contentType:"application/json"
  })
  }
});
