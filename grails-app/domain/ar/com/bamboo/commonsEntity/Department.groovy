package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Department extends BaseEntity{

    String name
    Province province

    static constraints = {
        name blank: false
    }

    @Override
    protected void executeMoreBeforeInsert() {

    }
}
