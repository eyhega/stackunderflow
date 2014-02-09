var Site =
{
	lang: 'fr-FR',

	user: {
		
	},
	
	initialize: function(callback) {
		Modernizr.load({
			load: [
				'js/site/i18n.js',
				'js/site/i18n/markup-translate.js',
				'js/site/i18n/markup-localize-datetime.js',
				'js/site/string/util.js',
				'js/site/fetcher.js',
				'js/site/ui.js',
				'js/site/ui/common.js',
				'js/site/ui/account.js',
				'js/site/ui/sidebar.js',
				'js/site/ui/question.js',
				'js/site/ui/login.js'
			],
			complete: function() {
				i18n_engine.initialize(
					Site.lang, 
					function() {
						Site.isLogOn(function() {
							if (callback != null) callback();
						});
					}
				);
			}
		});


	},
	
	isLogOn: function(callback) {
		$j.ajax({
    		type: "POST",
    		url: '/user/current',
    		data: 
    		{
    			
    		},
    		statusCode: 
    		{
      			200: function(user) {
					Site.user = user;
					UI.Login.onLogOn();
					if (callback != null) callback();
       			},
      			403: function() {
        			if (callback != null) callback();
       			}, 
       			400: function() { 
       				UI.Login.onLogError();
       			}
     		}
		});	
	},

	logIn: function(username, password) {
		if (username == null || password == null) return false;

		$j.ajax({
    		type: "POST",
    		url: '/user/login',
    		data: 
    		{
    			name: username, 
    			passwordHash: '' + CryptoJS.SHA512(password)
    		},
    		statusCode: 
    		{
      			200: function(user) {
					Site.user = user;
					UI.Login.onLogOn();
       			},
      			403: function() {
        			UI.Login.onBadID();
       			}, 
       			400: function() { 
       				//todo:report bad problem
       				UI.Login.onLogError();
       			}
     		}
		});		
	},

	logOut: function() {	
		$j.get('/user/logout');
		UI.Login.onNotLogOn();
	},

	register: function(username, password, email) {
		if (username == null || password == null || email == null) return false;

		$j.ajax({
    		type: "POST",
    		url: '/user/register',
    		data: 
    		{
    			name: username,
    			passwordHash: '' + CryptoJS.SHA512(password),
    			email: email
    		},
    		statusCode: 
    		{
      			200: function() {
					UI.Login.onRegistered();
       			},
      			403: function() {
      				UI.Login.onRegisterError();
      			}
     		}
		});
	},

	sendNewQuestion: function(title, body, tags) {
		if (title == null || body == null || tags == null) return false;

		$j.ajax({
    		type: "POST",
    		url: '/question/newQuestion',
    		data: 
    		{
    			title: title,
    			body: body,
    			tags: tags
    		},
    		statusCode: 
    		{
      			200: function() {
					UI.Questions.MyQuestions();
       			},
      			403: function() {
      				UI.Login.onLogError();
      			},
      			400: function() {
      				console.log('Critical error when sending question.');
      			}
     		}
		});		
	}
};

var $j = jQuery.noConflict();
