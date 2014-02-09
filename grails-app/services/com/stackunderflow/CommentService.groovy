package com.stackunderflow

class CommentService {
	
	class Constants {
		final static def REP_PER_VOTE = 10
	}

    def boolean addCommentToQuestion (Long questionId, Long userId, String body) {
		def questions = Question.findAllById(questionId)
		def users = User.findAllById(userId)
		boolean retStatement = false;
		
		if(questions.size() > 0 && users.size() > 0) {
			def currentQuestion = questions.get(0);
			def currentUser = users.get(0)
			def comment = new Comment(text: body, user: currentUser, question:currentQuestion)
			comment.save()
			retStatement = comment.validate()
		}
    }
	
	def boolean addCommentToAnswer(Long answerId, Long userId, String body) {
		def answers = Answer.findAllById(answerId)
		def users = User.findAllById(userId)
		boolean retStatement = false;
		
		if(answers.size() > 0 && users.size() > 0) {
			def currentAnswer = answers.get(0);
			def currentUser = users.get(0)
			def comment = new Comment(text: body, user: currentUser, answer:currentAnswer)
			comment.save()
			retStatement = comment.validate()
		}
		
		return retStatement
    }
	
	def upVoteComment(Long commentId, Long userId) {
		def comments = Comment.findAllById(commentId)
		def users = User.findAllById(userId)
		
		if(comments.size() > 0 && users.size() > 0) {
			def currentComment = comments.get(0)
			def currentUser = users.get(0)
			def votedActionsForUser =  VoteAction.findAllByUserAndElement(currentUser,currentComment)
			if(votedActionsForUser.size() == 0) { //non existing
				currentComment.upVote++
				//increase owner score
				currentComment.user.score += Constants.REP_PER_VOTE
				currentComment.save()
				def votedActionToAdd = new VoteAction(user: currentUser, element: currentComment, voteDate: new Date())
				votedActionToAdd.save()
			}
		}
	}
	
	def downVoteComment(int commentId) {
		def comments = Comment.findAllById(commentId)
		def users = User.findAllById(userId)
		
		if(comments.size() > 0 && users.size() > 0) {
			def currentComment = comments.get(0)
			def currentUser = users.get(0)
			def votedActionsForUser =  VoteAction.findAllByUserAndElement(currentUser,currentComment)
			if(votedActionsForUser.size() == 0) { //non existing
				currentComment.downVote++
				//decrease owner score
				currentComment.user.score -= Constants.REP_PER_VOTE
				currentComment.save()
				
				def votedActionToAdd = new VoteAction(user: currentUser, element: currentComment, voteDate: new Date())
				votedActionToAdd.save()
			}
		}
	}
	
	def editComment(Long commentId, Long userId, String newContent) {
		def comments = Comment.findAllById(commentId)
		def users = User.findAllById(userId)
		
		if(comments.size() > 0 && users.size() > 0) {
			def currentComment = comments.get(0)
			def currentUser = users.get(0)
			//if the currentUser own the comment
			if(currentComment.user == currentUser) {
				currentComment.lastEditionDate = new Date()
				currentComment.userEdit = currentUser
				currentComment.text = newContent
				currentComment.save()
			}
		}
	}
	
	def deleteComment(Long commentId, Long userId) {
		def comments = Comment.findAllById(commentId)
		def users = User.findAllById(userId)
		
		if(comments.size() > 0 && users.size() > 0) {
			def currentComment = comments.get(0)
			def currentUser = users.get(0)
			if(currentComment.user == currentUser || currentUser.admin) {
				currentComment.delete()
			}
		}
	}

}
