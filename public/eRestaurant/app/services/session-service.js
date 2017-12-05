import Ember from 'ember';

export default Ember.Service.extend({
  currentUser:null,
  currentUserId:null,
  currentUserRole:null,
  application:Ember.inject.controller('application'),
  authenticate(username,password,id,role){
    this.set('currentUser',username);
    this.set('currentUserId',id);
    this.set('currentUserRole',role);
  },
  isAuthenticated(){
    if (!this.get('currentUser')) return false;
    return true;
  },
  isAdmin(){
    return (this.get('currentUserRole')=="ADMIN");
  },
  getCurrentUser(){
    return this.get('currentUser');
  },
  getCurrentUserId(){
    return this.get('currentUserId');
  },
  unauthenticate(){
    this.set('currentUser',null);
    this.set('currentUserId',null);
    this.set('currentUserRole',null);
  }

});
