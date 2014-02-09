package com.stackunderflow

class Comment extends VoteAndEditElement {
    
    String text
    
    static belongsTo = [question:Question,answer:Answer,user:User]
    
    static constraints = {
        text(nullable:false, blank:false, size: 5..256)
        
        // a comment concerns an answer XOR a question
        question(nullable:true, validator: { val,obj ->
                if( (obj.answer == null) || (val == null) ) {
                    return true
                }
                return false
        })
        
        answer(nullable:true, validator: { val,obj -> 
            if( (obj.question == null) || (val == null) ) {
                return true
            }
            return false
        })
    
        user(nullable:false)
    }
}
