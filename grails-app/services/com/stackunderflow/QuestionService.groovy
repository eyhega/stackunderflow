package com.stackunderflow

import org.codehaus.groovy.grails.plugins.web.taglib.CountryTagLib;

class QuestionService {
	
	//inject service badge
	def badgeService = new BadgeService()
	
	class Constants {
		
		//Reputation
		final static def NEW_QUESTION_REP = 10
		final static def REP_PER_VOTE = 20
		final static def EDIT_QUESTION_REP = 1
		
		//Badges
		final static def NEWBIE = 1
		final static def TRAINEE = 10
		final static def PRO = 100
		final static def MASTER = 1000
		final static def GOD = 10000
	}
	
	def Question getQuestion(Long questionId) {
		def questions = Question.findAllById(questionId)
		def current = null
		if(questions.size() > 0) {
			current = questions.get(0)
		}
		
		return current
	}
	
	private void processVerification(User user,Tag t,int level, String description) {
		
		def badges = Badge.findAllByName("Question: ${t.name} ${description}")
		def badge = null
		
		if(badges.size() == 0) { //create the badge
			badge = new Badge(name:"Question: ${t.name} ${description}", point:level, description:"You have posted ${level} or more questions relative to ${t.name}")
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
			def countRelativePostedQuestions = Question.findAll { vars -> vars.tags.contains(t) && vars.user.id == user.id}.size()
			switch(countRelativePostedQuestions) {
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

    def boolean newQuestion(Long userId, String title, String body, List<Integer> tagsId) {
		
		def users = User.findAllById(userId)
		boolean retStatement = false
		
		if(users.size()  > 0) {
			def currentUser = users.get(0)
			
			def currentTags = []
			tagsId.each {
				currentTags.add(Tag.findAllById(it.value).get(0))
			}
			
			def newQuestion = new Question(title: title, text: body, submissionDate: new Date()
				, user:currentUser, tags: currentTags)
			newQuestion.save()
			currentUser.score += Constants.NEW_QUESTION_REP
			currentUser.save()
			
			//now save the question in tag reference
			currentTags.each {
				if(it.questions == null) {
					it.questions = []
				}	
				it.questions.add(newQuestion)
				it.save()
			}
			evaluateUserBadges(currentUser, currentTags)
			
			retStatement = newQuestion.validate()
		}
		
		
		return retStatement
    }
	
	def upVoteQuestion(Long questionId,Long userId) {
		def questions = Question.findAllById(questionId)
		def users = User.findAllById(userId)
		
		if(questions.size() > 0 && users.size() > 0) {
			def currentQuestion = questions.get(0)
			def currentUser = users.get(0)
			def votedActionsForUser =  VoteAction.findAllByUserAndElement(userId, currentQuestion)
			if(votedActionsForUser.size() == 0) { //non existing
				currentQuestion.upVote++
				//now increase owner rep
				currentQuestion.user.score += Constants.REP_PER_VOTE
				currentQuestion.save()
				
				def votedActionToAdd = new VoteAction(user: currentUser, element: currentQuestion, voteDate: new Date())
				votedActionToAdd.save()
			}
		}
	}
	
	def downVoteQuestion(Long questionId, Long userId) {
		def questions = Question.findAllById(questionId)
		def users = User.findAllById(userId)
		
		if(questions.size() > 0 && users.size() > 0) {
			def currentQuestion = questions.get(0)
			def currentUser = users.get(0)
			def votedActionsForUser =  VoteAction.findAllByUserAndElement(currentUser,currentQuestion)
			if(votedActionsForUser.size() == 0) { //non existing
				currentQuestion.downVote++
				//decrease owner rep
				currentQuestion.user.score -= Constants.REP_PER_VOTE
				currentQuestion.save()
				
				def votedActionToAdd = new VoteAction(user: currentUser, element: currentQuestion, voteDate: new Date())
				votedActionToAdd.save()
			}
		}
	}
	
	def editQuestion(Long questionId, Long userId, String newContent) {
		def questions = Question.findAllById(questionId)
		def users = User.findAllById(userId)
		
		if(questions.size() > 0 && users.size() > 0) {
			def currentQuestion = questions.get(0)
			def currentUser = users.get(0)
			if(currentQuestion.text != newContent) { //content has changed ?
				currentQuestion.lastEditionDate = new Date()
				currentQuestion.userEdit = currentUser
				currentQuestion.text = newContent
				currentQuestion.save()
				
				currentUser.score += Constants.EDIT_QUESTION_REP
				currentUser.save()
			}
		}
	}
	
	def deleteQuestion(Long questionId, Long userId) {
		def questions = Question.findAllById(questionId)
		def users = User.findAllById(userId)
		if(questions.size() > 0 && users.size() > 0) {
			def currentUser = users.get(0)
			if((currentUser.admin) || questions.user.id == userId) {
				questions.get(0).delete()
			}
		}
	}
}
