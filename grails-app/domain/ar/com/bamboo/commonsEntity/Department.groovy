package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Department implements BaseEntity{
    private static final Integer ID_CAPITAL_FEDERAL = 1

    String name
    Province province

    static constraints = {
    }

    static mapping = {
        cache usage: 'read-only'
    }

    boolean isCapitalFederal(){
        return this.id == ID_CAPITAL_FEDERAL
    }
}
