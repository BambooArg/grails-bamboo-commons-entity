grails-bamboo-commons-entity
============================

El plugin estña destinado al manejo de todas las clases comunes entity que pueden servir para los proyectos de bamboo


#Instalación

Agregar el plugin al proyecto
```groovy
compile ":grails-bamboo-commons-entity:0.1.0"
```

##Dependencias

El proyecto depende de los siguientes plugins. Actualmente al agregar grails-bamboo-security al proyecto, **no** agrega al proyecto las dependencias.

- grails-bamboo-architecture:0.1.0

#Build

Para compilar el proyecto e intalarlo localmente se debe ejecutar

 ```grails
grails maven-install
```

Para publicarlo se deje ejecutar

```grails
grails publish-plugin --protocol=webdav
```

El repositorio default para la publicación es https://repository-orkoapp.forge.cloudbees.com/snapshot/

###**Atención**
Tener en cuenta que se tiene que tener configurado en .grails/setting.groovy
```groovy
grails.project.repos.cloudbees.url = "dav:https://repository-orkoapp.forge.cloudbees.com/snapshot/"
grails.project.repos.cloudbees.username = yourUsername
grails.project.repos.cloudbees.password = yourPass
```


#Test

El proyecto usa travis-ci como entorno de integración continua. https://travis-ci.org/orkonano/grails-bamboo-commons-entity.
Se ejecutan tantos los test unitarios como integrales, corriendo la base de datos de test en memoria.





