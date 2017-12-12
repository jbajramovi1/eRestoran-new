import Ember from 'ember';

export default Ember.Controller.extend({
  tablesResponse:[],
  actions:{
    refreshModel:function(response){
      this.set('tablesResponse',response);
      this.send('refresh');

    }
}
});
