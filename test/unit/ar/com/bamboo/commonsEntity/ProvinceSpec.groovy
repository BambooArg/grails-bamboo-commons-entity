package ar.com.bamboo.commonsEntity

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Province)
class ProvinceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test constraint"() {
        given:
        mockForConstraintsTests(Province.class)

        when: "No se ingresan los parámetros obligatorios para el save"
        Province province = new Province()
        then: "La validación falla por nombre obligatorio"
        !province.validate()
        province.hasErrors()
        province.errors['name'] == 'nullable'

        when: "El parametro name está vacío, pero no null"
        province = new Province(name: "")
        then: "La validación falla por nombre obligatorio"
        !province.validate()
        province.hasErrors()
        province.errors['name'] == 'nullable'

        when: "Se ingresan los parámetros obligatorios para el save"
        province = new Province(name: "Buenos Aires")
        then: "La validación pasa con éxito"
        province.validate()
        !province.hasErrors()
        province.save(flush: true, failOnError: true)
    }
}
