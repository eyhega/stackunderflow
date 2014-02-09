package com.stackunderflow



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Badge)
class BadgeTests {

    void testConstraints() {
       
		def mockBadge = new Badge(name: "A name")
		mockForConstraintsTests(Badge.class, [mockBadge])
		
		assertFalse mockBadge.validate()
		assertEquals "nullable", mockBadge.errors["description"]
		assertEquals null, mockBadge.errors["point"] // primitive type
		
		def mockGoodBadge = new Badge(name: "C++ God", description:"You have answered more than 1000 questions about C++"
			, point: 30000)
		mockDomain(Badge.class, [mockGoodBadge])
		
		assertFalse mockGoodBadge.hasErrors()
    }
}
