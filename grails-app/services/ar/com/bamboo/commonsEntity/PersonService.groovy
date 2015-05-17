package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService
import ar.com.bamboo.framework.persistence.PaginatedResult
import grails.gorm.DetachedCriteria
import grails.transaction.Transactional

class PersonService extends BaseService{

    @Transactional(readOnly = true)
    public PaginatedResult list(Map params) {
        def where = { enabled == true } as DetachedCriteria<Person>
        return this.listWithLimit(Person.class, where, params)
    }


    @Transactional(readOnly = true)
    public List<Person> listAll() {
        def where = { enabled == true } as DetachedCriteria<Person>
        return this.listAll(Person.class, where)
    }

    @Transactional(readOnly = true)
    public PaginatedResult listWithLimit(params) {
        def where = { enabled == true } as DetachedCriteria<Person>
        return this.listWithLimit(Person.class, where, params)
    }
}
