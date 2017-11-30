import Ember from 'ember';
import BaseModel from '../models/base-model'

var _modelProperties = ['id','content','mark','account','restaurant','insertDate'];

export default Ember.Object.extend({
    modelProperties: _modelProperties
});
