package ar.com.bamboo.commonsEntity

import grails.buildtestdata.mixin.Build
import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
@Build([Country, Province])
class ProvinceServiceIntegrationSpec extends Specification {

    def provinceService
    Country countryA, countryB

    def setup() {
        countryA = Country.build().save(flush: true, failOnError: true)
        countryB = Country.build().save(flush: true, failOnError: true)
        (1..20).each {
            Province p = Province.build(name: "Buenos Aires ${it}", country: countryA).save(flush: true, failOnError: true)
            if ((it % 2) == 0){
                p.enabled = false
                p.save(flush: true, failOnError: true)
            }
            if (it in [9, 11, 15]){
                p.country = countryB
                p.save(flush: true, failOnError: true)
            }
        }
    }

    def cleanup() {
    }

    void "test listAll method"() {

        when: "Busco las provincias habilitadas"
        def provinceList = provinceService.listAll()

        then: "Sólo me trae las 10 habilitadas"
        provinceList
        provinceList.size() == 10
    }

    void "test listAllByCountry method"() {

        when: "Busco las provincias habilitadas por country A"
        def provinceList = provinceService.listAllByCountry(countryA)

        then: "Me trae 7 provincias"
        provinceList
        provinceList.size() == 7

        when: "Busco las provincias habilitadas por country B"
        provinceList = provinceService.listAllByCountry(countryB)

        then: "Me trae 3 provincias"
        provinceList
        provinceList.size() == 3
    }
}
