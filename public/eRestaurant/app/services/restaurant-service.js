import Ember from 'ember';
import Restaurant from '../models/restaurant';

export default Ember.Service.extend({
  getById(id){
    return $.ajax({
          method:'GET',
          url:'/api/v1/restaurant/'+id,
          contentType:"application/json",
          dataType: 'json'

  });

},
  createRestaurant(name,description,category,location,mark,votes){
    return $.ajax({
          method:'POST',
          url:'/api/v1/restaurant',
          data: JSON.stringify({
              name,
              description,
              category,
              location,
              mark,
              votes
          }),
          contentType:"application/json"
    })
  }
});
