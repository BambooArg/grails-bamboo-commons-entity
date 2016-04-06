package ar.com.bamboo.commonsEntity

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ProvinceController)
class ProvinceControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test provinceByCountry"() {
        given:
        def provinceService = Mock(ProvinceService)
        provinceService.listAllByCountry(_) >> { Country country ->
            return [new Province(id: 1, name: "Flores"), new Province(id: 2, name: "Matanza")]
        }
        controller.provinceService = provinceService

        when: "Busco todas las provincias de un país"
        request.method = 'POST'
        controller.provinceByCountry(1)

        then: "El resultado es un JSON con una lista de optionCombo"
        response.json.success == true
        response.json.content
        response.json.content.size() == 2
    }
}
