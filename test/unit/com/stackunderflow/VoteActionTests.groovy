package com.stackunderflow



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(VoteAction)
class VoteActionTests {

    void testConstraints() {
		def mockUser = new User(email: "test@test.com", name: "test", passwordHash: "abc", registerDate: new Date() )
		mockDomain(User.class, [mockUser])
		
		//Create simple tag to assign to the question
		def mockTag = new Tag(name: "The tag name", description: "A simple tag description")
		mockDomain(Tag.class, [mockTag])
		
		//Test good question
		def mockQuestion = new Question(title: 'How to write stackunderflow ?',
		text: 'What is the best way to develop a website like stackoverflow ?',submissionDate: new Date(),
		lastEditedDate: null, user:mockUser, tags: [mockTag])
	
		mockDomain(Question.class, [mockQuestion])
		
		def mockVoteAction = new VoteAction(user:mockUser,element: mockQuestion, voteDate: new Date())
		mockForConstraintsTests(VoteAction.class, [mockVoteAction])
		
		assertTrue mockVoteAction.validate()
		assertFalse mockVoteAction.hasErrors()
    }
}
