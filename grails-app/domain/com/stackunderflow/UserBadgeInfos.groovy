package com.stackunderflow

//association Many-To-Many table
class UserBadgeInfos {
	
    Date    dateOfAttribution
    
	static belongsTo = [user:User,badge:Badge]
	
    static constraints = {
        user(nullable:false)
        badge(nullable:false)
        dateOfAttribution(nullable:false)
    }
}
