<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="org.wipo.connect.shared.exchange.enumerator.*"%>
<%@ page import="org.wipo.connect.common.caseconverter.CaseConversionEnum"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="CONVERSION_LOWERCASE" value="<%=CaseConversionEnum.LOWERCASE.name()%>"  />
<c:set var="CONVERSION_UPPERCASE" value="<%=CaseConversionEnum.UPPERCASE.name()%>"  />
<c:set var="CONVERSION_FIRST_CAPITAL" value="<%=CaseConversionEnum.FIRST_CAPITAL.name()%>"  />
<c:set var="CONVERSION_ALL_CAPITAL" value="<%=CaseConversionEnum.ALL_CAPITAL.name()%>" />

<script type="text/javascript">
	(function(){
		// setup plugin defaults and localization
		setAllPluginsDefaults();

		function setValidationDefaults(params){
			var locale = params.locale;
			
			var messages = {
					required: "${msg['jsPlugin.validation.required']}",
					remote: "${msg['jsPlugin.validation.remote']}",
					email: "${msg['jsPlugin.validation.email']}",
					url: "${msg['jsPlugin.validation.url']}",
					date: "${msg['jsPlugin.validation.date']}",
					dateISO: "${msg['jsPlugin.validation.dateISO']}",
					number: "${msg['jsPlugin.validation.number']}",
					digits: "${msg['jsPlugin.validation.digits']}",
					creditcard: "${msg['jsPlugin.validation.creditcard']}",
					equalTo: "${msg['jsPlugin.validation.equalTo']}",
					maxlength: "${msg['jsPlugin.validation.maxlength']}" ,
					minlength: "${msg['jsPlugin.validation.minlength']}" ,
					rangelength: "${msg['jsPlugin.validation.rangelength']}" ,
					range: "${msg['jsPlugin.validation.range']}" ,
					max: "${msg['jsPlugin.validation.max']}" ,
					min: "${msg['jsPlugin.validation.min']}"
			};

			var dtFormat = "${msg['config.date-moment']}";

			$.extend($.validator.messages, messages);


			$.validator.setDefaults({
						ignore : '.exclude-validation , .no-valid',
						ignoreTitle : true,
						msgPlaceholder : "%MSG%",
						msgTemplate : "<span class='icon glyphicon glyphicon-alert' title='%MSG%'></span>",
						dateFormat : dtFormat,
						onkeyup : false
					});
		}

		function setSelectPickerDefaults(params){
			var locale = params.locale;
			
			var settings = {
				    noneSelectedText: "${msg['jsPlugin.selectPicker.noneSelectedText']}",
				    noneResultsText: "${msg['jsPlugin.selectPicker.noneResultsText']}",
				    countSelectedText: function (numSelected, numTotal) {
				      return (numSelected == 1) ? "${msg['jsPlugin.selectPicker.selected']}" : "${msg['jsPlugin.selectPicker.selectedPlural']}";
				    },
				    maxOptionsText: function (numAll, numGroup) {
				      var arr = [];

				      arr[0] = (numAll == 1) ? "${msg['jsPlugin.selectPicker.limitReached']}" : "${msg['jsPlugin.selectPicker.limitReachedPlural']}";
				      arr[1] = (numGroup == 1) ? "${msg['jsPlugin.selectPicker.groupLimitReached']}" : "${msg['jsPlugin.selectPicker.groupLimitReachedPlural']}";

				      return arr;
				    },
				    selectAllText: "${msg['jsPlugin.selectPicker.selectAllText']}",
				    deselectAllText: "${msg['jsPlugin.selectPicker.deselectAllText']}",
				    multipleSeparator: "${msg['jsPlugin.selectPicker.separator']}" + " ",
				    dropdownAlignRight: "auto",
				    selectOnTab: true
			  };
		
			jQuery.extend($.fn.selectpicker.Constructor.DEFAULTS, settings);
			//$.fn.selectpicker.defaults = settings;
		
		}

		function setDatePickerLang(params){
			var locale = params.locale;
			locale = locale || "en";

			$.fn.datepicker.dates[locale] = {
					days:   ["${msg['jsPlugin.datePicker.days.full.sunday']}",
							 "${msg['jsPlugin.datePicker.days.full.monday']}",
							 "${msg['jsPlugin.datePicker.days.full.tuesday']}",
							 "${msg['jsPlugin.datePicker.days.full.wednesday']}", 
							 "${msg['jsPlugin.datePicker.days.full.thursday']}", 
							 "${msg['jsPlugin.datePicker.days.full.friday']}", 
							 "${msg['jsPlugin.datePicker.days.full.saturday']}", 
							 "${msg['jsPlugin.datePicker.days.full.sunday']}"],
							 
					daysShort:  ["${msg['jsPlugin.datePicker.days.short.sunday']}",
								 "${msg['jsPlugin.datePicker.days.short.monday']}",
								 "${msg['jsPlugin.datePicker.days.short.tuesday']}",
								 "${msg['jsPlugin.datePicker.days.short.wednesday']}", 
								 "${msg['jsPlugin.datePicker.days.short.thursday']}", 
								 "${msg['jsPlugin.datePicker.days.short.friday']}", 
								 "${msg['jsPlugin.datePicker.days.short.saturday']}", 
								 "${msg['jsPlugin.datePicker.days.short.sunday']}"],
								 
					daysMin:    ["${msg['jsPlugin.datePicker.days.min.sunday']}",
								 "${msg['jsPlugin.datePicker.days.min.monday']}",
								 "${msg['jsPlugin.datePicker.days.min.tuesday']}",
								 "${msg['jsPlugin.datePicker.days.min.wednesday']}", 
								 "${msg['jsPlugin.datePicker.days.min.thursday']}", 
								 "${msg['jsPlugin.datePicker.days.min.friday']}", 
								 "${msg['jsPlugin.datePicker.days.min.saturday']}", 
								 "${msg['jsPlugin.datePicker.days.min.sunday']}"],
								 
					months:    ["${msg['jsPlugin.datePicker.months.full.january']}",
								"${msg['jsPlugin.datePicker.months.full.february']}", 
								"${msg['jsPlugin.datePicker.months.full.march']}", 
								"${msg['jsPlugin.datePicker.months.full.april']}", 
								"${msg['jsPlugin.datePicker.months.full.may']}", 
								"${msg['jsPlugin.datePicker.months.full.june']}", 
								"${msg['jsPlugin.datePicker.months.full.july']}", 
								"${msg['jsPlugin.datePicker.months.full.august']}", 
								"${msg['jsPlugin.datePicker.months.full.september']}", 
								"${msg['jsPlugin.datePicker.months.full.october']}", 
								"${msg['jsPlugin.datePicker.months.full.november']}", 
								"${msg['jsPlugin.datePicker.months.full.december']}"],
								
					monthsShort:   ["${msg['jsPlugin.datePicker.months.short.january']}",
									"${msg['jsPlugin.datePicker.months.short.february']}", 
									"${msg['jsPlugin.datePicker.months.short.march']}", 
									"${msg['jsPlugin.datePicker.months.short.april']}", 
									"${msg['jsPlugin.datePicker.months.short.may']}", 
									"${msg['jsPlugin.datePicker.months.short.june']}", 
									"${msg['jsPlugin.datePicker.months.short.july']}", 
									"${msg['jsPlugin.datePicker.months.short.august']}", 
									"${msg['jsPlugin.datePicker.months.short.september']}", 
									"${msg['jsPlugin.datePicker.months.short.october']}", 
									"${msg['jsPlugin.datePicker.months.short.november']}", 
									"${msg['jsPlugin.datePicker.months.short.december']}"],
									
					today: "${msg['jsPlugin.datePicker.today']}",
					clear: "${msg['jsPlugin.datePicker.clear']}",
					weekStart:"${msg['jsPlugin.datePicker.weekStart']}" ,
					
					format: "${msg['config.date-picker']}"
				};

			$.fn.datepicker.defaults.language = locale;
			$.fn.datepicker.defaults.autoclose = true;
			$.fn.datepicker.defaults.todayBtn = "linked";
			$.fn.datepicker.defaults.showOnFocus = false;
			$.fn.datepicker.defaults.todayHighlight = true;
			$.fn.datepicker.defaults.positionRef = window;
		}

		
		function setMomentDefaults(params){
			var locale = params.locale;
            locale = locale || "en";

            moment.locale(locale);

            moment.format = {};
            moment.format.date = "${msg['config.date-moment']}";
            moment.format.dateTime = "${msg['config.date-time-moment']}";
            moment.format.time = "${msg['config.time-moment']}";
            moment.format.iso8601 = "YYYY-MM-DD HH:mm:ss";
        }

		function setDataTableDefaults(params){
			var locale = params.locale;

			var language = {
				"sEmptyTable" : "${msg['jsPlugin.dataTable.sEmptyTable']}",
				"sInfo" : "${msg['jsPlugin.dataTable.sInfo']}",
				"sInfoEmpty" : "${msg['jsPlugin.dataTable.sInfoEmpty']}",
				"sInfoFiltered" : "${msg['jsPlugin.dataTable.sInfoFiltered']}",
				"sInfoPostFix" : "${msg['jsPlugin.dataTable.sInfoPostFix']}",
				"sInfoThousands" : "${msg['jsPlugin.dataTable.sInfoThousands']}",
				"sLengthMenu" : "${msg['jsPlugin.dataTable.sLengthMenu']}",
				"sLoadingRecords" : "" /*"${msg['jsPlugin.dataTable.sLoadingRecords']}"*/,
				"sProcessing" : ""/* "${msg['jsPlugin.dataTable.sProcessing']}"*/,
				"sSearch" : "${msg['jsPlugin.dataTable.sSearch']}",
				"sZeroRecords" : "${msg['jsPlugin.dataTable.sZeroRecords']}",
				"oPaginate" : {
					"sFirst" : "${msg['jsPlugin.dataTable.sFirst']}",
					"sLast" : "${msg['jsPlugin.dataTable.sLast']}",
					"sNext" : "${msg['jsPlugin.dataTable.sNext']}",
					"sPrevious" : "${msg['jsPlugin.dataTable.sPrevious']}"
				},
				"oAria" : {
					"sSortAscending" : "${msg['jsPlugin.dataTable.sSortAscending']}",
					"sSortDescending" : "${msg['jsPlugin.dataTable.sSortDescending']}"
				}
			};

			var customCssClass = {
		        "sFilter": "dataTables_filter no-confirm-on-changes ",
		        "sLength": "dataTables_length no-confirm-on-changes ",
		        "sPaging": "dataTables_paginate paging_bootstrap no-confirm-on-changes ",
		        "sProcessing" : "dataTables_processing_custom "
			}
			   
			$.extend($.fn.dataTableExt.oStdClasses, customCssClass);  // Used when bJQueryUI is false
			$.extend($.fn.dataTableExt.oJUIClasses, customCssClass); // Used when bJQueryUI is true


			$.extend(true, $.fn.dataTable.defaults, {
				"dom": "t",
				"pageLength": 50,
				"language" : language,
				"autoWidth": false,
				"processing": true,
				"columnDefs": [{
					"targets": '_all',
					"render": function ( data, type, row ) {
						if(data == null) return "";
						if (type !== "display") return data;
						return escapeHtml(data);
					}
				}]
			});

			$.fn.dataTable.ext.errMode = function ( settings, techNote, message ) {
				var responseText = "";
				if(settings && settings.jqXHR && settings.jqXHR.responseText){
					responseText = settings.jqXHR.responseText;
				}
				var isLogin = detectLoginPage(responseText);	
				if(isLogin === true){
					redirectToLogin();
					return;
				}			
				showErrorMessage({message:"${msg['jsPlugin.dataTable.errorMessage']}"});
				console.error( 'An error has been reported by DataTables: ', message );
		    };
		}

		function setToggleDefaults(params){
			var locale = params.locale;
			$.fn.bootstrapToggle.Constructor.DEFAULTS.width = "auto";

			$.fn.bootstrapToggle.Constructor.prototype.render_inner = $.fn.bootstrapToggle.Constructor.prototype.render;
			$.fn.bootstrapToggle.Constructor.prototype.render = function(){
				var retVal = $.fn.bootstrapToggle.Constructor.prototype.render_inner.call(this);

				var counter = $("body").data("toggle-count") || 1;
				$("body").data("toggle-count", ++counter);

				this.$toggle.attr("id","toggle_" + counter);

				return retVal;
			};

		}

		function setBootstrapDialogDefaults(params){
			BootstrapDialog.newGuid =  function (){
				var bdcount = $("body").data("bd-count") || 1;
				$("body").data("bd-count", ++bdcount);
				return "bdcount_" + bdcount;
			};

			BootstrapDialog.prototype.close =  function() {
	            if (this.isAutodestroy()) {
	                delete BootstrapDialog.dialogs[this.getId()];
	            }
	            this.setOpened(false);
	            this.getModal().modal('hide');

	            return this;
	        };
		}

		function setLobiboxDefaults(params){
			Lobibox.notify.DEFAULTS = $.extend({}, Lobibox.notify.DEFAULTS, {
				soundPath: params.ctxRes +'/sounds/',
				size: "mini",
				messageHeight: 200
			});
		}

		function setAutonumericDefaults(params){
			$.extend($.fn.autoNumeric.defaults, {
				digitGroupSeparator: params.groupingSeparator,
				decimalCharacter: params.decimalSeparator,
				decimalPlacesOverride: 2,
				showWarnings: false
			});  
		}

		function setTagsInputDefaults(params){
			var baseFunction = $.fn.tagsinput;
			
			$.fn.tagsinput = function(arg1, arg2, arg3){
				var output = baseFunction.call(this,arg1, arg2, arg3);

				this.each(function(){
					var $element = $(this);
					var $div = $element.prev("div.bootstrap-tagsinput");

					if($div.size()){
						$element.after($div);
					}					
				});

				return output;
			}
		}

		function setBlockUiDefaults(params){
			var baseFunction = $.blockUI.defaults;

			baseFunction.message = "<div class='progress-bar'></div>";
			baseFunction.overlayCSS = {};
			baseFunction.css = {};
			baseFunction.baseZ = 99999999;
			baseFunction.fadeIn = 0;
			baseFunction.fadeOut = 0;
		}
		
		function setAllPluginsDefaults(){
			var params = {
					"locale" : "${appLocale}",
					"ctx" : "${ctx}",
					"ctxMvc" : "${ctxMvc}",
					"ctxRes" : "${ctxRes}",
					"loginUrl" :"${ctx}/mvc/common/home",
					"massiveOperationThreshold" : parseInt("${configProperties['massiveOperationThreshold']}",10),
					"caseConversionSelected" : "${configProperties['fieldsCaseConversion']}",
					"fieldCaseConvUppercase" : "${CONVERSION_UPPERCASE}",
					"fieldCaseConvLowercase" : "${CONVERSION_LOWERCASE}",
					"fieldCaseConvFirstCapital" : "${CONVERSION_FIRST_CAPITAL}",
					"fieldCaseConvAllCapital" : "${CONVERSION_ALL_CAPITAL}",
					"decimalSeparator" : $('<textarea />').html("<spring:message text='${csf:getDecimalSeparator()}' javaScriptEscape='false'/>").text(),
					"groupingSeparator" : $('<textarea />').html("<spring:message text='${csf:getGroupingSeparator()}' javaScriptEscape='false'/>").text(),
					"isCurrencyBefore" : "${csf:isCurrencyBefore()}" === "true",
					"isGroupSeparatorActive" : "${csf:isGroupSeparatorActive()}" === "true",
					"currencySymbol" : $('<textarea />').html("<spring:message text='${csf:getCurrencySymbol()}' javaScriptEscape='false'/>").text(),
					"numberRegexCheck" : $('<textarea />').html("<spring:message text='${csf:getRegexCheck()}' javaScriptEscape='true'/>").text(),
					"digitRegexCheck" : $('<textarea />').html("<spring:message text='${csf:getRegexDigitCheck()}' javaScriptEscape='true'/>").text(),
					"timeRegexCheck" : $('<textarea />').html("^<spring:message text='${csf:getRegexTimeCheck()}' javaScriptEscape='true'/>$").text(),
					"messages" : JSON.parse($('<textarea />').html("<spring:message text='${msg.getJson()}' javaScriptEscape='true'/>").text())
				};
			window["globalParams"] = params;

			setValidationDefaults(params);
			setSelectPickerDefaults(params);
			setDatePickerLang(params);
			setMomentDefaults(params);
			setDataTableDefaults(params);
			setToggleDefaults(params);
			setBootstrapDialogDefaults(params);
			setLobiboxDefaults(params);
			setAutonumericDefaults(params);
			setTagsInputDefaults(params);
			setBlockUiDefaults(params);
		}
	})();
	
	//# sourceURL=helper.js
</script>