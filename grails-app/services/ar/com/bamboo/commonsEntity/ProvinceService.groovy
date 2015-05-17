package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService
import grails.gorm.DetachedCriteria

class ProvinceService extends BaseService{

    List<Province> listAll() {
        def where = { enabled  == true } as DetachedCriteria<Province>
        return this.listAll(Province.class, where)
    }
}
