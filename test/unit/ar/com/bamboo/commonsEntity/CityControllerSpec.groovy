package ar.com.bamboo.commonsEntity

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CityController)
class CityControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test cityByDepartment"() {
        given:
        def cityService = mockFor(CityService)
        cityService.demandExplicit.listAllByDepartment(){Department department ->
            return [new City(id: 1, name: "Flores"), new City(id: 2, name: "Matanza")]
        }
        controller.cityService = cityService.createMock()

        when: "Cuando se busca los departamentos por provincia"
        controller.cityByDepartment(1)
        then: "El resultado es un JSON con una lista de optionCombo"
        response.json.success == true
        response.json.content
        response.json.content.size() == 2
    }
}
