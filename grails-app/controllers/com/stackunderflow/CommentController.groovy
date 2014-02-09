package com.stackunderflow

import org.springframework.transaction.annotation.Transactional;

import grails.converters.JSON

class CommentController {
	
	//define CommentService
	def commentService

	//GET to /comment
    def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		JSON.use('deep') {
			render Comment.list(max) as JSON
		}
	}
	
	//GET to /comment/${id}
	def show(Comment comment) {
		if(comment == null) {
			render status:404
		}
		JSON.use('deep') {
			render comment as JSON
		}
	}
	
	//POST to /comment/${id}
	@Transactional
	def save(Comment comment) {
		if(comment == null) {
			render status:404
		}
	}
	
	//PUT to /comment/${id}
	@Transactional
	def update(Comment comment) {
		render status:404
	}
	
	def newCommentForQuestion = {
		def currentUser = session.user
		
		if(currentUser == null ) {
			//render error view
		}
		
		def questionId = params.questionId
		def commentContent = params.content
		def state = commentService.addCommentToQuestion(questionId, currentUser.id,commentContent)
		if(!state){
			//render error view
		}
		else {
			//render current question view
		}
	}
	
	def newCommentForAnswer = {
		def currentUser = session.user
		
		if(currentUser == null ) {
			//render error view
		}
		
		def answerId = params.answerId
		def commentContent = params.content
		def state = commentService.addCommentToAnswer(answerId, currentUser.id,commentContent)
		if(!state){
			//render error view
		}
		else {
			//render current question view
		}
	}
	
	def editCommentContent = {
		def currentUser = session.user
		if(currentUser == null) {
			//render error view
		}
		
		def commentId = params.commentId
		def newContent = params.content
		commentService.editComment(commentId, currentUser.id,newContent)
		//render current question view
	}
}
