import Ember from 'ember';

export default Ember.Component.extend({
  breakfastId:1,
  lunchId:1,
  dinnerId:1,
  breakfast:[{id:'1',name:'',description:'',price:''}],
  lunch:[{id:'1',name:'',description:'',price:''}],
  dinner:[{id:'1',name:'',description:'',price:''}],
  enableBreakfast:true,
  enableLunch:false,
  enableDinner:false,

  actions:{
    addItem:function(){
      if (this.get('enableBreakfast')){
        this.set('breakfastId',this.get('breakfastId')+1);
        this.get('breakfast').pushObject({id:this.get('breakfastId'),name:'',description:'',price:''});
    }
    if (this.get('enableLunch')){
      this.set('lunchId',this.get('lunchId')+1);
      this.get('lunch').pushObject({id:this.get('lunchId'),name:'',description:'',price:''});
    }
    if (this.get('enableDinner')){
      this.set('dinnerId',this.get('dinnerId')+1);
      this.get('dinner').pushObject({id:this.get('dinnerId'),name:'',description:'',price:''})
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
      this.get('breakfast').removeObject(item);
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
