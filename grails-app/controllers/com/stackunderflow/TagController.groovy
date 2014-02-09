package com.stackunderflow

import org.springframework.transaction.annotation.Transactional;

import grails.converters.JSON

class TagController {

	//link TagService
    def tagService
	
	//GET to /tag
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		JSON.use('deep') {
			render Tag.list(max) as JSON
		}
	}
	
	//GET to /tag/${id}
	def show(Tag tag) {
		if(tag == null) {
			render status:404
		}
		JSON.use('deep') {
			render tag as JSON
		}
	}
	
	//POST to /answer/${id}
	@Transactional
	def save(Tag tag) {
		if(tag == null) {
			render status:404
		}
	}
	
	//PUT to /tag/${id}
	@Transactional
	def update(Tag tag) {
		render status:404
	}
	
	def search = {
		def tagsToSearch = params.searchString
		def questions = tagService.getQuestionsRelativeToTagName(tagsToSearch)
		
		JSON.use('deep') {
			render questions as JSON
		}
	}
	
}
