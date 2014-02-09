Mark.pipes.localizeDate = function(str)
{
	return moment.utc(str *1000).local().format('L');
};

Mark.pipes.localizeLongDate = function(str)
{
	var res = moment.utc(str *1000).local().format('LLLL');
	var str = /^(.+?) [0-9]{1,2}:[0-9]{2}(?: .+)?.*$/.exec(res);
	return str[1];
};
