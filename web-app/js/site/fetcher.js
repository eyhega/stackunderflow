var Fetcher = {
	async: true,
	
	templates: {},

	questions: [],

	getTemplate: function(id, callback)
	{
		if (! Fetcher.templates.hasOwnProperty(id))
		{
			$j.get('tpl/' + id, function(data) {
				
				if (data != null)
					Fetcher.templates[id] = data;
				

				if (callback != null)
					callback(Fetcher.templates[id]);
			});
		}
		else
		{
			if (callback != null)
					callback(Fetcher.templates[id]);
		}
	},

	getQuestions: function(callback)
	{
		$j.getJSON('question/', function(questions) {
			if (callback != null)
				callback(questions);
		});
	},

	getQuestion: function(id, callback)
	{
		$j.getJSON('question/get', {id:id}, function(question) {
			if (callback != null)
				callback(question);
		});
	},

	getMyQuestions: function(callback)
	{
		$j.getJSON('user/listMyQuestions/', function(questions) {
			if (callback != null)
				callback(questions);
		});
	}	
};
