package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Province implements BaseEntity{

    String name
    Country country

    static constraints = {
    }

}
