package com.stackunderflow

class VoteAndEditElement {

    int 	upVote
    int 	downVote
    Date    lastEditionDate
    User    userEdit
    
    static mapping= {
        upVote defaultValue: 0
        downVote defaultValue: 0
    }
    
    static constraints = {
        lastEditionDate(nullable:true)
        userEdit(nullable:true)
    }
}
