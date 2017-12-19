import Ember from 'ember';

export default Ember.Service.extend({
  createReservation(tables,reservationDate,restaurant,account) {
  return $.ajax({
      method:'POST',
      url:'/api/v1/reservation',
      data: JSON.stringify({
          tables,
          reservationDate,
          restaurant,
          account
      }),
      contentType:"application/json"
  })
  }
});
