package com.stackunderflow


import grails.test.mixin.*
import org.junit.*
import org.apache.commons.codec.digest.DigestUtils

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

    void testConstraints() {
        
        String pwd = new String("myAwesomePwd");
        
        //Test correct user
        def mockUser = new User(name: 'Gandhi',passwordHash: DigestUtils.sha256Hex(pwd),
        email: 'gandhi@free.fr', registerDate: new Date())
        mockForConstraintsTests(User,[mockUser])

        assertTrue mockUser.validate()
        assertEquals 0,mockUser.errors.errorCount
        
        
        //Test null values
        def nullUser = new User()
        mockForConstraintsTests(User,[nullUser])
        assertFalse nullUser.validate()
        assertEquals 'nullable',nullUser.errors['name']
        assertEquals 'nullable',nullUser.errors['passwordHash']
        assertEquals 'nullable',nullUser.errors['email']
        assertEquals 'nullable',nullUser.errors['registerDate']
		
		//Test default values
		assertEquals 0, mockUser.score
		
		
		//Test for Admin
		def mockAdminLevel = new AdminLevel(level: 1, levelDescription: "Global Admin")
		mockDomain(AdminLevel.class, [mockAdminLevel])
		
		def mockAdmin = new User(name: 'Superman',passwordHash: DigestUtils.sha256Hex(pwd),
        email: 'superman@free.fr', registerDate: new Date(), admin: true, adminLevel: mockAdminLevel)
		mockForConstraintsTests(User.class, [mockAdmin])
		
		assertTrue mockAdmin.validate()
		assertFalse mockAdmin.hasErrors()
    }
}
