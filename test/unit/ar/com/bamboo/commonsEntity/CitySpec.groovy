package ar.com.bamboo.commonsEntity

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(City)
class CitySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test constraint"() {
        given:
        mockForConstraintsTests(City)
        Department d = new Department(name: "CABA")

        when: "Se valida sin los campos obligatorios"
        City city = new City()
        then: "La validacion falla"
        !city.validate()
        city.hasErrors()
        city.errors['department'] == 'nullable'
        city.errors['name'] == 'nullable'

        when: "El campo name está vacío, pero no null"
        city = new City(name: "", department: d)
        then: "La validacion falla por name vacío"
        !city.validate()
        city.hasErrors()
        city.errors['name'] == 'nullable'

        when: "Todos los campos están cargados"
        city = new City(name: "Flores", department: d)
        then: "La validacion pasa con éxito"
        city.validate()
        !city.hasErrors()
    }
}
