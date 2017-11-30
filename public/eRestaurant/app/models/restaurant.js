import Ember from 'ember';
import BaseModel from '../models/base-model'

var _modelProperties = ['id', 'name', 'description', 'mark', 'votes','priceRange',
    'imageFileName','coverFileName','category'];

export default Ember.Object.extend({
    modelProperties: _modelProperties
});
