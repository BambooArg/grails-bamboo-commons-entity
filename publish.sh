#!/bin/bash

echo "instalando bamboo commons-entity"
grails clean && grails refresh-dependencies && grails maven-install

#grails publish-plugin 

