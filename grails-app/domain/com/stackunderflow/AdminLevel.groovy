package com.stackunderflow

class AdminLevel {

    int		level
    String  levelDescription
    
    static hasMany = [users:User]
    
    static constraints = {
        level(nullable:false, blank:false, unique: true)
        levelDescription(nullable:false, blank:false, size:5..256)
    }
}
