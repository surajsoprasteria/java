(function(){

	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	    "nulllast-asc": function (str1, str2) {
	        if(str1 == undefined || str1 == null || str1 == "")
	            return 1;
	        if(str2 == undefined || str2 == null || str2 == "")
	            return -1;
	        return ((str1 < str2) ? -1 : ((str1 > str2) ? 1 : 0));
	    },

	    "nulllast-desc": function (str1, str2) {
	        if(str1 == undefined || str1 == null || str1 == "")
	            return 1;
	        if(str2 == undefined || str2 == null || str2 == "")
	            return -1;
	        return ((str1 < str2) ? 1 : ((str1 > str2) ? -1 : 0));
	    }
	} );
	
	(function($) {
	    $.fn.hasScrollBar = function() {
	    	
	    	var vertical = false;
	    	var horizontal = false;
	    	var widthSize = 0;
	    	
	    	if (this && this.get(0)) {
	    		var e = this.get(0);
	    		vertical = e.scrollHeight > e.clientHeight;
	    		horizontal = e.scrollWidth > e.clientWidth;		
	    		
	    		if (vertical || horizontal) {
	    			var $outer = $('<div>').css({visibility: 'hidden', width: 100, overflow: 'scroll'}).appendTo(this),
	    			widthWithScroll = $('<div>').css({width: '100%'}).appendTo($outer).outerWidth();
	    			$outer.remove();
	    			widthSize = 100 - widthWithScroll;
	    		}
	    	}
	    	
	        return {
	            "vertical": vertical,
	            "horizontal": horizontal,
	            "width" : widthSize
	        };
	    }
	})(jQuery);

})();