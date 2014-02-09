package com.stackunderflow

class Badge {
    
    String  name
    int		point
    String  description
    
    static hasMany = [userInfos:UserBadgeInfos]
	
    static constraints = {
        name(blank:false, nullable:false,size: 5..128, unique:true)
        point(blank:false, nullable:false)
        description(blank:false, nullable:false, size: 5..256)
    }
}
