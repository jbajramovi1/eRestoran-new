#!/bin/bash

# deploy ember app
cd ~/eRestoran-new/public/eRestaurant
ember build -prod

# delete old buid files
rm ../javascripts/vendor.js
rm ../javascripts/ember-app.js

rm ../stylesheets/vendor.css
rm ../stylesheets/ember-app.css

# copy built filed for index scala
cp dist/assets/vendor*.js ../javascripts/vendor.js
cp dist/assets/e-restaurant-fe*.js ../javascripts/ember-app.js

cp dist/assets/vendor*.css ../stylesheets/vendor.css
cp dist/assets/e-restaurant-fe*.css ../stylesheets/ember-app.css

cp dist/assets/sections/*.css ../stylesheets/sections
cd dist/assets/images/*.jpg ../../app/assets/images
