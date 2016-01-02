package ar.com.bamboo.commonsEntity

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Department)
class DepartmentSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test constraint"() {
        given:
        Province p = new Province(name: "Buenos Aires")

        when: "Se valida sin los campos obligatorios"
        Department department = new Department()
        then: "La validacion falla"
        !department.validate()
        department.hasErrors()
        department.errors.getFieldError("province").code == 'nullable'
        department.errors.getFieldError("name").code == 'nullable'

        when: "El campo name está vacío, pero no null"
        department = new Department(name: "", province: p)
        then: "La validacion falla por name vacío"
        !department.validate()
        department.hasErrors()
        department.errors.getFieldError("name").code == 'nullable'

        when: "Todos los campos están cargados"
        department = new Department(name: "CABA", province: p)
        then: "La validacion pasa con éxito"
        department.validate()
        !department.hasErrors()
    }
}
