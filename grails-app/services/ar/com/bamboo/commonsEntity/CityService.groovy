package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService
import grails.gorm.DetachedCriteria

class CityService extends BaseService{

    List<City> listAllByDepartment(Department departmentArg) {
        def where = { enabled == true && department.id == departmentArg.id} as DetachedCriteria<City>
        return this.listAll(City.class, where)
    }
}
