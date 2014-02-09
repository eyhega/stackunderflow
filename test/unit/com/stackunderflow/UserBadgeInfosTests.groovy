package com.stackunderflow



import grails.test.mixin.*

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(UserBadgeInfos)
class UserBadgeInfosTests {

    void testConstraints() {
       
		//Define a user
		String pwd = "MyPassword"
		def mockUser = new User(name: 'Gandhi',passwordHash: DigestUtils.sha256Hex(pwd),
			email: 'gandhi@free.fr', registerDate: new Date())
		mockDomain(User,[mockUser])
		
		//Define a Badge
		def mockBadge = new Badge(name: "C++ God", description:"You have answered more than 1000 questions about C++"
			, point: 30000)
		mockDomain(Badge.class, [mockBadge])
		
		//Now define a UserBadgeInfo
		def mockUserBadgeInfo = new UserBadgeInfos(user:mockUser, badge:mockBadge, dateOfAttribution: new Date())
		mockForConstraintsTests(UserBadgeInfos.class, [mockUserBadgeInfo])
		
		assertTrue mockUserBadgeInfo.validate()
		assertFalse mockUserBadgeInfo.hasErrors()
    }
}
