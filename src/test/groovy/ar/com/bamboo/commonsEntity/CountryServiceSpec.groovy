package ar.com.bamboo.commonsEntity

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CountryService)
@Build(Country)
class CountryServiceSpec extends Specification {


    def setup() {
        (1..20).each {
            Country country = Country.build().save(flush: true, failOnError: true)
            if ((it % 2) == 0){
                country.enabled = false
                country.save(flush: true, failOnError: true)
            }
        }
    }

    def cleanup() {
    }

    void "test listAll method"() {
        when: "Busco los paises habilitados"
        def listOfCountries = service.listAll()

        then: "Sólo me trae los 10 habilitados"
        listOfCountries
        listOfCountries.size() == 10
    }
}
