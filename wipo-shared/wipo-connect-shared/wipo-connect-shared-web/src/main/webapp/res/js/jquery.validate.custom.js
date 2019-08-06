/*!
 * jQuery Validation Plugin - Custom
 *
 * Author: Andrea Fumagalli
 *
 * Custom:
 * - Message template
 * - Remote: type text , elementValue
 * - showError: fix remote issue
 * - reenabled readonly fields validation
 */



/*
  Message template example:

  $.validator.setDefaults({
		msgPlaceholder : "%MSG%",
		msgTemplate : "<span class='icon glyphicon glyphicon-alert' title='%MSG%'></span>"
	});
 */

$.validator.prototype.showLabel_inner = $.validator.prototype.showLabel;
$.validator.prototype.showLabel = function( element, message ) {
	var validator = this;
	var msgPlaceholder = validator.settings.msgPlaceholder || "";
	var msgTemplate = validator.settings.msgTemplate || "";
	var re = new RegExp(msgPlaceholder,"g");

	message = message || "";
	message = msgTemplate.replace(re,message);

	return validator.showLabel_inner.call(validator,element,message);
};

$.validator.prototype.elements = function() {
	var validator = this,
		rulesCache = {};

	// select all valid inputs inside the form (no submit or reset buttons)
	return $( this.currentForm )
	.find( "input, select, textarea" )
//	.not( ":submit, :reset, :image, [disabled], [readonly]" )
	.not( ":submit, :reset, :image, [disabled]")  // reenabled readonly fields validation
	.not( this.settings.ignore )
	.filter( function() {
		if ( !this.name && validator.settings.debug && window.console ) {
			console.error( "%o has no name assigned", this );
		}

		// select only the first element for each name, and only those with rules specified
		if ( this.name in rulesCache || !validator.objectLength( $( this ).rules() ) ) {
			return false;
		}

		rulesCache[ this.name ] = true;
		return true;
	});
};


$.validator.prototype.showErrors = function ( errors ) {
	if ( errors ) {
		// add items to error list and map
		$.extend( this.errorMap, errors );
		this.errorList = this.errorList || [];  //THIS FIX THE REMOTE VALIDATION ISSUE
		for ( var name in errors ) {
			this.errorList.push({
				message: errors[ name ],
				element: this.findByName( name )[ 0 ]
			});
		}
		// remove items from success list
		this.successList = $.grep( this.successList, function( element ) {
			return !( element.name in errors );
		});
	}
	if ( this.settings.showErrors ) {
		this.settings.showErrors.call( this, this.errorMap, this.errorList );
	} else {
		this.defaultShowErrors();
	}
};

$.validator.addMethod("remote", function( value, element, param ) {
	if ( this.optional( element ) ) {
		return "dependency-mismatch";
	}

	var previous = this.previousValue( element ),
		validator, data;

	if (!this.settings.messages[ element.name ] ) {
		this.settings.messages[ element.name ] = {};
	}
	previous.originalMessage = this.settings.messages[ element.name ].remote;
	this.settings.messages[ element.name ].remote = previous.message;

	param = typeof param === "string" && { url: param } || param;

	if ( previous.old === value ) {
		return previous.valid;
	}

	previous.old = value;
	validator = this;
	this.startRequest( element );
	data = {};
	data[ element.name ] = value;
	data[ 'elementValue' ] = value; // current element value (if you don't know elemnt name on server side)
	$.ajax( $.extend( true, {
		url: param,
		mode: "abort",
		port: "validate" + element.name,
		dataType: "text",  // To avoid parsingError when the server returns a string
		data: data,
		context: validator.currentForm,
		success: function( response ) {
			var valid = response === true || response === "true",
				errors, message, submitted;

			validator.settings.messages[ element.name ].remote = previous.originalMessage;
			if ( valid ) {
				submitted = validator.formSubmitted;
				validator.prepareElement( element );
				validator.formSubmitted = submitted;
				validator.successList.push( element );
				delete validator.invalid[ element.name ];
				validator.showErrors();
			} else {
				errors = {};
				message = response || validator.defaultMessage( element, "remote" );
				errors[ element.name ] = previous.message = $.isFunction( message ) ? message( value ) : message;
				validator.invalid[ element.name ] = true;
				validator.showErrors( errors );
			}
			previous.valid = valid;
			validator.stopRequest( element, valid );
		}
	}, param ) );
	return "pending";
});


//custom fincons methods
$.validator.addMethod("maxFileSize", function(value, element, params) {

	var isValid;
	var fileList;
	fileList = $(element).prop("files");

	isValid = true;
	if(fileList && fileList[0] && fileList[0].size){
		isValid = fileList[0].size <= params;
	}

	return this.optional(element) || isValid;

}, function(params){
	var message = "Please choose a file smaller than " + getReadableFileSize(params);
	return message;
});

$.validator.addMethod("date", function(value, element) {
	var validator = this;
	var format = validator.settings.dateFormat;
	return this.optional(element) || moment(value,format,true).isValid();
}, "Please enter a valid date.");

$.validator.addMethod("dateGE", function(value, element, otherElement) {
    var startDt = value;
    var endDt = $(otherElement).val();
    var dtFormat = momentDateFormat || "DD/MM/YYYY";

    var isValid = true;

    if(moment(startDt,dtFormat).isValid() && moment(endDt,dtFormat)){
        isValid = moment(startDt,dtFormat).unix() >= moment(endDt,dtFormat).unix();
    }

    return this.optional(element) || isValid;

}, function(otherElement){
    var message = "Please enter a date greater than or equal to " + $(otherElement).val() + "";
    return message;
});

$.validator.addMethod("timeInfinite", function(value, element) {
	if (value) {
		return new RegExp(globalParams.timeRegexCheck).test(value);
	}
	return true;
}, "Please enter a valid time");

$.validator.addMethod("notEquals", function(value, element, valueToCompare) {
	var value_array = valueToCompare.split('|');
	var isValid=$(value_array[0]).val() !== $(value_array[1]).val();
	return this.optional(element) || isValid;
}, function(otherElement){
    var message = "Please select a different value";
    return message;
});


$.validator.addMethod("dateGT", function(value, element, otherElement) {
    var startDt = value;
    var endDt = $(otherElement).val();
    var dtFormat = momentDateFormat || "DD/MM/YYYY";

    var isValid = true;

    if(moment(startDt,dtFormat).isValid() && moment(endDt,dtFormat)){
        isValid = moment(startDt,dtFormat).unix() > moment(endDt,dtFormat).unix();
    }

    return this.optional(element) || isValid;
}, function(otherElement){
    var message = "Please enter a date greater than " + $(otherElement).val() + "";
    return message;
});

$.validator.addMethod("dateLE", function(value, element, otherElement) {
    var startDt = value;
    var endDt = $(otherElement).val();
    var dtFormat = momentDateFormat || "DD/MM/YYYY";

    var isValid = true;

    if(moment(startDt,dtFormat).isValid() && moment(endDt,dtFormat)){
        isValid = moment(startDt,dtFormat).unix() <= moment(endDt,dtFormat).unix();
    }

    return this.optional(element) || isValid;

}, function(otherElement){
    var message = "Please enter a date less than or equal to " + $(otherElement).val() + "";
    return message;
});
$.validator.addMethod("dateLT", function(value, element, otherElement) {
    var startDt = value;
    var endDt = $(otherElement).val();
    var dtFormat = momentDateFormat || "DD/MM/YYYY";

    var isValid = true;

    if(moment(startDt,dtFormat).isValid() && moment(endDt,dtFormat)){
        isValid = moment(startDt,dtFormat).unix() < moment(endDt,dtFormat).unix();
    }

    return this.optional(element) || isValid;

}, function(otherElement){
    var message = "Please enter a date less than " + $(otherElement).val() + "";
    return message;
});


$.validator.addMethod("max", function(value, element, param) {
	return this.optional( element ) || NumericConversionUtils.parse(value)  <= param;
}, function(param){
    var message = "Please enter a value less than or equal to " + NumericConversionUtils.format(param) + ".";
    return message;
});

$.validator.addMethod("min", function(value, element, param) {
	return this.optional( element ) || NumericConversionUtils.parse(value)  >= param;
}, function(param){
    var message = "Please enter a value greater than or equal to " +  NumericConversionUtils.format(param) + ".";
    return message;
});

$.validator.addMethod("range", function(value, element, param) {
	return this.optional( element ) || ( NumericConversionUtils.parse(value) >= param[ 0 ] && NumericConversionUtils.parse(value) <= param[ 1 ] );
}, function(param){
    var message = "Please enter a value between " + NumericConversionUtils.format(param[0]) + " and "+ NumericConversionUtils.format(param[1]) +".";
    return message;
});

$.validator.addMethod("number", function(value, element, param) {
	return this.optional( element ) || NumericConversionUtils.checkFormat(value);
}, function(param){
    var message = "Please enter a valid number.";
    return message;
});

$.validator.addMethod("digits", function(value, element, param) {
	return this.optional( element ) || NumericConversionUtils.checkDigitFormat(value);
}, function(param){
    var message = "Please enter a valid digit.";
    return message;
});

$.validator.addMethod("notequal", function(value, element, param) {
	return this.optional( element ) || value != $(param).val();
}, function(param){
    var message = "Please enter a value different from " + $(param).val();
    return message;
});

$.validator.addMethod("numberLE", function(value, element, param) {
	var paramValue = NumericConversionUtils.parse($(param).val());
	if (isNaN(paramValue) || paramValue == "") { return true; }
	return this.optional( element ) || NumericConversionUtils.parse(value) <= paramValue;
}, function(param){
    var message = "Please enter a number less than or equal to " + $(param).val();
    return message;
});

$.validator.addMethod("pattern", function(value, element, param) {
	if (this.optional(element)) {
		return true;
	}

	if (typeof param === "string") {
		param = new RegExp("^(?:" + param + ")$");
	}else if (typeof param === "object") {
		param = new RegExp("^(?:" + param.pattern + ")$",param.flags);
	}

	return param.test(value);
}, "Invalid format.");

