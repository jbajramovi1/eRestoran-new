import Ember from 'ember';
import Account from '../models/account';
import Comment from '../models/comment';
import Restaurant from '../models/restaurant';
export default Ember.Component.extend({
  commentService: Ember.inject.service('comment-service'),
  sessionService: Ember.inject.service('session-service'),
  notifications: Ember.inject.service('notification-messages'),
  stars:5,
  enableButton:false,
  init:function(){
    if (this.get('sessionService').getCurrentUser()){
      this.set('enableButton',true);
    }
    return this._super();
  },
  actions: {
        toggleModal: function() {
          if (this.get('sessionService').getCurrentUser()!=null) {
            this.toggleProperty('enabled');
         }
          else {
          this.get('notifications').error('Please login to continue', {
           autoClear: true,
           clearDuration: 1500
         });
       }
        },
        saveRating:function(){
          var account=Account.create({});
          var comment=Comment.create({});
          var restaurant=Restaurant.create({});
          account.set('id',this.get('sessionService').getCurrentUserId());
          comment.setProperties({'content':this.get('review'),'mark':this.get('stars'),'account':account});
          restaurant.set('id',this.get('model.id'))
          this.get('commentService').leaveComment(this.get('review'),this.get('stars'),new Date(),restaurant)
          .done(response => {
               this.get('notifications').success('Your rating is saved!', {
                autoClear: true,
                clearDuration: 1500
              });
               this.transitionToRoute('home');
         })
           .fail(response => {
              this.get('notifications').error('Rating error', {
               autoClear: true,
               clearDuration: 1500
             });
           });
          this.toggleProperty('enabled');

        },
        rate:function(params){
          this.set('stars',params);
        }
    }

});
