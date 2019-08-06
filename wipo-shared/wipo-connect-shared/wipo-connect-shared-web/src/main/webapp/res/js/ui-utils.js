function openModalDetail(options){
  options = $.extend({
    "type": "POST",
    "wrapperClass": null,
    "dialogTitle": null,
    "targetDiv": null,
    "showScreenshotButton":false,
    "confirmExitIfChanged":false
  },options);

  if(options.url){
	  $.ajax({
		    "type": options.type,
		    "url":  options.url,
		    "data": options.data,
		    "success": successHandler,
		    "error": errorHandler
	 });
  }else if (options.targetDiv){
	  	var wrapper = $(options.targetDiv);

	  	createDialog(wrapper, options);
  }

  function successHandler(data){
    var wrapper = $("<div>")
              .addClass("modal-detail-content")
              .addClass(options.wrapperClass)
              .append(data);

    createDialog(wrapper, options);

  };

  function errorHandler (data){
	  showErrorMessage({message:globalParams.messages['jsPlugin.dataTable.errorMessage']});
  };

  function dataChangeHandler(container){
	  container.data("confirm-on-exit",true);
  }

  function confirmExit(dialogRef){
	  var $modalDialog = $(dialogRef.getModalDialog());

	  var confirmOnExit = $modalDialog.data("confirm-on-exit");

	  if(!options.confirmExitIfChanged){
		  //return true;
	  }else if($modalDialog.data("force-close") === true){
		  //return true;
	  }else if(!confirmOnExit){
		  //return true;
	  }else{
		  BootstrapDialog.show({
	          closable:false,
			  title: globalParams.messages['global.exit-confirm.title'],
	          message: globalParams.messages['global.exit-confirm.message'],
	          buttons: [{
	              label: globalParams.messages['global.exit-confirm.cancel-exit'],
	              action: function(dialog) {
	                  dialog.close()
	              }
	          }, {
	              label: globalParams.messages['global.exit-confirm.exit-without-saving'],
	              action: function(dialog) {
	            	  $modalDialog.data("force-close", true);
	            	  dialog.close();
	            	  dialogRef.close();
	              }
	          }]
	      });

		  $modalDialog.data("force-close", false);
		  dialogRef.open();
		  BootstrapDialog.dialogs[dialogRef.defaultOptions.id]=dialogRef;
		  return false;
	  }
	  
	  
	  var reloadGridOnExit = $modalDialog.data("reload-grid-on-exit");
	  if(reloadGridOnExit && options.reloadDtTarget) {
		  var tblObj = $(options.reloadDtTarget).DataTable();
		  if(tblObj) tblObj.ajax.reload();		  
	  }
	  
	  var reloadDetailOnExit = $modalDialog.data("reload-dialog-on-exit");
	  if(reloadDetailOnExit && options.reloadDialogTarget) {
		  reloadModalDetail(options.reloadDialogTarget); 
	  }
	  
	  return true;	  
  }

  function createDialog(wrapper,options){
	  var dialogObj = BootstrapDialog.show({
         size: BootstrapDialog.SIZE_WIDE,
         closeByBackdrop: false,
         closeByKeyboard: false,
         title: options.dialogTitle,
         onhide: confirmExit,
         message: wrapper,
         onshown: function(dialogRef){ dialogRef.setData("dialog-loaded", true); }
     });

     dialogObj.srcOptions = options;
     if(options.showScreenshotButton){
    	 var header = dialogObj.getModalHeader();

    	 header.find(".bootstrap-dialog-header").append(
	    	 "<div class='bootstrap-dialog-screenshot-button btn-group pull-right'>" +
	    	 "  <div class='dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" +
	    	 "  </div>" +
	    	 "  <ul class='dropdown-menu'>" +
	    	 "    <li><a data-format='pdf' href='#'>" + globalParams.messages['global.screenshot-pdf'] +  "</a></li>" +
	    	 "    <li><a data-format='jpg' href='#'>" + globalParams.messages['global.screenshot-jpg'] +  "</a></li>" +
	    	 "    <li><a data-format='png' href='#'>" + globalParams.messages['global.screenshot-png'] +  "</a></li>" +
	    	 "  </ul>" +
	    	 "</div>"
    	 );
     }

     setTimeout(function(){
    	 var $modalDialog = $(dialogObj.getModalDialog());
         $modalDialog.on("change", ":input:not(.no-confirm-on-changes)", function(event){ 
        	 if (!$(this).parents().hasClass('no-confirm-on-changes')) dataChangeHandler.call(this, $modalDialog);
         });
         $modalDialog.on("click", "button:not(.no-confirm-on-changes, .dropdown-toggle)", function(event){
        	 if (!$(this).parents().hasClass('no-confirm-on-changes')) dataChangeHandler.call(this, $modalDialog);
         });
     },750);

  }

}

function setModalDataChange(container, change){
	var modalDialog = $(container.getModalDialog());
	modalDialog.data("confirm-on-exit", change);
}

function reloadModalDetail(elementSelector, data){
	  var dialogObj = getParentDialog(elementSelector);
	  
	  if (dialogObj == null) {
		  window.location.reload();
		  return;
	  }
	  
	  var options = jQuery.extend(true, {}, dialogObj.srcOptions);

	  options.data = $.extend(options.data,data);


	  function successHandler(data){
		    var wrapper = $("<div>")
		              .addClass("modal-detail-content")
		              .addClass(options.wrapperClass)
		              .append(data);

		    dialogObj.setMessage(wrapper);
		    dialogObj.setTitle(options.dialogTitle);
		    dialogObj.srcOptions = options;

		    var $modalDialog = $(dialogObj.getModalDialog());
		    $modalDialog.data("confirm-on-exit",false);
	  };

	 function errorHandler (data){
	      var errorMsg = globalParams.messages['global.result-ko'];

	      if(data === Object(data) && data.useGenericError===false){
	    	  errorMsg = data.message;
	    	  if (data.messageList){
	    		  errorMsg = data.messageList;
	    	  }

	    	  if (data.type==="ERROR") {
	    		  showErrorMessage({message:errorMsg});
	    	  } else if (data.type==="WARN") {
	    		  showWarnMessage({message:errorMsg});
	    	  }
	      } else {
	    	  showErrorMessage({message:errorMsg});
	      }
	 };

	 $.ajax({
		    "type": options.type,
		    "url":  options.url,
		    "data": options.data,
		    "success": successHandler,
		    "error": errorHandler
		  });

}

function reloadModalDetailSync(elementSelector, data){
	  var dialogObj = getParentDialog(elementSelector);
	  var options = jQuery.extend(true, {}, dialogObj.srcOptions);

	  options.data = $.extend(options.data,data);


	  function successHandler(data){
		    var wrapper = $("<div>")
		              .addClass("modal-detail-content")
		              .addClass(options.wrapperClass)
		              .append(data);

		    dialogObj.setMessage(wrapper);
		    dialogObj.setTitle(options.dialogTitle);
		    dialogObj.srcOptions = options;
	  };

	 function errorHandler (data){
	      var errorMsg = globalParams.messages['global.result-ko'];

	      if(data === Object(data) && data.useGenericError===false){
	    	  errorMsg = data.message;
	    	  if (data.messageList){
	    		  errorMsg = data.messageList;
	    	  }

	    	  if (data.type==="ERROR") {
	    		  showErrorMessage({message:errorMsg});
	    	  } else if (data.type==="WARN") {
	    		  showWarnMessage({message:errorMsg});
	    	  }
	      } else {
	    	  showErrorMessage({message:errorMsg});
	      }
	 };

	 $.ajax({
		    "type": options.type,
		    "async": false,
		    "url":  options.url,
		    "data": options.data,
		    "success": successHandler,
		    "error": errorHandler
		  });

}

function getParentDialog(elementSelector){
  var dialogId = $(elementSelector).closest("div.modal.bootstrap-dialog").attr("id");
  return 	BootstrapDialog.dialogs[dialogId];
}

function initAjaxForm(selector, options){
  if(!selector){
    alert("initAjaxForm - selector cannot be null");
    return;
  }

  options = $.extend({
    type: "POST",
    iframe: false,
    dataType: null,
    error: function(data,status,xhr,formObj){
      var errorMsg = globalParams.messages['global.result-ko'];


      if(data === Object(data) && data.useGenericError===false){
    	  errorMsg = data.message;
    	  if (data.messageList){
    		  errorMsg = data.messageList;
    	  }

    	  if (data.type==="ERROR") {
    		  showErrorMessage({message:errorMsg});
    	  } else if (data.type==="WARN") {
    		  showWarnMessage({message:errorMsg});
    	  }
      } else {
    	  showErrorMessage({message:errorMsg});
      }

    }
  },options);



  function successHandler (data,status,xhr,formObj){
	if(detectLoginPage(data)){
		redirectToLogin();
	} else if (data === Object(data) && (data.type==="ERROR" || data.type==="WARN")) {
		errorHandler.call(this,data,status,xhr,formObj);
    } else {
      if (typeof options.success === "function"){
        options.success.call(this,data,status,xhr,formObj);
      }
    }
  };

  function errorHandler(data,status,xhr,formObj){
    if(typeof options.error === "function"){
      options.error.call(this,data,status,xhr,formObj);
    }
  };

  $(selector).ajaxForm({
	    type: options.type,
	    iframe: options.iframe,
	    success: successHandler,
	    error: errorHandler,
	    dataType: options.dataType
	  });
}

/**
 * goToUrl
 * @param {String} path
 * @param {String} params
 * @param {String} method
 * @param {String} target
 */
function goToUrl(path, options) {

  options = $.extend({
    "params": null,
    "method": "post",
    "target": null
  },options);

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", options.method);
    form.setAttribute("action", path);

    if(options.target){
      form.setAttribute("target", options.target);
    }

    for(var key in options.params) {
        if(options.params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", options.params[key]);

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}

function clearForm(selector,exclude){
	var inputList;

	if($(selector).not(exclude).is(":input")){
		inputList = $(selector);
	}else{
		inputList = $(selector).find(":input").not(exclude);
	}

	inputList.each(function(i){
		$this = $(this);

		if($this.is(":checkbox , :radio")){
			$this.prop("checked",false).change();
		} else if($this.is(":input:not([type=hidden])")){
			var restoreVal = $this.attr("restoreValue") ? $this.attr("restoreValue") : "";
			
			if($this.data("selectpicker")){
				if (this.multiple == false && restoreVal == "") {
					var firstOption = $this.find("option:first-child").val();
					restoreVal = (restoreVal == firstOption) ? restoreVal : firstOption;
				}
				$this.val(restoreVal);
				$this.selectpicker("refresh");
			} else {
				$this.val(restoreVal);
			}
		}

		$(selector).find("tr.selected").removeClass("selected");

	});

//	$(selector).find(".tag-input").tagsinput('removeAll');

	return inputList;
}

function showErrorMessage (options){
    options = $.extend({
	    "dialogTitle": "Error",
	    "message": "Error loading data",
	    "type": "error",
	    "delay": false
	  },options);

	return showPopupMessage(options);
};

function showWarnMessage (options){
    options = $.extend({
	    "dialogTitle": "Warning",
	    "message": "Warning message",
	    "type": "warning",
	    "delay": false,
	    "width": "auto",
	    "position": "top center"
	  },options);

    return showPopupMessage(options);
};


function showInfoMessage (options){
	options = $.extend({
	    "dialogTitle": "Message",
	    "message": "",
	    "type": "info",
	    "width": "auto",
	    "position": "top center"
	  },options);

	return showPopupMessage(options);
};

function showSuccessMessage (options){
	options = $.extend({
	    "dialogTitle": "Message",
	    "message": "",
	    "type": "success",
	    "width": "auto",
	    "position": "top center"
	  },options);
	return showPopupMessage(options);
};

function showPopupMessage (options){
	options = $.extend({
	    "message": "",
	    "type": "info",
	    "delayIndicator": false,
	    "delay": Lobibox.notify.DEFAULTS.delay,
	    "sound": false
	  },options);

	//if has list message
	if( Object.prototype.toString.call(options.message) === '[object Array]' ) {
		jQuery.each( options.message, function( i, val ) {
			options.message = val;
			showLobibox(options);
		});
		return null;
	} else{
		return showLobibox(options);
	}
};
function showLobibox (options){
	options.msg = options.message;
	return Lobibox.notify(options.type, options);
}

function showConfirmMessage (options){
	options = $.extend({
	    "dialogTitle": globalParams.messages['global.confirm'],
	    "message": globalParams.messages['global.confirm-question'],
	    "closable":false,
	    "type": BootstrapDialog.TYPE_INFO,
	    "size": BootstrapDialog.SIZE_NORMAL,
	    "confirmLabel":globalParams.messages['global.yes'],
	    "confirmAction": function(dialog) {},
	    "cancelLabel":globalParams.messages['global.no'],
	    "cancelAction": function(dialog) {dialog.close();},
	    "numberOfItems": null,
	    "massiveThreshold": globalParams.massiveOperationThreshold,
	    "massiveWarningMessage" : globalParams.messages['global.confirm-massive-warning'],
	    "onCloseAction": function(dialog) {},
	  },options);

	var fullMessage = "<p>" + options.message + "</p>"
	if(options.numberOfItems > options.massiveThreshold){
		fullMessage += "<p>" + options.massiveWarningMessage + "</p>"
	}

	var dialogObj = BootstrapDialog.show({
	     type: options.type,
	     size: options.size ,
	     closeByBackdrop: false,
	     closeByKeyboard: false,
	     closable: options.closable,
	     title: options.dialogTitle,
	     message: fullMessage,
	     onhide: function(dialog){
	    	 	return options.onCloseAction.call(this, dialog);
         },
	     buttons: [{
             label: options.cancelLabel,
             cssClass: "btn-default confirm-action-btn-no",
             action: options.cancelAction
         }, {
        	 label: options.confirmLabel,
        	 cssClass: "btn-default confirm-action-btn-yes",
             action: options.confirmAction
         }]
	    });

	dialogObj.srcOptions = options;
};

function executeAction(options) {
	options = $.extend({
		async: true,
		cache: false,
		global: true,
		url : null,
		hideProgressBar : false,
		timeout : 0,
		method : "POST",
		processData : true,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		error : function(data, status, xhr, formObj) {
			var errorMsg = globalParams.messages['global.result-ko'];

		      if(data === Object(data) && data.useGenericError===false){
		    	  errorMsg = data.message;
		    	  if (data.messageList){
		    		  errorMsg = data.messageList;
		    	  }

		    	  if (data.type==="ERROR") {
		    		  showErrorMessage({message:errorMsg});
		    	  } else if (data.type==="WARN") {
		    		  showWarnMessage({message:errorMsg});
		    	  }
		      } else {
		    	  showErrorMessage({message:errorMsg});
		      }
		},
		complete:null
	}, options);


	function successHandler(data, status, xhr, formObj) {
		if(detectLoginPage(data)){
			redirectToLogin();
		} else if (data === Object(data) && (data.type==="ERROR" || data.type==="WARN")) {
			errorHandler.call(this, data, status, xhr, formObj);
		} else if (typeof options.success === "function") {
			options.success.call(this, data, status, xhr, formObj);
		}
	};

	function errorHandler(data, status, xhr, formObj) {
		if (typeof options.error === "function") {
			options.error.call(this, data, status, xhr, formObj);
		}
	};

	$.ajax({
		method : options.method,
		url : options.url,
		data : options.data,
		success : successHandler,
		error : errorHandler,
		complete : options.complete,
		cache : options.cache,
		async : options.async,
		contentType : options.contentType,
		processData : options.processData,
		global : options.global,
		timeout : options.async ? options.timeout : 60000,
		hideProgressBar : options.hideProgressBar
	});

}

function loadCustomValidationRules(fields, container){
	for(var i=0; i < fields.length; i++){
		var field = fields[i];
		var rule = {};


		//Convert InputBox to Select if there are possibleValues specified
		if (field.isString && field.possibleValues != null && field.possibleValues != "") {
			var mapPossibleValues = new Object();
			var list = field.possibleValues.split('|');
			var inputBoxList = $("input:text.CV_" + field.fieldCode).not("[class*=template] input:text.CV_" + field.fieldCode);

			/* ONLY VALUE implementation */
			$.each(list, function( index, value ) {
				if (value != null && value != "") {
					mapPossibleValues[value] = value;
				}
			});

			/* VALUE and TEXT implementation
			$.each(list, function( index, value ) {
				var item = value.split(':');
				if (item[0] && item[1]) {
					mapPossibleValues[item[1]] = item[0];
				}
			}); */

			$.each(inputBoxList, function( index ) {

				var inputText = $(this).val();

				if (inputText != null && inputText != "") {
					if (!(inputText in mapPossibleValues)) {
						mapPossibleValues[inputText] = inputText;
					}
				}

				var select = $('<select>');
				$(select).attr("class", $(this).attr("class"));
				$(select).attr("name", $(this).attr("name"));
				$(select).attr("id", $(this).attr("id"));
				$(select).addClass("tr-cv");

				if ($(this).prop("readonly")) {
					$(select).addClass("blockField");
				} else if ($(this).prop("disabled")) {
					$(select).addClass("blockField");
				}

				select.append($("<option>"));

				$.each(mapPossibleValues, function( key, val ) {
					var option = $("<option>").attr('value',key).text(val);
					if (key == inputText) {
						option.attr("selected", true);
					}
					select.append(option);
				});

				$(this).replaceWith(select);

			});

			$("select.CV_" + field.fieldCode).not("[class*=template]").selectpicker();
			$("select.disabled.CV_" + field.fieldCode).not("[class*=template]").blockEdit();
			$("select.blockField.CV_" + field.fieldCode).not("[class*=template]").blockEdit();

			field.isString = false;
			field.isList = true;
		}

		if(field.isMandatory || field.isInternational){

			rule.required = {depends:function(element){
				var fieldInfo = $(element).data("validation-info");
				if(!fieldInfo){
					return false;
				}
				var isRequired = false;
				var validType = $(container).find("form").data("validation-type");

				isRequired =  (validType === "LOCAL" && fieldInfo.isMandatory)
						  		||
						  	  (validType === "INTERNATIONAL" && fieldInfo.isInternational)
						  	    ||
						  	  ((validType === "FORMAL" || !validType) && fieldInfo.isForced && fieldInfo.isMandatory);

				return isRequired;
			}};

		}
		if(field.isInteger){
			rule.digits = true;
		}
		if(field.isDecimal){
			rule.number = true;
		}
		if(field.isDate){
			rule.date = true;
		}
		if((field.isInteger || field.isDecimal) && (field.minValue != "" && field.maxValue != "")){
			rule.range = [field.minValue , field.maxValue];
		}else if((field.isInteger || field.isDecimal) && field.minValue != ""){
			rule.min = field.minValue;
		}else if((field.isInteger || field.isDecimal) && field.maxValue != ""){
			rule.max = field.maxValue;
		}
		if(field.isString && field.maxLength != ""){
			rule.maxlength = field.maxLength;
		}
		if(field.isString && field.regularExpression != ""){
			rule.pattern = {"pattern":field.regularExpression, flags:"i"};
		}
		if((field.isString || field.isFile) && field.regularExpression != ""){
			rule.pattern = {"pattern":field.regularExpression, flags:"i"};
		}
		if(field.isFile && field.maxFileSize != ""){
			rule.maxFileSize = field.maxFileSize;
		}

		var inputList = $(container).find(":input:not(.dyn-rule).CV_" + field.fieldCode + ", :input.dyn-rule-multi.CV_" + field.fieldCode);
		inputList.each(function(){ //TODO: merge multiple data
			$(this).data("validation-rule",rule);
			$(this).data("validation-info",field);
			$(this).addClass("dyn-rule").rules("add", rule);
		});

	}
}

function loadDynamicFieldsRules(fields, container, controllerPath){
	for(var i=0; i < fields.length; i++){
		var field = fields[i];
		var rule = {};

		//console.info("field: ",field.fieldCode,field);

		if(field.isMandatory){
			rule.required = {depends:function(element){
				var fieldInfo = $(element).data("validation-info");
				if(!fieldInfo){
					return false;
				}
				var isRequired = false;
				var validType = $(container).find("form").data("validation-type");

				if(validType === "FORMAL" || !validType){
					isRequired = false;
				}else{
					isRequired =  validType === "LOCAL" && fieldInfo.isMandatory;
				}

				return isRequired;
			}};
		}
		if(field.isInteger){
			rule.digits = true;
		}
		if(field.isDecimal){
			rule.number = true;
		}
		if(field.isDate){
			rule.date = true;
		}
		if((field.isInteger || field.isDecimal) && (field.minValue != "" && field.maxValue != "")){
			rule.range = [field.minValue , field.maxValue];
		}else if((field.isInteger || field.isDecimal) && field.minValue != ""){
			rule.min = field.minValue;
		}else if((field.isInteger || field.isDecimal) && field.maxValue != ""){
			rule.max = field.maxValue;
		}
		if(field.isString && field.maxLength != ""){
			rule.maxlength = field.maxLength;
		}
		if(field.isUnique){
			rule.remote = function(element){
				var dynFieldObj = $(element).closest("div.form-group").find(":input").serializeObject();
				var dataObj = {};
				$.each(dynFieldObj,function(k,v){
					dataObj[k.substr(k.indexOf(".") + 1)] = v;
				});
				//console.log("dynVal", dataObj);

				return	{
					url: globalParams.ctxMvc + "/" + controllerPath + "/checkDynamicFieldUniqueness.json",
			        type: "post",
			        data: dataObj
			  };
			};
		}

		var inputList = $(container).find("#" + field.elementId + ":input:not(.dyn-rule)");
		inputList.each(function(){ //TODO: merge multiple data
			$(this).data("validation-rule",rule);
			$(this).data("validation-info",field);
			$(this).addClass("dyn-rule").rules("add", rule);
		});
	}
}

function getReadableFileSize(size) {
	var suffix = ["bytes", "KB", "MB", "GB", "TB", "PB"],
		tier = 0;

	while(size >= 1024) {
		size = size / 1024;
		tier++;
	}

	return Math.round(size * 10) / 10 + " " + suffix[tier];
}

function getMassiveButtonStatus(table,btn){
	var checked = getSelectedNodes(table);
	var countTot = checked.size();
	var countDis = 0;
	var tblObj = $(table).dataTable().api();
	checked.each(function(){
		var row = $(this).closest("tr");
		if(row.find(btn).is(":disabled")){
			countDis++;
		}
	});
	return (countTot > 0 && countDis==0);
}

function getMassiveButtonStatusByCount(table,btn,countToValid){
	var checked = getSelectedNodes(table);
	var countTot = checked.size();
	var countDis = 0;
	var tblObj = $(table).dataTable().api();
	checked.each(function(){
		var row = $(this).closest("tr");
		if(row.find(btn).is(":disabled")){
			countDis++;
		}
	});
	return (countTot == countToValid && countDis==0);
}

function refreshCodeMirrorInstances(container){
	var cmList = container.find(".CodeMirror");
	cmList.each(function(){
		if(this.CodeMirror && typeof this.CodeMirror === "object"){
			this.CodeMirror.refresh();
		}
	});
}

function updateCodeMirrorContent(container){
	var cmList = container.find(".CodeMirror");
	cmList.each(function(){
		if(this.CodeMirror && typeof this.CodeMirror === "object"){
			this.CodeMirror.save();
		}
	});
}

var NumericConversionUtils = {
		
		integerField : $("<input>").autoNumeric("init", {decimalPlacesOverride:0}) ,
		decimalField : $("<input>").autoNumeric("init") ,
		decimalVarField : $("<input>").autoNumeric("init"),

		parse: function(value){
			if(value == null){
				return value;
			}
			
			var aux = this.decimalField.val(value).autoNumeric('getNumber');
			return parseFloat(aux);
		},

		parseInteger: function(value){
			if(value == null || value == ""){
				return value;
			}

			var aux = this.integerField.val(value).autoNumeric('getNumber');
			return parseInt(aux, 10);

		},

		parseAmount: function(value){
			return this.parse(value);
		},

		formatWithScale: function(value,scale){
			if(value == null){
				return value;
			}
			
			this.decimalVarField.autoNumeric("update",{decimalPlacesOverride:scale});
			return this.decimalVarField.autoNumeric("set",value).autoNumeric('getFormatted');
		},

		format: function(value){
			if(value == null){
				return value;
			}

			return this.decimalField.autoNumeric("set",value).autoNumeric('getFormatted');
		},

		formatInteger: function(value){
			if(value == null){
				return value;
			}

			return this.integerField.autoNumeric("set",value).autoNumeric('getFormatted');
		},

		formatAmount: function(value){
			var aux = this.format(value);

			if(globalParams.isCurrencyBefore){
				return globalParams.currencySymbol + " " + aux;
			}else{
				return aux + " " + globalParams.currencySymbol;
			}

		},

		formatAmountWithScale: function(value,scale){
			var aux = this.formatWithScale(value,scale);

			if(globalParams.isCurrencyBefore){
				return globalParams.currencySymbol + " " + aux;
			}else{
				return aux + " " + globalParams.currencySymbol;
			}

		},

		checkFormat: function(value){
			var re = new RegExp(globalParams.numberRegexCheck);
			return re.test(value);
		},

		checkDigitFormat: function(value){
			var re = new RegExp(globalParams.digitRegexCheck);
			return re.test(value);
		},

		secondsToHMs: function(d) {
			if (d == null || d === "") return null;
			d = Number(d);
		    var h = Math.floor(d / 3600);
		    var m = Math.floor(d % 3600 / 60);
		    var s = Math.floor(d % 3600 % 60);			    
		    return (h > 0 ? h+':' : '')+((h > 0 || m > 0) ? (m < 10 && h > 0 ? '0'+m : m) + ':' : '')+(s < 10 && m > 0 ? '0'+s : s);
		},
		hMsToSeconds: function(hms) {
			var re = new RegExp(globalParams.timeRegexCheck);			
			var a = re.test(hms) ? hms.split(':') : [];
			if (a.length == 3) {
				return (+a[0]) * 60 * 60 + (+a[1]) * 60 + (+a[2]);
			} else if (a.length == 2) {
				return (+a[0]) * 60 + (+a[1]);
			} else if (a.length == 1) {
				return (+a[0]);
			}
			return null;
		}
};



function applyAutoNumeric($obj) {
	if ($obj && $obj instanceof jQuery && !$obj.hasClass("autoNumeric")) {
		if ($obj.hasClass("auto-decimal")) {
			$obj.autoNumeric("init").addClass('autoNumeric');
		} else if ($obj.hasClass("auto-decimal-5")) {
			$obj.autoNumeric("init",{decimalPlacesOverride:5}).addClass('autoNumeric');
		} else if ($obj.hasClass("auto-decimal-6")) {
			$obj.autoNumeric("init",{decimalPlacesOverride:6}).addClass('autoNumeric');
		} else if ($obj.hasClass("auto-integer")) {
			$obj.autoNumeric("init", {decimalPlacesOverride:0}).addClass('autoNumeric');
		}
	} else if ($obj && $obj instanceof jQuery && $obj.hasClass("autoNumeric")) {
		$obj.autoNumeric("update");
	} else {
		$("input.auto-decimal:not(.autoNumeric)").autoNumeric("init").addClass('autoNumeric');
		$("input.auto-decimal-5:not(.autoNumeric)").autoNumeric("init",{decimalPlacesOverride:5}).addClass('autoNumeric');
		$("input.auto-decimal-6:not(.autoNumeric)").autoNumeric("init",{decimalPlacesOverride:6}).addClass('autoNumeric');
		$("input.auto-integer:not(.autoNumeric)").autoNumeric("init", {decimalPlacesOverride:0}).addClass('autoNumeric');
	}
}

function applyAutoDateTime() {
	$("input.auto-time:not(.autoDateTime)").each(function() {
		var second = NumericConversionUtils.parseInteger($(this).val());
		$(this).data('dateTime', second);
		$(this).val(NumericConversionUtils.secondsToHMs(second));
		$(this).mask('##:09:09', {
			reverse: true, 
			onChange: function(cep, event, element){
				$(element).data('dateTime', NumericConversionUtils.hMsToSeconds(cep));
			}
		}).addClass('autoDateTime');
	});
}

function getTableSelection(tableSelector, forceAllRows){
	var $table;
	if(tableSelector instanceof jQuery){
		$table = tableSelector;
	}else{
		$table = $(tableSelector);
	}

	var $chkAll = $table.find(":checkbox.select-all-rows");
	var $data;

	if(forceAllRows == null){
		forceAllRows = $chkAll.prop("checked");
	}

	if(forceAllRows){
		$data = $table.dataTable().api().rows({filter:"applied"});
	}else{
		$data = $table.dataTable().api().rows(".selected",{filter:"applied"});
	}

	return $data;
}

function getSelectedRows(tableSelector, forceAllRows){
	var $data = getTableSelection(tableSelector, forceAllRows);
	var sel;

	if($data && $data.data()){
		sel = $data.data().toArray();
	}else{
		sel = [];
	}
	return $(sel);
}

function getSelectedNodes(tableSelector, forceAllRows){
	var $data = getTableSelection(tableSelector, forceAllRows);
	var sel;

	if($data && $data.nodes()){
		sel = $data.nodes().toArray();
	}else{
		sel = [];
	}
	return $(sel);
}

function guid() {
	function s4() {
		return Math.floor((1 + Math.random()) * 0x10000).toString(16)
				.substring(1);
	}
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4()
			+ s4() + s4();
}

function isJson(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}

function detectLoginPage(data){
	if(!typeof data === "string"){
		return false;
	}

	if(isJson(data)){
		return false;
	}

	var $data = $(data);
	var loginCheck = $data.find(".container-login :input.login-check");
	if(loginCheck.size() != 1){
		return false;
	}

	var loginCheckVal = loginCheck.val();
	if(loginCheckVal !== "true"){
		false;
	}

	return true;
}

function redirectToLogin(){
	window.location.replace(globalParams.loginUrl);
}

function saveScreenshot(element,format,filename){
	var typeMap = {
			"jpg":"image/jpeg",
			"png":" image/png"
	};
	var compression = 0.9;
	if(format){
		format = format.toLowerCase();
	}else{
		format = "png";
	}

	filename = filename || moment().format('YYYYMMDDHHmmss') + "." + format;
	$("body").height("10000px");
	
	html2canvas(element, {logging:false}).then(function(canvas) {
		$("body").height("");
    	if(format === "pdf"){
    		 var a4Ratio = 210/297;
    		 var elemHeight = $(element).outerHeight();
    		 var elemWidth = $(element).outerWidth();
    		 var aspectRatio = elemHeight / elemWidth;
    		 var pageDirection = (aspectRatio >= a4Ratio) ? "p" : "l";
       		 var pdf = new jsPDF(pageDirection, "mm", "a4");
       		 var img = canvas.toDataURL("image/jpeg", compression);
       		 var width = pdf.internal.pageSize.width;
       		 var height = aspectRatio * width;
       		 pdf.addImage(img, 'jpeg', 0, 0, width, height);
       		 pdf.save(filename);
       	}else{
       		canvas.toBlob(function(blob) {
       			saveAs(blob, filename);
       		},typeMap[format],compression);
       	}
	});
}


/** cache ajax request with option cache: true 			**/
/** use localStorage.removeItem(url) to remove cache 	**/
$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
	if (options.cache) {
	    var success = originalOptions.success || $.noop, url = originalOptions.url;

	    options.cache = false; //remove jQuery cache as we have our own localStorage
	    options.beforeSend = function () {
	        if (sessionStorage.getItem(url)) {
	        	
	        	var parsedData;
	        	try {
	        		parsedData = JSON.parse(sessionStorage.getItem(url));
	        	} catch(e) {
	        		parsedData = sessionStorage.getItem(url);
	        	}
	        	
	            success(parsedData);
	            return false;
	        }
	        return true;
	    };
	    options.success = function (data, textStatus) {
	    	sessionStorage.setItem(url, JSON.stringify(data));
	        if ($.isFunction(success)) success(data); //call back to original ajax call
	    };
	}
});


function downloadFile(url, dataObj, blockUI){
	
	dataObj = typeof dataObj !== 'undefined' ? dataObj : {};
	blockUI = typeof blockUI !== 'undefined' ? blockUI : true;
	
	if (blockUI) $.blockUI();

	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.responseType = 'arraybuffer';
	xhr.onerror = function() { showErrorMessage({message:globalParams.messages['global.download-error']}); $.unblockUI(); };
	xhr.ontimeout = function() { showErrorMessage({message:globalParams.messages['global.download-error']}); $.unblockUI(); };
	xhr.onabort = function() { showErrorMessage({message:globalParams.messages['global.download-error']}); $.unblockUI(); };
	xhr.onload = function () {
	    if (this.status === 200) {
	    	
	    	var checkResponse = document.createElement('a');
	    	checkResponse.href = this.responseURL;	    	
	    	if (checkResponse.pathname != url) {
	    		console.debug("File not downloaded");
	    		window.location = this.responseURL;
	    		return;
	    	}
	    	
	        var filename = "";
	        var disposition = xhr.getResponseHeader('Content-Disposition');
	        if (disposition && disposition.indexOf('attachment') !== -1) {
	            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
	            var matches = filenameRegex.exec(disposition);
	            if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
	        }
	        var type = xhr.getResponseHeader('Content-Type');

	        var blob = typeof File === 'function'
	            ? new File([this.response], filename, { type: type })
	            : new Blob([this.response], { type: type });
	        if (typeof window.navigator.msSaveBlob !== 'undefined') {
	            // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
	            window.navigator.msSaveBlob(blob, filename);
	        } else {
	            var URL = window.URL || window.webkitURL;
	            var downloadUrl = URL.createObjectURL(blob);

	            if (filename) {
	                // use HTML5 a[download] attribute to specify filename
	                var a = document.createElement("a");
	                // safari doesn't support this yet
	                if (typeof a.download === 'undefined') {
	                    window.location = downloadUrl;
	                } else {
	                    a.href = downloadUrl;
	                    a.download = filename;
	                    document.body.appendChild(a);
	                    a.click();
	                }
	            } else {
	                window.location = downloadUrl;
	            }

	            setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
	            console.debug("File downloaded successfully");
	        }
	    } else {
	    	showErrorMessage({message:globalParams.messages['global.download-error']});
	    }
	    
	    $.unblockUI();
	};
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.send($.param(dataObj));
	
}