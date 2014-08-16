package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Person extends BaseEntity{

    String firstName
    String lastName
    String phone
    String phoneMobile
    String email

    static constraints = {
        firstName blank: true, nullable: true
        lastName blank: true, nullable: true
        phone blank: true, nullable: true
        phoneMobile blank: true, nullable: true
        email blank: true, nullable: true, email: true
    }

    @Override
    protected void executeMoreBeforeInsert() {

    }

    public String toString(){
        String output = firstName
        if (output){
            output += " "
        }
        output += lastName

        if (output){
            output += " - "
        }

        output += email
        return output
    }
}
