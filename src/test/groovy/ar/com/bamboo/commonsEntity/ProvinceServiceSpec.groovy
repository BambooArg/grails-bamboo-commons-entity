package ar.com.bamboo.commonsEntity

import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ProvinceService)
@Mock(Province)
@Build(Province)
class ProvinceServiceSpec extends Specification {


    def setup() {
        (1..20).each {
            Province p = Province.build(name: "Buenos Aires ${it}").save(flush: true, failOnError: true)
            if ((it % 2) == 0){
                p.enabled = false
                p.save(flush: true, failOnError: true)
            }
        }
    }

    def cleanup() {
    }

    void "test listAll method"() {

        when: "Busco las provincias habilitadas"
        def provinceList = service.listAll()
        then: "Sólo me trae las 10 habilitadas"
        provinceList
        provinceList.size() == 10
    }
}
