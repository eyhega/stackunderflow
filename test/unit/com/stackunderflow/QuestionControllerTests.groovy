package com.stackunderflow


import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(QuestionController)
@Mock([Question,User,Tag])
class QuestionControllerTests {
	
	public void setUp() {
		//DEFINE A QUESTION
		def userService = new UserService()
		def questionService = new QuestionService()
		userService.addNewUser("Gandhi", "abcdefgh", "gandhi@free.fr")
		def gandhiUser = User.findAllByName("Gandhi").get(0)
		
		def currentTag = new Tag(name: "C++",description: "C++ is the best programming language.")
		currentTag.save()
		
		questionService.newQuestion(gandhiUser.id, "How to develop in C++", "I was just thinking of that, how to dev in C++ ?",[currentTag.id])
		//END DEFINE
	}
	
    void testIndex() {

		controller.request.addHeader "Accept","application/json, text/xml, */*"

		controller.index()
		def content = this.controller.response
		assertEquals content.json.title[0],"How to develop in C++"
		assertEquals content.json.user.id, [1]
    }
}
