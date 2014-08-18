package ar.com.bamboo.commonsEntity

import ar.com.bamboo.commons.combo.OptionCombo
import grails.converters.JSON

class DeparmentController {

    def departmentService

    def departmentByProvince(Long idProvince) {
        Province province = new Province(id: idProvince)
        List<Department> departmentList =  departmentService.listAllByProvince(province)
        List<OptionCombo> comboList = new ArrayList<OptionCombo>(departmentList.size())
        for (deparment in departmentList){
            comboList.add(new OptionCombo(value: deparment.id, label: deparment.name))
        }

        def result = [ success: true, content: comboList ]
        render result as JSON
    }
}
