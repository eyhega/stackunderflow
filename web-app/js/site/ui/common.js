UI.resizeMainContent = function()
{
	$j('#zz-content').height(($j(window).height() - $j('#zz-navbar').height() - $j('#zz-navbar').css('margin-bottom').replace('px', '')) + 'px');
};

UI.swipe = function()
{
	$j('#' + old_elt).hide( "slide", { direction: "left", easing: "easeInCirc" }, "fast",
		function() {$j('#' + new_elt).show( "slide", { direction: "right", easing: "easeOutCirc" }, 700 );});
};