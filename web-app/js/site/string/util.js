String.prototype.zeroPad = function(places) {
  var zero = places - this.toString().length + 1;
  return Array(+(zero > 0 && zero)).join("0") + this;
};

String.prototype.toUpperCaseFirst = function() {
	return this.slice(0,1).toUpperCase() + this.slice(1).toLowerCase();
};