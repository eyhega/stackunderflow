package com.stackunderflow

class Tag {
    
    String  name
    String  description
    
    static hasMany = [questions:Question]
    static belongsTo = Question //Question is the owner. We store questions here manually
    
    static constraints = {
        name(nullable:false, blank: false, size:1..32, unique:true)
        description(nullable:false, blank: false, size: 10..256)
        questions(nullable:true)
    }
}
