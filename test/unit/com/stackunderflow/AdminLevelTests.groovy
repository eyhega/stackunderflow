
package com.stackunderflow

import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AdminLevel)
class AdminLevelTests {

    void testConstraints() {
        
        //Test good values
        def adminLevel  = new AdminLevel(level: 50, levelDescription: "a level description")
        mockDomain(AdminLevel, [adminLevel])
        
        assertTrue adminLevel.validate()
        assertTrue adminLevel.errors.errorCount == 0
        
        //Test null values
        def badLevel = new AdminLevel()
        mockForConstraintsTests(AdminLevel, [badLevel])
        
        assertFalse badLevel.validate()
        assertEquals( 1, badLevel.errors.errorCount)
    }
}
