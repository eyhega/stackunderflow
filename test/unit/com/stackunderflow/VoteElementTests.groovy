package com.stackunderflow



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(VoteAndEditElement)
class VoteAndEditElementTests {

    void testSomething() {
		
		def mockElement = new VoteAndEditElement()
		mockForConstraintsTests(VoteAndEditElement.class, [mockElement])
		
		assertTrue mockElement.validate()
		assertFalse mockElement.hasErrors()
    }
}
