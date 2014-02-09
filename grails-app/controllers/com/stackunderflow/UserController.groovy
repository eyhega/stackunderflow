package com.stackunderflow

import grails.converters.JSON
import org.springframework.transaction.annotation.Transactional;

class UserController {
	
	static responseFormats = ['json', 'xml']
	
	//define UserService
	def userService
	
	//link AdminLevelService
	def adminLevelService
	
	//GET to /user
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		JSON.use('deep') {
			render User.list(params.max) as JSON
		}
	}
	
	//GET to /question/${id}
	def show(User user) {
		if(user == null) {
			render status:404
		}
		JSON.use('deep') {
			render user as JSON
		}
	}
	
	//POST to /question
	@Transactional
	def save(User user) {
		if(user == null) {
			render status:404
		}
	}
	
	//PUT to /question/${id}
	@Transactional
	def update(User user) {
		render status:404
	}
	
	def register() {
		if (params.name == null || 
			params.passwordHash == null ||
			params.email == null)	{
			render status:400
		}
		else
		{
			if (userService.addNewUser(params.name, params.passwordHash, params.email))
			{
				render status:200
			}
			else
			{
				render status:403
			}
		}
	}
	
	def login() {
		// if the login or password is null, we do not continue
		if (params.name == null || params.passwordHash == null)	{
			render status:400
		}
		else
		{
			// good password => login | bad password => 403 forbidden
			if(userService.connect(params.name, params.passwordHash)) {
				session.user = userService.getUser(params.name)
				render session.user as JSON
			}
			else {
				render status:403
			}
		}
	}
	
	def current() {
		if (session.user != null)
		{
			render session.user as JSON
		}
		else
		{
			render status:403
		}
	}
	
	def logout = {
		session.invalidate();
		render status:200
	}
	
	def editProfile = {
		def currentUser = session.user
		if(currentUser == null) {
			//TODO render error view
		}
		
		def newContent = params.profileContent
		userService.modifyProfile(currentUser.id, newContent)
		//render profile view
	}
	
	def listMyQuestions = {
		def currentUser = session.user
		if(currentUser == null) {
			//TODO render error view
		}
		
		render userService.getQuestionsForUser(currentUser.id) as JSON
	}
	
	def listMyComments = {
		def currentUser = session.user
		if(currentUser == null) {
			//TODO render error view
		}
		
		render userService.getCommentsForUser(currentUser.id) as JSON
	}
	
	def listMyAnswers = {
		def currentUser = session.user
		if(currentUser == null) {
			//TODO render error view
		}
		
		render userService.getAnswersForUser(currentUser.id) as JSON
	}
	
	def grantAdmin = {
		def currentUser = session.user
		if(currentUser == null) {
			render status:403
		}
		else
		{
			def userIdToGrant = params.userIdToPromote
			def level = params.level
			if(userIdToGrant == null || level == null) {
				render status:404
			}
			adminLevelService.grantAdminRight(currentUser.id, userIdToGrant,level)
		}
	}
}
