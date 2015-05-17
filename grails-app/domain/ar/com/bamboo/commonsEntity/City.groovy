package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class City extends BaseEntity{

    String name
    Department department

    static constraints = {
        name blank: false
    }

    @Override
    protected void executeMoreBeforeInsert() {

    }
}
