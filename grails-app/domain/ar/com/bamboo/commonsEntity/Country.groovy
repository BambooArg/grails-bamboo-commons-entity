package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Country implements BaseEntity{

    String name
    String isoCode

    static constraints = {
        isoCode nullable: true, blank: true
    }
}
