import Ember from 'ember';

export default Ember.Component.extend({
  actions:{
    addFile(){
      $("input[type='file']").click();
    }
  }
});
