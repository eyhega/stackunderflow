package com.stackunderflow

class Question extends VoteAndEditElement {
    
    String          title
    String          text
    Date            submissionDate
    
    static belongsTo = [user:User]
    
    static hasMany = [answers:Answer , comments:Comment , tags:Tag]

    static constraints = {
        title(blank: false, nullable:false, size: 10..150)
        text(blank:false, nullable:false, size: 10..512)
        submissionDate(nullable:false)
        user(nullable:false,blank:false)
        answers(nullable:true)
        comments(nullable:true)
        tags(nullable:false)
    }
}
