package ar.com.bamboo.commonsEntity

import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CityService)
@Mock([Province, Department, City])
@Build([Province, Department, City])
class CityServiceSpec extends Specification {

    Department caba, matanza

    def setup() {
        Province ba = Province.build(name: "Buenos Aires").save(flush: true, failOnError: true)
        caba = Department.build(name: "caba", province: ba).save(flush: true, failOnError: true)
        matanza = Department.build(name: "matanza", province: ba).save(flush: true, failOnError: true)
        (1..20).each {
            City city = null
            if ((it % 2) == 0){
                city = City.build(name: "deparment ${it}", department: caba).save(flush: true, failOnError: true)
            }else{
                city = City.build(name: "deparment ${it}", department: matanza).save(flush: true, failOnError: true)
            }
            if (city.id in [1L, 5L, 6L, 12L, 16L]){
                city.enabled = false
                city.save(flush: true, failOnError: true)
            }
        }
    }

    def cleanup() {
    }

    void "test list all by city"() {

        when: "Busco las ciudades  habilitados de CABA"
        def listOfCities = service.listAllByDepartment(caba)
        then: "Sólo me trae las 7 habilitadas"
        listOfCities
        listOfCities.size() == 7

        when: "Busco las ciudades habilitadas de la matanza"
        listOfCities = service.listAllByDepartment(matanza)
        then: "Sólo me trae las 8 habilitadas"
        listOfCities
        listOfCities.size() == 8
    }
}
