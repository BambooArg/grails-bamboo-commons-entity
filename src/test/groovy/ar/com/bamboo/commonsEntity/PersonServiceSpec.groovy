package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.persistence.PaginatedResult
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
        //Guardo varias personas para poder hacer el test luego
        for ( i in 1..20 ){
            Person person = new Person(email: "bamboo${i}@gmail.com").save(flush: true, failOnError: true)
            if ( (i % 2) == 0){
                person.enabled = false
                person.save(flush: true, failOnError: true)
            }
        }
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

    void "test list action"(){
        def params = [max: 5]

        when: "Cuando se busca con un maximo de 5"
        PaginatedResult result = service.list(params)

        then: "El resultado de es el maximos para el listResult y el total para countResult"
        result
        result.result.size() == 5
        result.totalRows == 10
    }
}
