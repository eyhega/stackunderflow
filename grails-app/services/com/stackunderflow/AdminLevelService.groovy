package com.stackunderflow

class AdminLevelService {
	
	class Constants {
		final static def SUPER_USER_LEVEL = 1
		final static def MODERATOR = 2
	}

    def grantAdminRight(Long idCurrentAdmin, Long idReceiver,int level) {
		def admins = User.findAllById(idCurrentAdmin)
		def receivers = User.findAllById(idReceiver)
		
		if(admins.size() > 0 && receivers.size() > 0) {
			def currentAdmin = admins.get(0)
			def currentUser = receivers.get(0)
			
			if(currentAdmin.admin && currentAdmin.adminLevel.level == Constants.SUPER_USER_LEVEL) {
				def adminLevel = AdminLevel.findAllByLevel(level)
				if(adminLevel.size() > 0) {
					currentUser.admin = true
					currentUser.adminLevel = adminLevel.get(0)
					currentUser.save()
				}
			}
		}
	}
}
