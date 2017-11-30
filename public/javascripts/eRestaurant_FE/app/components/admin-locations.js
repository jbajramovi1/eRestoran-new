import Ember from 'ember';

export default Ember.Component.extend({
  adminpanel:Ember.inject.controller(),
  actions:{
    openAddLocation:function(){
        this.get('adminpanel').send('openAddLocation');
    }
  }
});
