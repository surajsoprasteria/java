/**
 * WCC INPUT 2 SPAN V 0.2
 **/

(function($) {


  var methods = {
      "init" : init
  };

  var pluginName = "input2Span";
  var exclude = ".no-span";
  var baseSelector = "input[type='date'], input[type='datetime'], input[type='datetime-local'], input[type='email']," +
					" input[type='month'], input[type='number'], input[type='range']," +
					" input[type='search'], input[type='tel'], input[type='text']," +
					" input[type='time'], input[type='url'], input[type='week']," +
					" textarea, select";


	function init(){
		var $this = this;

		var inputList;
		var labelList;

		// get the element list to block
		if($this.is(baseSelector)){
			inputList = $this.not(exclude);
		}else{
			inputList = $this.find(baseSelector).not(exclude);
		}

		inputList.each(function(){
			var $this = $(this);
			initEle($this);
			toSpan($this);
		});

		return $this;
	}

	function initEle(ele){
		var $this = ele;
		var data = $this.data(pluginName);

		if(!data){
			data = {};

			data.isVisible = $this.is(":visible");
			data.isDisabled = $this.prop("disabled") || $this.is(".btn.disabled");
			data.isReadonly = $this.prop("readonly");
			$this.data(pluginName, data);
		}
	}

	function toSpan(ele){
		var $this = ele;
		var data = $this.data(pluginName);
		if(data){

			var textVal = "";


			if($this.is("input[type='date'], input[type='datetime'], input[type='datetime-local'], input[type='email']," +
					" input[type='month'], input[type='number'], input[type='range']," +
					" input[type='search'], input[type='tel'], input[type='text']," +
					" input[type='time'], input[type='url'], input[type='week']," +
					" textarea")){

				textVal = $this.val();
			} else if($this.is("select")) {
				$this.find("option:selected").each(function() {
					textVal += ", " + $(this).text();
				});
				textVal = textVal.replace(", ","");
				
				if ($this.data("selectpicker")) $this = $this.parent();
			} else {
				console.error(pluginName,"Invalid element",$this);
			}


			//hide the current element
			$this.addClass("hidden");
			if($this.next(".input-group-addon").size()){
				$this.next(".input-group-addon").addClass("hidden");
			}

			//create span tag to replace the input
			$span = $("<span>")
						.addClass("form-control")
						.addClass(pluginName)
						.text(textVal);

			if($this.is("textarea")){
				var auxTxt = $span.html();
				if(auxTxt){
					auxTxt = auxTxt.replaceAll("\n","<br>");
					$span.html(auxTxt);
					$span.css("height",$this.actual("outerHeight"));
					$span.css("overflow","auto");
				}
			}

			$span.insertAfter($this);
			$span = $this.nextAll("span."+pluginName);

			if(isEllipsisActive($span)){
				$span.attr("title", textVal);
			}



			//data information
			$span.data(pluginName,{element:$this});
			data.span = $span;
			$this.data(pluginName,data);
		}
	}

	function isEllipsisActive(e) {
	    // calculate the width of the clone
	    var offsetWidth = e.actual("offsetWidth", {useHtmlProp:true});
	    var scrollWidth = e.actual("scrollWidth", {useHtmlProp:true});

	    return offsetWidth < scrollWidth;
	}



  $.fn[pluginName] = function(method) {
    // Method calling logic
    if (methods[method]) {
      return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
    } else if (typeof method === 'object' || !method) {
      return methods.init.apply(this, arguments);
    } else {
      $.error('Method ' + method + ' does not exist on jQuery.'+pluginName);
    }

  };

})(jQuery);
