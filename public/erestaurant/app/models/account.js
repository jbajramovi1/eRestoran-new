import Ember from 'ember';
import BaseModel from '../models/base-model'

var _modelProperties = ['id','email','password','country','city','firstName','lastName'];

export default Ember.Object.extend({
    modelProperties: _modelProperties
});
