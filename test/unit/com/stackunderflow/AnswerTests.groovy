package com.stackunderflow



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Answer)
class AnswerTests {

    void testConstraints() {
    
		def mockAnswer = new Answer(text: "This an text example for a useful answer")
		mockForConstraintsTests(Answer.class,[mockAnswer])
		
		//assertTrue mockAnswer.hasErrors()
		assertFalse mockAnswer.validate()
		assertEquals "nullable", mockAnswer.errors["question"]
		assertEquals "nullable", mockAnswer.errors["user"]
		assertEquals "nullable", mockAnswer.errors["postedDate"]
		
		def mockUser = new User(email: "test@test.com", name: "testName", passwordHash: "abcdefgh", registerDate: new Date() )
		mockDomain(User.class,[mockUser])
		//Create simple tag to assign to the question
		def mockTag = new Tag(name: "The tag name", description: "A simple tag description")
		mockDomain(Tag.class, [mockTag])
		//Create question
		def mockQuestion = new Question(title: 'How to write stackunderflow ?',
        text: 'What is the best way to develop a website like stackoverflow ?',submissionDate: new Date(),
        lastEditedDate: null, user:mockUser, tags:[mockTag])
		mockDomain(Question.class,[mockQuestion])
		
		assertFalse mockUser.hasErrors()
		assertFalse mockQuestion.hasErrors()
		
		def mockGoodAnswer = new Answer(text: "This an text example for a useful answer", postedDate: new Date(), user:mockUser, question:mockQuestion )
		mockDomain(Answer.class,[mockGoodAnswer])
		
		assertFalse mockGoodAnswer.hasErrors()
    }
	
}
