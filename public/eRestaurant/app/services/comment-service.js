import Ember from 'ember';

export default Ember.Service.extend({
  leaveComment(content,mark,insertTime,restaurant) {
  return $.ajax({
      method:'POST',
      url:'/api/v1/comment',
      data: JSON.stringify({
          content,
          mark,
          insertTime,
          restaurant
      }),
      contentType:"application/json"
  })
}
});
