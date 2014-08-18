package ar.com.bamboo.commonsEntity

import ar.com.bamboo.commons.combo.OptionCombo
import grails.converters.JSON

class DepartmentController {

    static allowedMethods = [departmentByProvince: "POST"]

    def departmentService

    def departmentByProvince(Long idProvince) {
        Province province = new Province(id: idProvince)
        List<Department> departmentList =  departmentService.listAllByProvince(province)

        def result = [ success: true, content: new ArrayList<OptionCombo>(departmentList.size()) ]
        for (deparment in departmentList){
            result.content.add([value: deparment.id, label: deparment.name])
        }

        render result as JSON
    }
}
