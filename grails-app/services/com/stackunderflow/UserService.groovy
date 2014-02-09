package com.stackunderflow

class UserService {
	
    /**
     * Add a new user to the database.
     * 
     * return true if the user is valid, false otherwise
     */
    def boolean addNewUser(String name,String passwordHash,String email) {
        def newUser = new User(name: name, passwordHash: passwordHash, email: email, registerDate: new Date())
        newUser.save()
        
        return newUser.validate()
    }
    
	def User getUser(String userName) {
		User.findAllByName(userName).first()
	}
    
    /**
     * Allow a user to be connected
     * 
     * Return true if the user informations are correct, false otherwise
     */
    def boolean connect(String name,String passwordHash) {
        boolean allowConnection = false
        def users = User.findAllByName(name)
		
        if( (users.size() > 0) && (users[0].passwordHash.equals(passwordHash)) )
        {
            allowConnection = true
        }

        return allowConnection
    }
	
	def List<Question> getQuestionsForUser(Long userId) {
		def users = User.findAllById(userId)
		def questions = []
		if(users.size() > 0) {
			questions.addAll(users.get(0).questions)
		}
		return questions
	}
	
	def List<Comment> getCommentsForUser(Long userId) {
		def users = User.findAllById(userId)
		def comments = []
		if(users.size() > 0) {
			comments.addAll(users.get(0).comments)
		}
		return comments
	}
	
	def List<Answer> getAnswersForUser(Long userId) {
		def users = User.findAllById(userId)
		def answers = []
		if(users.size() > 0) {
			answers.addAll(users.get(0).answers)
		}
		return answers
	}
	
	def modifyProfile(Long userId, String newDescription) {
		def users = User.findAllById(userId);
		
		if(users.size() > 0) {
			def currentUser = users.get(0)
			currentUser.description = newDescription
			currentUser.save()
		}
	}
}