package com.stackunderflow

class VoteAction {

	User 				user
	VoteAndEditElement	element
	Date 				voteDate
	
    static constraints = {
		user(nullable:false)
		voteDate(nullable:false)
		element(nullable:false)
    }
}
