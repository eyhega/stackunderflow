package com.stackunderflow

class User {

    String          name
    String          passwordHash
    String          email
    Date            registerDate
    int				score
	boolean			admin
	String			description
    
    static hasMany =  [questions:Question,comments:Comment,answers:Answer,badgesInfos:UserBadgeInfos]
	
	static belongsTo = [adminLevel: AdminLevel]
	
	static mapping = {
		score defaultValue: 0
		admin defaultValue: false
	}
    
    static constraints = {
        name(blank: false, nullable:false, size: 5..30, unique: true)
        passwordHash(blank: false, nullable:false, maxSize: 512)
        email(email:true, blank:false, nullable:false)
        registerDate(nullable:false)
        questions(nullable:true)
        comments(nullable:true)
        answers(nullable:true)
		badgesInfos(nullable:true)
		admin(nullable:true)
		adminLevel(nullable:true, validator: { val,obj ->
			if( (val == null) || obj.admin) {
				return true
			}
			return false
        })
		description(nullable:true, blank:true, size: 0..1024)
    }
}
