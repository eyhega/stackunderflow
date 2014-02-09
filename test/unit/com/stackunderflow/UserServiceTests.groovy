package com.stackunderflow



import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import org.junit.*
import org.apache.commons.codec.digest.DigestUtils

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User])
class UserServiceTests {
    
    void testAddNewUser() {
        String pwd = new String("myAwesomePwd")
        String hash = DigestUtils.sha256Hex(pwd)
        
        def userService = new UserService()
        
        assertTrue userService.addNewUser('Gandhi',hash,'gandhi@free.fr')
        assertFalse userService.addNewUser('Gandhi',hash,'gandhi@free.fr')
        assertTrue userService.addNewUser('Gandhi2',hash,'gandhi@free.fr')
        assertFalse userService.addNewUser('Gandhi3',hash,'gandhi_free.fr')
    }
    
    void testConnect() {
        String pwd = new String("myAwesomePwd")
        String hash = DigestUtils.sha256Hex(pwd)
        
        def userService = new UserService()
		
		
        assertTrue(userService.addNewUser('Gandhi',hash,'gandhi@free.fr'))
        assertTrue(userService.connect('Gandhi',hash))
    }
	
	void testModifyProfile() {
		String pwd = new String("myAwesomePwd")
		String hash = DigestUtils.sha256Hex(pwd)
		
		def userService = new UserService()
		//Creating user
		userService.addNewUser('Gandhi',hash,'gandhi@free.fr')
		
		//Get the user object
		def gandhiUser = User.findAllByName("Gandhi").get(0)
		
		userService.modifyProfile(gandhiUser.id, "My new description")
		
		assertNotNull gandhiUser.description
	}
}
