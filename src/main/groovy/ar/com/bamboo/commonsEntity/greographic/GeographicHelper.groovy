package ar.com.bamboo.commonsEntity.greographic

import ar.com.bamboo.commonsEntity.*

/**
 * Created by orko on 20/08/14.
 */
class GeographicHelper {

    ProvinceService provinceService
    DepartmentService departmentService
    CityService cityService

    public List<Object> getDependentGeographicList(objectWithGeographic){
        List<Province> provinces = provinceService.listAll()
        List<Department> departments
        List<City> cities
        if (objectWithGeographic?.province?.id){
            departments = departmentService.listAllByProvince(objectWithGeographic.province)
        }else{
            departments = []
        }
        if (objectWithGeographic?.department?.id){
            cities = cityService.listAllByDepartment(objectWithGeographic.department)
        }else{
            cities = []
        }

        return [provinces, departments, cities]

    }

}
