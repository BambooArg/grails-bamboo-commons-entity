package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService

class CountryService extends BaseService{

    List<Country> listAll() {
        return Country.withCriteria {
            eq 'enabled', true
            order 'name'
        }
    }
}
