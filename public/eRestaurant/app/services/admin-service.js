import Ember from 'ember';

export default Ember.Service.extend({
  addRestaurant(name, description,category) {
  return $.ajax({
      method:'POST',
      url:'/api/v1/admin/addRestaurant',
      data: JSON.stringify({
          name,
          description,
          category
      }),
      contentType:"application/json"
  })
},
  getRestaurants(){
    return $.ajax({
      method:'GET',
      url:'/api/v1/restaurants',
      contentType:"application/json"
    })
  },
  getUsers(){
    return $.ajax({
      method:'GET',
      url:'/api/v1/accounts',
      contentType:"application/json"
    })
  },
  deleteUser(id){
    return $.ajax({
      method:'DELETE',
      url:'/api/v1/admin/deleteAccount/'+id,
      contentType:"application/json"
    })
  },
  deleteRestaurant(id){
    return $.ajax({
      method:'DELETE',
      url:'/api/v1/admin/deleteRestaurant/'+id,
      contentType:"application/json"
    })
  },
  addUser(firstName,lastName,email,password,phone,country,city) {
  return $.ajax({
      method:'POST',
      url:'/api/v1/admin/addAccount',
      data: JSON.stringify({
          firstName,
          lastName,
          email,
          password,
          phone,
          country,
          city
      }),
      contentType:"application/json"
  })
},
});
