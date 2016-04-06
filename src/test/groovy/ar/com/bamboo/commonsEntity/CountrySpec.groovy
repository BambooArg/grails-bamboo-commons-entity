package ar.com.bamboo.commonsEntity

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Country)
@Build(Country)
class CountrySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test constraint"() {

        when: "Validate an invalid Country"
        Country invalidCountry = new Country()

        then: "The validation fails"
        !invalidCountry.validate()
        invalidCountry.hasErrors()
        invalidCountry.errors.errorCount == 1
        invalidCountry.errors.getFieldError("name").code == 'nullable'

        when: "Validate a valid Country"
        Country validCountry = Country.build()

        then: "The validation was successfully"
        validCountry.validate()
        !validCountry.hasErrors()
    }
}
