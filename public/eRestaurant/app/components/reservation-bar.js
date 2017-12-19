import Ember from 'ember';
import Account from '../models/account';
import Restaurant from '../models/restaurant';
export default Ember.Component.extend({
  reservation: Ember.inject.service('reservation-service'),
  notifications: Ember.inject.service('notification-messages'),
  sessionService: Ember.inject.service('session-service'),
  restaurant:Ember.inject.controller(),
  tableSearchEnable:false,
  tablesEmpty:false,
  people:2,
  time:18,
  date:new Date(),
  setupController: function(controller,model) {
    this._super(controller, model);

  },
  actions:{
    selectPeople(value){
      this.set('people',value);
    },

    selectTime(value){
      this.set('time',value);
    },
    selectDate(input){
      this.set('date',input);
    },
    saveReservation(){

      if (this.get('sessionService').getCurrentUser()==null) {
        this.get('notifications').error('Please login to continue', {
         autoClear: true,
         clearDuration: 1500
       });
     }
     else {
      var account=Account.create({});
      var restaurant=Restaurant.create({});
      account.set('id',this.get('sessionService').getCurrentUserId());
      restaurant.set('id',this.get('model.restaurant.id'));
      var date=this.get('date');
      date.setHours(date.getHours()+this.get('time'));
      this.get('reservation').createReservation(this.get('people'),date,restaurant,account)
      .done(response => {
        if (response.responseType=="SUCCESS"){

           this.get('notifications').success('Successful reservation!', {
            autoClear: true,
            clearDuration: 1500
          });
          this.set('tableSearchEnable',false);
        }
        else {
          this.set('tablesEmpty',false);
          this.set('tablesResponse',response.response);
          if (response.response.length==0){
            this.set('tablesEmpty',true);
          }
          this.set('tableSearchEnable',true);
        }

     })
       .fail(response => {
          this.get('notifications').error('Unable to make the reservation', {
           autoClear: true,
           clearDuration: 1500
         });

       });
     }
   },
   makeNewReservation(places){
     let oldValue=this.get('people');
     this.set('people',places);
     this.send('saveReservation');
     this.set('tableSearchEnable',false);

     this.set('people',oldValue);
   }
  }

});
