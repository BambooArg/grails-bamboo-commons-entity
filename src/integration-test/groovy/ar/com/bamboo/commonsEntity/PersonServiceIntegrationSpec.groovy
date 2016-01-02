package ar.com.bamboo.commonsEntity

import ar.com.bamboo.framework.persistence.PaginatedResult
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

/**
 *
 */
@Integration
@Rollback
class PersonServiceIntegrationSpec extends Specification{

    def personService

    void setupData() {
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

    def "test save action"() {

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

    }

    void "test list action"(){
        setup:
        setupData()
        def params = [max: 5]

        when: "Cuando se busca con un maximo de 5"
        PaginatedResult result = personService.list(params)

        then: "El resultado de es el maximos para el listResult y el total para countResult"
        result
        result.result.size() == 5
        result.totalRows == 10
    }
}
