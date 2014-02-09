package com.stackunderflow



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Tag)
class TagTests {

    void testConstraints() {
		
		def mockTag = new Tag(name: "Grails")
		mockForConstraintsTests(Tag.class,[mockTag])
		
		assertFalse mockTag.validate()
		assertEquals "nullable", mockTag.errors["description"]
		assertEquals 1, mockTag.errors.errorCount
		
		
		def mockGoodTag = new Tag(name: "Java", description: "The programmation language Java.")
		mockDomain(Tag.class, [mockGoodTag])
		
		assertFalse mockGoodTag.hasErrors()
    }
}
