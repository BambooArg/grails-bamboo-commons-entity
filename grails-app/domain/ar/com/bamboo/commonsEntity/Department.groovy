package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.domains.BaseEntity

class Department extends BaseEntity{
    private static final Integer ID_CAPITAL_FEDERAL = 1

    String name
    Province province

    static constraints = {
        name blank: false
    }

    @Override
    protected void executeMoreBeforeInsert() {

    }

    boolean isCapitalFederal(){
        return this.id == ID_CAPITAL_FEDERAL
    }
}
