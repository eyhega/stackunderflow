var i18n_engine = {
	
	initialize: function(lang, callback)
	{
		var option = {
			resGetPath: 'i18n/__lng__/__ns__.json',
			getAsync: false,
			lng: lang
		};

		i18n.init(option, function(t) {
			if (callback != null) callback();
		});
		
		moment.lang(lang.substr(0, lang.indexOf('-')));
	},
	
	afterInit: null,
	
};
