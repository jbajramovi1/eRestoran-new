#!/bin/bash

# deploy ember app
cd ~/Desktop/api-ember/public/ember-app
ember build -prod

# delete old buid files
rm ../javascripts/vendor.js
rm ../javascripts/ember-app.js

rm ../stylesheets/vendor.css
rm ../stylesheets/ember-app.css

# copy built filed for index scala
cp dist/assets/vendor*.js ../javascripts/vendor.js
cp dist/assets/ember-app*.js ../javascripts/ember-app.js

cp dist/assets/vendor*.css ../stylesheets/vendor.css
cp dist/assets/ember-app*.css ../stylesheets/ember-app.css