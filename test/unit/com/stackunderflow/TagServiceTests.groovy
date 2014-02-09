package com.stackunderflow



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TagService)
@Mock([Question, User, Tag, Comment, Badge, UserBadgeInfos])
class TagServiceTests {

	void setUp() {
		def userService = new UserService()
		userService.addNewUser("toutou", "519cc6748c483db8c4fd6043709ea22da57f606b0baed240334f4179499439698fe1125f3cabd41037d3e490d39bbc0df2e77a449f15f2ed0dba6136f498b09d", "gandhi@free.fr")
		def toutou = User.findAllByName("toutou").get(0)
		
		def currentTag = new Tag(name: "C++",description: "C++ is the best programming language.")
		currentTag.save()
		def javaTag = new Tag(name: "Java",description: "Java is the best programming language.")
		javaTag.save()
		
		def questionService = new QuestionService()
		
		questionService.newQuestion(toutou.id, "How to develop in C++", "I was just thinking of that, how to dev in C++ ?",[currentTag.id])
		def currentQuestion = Question.findAllByTitle("How to develop in C++")?.get(0)
		
		questionService.newQuestion(toutou.id, "How to instanciate JVM through C++ ?", "How to instanciate JVM through C++ by using JNI...", [currentTag.id,javaTag.id])
		
		def commentService = new CommentService()
		commentService.addCommentToQuestion(currentQuestion.id, toutou.id, "Can you explain a bit more ?")
		
	}
	
    void testGetQuestionRelativeToTagName() {
        
		def tagService = new TagService()
		def questionCollection = tagService.getQuestionsRelativeToTagName("C++")
		
		assertEquals 2, questionCollection.size()
		
		def javaQuestionCollection = tagService.getQuestionsRelativeToTagName("Java")
		
		assertEquals 1,javaQuestionCollection.size()
    }
}
