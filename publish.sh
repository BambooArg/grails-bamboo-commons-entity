#!/bin/bash

echo "instalando bamboo commons-entity"
grails clean && grails refresh-dependencies && grails publish-plugin

#grails publish-plugin 

