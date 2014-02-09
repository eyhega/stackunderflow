import grails.converters.JSON

import com.stackunderflow.*
import com.stackunderflow.User

class BootStrap {

    def init = { servletContext ->
		
		JSON.registerObjectMarshaller(User) {
			def returnArray = [:]
			returnArray["name"] = it.name
			returnArray["email"] = it.email
			returnArray["registerDate"] = it.registerDate
			returnArray["score"] = it.score
			returnArray["admin"] = it.admin
			returnArray["description"] = it.description
			returnArray["questions"] = it.questions
			returnArray["comments"] = it.comments
			returnArray["answers"] = it.answers
			returnArray["adminLevel"] = it.adminLevel
			return returnArray
		}
		
		def userService = new UserService()
		userService.addNewUser("toutou", "519cc6748c483db8c4fd6043709ea22da57f606b0baed240334f4179499439698fe1125f3cabd41037d3e490d39bbc0df2e77a449f15f2ed0dba6136f498b09d", "gandhi@free.fr")
		def toutou = User.findAllByName("toutou").get(0)
		
		def currentTag = new Tag(name: "C++",description: "C++ is the best programming language.")
		currentTag.save()
		
		def questionService = new QuestionService()
		questionService.newQuestion(toutou.id, "How to develop in C++", "I was just thinking of that, how to dev in C++ ?",[currentTag.id])
		def currentQuestion = Question.findAllByTitle("How to develop in C++")?.get(0)
		
		def commentService = new CommentService()
		commentService.addCommentToQuestion(currentQuestion.id, toutou.id, "Can you explain a bit more ?")
		
		//mdp "toutou"
		//519cc6748c483db8c4fd6043709ea22da57f606b0baed240334f4179499439698fe1125f3cabd41037d3e490d39bbc0df2e77a449f15f2ed0dba6136f498b09d
    }
    def destroy = {
    }
}
