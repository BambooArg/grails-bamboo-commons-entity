package ar.com.bamboo.commonsEntity

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DepartmentController)
class DepartmentControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test departmentByProvince"() {
        given:
        def departmentService = mockFor(DepartmentService)
        departmentService.demandExplicit.listAllByProvince(){Province province ->
            return [new Department(id: 1, name: "BA"), new Department(id: 2, name: "CORDOBA")]
        }
        controller.departmentService = departmentService.createMock()

        when: "Cuando se busca los departamentos por provincia"
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'POST'
        controller.departmentByProvince(1)
        then: "El resultado es un JSON con una lista de optionCombo"
        response.json.success == true
        response.json.content
        response.json.content.size() == 2
    }
}
