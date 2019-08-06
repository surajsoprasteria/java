function escapeRegExp(str) {
	    return str.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
	}
function replaceAll(str, search, replacement) {
	var target = str || this;
	return target.replace(new RegExp(escapeRegExp(search), 'g'), replacement);
};
String.prototype.replaceAll = replaceAll;