package com.stackunderflow

class BadgeService {

    def attributeBadge(Long idBadge, Long idUser) {
		def badges = Badge.findAllById(idBadge)
		def users = User.findAllById(idUser)
		
		if(badges.size() > 0 && users.size() > 0 ) {
			def currentUser = users.get(0)
			def currentBadge = badges.get(0)
			
			def counter = UserBadgeInfos.findAllByUserAndBadge(currentUser,currentBadge).size()
			if(counter == 0) { //attribution
				def infos = new UserBadgeInfos(user:currentUser, badge:currentBadge, dateOfAttribution: new Date())
				infos.save()
				currentUser.score += currentBadge.point
				currentUser.save()
			}
		}
	}
	
	def List<Badge> getBadgesForUser(Long userId) {
		def userBadgeInfoCollection = UserBadgeInfos.findAll {
			it.user.id == userId
			}
		def badgeCollection = []
		userBadgeInfoCollection.each {
			badgeCollection.add(it.badge)
		}
		
		return badgeCollection
	}
}
