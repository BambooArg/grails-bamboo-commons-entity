package ar.com.bamboo.commonsEntity.greographic

import ar.com.bamboo.commonsEntity.City
import ar.com.bamboo.commonsEntity.Department
import ar.com.bamboo.commonsEntity.Province
import grails.buildtestdata.mixin.Build
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

/**
 *
 */
@Integration
@Rollback
@Build(Province)
class GeographicHelperIntegrationSpec extends Specification{

    def geographicHelper

    def setup() {
    }

    def cleanup() {
    }

    void "test injecciones de servicios"() {
        given:
        EntityGeographic entityGeographic = new EntityGeographic()
        Province province = Province.buildWithoutSave(name: "Buenos Aires")

        province.save(flush: true, failOnError: true)

        when: "No se tiene cargada ninguna ubicacion"
        def (List<Province> provinces, List<Department> departments, List<City> cities) = geographicHelper
                .getDependentGeographicList(entityGeographic)

        then: "Sólo la provincia es cargada"
        provinces
        departments != null
        !departments
        cities != null
        !cities
    }

    private class EntityGeographic{
        Province province
        Department department
        City city
    }
}
