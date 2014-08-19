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
        StringBuilder output = new StringBuilder()
        if (firstName){
            output.append(firstName)
        }
        if (lastName){
            if (output.length() != 0){
                output.append(" ")
            }
            output.append(lastName)
        }

        if (email){
            if (output.length() != 0){
                output.append(" - ")
            }
            output.append(email)
        }
        return output
    }
}
