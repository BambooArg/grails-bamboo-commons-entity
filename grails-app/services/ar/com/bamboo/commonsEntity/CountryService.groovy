package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService
import grails.gorm.DetachedCriteria

class CountryService extends BaseService{

    List<Country> listAll() {
        def where = { enabled  == true } as DetachedCriteria<Country>
        return this.listAll(Country.class, where)
    }
}
