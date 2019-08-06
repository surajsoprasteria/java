/**
 * WIPO-Connect collapsiblePanel V 0.4
 **/

(function($) {


  var methods = {
      "init" : init
  };

  function init(){
	  var $elements = this;

	  if(!$elements.is("div.panel")){
		  $elements = $elements.find("div.panel");
	  }

	  $elements.each(function(){
			var $panel = $(this);
			var $header = $panel.find("> .panel-heading");
			var $body = $panel.find("> .panel-body");
			var linked = ($panel.data("linked-panel")) ? $panel.data("linked-panel") : "";

			var data = $panel.data("collapsible-panel");

			if(!data){
				$panel.addClass("collapsible-panel");

				var $btn=$("<a class='btn btn-xs btn-default btn-toggle-panel'><span class='glyphicon glyphicon-chevron-up'></span></a>");
				$header.prepend($btn);

				data = {
						"button" : $btn,
						"panel" : $panel,
						"header" : $header,
						"body" : $body,
						"linked" : linked
				};

				$btn.click(function(){
					toggleHandler(data);
				});

				$panel.data("collapsible-panel",data);

				if($panel.hasClass("collapsed")){
					$panel.removeClass("collapsed");
					$(linked).removeClass("collapsed");
					toggleHandler(data,true);
				}
			}
		});

	  return this;
  }

  function toggleHandler(data,disableAnimation){
		var $btn = data.button;
		var $panel = data.panel;
		var $header = data.header;
		var $body = data.body;
		var linked = data.linked;

		$panel.toggleClass("collapsed");
		$(linked).toggleClass("collapsed");

		var isCollapsed = $panel.is(".collapsed");

		if(isCollapsed){			
			if(!disableAnimation){
				$body.slideUp();
				$(linked).find("> .panel-body").slideUp();
			}else{
				$body.hide();
				$(linked).find("> .panel-body").hide();
			}
			$btn.find("span.glyphicon").addClass("glyphicon-chevron-down").removeClass("glyphicon-chevron-up");
			$(linked).find(".btn-toggle-panel span.glyphicon").addClass("glyphicon-chevron-down").removeClass("glyphicon-chevron-up");
		}else{
			if(!disableAnimation){
				$body.slideDown();
				$(linked).find("> .panel-body").slideDown();
			}else{
				$body.show();
				$(linked).find("> .panel-body").show();
			}
			$btn.find("span.glyphicon").addClass("glyphicon-chevron-up").removeClass("glyphicon-chevron-down");
			$(linked).find(".btn-toggle-panel span.glyphicon").addClass("glyphicon-chevron-up").removeClass("glyphicon-chevron-down");
		}

		$panel.trigger("collapsible.change",isCollapsed);
		$(linked).trigger("collapsible.change",isCollapsed);
  }


  $.fn.collapsiblePanel = function(method) {
    // Method calling logic
    if (methods[method]) {
      return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
    } else if (typeof method === 'object' || !method) {
      return methods.init.apply(this, arguments);
    } else {
      $.error('Method ' + method + ' does not exist on jQuery.collapsiblePanel');
    }

  };

})(jQuery);
