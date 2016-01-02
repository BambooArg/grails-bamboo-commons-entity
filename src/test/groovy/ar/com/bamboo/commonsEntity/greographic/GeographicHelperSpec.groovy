package ar.com.bamboo.commonsEntity.greographic

import ar.com.bamboo.commonsEntity.*
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

        def provinceService = Mock(ProvinceService)
        provinceService.listAll() >> {
            [new Province(), new Province()]
        }
        geographicHelper.provinceService = provinceService

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
        Province province1 = new Province()
        province1.id = 1
        EntityGeographic entityGeographic = new EntityGeographic(province: province1)
        GeographicHelper geographicHelper = new GeographicHelper()

        def provinceService = Mock(ProvinceService)
        provinceService.listAll() >> {
            [new Province(), new Province()]
        }
        geographicHelper.provinceService = provinceService

        def departmentService = Mock(DepartmentService)
        departmentService.listAllByProvince(_) >> { Province province ->
            [new Department(), new Department()]
        }
        geographicHelper.departmentService = departmentService

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
        Province province1 = new Province()
        province1.id = 1
        Department department = new Department()
        department.id = 1
        EntityGeographic entityGeographic = new EntityGeographic(province: province1, department: department)
        GeographicHelper geographicHelper = new GeographicHelper()

        def provinceService = Mock(ProvinceService)
        provinceService.listAll() >> {
            [new Province(), new Province()]
        }
        geographicHelper.provinceService = provinceService

        def departmentService = Mock(DepartmentService)
        departmentService.listAllByProvince(_) >> { Province province ->
            [new Department(), new Department()]
        }
        geographicHelper.departmentService = departmentService

        def cityService = Mock(CityService)
        cityService.listAllByDepartment(_) >>{ Department province ->
            [new City(), new City()]
        }
        geographicHelper.cityService = cityService

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
