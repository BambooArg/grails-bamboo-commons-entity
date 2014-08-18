package ar.com.bamboo.commonsEntity

import ar.com.bamboo.commons.combo.OptionCombo
import grails.converters.JSON

class CityController {

    def cityService

    def cityByDepartment(Long idDepartment) {
        Department department = new Department(id: idDepartment)
        List<City> cities = cityService.listAllByDepartment(department)
        List<OptionCombo> comboList = new ArrayList<OptionCombo>(cities.size())
        for (city in cities) {
            comboList.add(new OptionCombo(value: city.id, label: city.name))
        }

        def result = [success: true, content: comboList]
        render result as JSON
    }
}