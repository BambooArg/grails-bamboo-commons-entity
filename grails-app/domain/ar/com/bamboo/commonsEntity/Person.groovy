package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Person extends BaseEntity{

    String nombre
    String apellido
    String telefonoFijo
    String telefonoCelular
    String email

    static constraints = {
        nombre blank: true, nullable: true
        apellido blank: true, nullable: true
        telefonoFijo blank: true, nullable: true
        telefonoCelular blank: true, nullable: true
        email blank: true, nullable: true, email: true
    }

    @Override
    protected void executeMoreBeforeInsert() {

    }
}
