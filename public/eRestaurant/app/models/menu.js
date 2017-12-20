import Ember from 'ember';
import BaseModel from '../models/base-model'

var _modelProperties = ['id','restaurant','name'];

export default Ember.Object.extend({
    modelProperties: _modelProperties
});
