import Ember from 'ember';

export default Ember.Object.extend({
    modelProperties: [],

    getModelProperties: function() {
        return this.get('modelProperties');
    }
});
