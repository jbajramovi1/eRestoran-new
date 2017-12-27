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
  filter(pageSize,pageNumber,name){
    return $.ajax({
          method:'GET',
          url:'/api/v1/restaurants/filter?pageSize='+pageSize+'&pageNumber='+pageNumber+'&name='+name,
          contentType:"application/json",
          dataType: 'json'
  });

  }
});
