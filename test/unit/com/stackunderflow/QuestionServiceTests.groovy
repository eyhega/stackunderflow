package com.stackunderflow



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(QuestionService)
@Mock([Question,User,Tag])
class QuestionServiceTests {
	
	void testNewQuestion() {
		mockService(UserService.class)
		def userService = new UserService()
		userService.addNewUser("Gandhi", "abcdefgh", "gandhi@free.fr")
		def gandhiUser = User.findAllByName("Gandhi").get(0)
		
		def currentTag = new Tag(name: "C++",description: "C++ is the best programming language.")
		currentTag.save()
		
		def questionService = new QuestionService()
		assertTrue questionService.newQuestion(gandhiUser.id, "How to develop in C++", "I was just thinking of that, how to dev in C++ ?",[currentTag.id])
		assertEquals  1,gandhiUser.questions.size()
	}
	
	void testVote() {
		//DEFINE A QUESTION
		mockService(UserService.class)
		def userService = new UserService()
		userService.addNewUser("Gandhi", "abcdefgh", "gandhi@free.fr")
		def gandhiUser = User.findAllByName("Gandhi").get(0)
		
		def currentTag = new Tag(name: "C++",description: "C++ is the best programming language.")
		currentTag.save()
		
		def questionService = new QuestionService()
		questionService.newQuestion(gandhiUser.id, "How to develop in C++", "I was just thinking of that, how to dev in C++ ?",[currentTag.id])
		//END DEFINE
		
		
		def currentQuestion = gandhiUser.questions.first()
		questionService.upVoteQuestion(currentQuestion.id,gandhiUser.id)
		
		assertEquals 1,currentQuestion.upVote
		assertEquals 0,currentQuestion.downVote
		
		questionService.downVoteQuestion(currentQuestion.id,gandhiUser.id)
		assertEquals 0,currentQuestion.downVote//already voted
	}
}
