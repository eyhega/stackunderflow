UI.Sidebar = {};

// -- View
UI.Sidebar.show = function()
{
	Fetcher.getTemplate('sidebar', function(sidebar_tpl) {
		var context_sidebar = {
			do: 1,
			user: Site.user,
			badges: []
		};
		
		$j('#zz-sidebar').html(Mark.up(sidebar_tpl, context_sidebar));

		$j('#zz-main-content').removeClass("col-md-12");
		$j('#zz-main-content').addClass("col-md-9");

		$j('#zz-sidebar').show();
	});

	
};

UI.Sidebar.hide = function()
{
	$j('#zz-sidebar').hide();

	$j('#zz-main-content').removeClass("col-md-9");
	$j('#zz-main-content').addClass("col-md-12");
};