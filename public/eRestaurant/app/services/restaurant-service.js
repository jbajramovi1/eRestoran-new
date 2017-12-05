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

    }
});
