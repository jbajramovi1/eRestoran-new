import Ember from 'ember';

export default Ember.Controller.extend({
  restaurants:[],
  pageNumber:1,
  pageSize:6,
  totalPages:null,
  name:"",
  actions:{
    search(){
      this.get('restaurantService').filter(this.get('pageSize'),this.get('pageNumber'),this.get('name'))
      .done(response=>{
        this.set('pageNumber',response.pageNumber);
        this.set('restaurants',response.data);
        this.set('totalPages',response.count);
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
