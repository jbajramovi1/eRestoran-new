import Ember from 'ember';

export default Ember.Component.extend({
  tables:[{id:'1',people:'2',amount:'0'}],
  tablesId:1,
  actions:{
    addItem:function(){
        this.set('tablesId',this.get('tablesId')+1);
        this.get('tables').pushObject({id:this.get('tablesId'),people:'2',amount:'0'});
    }
  }
});
