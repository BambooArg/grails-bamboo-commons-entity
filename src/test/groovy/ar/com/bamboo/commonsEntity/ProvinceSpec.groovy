package ar.com.bamboo.commonsEntity

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Province)
@Build(Province)
class ProvinceSpec extends Specification {


    def setup() {
    }

    def cleanup() {
    }

    void "test constraint"() {

        when: "No se ingresan los parámetros obligatorios para el save"
        Province province = new Province()
        then: "La validación falla por nombre obligatorio"
        !province.validate()
        province.hasErrors()
        province.errors.getFieldError("name").code == 'nullable'
        province.errors.getFieldError("country").code == 'nullable'

        when: "El parametro name está vacío, pero no null"
        province = new Province(name: "")
        then: "La validación falla por nombre obligatorio"
        !province.validate()
        province.hasErrors()
        province.errors.getFieldError("name").code == 'nullable'
        province.errors.getFieldError("country").code == 'nullable'

        when: "Se ingresan los parámetros obligatorios para el save"
        province = Province.build()
        then: "La validación pasa con éxito"
        province.validate()
        !province.hasErrors()
        province.save(flush: true, failOnError: true)
    }
}
