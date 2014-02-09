package com.stackunderflow

import java.util.List;

class AnswerService {
	
	//BadgeService injection
	def badgeService
	
	class Constants {
		// Reputation
		final static def NEW_ANSWER_REP = 20
		final static def REP_PER_VOTE = 20
		final static def EDIT_ANSWER_REP = 1
		final static def BEST_ANSWER = 200
		
		//Badges
		final static def NEWBIE = 1
		final static def TRAINEE = 10
		final static def PRO = 100
		final static def MASTER = 1000
		final static def GOD = 10000
	}
	
	private void processVerification(User user,Tag t,int level, String description) {
		
		def badges = Badge.findAllByName("Answer: ${t.name} ${description}")
		def badge = null
		
		if(badges.size() == 0) { //create the badge
			badge = new Badge(name:"Answer: ${t.name} ${description}", point:level, description:"You have posted ${level} or more answers relative to ${t.name}")
			badge.save()
		}else {
			badge = badges.get(0)
		}
			
		badgeService.attributeBadge(badge.id,user.id)
	}
	
	/*
	 * Attribute a badge regarding the question tag.
	 * If the user has posted severals question concerning these tags, he will gain a badge
	 */
	private void evaluateUserBadges(User user, List<Tag> tags) {
		
		for(Tag t : tags) {
			def countRelativePostedAnswer = Answer.findAll { vars -> vars.question.tags.contains(t) && vars.user.id == user.id}.size()
			switch(countRelativePostedAnswer) {
				case Constants.NEWBIE:
				processVerification(user, t, Constants.NEWBIE, "Newbie")
				break
				
				case Constants.TRAINEE:
				processVerification(user, t, Constants.TRAINEE, "Trainee")
				break
				
				case Constants.PRO:
				processVerification(user, t, Constants.PRO, "Pro")
				break
				
				case Constants.MASTER:
				processVerification(user, t, Constants.MASTER, "Master")
				break
				
				case Constants.GOD:
				processVerification(user, t, Constants.GOD, "God")
				break
			}
		}
	}

    def boolean addNewAnswer(int questionId, int userId, String body) {
		def questions = Question.findAllById(questionId)
		def users = User.findAllById(userId)
		boolean retStatement = false
		
		if(users.size() > 0 && questions.size() > 0) {
			def currentUser = users.get(0)
			def currentQuestion = questions.get(0)
			def newAnswer =  new Answer(text: body, postedDate: new Date()
				, user: currentUser, question: currentQuestion)
			newAnswer.save()
			
			currentUser.score += Constants.NEW_ANSWER_REP
			currentUser.save()
			
			evaluateUserBadges(currentUser, currentQuestion.tags)
			retStatement = newAnswer.validate()
		}
		
		return retStatement
    }
	
	def editAnswer(int answerId, int userId, String newContent) {
		def answers = Answer.findAllById(answerId)
		def users  = User.findAllById(userId)
		
		if(answers.size() > 0 && users.size() > 0) {
			def currentAnswer = answers.get(0)
			def currentUser = users.get(0)
			if(currentAnswer.text != newContent) { //content has changed ?
				currentAnswer.text = newContent
				currentAnswer.lastEditionDate = new Date()
				currentAnswer.userEdit = currentUser
				currentAnswer.save()
				
				currentUser.score += Constants.EDIT_ANSWER_REP
				currentUser.save()
			}
		}
	}
	def deleteAnswer(Long answerId, Long userId) {
		def answers = Answer.findAllById(answerId)
		def users  = User.findAllById(userId)
		
		if(answers.size() > 0 && users.size() > 0) {
			def currentAnswer = answers.get(0)
			def currentUser = users.get(0)
			if(currentAnswer.user == currentUser || currentUser.admin) {
				currentAnswer.delete()
			}
		}
	}
	
	def upVoteAnswer(Long answerId, Long userId) {
		def answers = Answer.findAllById(answerId)
		def users = User.findAllById(userId)
		
		if(answers.size() > 0 && users.size() > 0) {
			def currentAnswer = answers.get(0)
			def currentUser = users.get(0)
			def votedActionsForUser =  VoteAction.findAllByUserAndElement(currentUser,currentAnswer)
			if(votedActionsForUser.size() == 0) { //non existing
				currentAnswer.upVote++
				//increase owner score
				currentAnswer.user.score += Constants.REP_PER_VOTE
				currentAnswer.save()
				
				def votedActionToAdd = new VoteAction(user: currentUser, element: currentAnswer, voteDate: new Date())
				votedActionToAdd.save()
			}
		}
	}
	
	def downVoteComment(Long answerId, Long userId) {
		def answers = Answer.findAllById(answerId)
		def users = User.findAllById(userId)
		
		if(answers.size() > 0 && users.size() > 0) {
			def currentAnswer = answers.get(0)
			def currentUser = users.get(0)
			def votedActionsForUser =  VoteAction.findAllByUserAndElement(currentUser,currentAnswer)
			if(votedActionsForUser.size() == 0) { //non existing
				currentAnswer.downVote++
				//decrease owner score
				currentAnswer.user.score -= Constants.REP_PER_VOTE
				currentAnswer.save()
				
				def votedActionToAdd = new VoteAction(user: currentUser, element: currentAnswer, voteDate: new Date())
				votedActionToAdd.save()
			}
		}
	}
	
	def chooseAsBestAnswer(Long answerId, Long userId) {
		def answers = Answer.findAllById(answerId)
		def users = User.findAllById(userId)
		
		if(answers.size() > 0 && users.size() > 0) {
			def currentAnswer = answers.get(0)
			def currentUser = users.get(0)
			
			if(currentAnswer.question.user == currentUser) { //if the user own the question
				currentAnswer.bestAnswer = true
				currentAnswer.user.score += Constants.BEST_ANSWER
				currentAnswer.save()
			}
		}
	}
}
