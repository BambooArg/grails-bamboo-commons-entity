package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Province implements BaseEntity{

    String name

    static constraints = {
        name blank: false
    }

}
