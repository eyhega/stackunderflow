package com.stackunderflow



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
class CommentTests {

    void testContraints() {
       
		//Create a simple user
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
		
		//Now we can create a Comment object
		def mockComment = new Comment(text: "Can you provide us more code?", user: mockUser, question:mockQuestion)
		mockDomain(Comment.class, [mockComment])
		
		assertFalse mockComment.hasErrors()
    }
}
