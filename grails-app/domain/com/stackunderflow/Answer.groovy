package com.stackunderflow

class Answer extends VoteAndEditElement {
    
    String          text
    boolean         bestAnswer
	Date 			postedDate
    
    static hasMany = [comments:Comment]
    
    static belongsTo = [question:Question, user:User]
    
    static mapping = {
        bestAnswer defaultValue: false
    }
    
    static constraints = {
        text(blank:false, nullable:false, size:5..512)
        comments(nullable:true)
        question(nullable:false)
        user(nullable:false)
		postedDate(nullable:false)
		bestAnswer(nullable:false)
    }
}
