package ar.com.bamboo.commonsEntity

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DepartmentService)
@Mock([Province, Department])
class DepartmentServiceSpec extends Specification {

    Province ba, cordoba

    def setup() {
        ba = new Province(name: "Buenos Aires").save(flush: true, failOnError: true)
        cordoba = new Province(name: "Cordoba").save(flush: true, failOnError: true)
        (1..20).each {
            Department d = null
            if ((it % 2) == 0){
                d = new Department(name: "deparment ${it}", province: ba).save(flush: true, failOnError: true)
            }else{
                d = new Department(name: "deparment ${it}", province: cordoba).save(flush: true, failOnError: true)
            }
            if (d.id in [1L, 5L, 6L, 12L, 16L]){
                d.enabled = false
                d.save(flush: true, failOnError: true)
            }
        }
    }

    def cleanup() {
    }

    void "test listAllByProvince method"() {

        when: "Busco los departamentos  habilitados de BA"
        def departmentList = service.listAllByProvince(ba)
        then: "Sólo me trae las 7 habilitadas"
        departmentList
        departmentList.size() == 7

        when: "Busco los departamentos  habilitados de Cordoba"
        departmentList = service.listAllByProvince(cordoba)
        then: "Sólo me trae las 8 habilitadas"
        departmentList
        departmentList.size() == 8
    }
}
