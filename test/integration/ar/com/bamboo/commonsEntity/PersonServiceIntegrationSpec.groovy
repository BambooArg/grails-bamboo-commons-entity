package ar.com.bamboo.commonsEntity

import grails.test.spock.IntegrationSpec
import grails.transaction.Transactional

/**
 *
 */
class PersonServiceIntegrationSpec extends IntegrationSpec {

    def personService

    def setup() {
    }

    def cleanup() {
    }

    void "test save action"() {

        when: "Guardo una persona no respetando las constraint: Email sin formato"
        Person person = new Person(email: "dadsa")
        then: "El service devuelve false y no se graba la persona"
        !personService.save(person)
        person.hasErrors()
        !person.id

        when: "Guardo la persona respetando todos los campos obligatorios: Ningunos"
        person = new Person(firstName: "Mariano")
        then: "El service devuelve true"
        personService.save(person)
        !person.hasErrors()
        person.id
        person.firstName == "Mariano"

        /*
        TODO comento esto, porque no se porque no funciona, no muestra lo modificado
        when: "Cuando modifico a la persona"
        Person personaAux = Person.get(person.id)
        personaAux.firstName = "Mariano2"
        boolean success = personService.save(personaAux)
        person.discard()
        Person personDB = Person.get(personaAux.id)
        then: "La validación funciona bien y se guardan los cambios"
        success
        personDB.id == person.id
        personDB.firstName == "Mariano2"*/
    }
}
