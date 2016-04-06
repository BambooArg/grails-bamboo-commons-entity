package ar.com.bamboo.commonsEntity

import ar.com.bamboo.commons.combo.OptionCombo
import grails.converters.JSON

class ProvinceController {

    static allowedMethods = [provinceByCountry: "POST"]

    ProvinceService provinceService

    def provinceByCountry(Long idCountry) {
        Country country = new Country()
        country.id = idCountry
        List<Province> provinces = provinceService.listAllByCountry(country)
        def result = [success: true, content: new ArrayList<OptionCombo>(provinces.size())]

        for (province in provinces) {
            result.content.add([value: province.id, label: province.name])
        }

        render result as JSON
    }
}
