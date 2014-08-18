package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.BaseService
import grails.gorm.DetachedCriteria

class DepartmentService extends BaseService{

    List<Department> listAllByProvince(Province provinceArg) {
        def where = { enabled == true && province.id == provinceArg.id} as DetachedCriteria<Department>
        return this.listAll(Department.class, where)
    }
}
