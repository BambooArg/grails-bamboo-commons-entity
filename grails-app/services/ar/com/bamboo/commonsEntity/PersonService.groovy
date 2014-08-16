package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService
import grails.gorm.DetachedCriteria
import grails.transaction.Transactional

class PersonService extends BaseService{

    @Transactional(readOnly = true)
    List<Object> list(Map params) {
        def where = { enabled == true } as DetachedCriteria<Person>
        return this.listWithLimit(Person.class, where, params)
    }

}
