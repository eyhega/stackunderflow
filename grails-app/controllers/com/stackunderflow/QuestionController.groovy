package com.stackunderflow

import org.springframework.transaction.annotation.Transactional;


import grails.converters.JSON
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true)
class QuestionController{
	static responseFormats = ['json', 'xml']
	
	//define QuestionService
	def questionService
	
	//GET to /question
    def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		render Question.list(max) as JSON//, model:[questionCount: Question.count()]
	}
	
	//GET to /question/${id}
	def show(Question question) {
		if(question == null) {
			render status:404
		}
		JSON.use('deep') {
			render question as JSON
		}
	}
	
	def get = {
		def questionId = params.id
		if(questionId == null) {
			render status:400
		}
		else {
			def current = questionService.getQuestion(Integer.valueOf(questionId))
			if (current == null)
			{
				render status:404
			}
			else
			{
				render current as JSON
			}
		}
	}
	
	//POST to /question/${id}
	@Transactional
	def save(Question question) {
		if(question == null) {
			render status:404
		}
	}
	
	//PUT to /question/${id}
	@Transactional
	def update(Question question) {
		render status:404
	}
	
	//When the current user has filled the question form
	def newQuestion ={
		if(session.user == null) {
			render status:403
		}
		
		def id = session.user.id
		def title = params.title
		def body = params.body
		def tags = (params.tags == null)? [] : params.tags
		
		if(! questionService.newQuestion(session.user.id, params.title, params.body, tags)) {
			render status:400
		}
		else {
			render status:200
		}
	}
	
	def editBodyQuestion = {
		def currentUser =  session.user
		def currentQuestionId = params.questionId
		def content = params.newContent
		if(currentUser == null) {
			//TODO render error view
		}
		
		questionService.editQuestion(currentQuestionId, currentUser.id,newContent)
		//TODO render view question edited
	}
}
