/**
 * WCC BLOCK EDIT V 0.22
 **/

(function($) {


  var methods = {
      "init" : block,
      "block" : block,
      "unblock" : unblock
  };

  var exclude = ".no-block , :button.selectpicker";


  function block() {
    var $this = this;

    var inputList;
    var labelList;

    // get the element list to block
    if($this.is(":input, .btn")){
      inputList = $this.not(exclude);
    }else{
      inputList = $this.find(":input, .btn").not(exclude);
    }

    inputList.each(function(){
      var $this = $(this);
      initEle($this);
      blockEle($this);
    });

    return $this;
  }

  function unblock() {
	var $this = this;
    var inputList;

    // get the element list to unblock
    if($this.is(":input, .btn")){
      inputList = $this;
    }else{
      inputList = $this.find(":input, .btn");
    }

    inputList.each(function(){
      var $this = $(this);
      unblockEle($this);
    });

    return $this;
  }

  function initEle(ele){
    var $this = ele;
    var data = $this.data('blockEdit');

    if(!data){
      data = {};

      data.isVisible = $this.is(":visible");
      data.isDisabled = $this.prop("disabled") || $this.is(".btn.disabled");
      data.isReadonly = $this.prop("readonly");
      data.isBlocked = false;
      $this.data('blockEdit', data);
    }
  }

  function blockEle(ele){
    var $this = ele;
    var data = $this.data('blockEdit');

    if(data){
      if(!data.isBlocked && !data.isDisabled && !data.isReadonly){
        if($this.is("select , :checkbox , :radio , :button , input[type=file]")){
          $this.prop("disabled",true);

          var hid = $("<input>")
                .attr("type","hidden")
                .attr("name",$this.attr("name"))
                .addClass("block-val")
                .val($this.val());
          
          if(!$this.is(":checkbox") || $this.prop('checked')) {
        	  hid.val($this.val());
          }

          data.hiddenEle = $this.after(hid).next();
        }else{
          $this.prop("readonly",true);
        }

        $this.addClass("disabled").addClass("blocked")
        data.isBlocked = true;

        //block label click
        $this.parent("label").on("click.blockEdit",disableClickHandler);
        if($this.attr("id")){
          $("label[for='"+$this.attr("id")+"']").on("click.blockEdit",disableClickHandler);
        }

        var selectpickerData = $this.data("selectpicker");
        if(selectpickerData){
        	$this.selectpicker('refresh');
        	data.isSelectPicker = true;
        }

//        var datepickerData = $this.parent(".input-group.date").data("datepicker");
//        if(datepickerData){
//        	$this.parent(".input-group.date").datepicker("remove");
//        	data.isDatePicker = true;
//        }
        var btnClass = $this.is(".btn");
        if(btnClass){
        	$this.addClass("disabled");
        }

        var bsToggleData = $this.data("bs.toggle");
        if(bsToggleData){
        	$this.bootstrapToggle("disable");
        	data.isBsToggleData = true;
        }
        
        //bootstrap tags input
        $this.parents('.bootstrap-tagsinput').addClass('disabled');

        //save plugin data
        $this.data('blockEdit', data);
      }
    }

  }

  function unblockEle(ele){
    var $this = ele;
    var data = $this.data('blockEdit');

    if(data){
      if(data.isBlocked){
    	if($this.is("select , :checkbox , :radio , :button , input[type=file]")){
          $this.prop("disabled",false);
          data.hiddenEle.remove();
          data.hiddenEle =  null;
        }else{
          $this.prop("readonly",false);
        }

        $this.removeClass("disabled").removeClass("blocked")
        data.isBlocked = false;

        //unblock label click
        $this.parent("label").off(".blockEdit");
        if($this.attr("id")){
          $("label[for='"+$this.attr("id")+"']").off(".blockEdit");
        }

        if(data.isSelectPicker){
        	$this.selectpicker('refresh');
        }

//        if(data.isDatePicker){
//        	$this.parent(".input-group.date").datepicker();
//        }
        var btnClass = $this.is(".btn");
        if(btnClass){
        	$this.removeClass("disabled");
        }

        if(data.isBsToggleData){
        	$this.bootstrapToggle("enable");
        }
        
        //bootstrap tags input
        $this.parents('.bootstrap-tagsinput').removeClass('disabled');

        //save plugin data
        $this.data('blockEdit', data);
      }
    }
  }

  function disableClickHandler(event){
    event.preventDefault();
  }


  $.fn.blockEdit = function(method) {
    // Method calling logic
    if (methods[method]) {
      return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
    } else if (typeof method === 'object' || !method) {
      return methods.init.apply(this, arguments);
    } else {
      $.error('Method ' + method + ' does not exist on jQuery.blockEdit');
    }

  };

})(jQuery);
