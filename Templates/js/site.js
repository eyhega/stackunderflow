function resizeContent() {
	$('#zz-content').height(($(window).height() - $('#zz-navbar').height() - $('#zz-navbar').css('margin-bottom').replace('px', '')) + 'px');
}