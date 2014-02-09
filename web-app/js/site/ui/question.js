UI.Question = {};

UI.Question.showList = function() {
	Fetcher.getQuestions(function(questions) {
		Fetcher.getTemplate('question_list', function(main_tpl) {
			var context_main = {
				do: 1,
				questions: questions
			};
			
			$j('#nav_topics').removeClass('active');
			$j('#nav_myquestions').removeClass('active');
			$j('#nav_myanswers').removeClass('active');
			$j('#nav_mycomments').removeClass('active');
			$j('#nav_messages').removeClass('active');

			$j('#nav_topics').addClass('active');

			$j('#zz-main-content').html(Mark.up(main_tpl, context_main));
		});
	});
};

UI.Question.showQuestion = function(id) {
	Fetcher.getQuestion(id, function(question) {
		Fetcher.getTemplate('question', function(main_tpl) {
			var context_main = {
				do: 1,
				question: question
			};

			$j('#zz-main-content').html(Mark.up(main_tpl, context_main));
		});
	});
};

UI.Question.showNewQuestion = function() {
	if (Object.keys(Site.user).length > 0)
	{
		$j('#zz-modal-question-new').modal('show');	
	}
	else
	{
		UI.Login.ShowLogIn();
	}

};

UI.Question.showMyQuestions = function() {
	Fetcher.getMyQuestions(function(questions) {
		Fetcher.getTemplate('question_list', function(main_tpl) {
			var context_main = {
				do: 1,
				questions: questions
			};
			
			$j('#nav_topics').removeClass('active');
			$j('#nav_myquestions').removeClass('active');
			$j('#nav_myanswers').removeClass('active');
			$j('#nav_mycomments').removeClass('active');
			$j('#nav_messages').removeClass('active');

			$j('#nav_myquestions').addClass('active');

			$j('#zz-main-content').html(Mark.up(main_tpl, context_main));
		});
	});
},

UI.Question.sendNewQuestion = function() {

	var title = $j('#zz-modal-question-new-title').val();
	var body = $j('#zz-modal-question-new-body').val();
	var tags = [];

	if (title.length < 10)
	{
		$j('#zz-modal-question-new-error').text($j.t('error.title_too_short'));
		$j('#zz-modal-question-new-error').show();
	}
	else if (body.length < 10)
	{
		$j('#zz-modal-question-new-error').text($j.t('error.body_too_short'));
		$j('#zz-modal-question-new-error').show();
	}
	else 
	{
		$j('#zz-modal-question-new-error').hide();
		if (Object.keys(Site.user).length > 0)
		{
			$j('#zz-modal-question-new').modal('hide');
			Site.sendNewQuestion(title, body, tags);
		}
		else
		{
			UI.Login.ShowLogIn();
		}
	}
};