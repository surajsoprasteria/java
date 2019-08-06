(function(){
	$.fn.serializeObject = function() {
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
})();


(function ( $ ) {
    $.fn.serializeIndex = function() {
        var newParams = {};
		$.each( this.serializeObject(), function( key, value ) {
			if ($.isArray(value)) {
				$.each( value, function( i, v ) {
					newParams[key + "[" + i + "]"] = v;
                });
            } else {
				newParams[key] = value;
            }
        });

        return newParams;
    }
}( jQuery ));


(function ( $ ) {
    $.fn.serializeArrayFlat = function() {
    	
    	var obj = this[0] || {};
    	var newParams = [];
    	
    	
    	$.each(obj, function( key, value ) {
    		if ($.isArray(value)) {
				$.each( value, function( i, v ) {
					newParams.push({'name':key, 'value':v});
                });
            } else {
            	newParams.push({'name':key, 'value':value});
            }
        });
    	
        return newParams;
    }
}( jQuery ));
