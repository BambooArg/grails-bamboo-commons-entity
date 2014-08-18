package ar.com.bamboo.commonsEntity

import ar.com.bamboo.commons.combo.OptionCombo
import grails.converters.JSON

class CityController {

    static allowedMethods = [cityByDepartment: "POST"]

    def cityService

    def cityByDepartment(Long idDepartment) {
        Department department = new Department(id: idDepartment)
        List<City> cities = cityService.listAllByDepartment(department)
        def result = [success: true, content: new ArrayList<OptionCombo>(cities.size())]

        for (city in cities) {
            result.content.add([value: city.id, label: city.name])
        }

        render result as JSON
    }
}