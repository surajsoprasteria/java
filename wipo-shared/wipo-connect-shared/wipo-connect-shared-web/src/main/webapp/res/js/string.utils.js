/*
 * STRING UTILS
 * */


(function(){

	function escapeRegExp(str) {
	    return str.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
	}

	String.prototype.replaceAll = function(search, replacement) {
	    var target = this;
	    return target.replace(new RegExp(escapeRegExp(search), 'g'), replacement);
	};

	String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	      ? args[number]
	      : '{' + number + '}'
	    ;
	  });
	};

	// endWith
	String.prototype.endsWith = function(suffix) {
	    return (this.indexOf(suffix, this.length - suffix.length) !== -1);
	};

	//startsWith
	String.prototype.startsWith = function(prefix) {
		return (this.indexOf(prefix) === 0);
	};

	//contains
	String.prototype.contains = function(subStr) {
		return (this.indexOf(subStr) !== -1);
	};


	String.prototype.htmlEncode = function() {
		var str = this;
		var i = str.length, aRet = [];

		while (i--) {
			var iC = str[i].charCodeAt();
			if (iC < 65 || iC > 127 || (iC > 90 && iC < 97)) {
				aRet[i] = '&#' + iC + ';';
			} else {
				aRet[i] = str[i];
			}
		}
		return aRet.join('');
	};

	String.prototype.trim = function() {
		var ret = this;

		if(this != null){
			ret = this.replace(/^\s+|\s+$/g, '');
		}

		return ret;
	};

	String.prototype.trimToDefault = function(def) {
		return this.trim() == "" ? def : this.trim();
	};

	String.prototype.trimToNull = function() {
		return this.trimToDefault(null);
	};
})();

function compareCaseInsensitive(a, b){
  var s1 = a.toLowerCase();
  var s2 = b.toLowerCase();
  return ((s1 < s2) ? -1 : ((s1 > s2) ? 1 : 0));
}

function htmlDecode(html) {
    var textarea = document.createElement("textarea");
    html= html.replace(/\r/g, String.fromCharCode(0xe000)); // Replace "\r" with reserved unicode character.
    textarea.innerHTML = html;
    var result = textarea.value;
    return result.replace(new RegExp(String.fromCharCode(0xe000), 'g'), '\r');
}

function escapeHtml(str) {
	
	if (typeof str !== 'string' && !(str instanceof String)) {
		return str;
	}
	
	var entityMap = {
		'&' : '&amp;',
		'<' : '&lt;',
		'>' : '&gt;',
		'"' : '&quot;',
		"'" : '&#39;',
		'/' : '&#x2F;',
		'`' : '&#x60;',
		'=' : '&#x3D;'
	};

	str = htmlDecode(str);
	
	return String(str).replace(/[&<>"'`=\/]/g, function(s) {
		return entityMap[s];
	});
}