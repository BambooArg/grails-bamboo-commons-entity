package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService
import grails.gorm.DetachedCriteria

class DepartmentService extends BaseService{

    List<Department> listAllByProvince(Province province) {
        def where = { enabled == true && province == province} as DetachedCriteria<Department>
        return this.listAll(Department.class, where)
    }
}
