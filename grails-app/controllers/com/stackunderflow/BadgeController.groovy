package com.stackunderflow

import grails.converters.JSON
import org.springframework.transaction.annotation.Transactional;

class BadgeController {

    //link service
	def badgeService
	
	//GET to /badge
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		JSON.use('deep') {
			render Badge.list(max) as JSON
		}
	}
	
	//GET to /badge/${id}
	def show(Badge badge) {
		if(badge == null) {
			render status:404
		}
		JSON.use('deep') {
			render badge as JSON
		}
	}
	
	//POST to /badge/${id}
	@Transactional
	def save(Badge badge) {
		if(badge == null) {
			render status:404
		}
	}
	
	//PUT to /badge/${id}
	@Transactional
	def update(Badge badge) {
		render status:404
	}
	
	def badgeList = {
		def currentUser = session.user
		if(currentUser != null) {
			def badges = badgeService.getBadgesForUser(currentUser.id)
			//TODO new view or render
			render badges as JSON
		}
	}
}
