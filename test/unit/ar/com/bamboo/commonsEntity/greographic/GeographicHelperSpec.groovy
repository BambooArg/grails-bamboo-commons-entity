package ar.com.bamboo.commonsEntity.greographic

import ar.com.bamboo.commonsEntity.City
import ar.com.bamboo.commonsEntity.CityService
import ar.com.bamboo.commonsEntity.Department
import ar.com.bamboo.commonsEntity.DepartmentService
import ar.com.bamboo.commonsEntity.Province
import ar.com.bamboo.commonsEntity.ProvinceService
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class GeographicHelperSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test getDependentGeographicList con objeto que no tiene nada cargado"() {
        given:
        EntityGeographic entityGeographic = new EntityGeographic()
        GeographicHelper geographicHelper = new GeographicHelper()

        def provinceService = mockFor(ProvinceService)
        provinceService.demandExplicit.listAll(){ ->
            return [new Province(), new Province()]
        }
        geographicHelper.provinceService = provinceService.createMock()

        when: "Cuando no tiene cargado ningún dato"
        def (List<Province> provinces, List<Department> departments, List<City> cities) = geographicHelper
                .getDependentGeographicList(entityGeographic)
        then: "Sólo la lista de las provincias tiene datos"
        provinces
        departments != null
        !departments
        cities != null
        !cities
    }

    void "test getDependentGeographicList con objeto que tiene cargada la provincia"() {
        given:
        EntityGeographic entityGeographic = new EntityGeographic(province: new Province(id: 1))
        GeographicHelper geographicHelper = new GeographicHelper()

        def provinceService = mockFor(ProvinceService)
        provinceService.demandExplicit.listAll(){ ->
            return [new Province(), new Province()]
        }
        geographicHelper.provinceService = provinceService.createMock()

        def departmentService = mockFor(DepartmentService)
        departmentService.demandExplicit.listAllByProvince(){Province province ->
            return [new Department(), new Department()]
        }
        geographicHelper.departmentService = departmentService.createMock()

        when: "Cuando tiene cargado sólo la provincia"
        def (List<Province> provinces, List<Department> departments, List<City> cities) = geographicHelper
                .getDependentGeographicList(entityGeographic)
        then: "La lista de provincias y departamentos tienen datos"
        provinces
        departments
        cities != null
        !cities
    }

    void "test getDependentGeographicList con objeto que tiene cargada la provincia y el departamento"() {
        given:
        EntityGeographic entityGeographic = new EntityGeographic(province: new Province(id: 1),
                department: new Department(id: 1))
        GeographicHelper geographicHelper = new GeographicHelper()

        def provinceService = mockFor(ProvinceService)
        provinceService.demandExplicit.listAll(){ ->
            return [new Province(), new Province()]
        }
        geographicHelper.provinceService = provinceService.createMock()

        def departmentService = mockFor(DepartmentService)
        departmentService.demandExplicit.listAllByProvince(){Province province ->
            return [new Department(), new Department()]
        }
        geographicHelper.departmentService = departmentService.createMock()

        def cityService = mockFor(CityService)
        cityService.demandExplicit.listAllByDepartment(){Department province ->
            return [new City(), new City()]
        }
        geographicHelper.cityService = cityService.createMock()

        when: "Cuando tiene cargado la provincia y el departamento"
        def (List<Province> provinces, List<Department> departments, List<City> cities) = geographicHelper
                .getDependentGeographicList(entityGeographic)
        then: "La lista de provincias, departamentos y la ciudad tienen datos"
        provinces
        departments
        cities
    }

    private class EntityGeographic{
        Province province
        Department department
        City city
    }
}
