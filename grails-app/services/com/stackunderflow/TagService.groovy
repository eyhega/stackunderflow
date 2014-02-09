package com.stackunderflow

class TagService {
	
	def List<Question> getQuestionsRelativeToTagName(String tagName) {
		def tagCollection = Tag.findAll {
			tagName.contains(it.name)
		}
		
		def questionCollection = []
		tagCollection.each {
			questionCollection.addAll(it.questions)
		}
		
		return questionCollection
	}
}

