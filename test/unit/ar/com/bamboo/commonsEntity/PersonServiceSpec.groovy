package ar.com.bamboo.commonsEntity

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PersonService)
@Mock(Person)
class PersonServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test save action"() {

        when: "Guardo una persona no respetando las constraint: Email sin formato"
        Person person = new Person(email: "dadsa")
        then: "El service devuelve false y no se graba la persona"
        !service.save(person)
        person.hasErrors()
        !person.id

        when: "Guardo la persona respetando todos los campos obligatorios: Ningunos"
        person = new Person(firstName: "Mariano")
        then: "El service devuelve true"
        service.save(person)
        !person.hasErrors()
        person.id
        person.firstName == "Mariano"

        when: "Cuando modifico a la persona"
        person.firstName = "Mariano"
        boolean success = service.save(person)
        Person personDB = Person.get(person.id)
        then: "La validación funciona bien y se guardan los cambios"
        success
        personDB.id == person.id
        personDB.firstName == "Mariano"
    }
}
