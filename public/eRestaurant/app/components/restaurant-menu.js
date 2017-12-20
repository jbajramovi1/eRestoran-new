import Ember from 'ember';

export default Ember.Component.extend({

  enableBreakfast:true,
  enableLunch:false,
  enableDinner:false,
  breakfast: null,
  newBreakfast: function () {
    return this.get('breakfast').map(({name, description, price}) => ({
      name,
      description,
      price
    }));
  }.property('breakfast'),
  actions:{
    addItem:function(){
      if (this.get('enableBreakfast')){
        debugger;
        this.get('newBreakfast').push({name:null,description:null,price:null});

      }
      if (this.get('enableLunch')){
        this.get('lunch').pushObject({name:null,description:null,price:null});
      }
      if (this.get('enableDinner')){
        this.get('dinner').pushObject({name:null,description:null,price:null})
      }

  },
  openBreakfast:function(){
    this.set('enableBreakfast',true);
    this.set('enableLunch',false);
    this.set('enableDinner',false);
  },
  openLunch:function(){
    this.set('enableBreakfast',false);
    this.set('enableLunch',true);
    this.set('enableDinner',false);
  },
  openDinner:function(){
    this.set('enableBreakfast',false);
    this.set('enableLunch',false);
    this.set('enableDinner',true);
  },
  deleteItem:function(item){
    if (this.get('enableBreakfast') ){
      this.get('newBreakfast').removeObject(item);
    }
    if (this.get('enableLunch')){
      this.get('lunch').removeObject(item);
    }
    else{
      this.get('dinner').removeObject(item);
    }
  }
  }
});
