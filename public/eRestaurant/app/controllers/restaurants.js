import Ember from 'ember';

export default Ember.Controller.extend({
  restaurantService:Ember.inject.service('restaurant-service'),
  notifications: Ember.inject.service('notification-messages'),
  restaurants:[],
  pageNumber:1,
  pageSize:6,
  totalPages:null,
  name:"",
  pageObserver:Ember.observer('pageNumber',function(){
    this.get('restaurantService').filter(this.get('pageSize'),(this.get('pageNumber')-1),this.get('name'))
    .done(response=>{
      this.set('restaurants',response.data);
      this.set('totalPages',response.count);

    })
    .fail(response=>{
      this.get('notifications').error("Error while loadin restaurants!", {
       autoClear: true,
       clearDuration: 1500
        });
    });

  }),
  actions:{
    search(){
      this.get('restaurantService').filter(this.get('pageSize'),(this.get('pageNumber')-1),this.get('name'))
      .done(response=>{
        this.set('restaurants',response.data);
        this.set('totalPages',response.count);
        console.log('search');
        console.log(this.get('totalPages'));
        
      })
      .fail(response=>{
        this.get('notifications').error("Error while loadin restaurants!", {
         autoClear: true,
         clearDuration: 1500
          });
      });
    }
  }
});
