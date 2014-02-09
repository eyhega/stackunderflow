package com.stackunderflow

import org.springframework.transaction.annotation.Transactional;
import grails.converters.JSON
import org.springframework.transaction.annotation.Transactional;

class AnswerController {

	//define AnswerService
	def answerService

	//GET to /answer
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		JSON.use('deep') {
			render Answer.list(max) as JSON
		}
	}
	
	//GET to /answer/${id}
	def show(Answer answer) {
		if(answer == null) {
			render status:404
		}
		JSON.use('deep') {
			render answer as JSON
		}
	}
	
	//POST to /answer/${id}
	@Transactional
	def save(Answer answer) {
		if(answer == null) {
			render status:404
		}
	}
	
	//PUT to /answer/${id}
	@Transactional
	def update(Answer answer) {
		render status:404
	}
	
}
