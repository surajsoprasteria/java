<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<!DOCTYPE html>
<html>

<head profile="http://www.w3.org/2005/10/profile">

    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="shortcut icon" type="image/png" href="${ctx}/res/img/icon.png"/>
    <link rel="icon" type="image/png" href="${ctx}/res/img/icon.png"/>
    

    <title>Connect - <tiles:getAsString name="title"/></title>

	<!-- CSS -->
	<link href="${ctxRes}/css/bootstrap.css" rel="stylesheet" />
	<link href="${ctxRes}/css/lobibox.css" rel="stylesheet" />
	<link href="${ctxRes}/css/login.css" rel="stylesheet" />
	<link href="${ctxRes}/css/font-awesome.css" rel="stylesheet" />
	<link href="${ctxRes}/css/custom-fonts.css" rel="stylesheet" />
	<link href="${ctxRes}/css/bootstrap-dialog.css" rel="stylesheet" />
	<link href="${ctxRes}/css/bootstrap-select.css" rel="stylesheet" />
	<link href="${ctxRes}/css/bootstrap-toggle.css" rel="stylesheet" />
	<link href="${ctxRes}/css/bootstrap-datepicker.css" rel="stylesheet" />
	<link href="${ctxRes}/css/jquery.dataTables.css" rel="stylesheet" />
	<link href="${ctxRes}/css/tooltipster.bundle.min.css" rel="stylesheet" />
	<link href="${ctxRes}/css/dataTables.bootstrap.css" rel="stylesheet" />
	<link href="${ctxRes}/css/dataTables-custom.css" rel="stylesheet" />
	<link href="${ctxRes}/css/bootstrap-custom.css" rel="stylesheet" />
	<link href="${ctxRes}/css/bootstrap-wizard.css" rel="stylesheet" />
	<link href="${ctxRes}/css/codemirror.css" rel="stylesheet" />
	<link href="${ctxRes}/css/collapsible-panel.css" rel="stylesheet" />
	<link href="${ctxRes}/css/template-default.css" rel="stylesheet" />
	<link href="${ctxRes}/css/validate.css" rel="stylesheet" />
	<link href="${ctxRes}/css/base.css" rel="stylesheet" />
	<link href="${ctxRes}/css/wipo.css" rel="stylesheet" />
	<link href="${ctxMvc}/common/external.css" rel="stylesheet" />
</head>

<body class="hidden-print">
	<!-- JAVASCRIPT -->
	<script src="${ctxRes}/js/jquery.js"></script>
	<script src="${ctxRes}/js/jquery.blockUI.js"></script>
	<script src="${ctxRes}/js/jquery-ui.js"></script>
	<script src="${ctxRes}/js/bootstrap.js"></script>
	<script src="${ctxRes}/js/bootstrap.dialog.js"></script>
	<script src="${ctxRes}/js/bootstrap.select.js"></script>
	<script src="${ctxRes}/js/bootstrap-toggle.js"></script>
	<script src="${ctxRes}/js/bootstrap.datepicker.js"></script>
	<script src="${ctxRes}/js/string.utils.js"></script>
	<script src="${ctxRes}/js/moment.js"></script>
	<script src="${ctxRes}/js/jquery.form.js"></script>
	<script src="${ctxRes}/js/jquery.serializeObject.js"></script>
	<script src="${ctxRes}/js/jquery.validate.js"></script>
	<script src="${ctxRes}/js/jquery.validate.additional-methods.js"></script>
	<script src="${ctxRes}/js/jquery.validate.custom.js"></script>
	<script src="${ctxRes}/js/jquery.dataTables.js"></script>
	<script src="${ctxRes}/js/jquery.dataTables.custom.js"></script>
	<script src="${ctxRes}/js/dataTables.bootstrap.js"></script>
	<script src="${ctxRes}/js/dataTables.sort.js"></script>
	<script src="${ctxRes}/js/dataTables.scroller.js"></script>
	<script src="${ctxRes}/js/dataTables.renders.js"></script>
	<script src="${ctxRes}/js/codemirror.js"></script>
	<script src="${ctxRes}/js/codemirror.mode.groovy.js"></script>
	<script src="${ctxRes}/js/codemirror.mode.javascript.js"></script>
	<script src="${ctxRes}/js/codemirror.addon.edit.closebrackets.js"></script>
	<script src="${ctxRes}/js/codemirror.addon.edit.matchbrackets.js"></script>
	<script src="${ctxRes}/js/jquery.blockEdit.js"></script>
	<script src="${ctxRes}/js/jquery.collapsible-panel.js"></script>
	<script src="${ctxRes}/js/js.cookie.js"></script>
	<script src="${ctxRes}/js/lobibox.notifications.js"></script>
	<script src="${ctxRes}/js/formulaUtils.js"></script>
	<script src="${ctxRes}/js/jquery.actual.custom.js"></script>
	<script src="${ctxRes}/js/jquery.input2Span.js"></script>
	<script src="${ctxRes}/js/autoNumeric-min.js"></script>
	<script src="${ctxRes}/js/fileSaver.js"></script>
	<script src="${ctxRes}/js/jspdf.js"></script>
	<script src="${ctxRes}/js/jquery.mask.js"></script>
	<script src="${ctxRes}/js/html2canvas.js"></script>
	<script src="${ctxRes}/js/tooltipster.bundle.js"></script>

	<jsp:include page="/WEB-INF/config/js-plugin-config-helper.jsp" flush="true"></jsp:include>
	
	<script src="${ctxRes}/js/ui-utils.js"></script>

	<script type="text/javascript">
		// disable back navigation button
		window.location.hash="top";
		window.location.hash="top2";//again because google chrome don't insert first hash into history
		window.onhashchange=function(){window.location.hash="top";};
		
		// date and date time format (moment.js format)
        var momentDateFormat = "${msg['config.date-moment']}";
        var momentDateTimeFormat = "${msg['config.date-time-moment']}";
        
		//row select event
		$(document).on("change","table.dataTable tbody input[type=checkbox].select-row", selectRowHandler);
		$(document).on("change","table.dataTable thead input[type=checkbox].select-all-rows", selectAllRowsHandler);

		function selectAllRowsHandler(event){
			var chk = $(this);
			var table = chk.closest("table.dataTable");

			if(table.find("tbody tr td.dataTables_empty").size()){
				chk.prop("checked",false);
			}else{
				var isChecked = chk.prop("checked");

				var allNodes = table.find("input[type=checkbox]:enabled.select-row");
				if (allNodes && allNodes.length > 0) {
					allNodes.prop("checked",isChecked);
					if (isChecked) {
						allNodes.closest("tr").addClass("selected");
					} else {
						allNodes.closest("tr").removeClass("selected");
					}
					$(allNodes[0]).trigger("row-select-change.wcc");
				}

			}
		}
		
		function selectRowHandler(event,ignoreSelectAll, ignoreTriggerEvent){
			var chk = $(this);
			var table = chk.closest("table.dataTable");
			var row = chk.closest("tr");

			if(!chk.prop("disabled")){
				if(chk.prop("checked")){
					row.addClass("selected");
				}else{
					row.removeClass("selected");
				}

				var selectedRows = getSelectedNodes(table,false).length;
				var allRows = getSelectedNodes(table,true).length;

				if(!ignoreSelectAll){
					table.find("thead input[type=checkbox].select-all-rows").prop("checked",selectedRows === allRows);
				}
				if(!ignoreTriggerEvent){
					chk.trigger("row-select-change.wcc");
				}
			}
		}

	 	$(document).on("draw.dt",function(e,settings){
			var chk = $("#"+settings.sTableId + " thead input[type=checkbox].select-all-rows");
			if (chk.length > 0) {
				chk.prop("checked",false);
				selectAllRowsHandler.call(chk[0],e);
			}
		}); 

		$(document).on("draw.dt","table.auto-scroll-x",function(e,settings){
			var table = $("#"+settings.sTableId);
			var wrapper = table.closest("div.dataTables_wrapper");

			if(wrapper.size() < 1) return;
			
			var width1 = wrapper.actual("scrollWidth",{useHtmlProp:true});
			var width2 = wrapper.actual("width");
			if((width1 - 10) > width2){
				wrapper.css("overflow-x","auto");
				wrapper.css("overflow-y","hidden");
				wrapper.css("height", "auto");
				
				if ((wrapper[0].offsetWidth - wrapper[0].clientWidth) > 0) {
					wrapper.css("height", wrapper.height()+scrollbarWidth+1);
				}
				
				var menuPosition = function(event) {
					$parent = $("div.dropdown:has(div.dropdown-menu > [aria-expanded='true'])");
				    $child = $parent.find("div.dropdown-menu:has([aria-expanded='true'])");
				    
				    if ($parent.length == 0 || $child.length == 0) return;
				    
					var childStyle = {
						"position":"fixed", 
						"min-width" :$parent.css('width'),
						"max-height":$child.css('max-height'),
						"min-height":$child.css('min-height')
					};
					$child.css(childStyle);
				    $child.position({
				    	my:        "right top",
				    	at:        "right bottom",
				    	of:        $parent,
				    	collision: "flipfit"
				    });
				}
				
				$(document).on("click", ".bootstrap-select button", menuPosition);
				$(document).scroll(menuPosition);				
			} else {
				wrapper.css("overflow-x","");
				wrapper.css("overflow-y","");
				wrapper.css("height", "auto");
			}
		});

		$(document).ready(function(){
			$(".content-body.body-${currentMillis} input").attr("autocomplete","off");
			$(".content-body.body-${currentMillis}").collapsiblePanel();

			var dateFormat = $.fn.datepicker.dates[$.fn.datepicker.defaults.language].format;
			$(".content-body.body-${currentMillis} .input-group.date input[type=text]").each(function(){
				if(!$(this).attr("placeholder")){
					$(this).attr("placeholder",dateFormat);
					var formatMask = dateFormat.replace(/dd|mm|yy/gi,"00");
					$(this).mask(formatMask,{placeholder:dateFormat});
				}
			});

			applyAutoNumeric();
		});

		$(document).ready(function() {
			 
			// For the Second level Dropdown menu, highlight the parent	
			$( ".dropdown-menu" )
			.mouseenter(function() {
				$(this).parent('li').addClass('active');
			})
			.mouseleave(function() {
				$(this).parent('li').removeClass('active');
			});
		 
		});

		$(document).ajaxStart(function(e, data) {
			if(data && data.hideProgressBar) {
				$.blockUI({message:"", overlayCSS:{ cursor:'wait' }});
			} else {
				$.blockUI();
			}
		});

		$(document).ajaxStop(function(e, data) {
			$.unblockUI();
		});

		window.onerror_inner = window.onerror;
		window.onerror = function(error,url,line){
			var retVal;
			if(typeof window.onerror_inner === "function"){
				retVal= window.onerror_inner.call(this,error,url,line);
			}
			$.unblockUI({fadeOut:0});

			$.each(BootstrapDialog.dialogs, function( index, dialog ) {
				if (dialog.getData("dialog-loaded") != true) {
			  		dialog.close();
				}
			});
			
			return retVal;
		};

		setInterval(function(){ 
			//$(":input:disabled.error, [readonly]:input.error, :input:hidden.error").each(function(){
			$(":input.error:not(.no-valid)").each(function(){
				$(this).trigger("revalid.wcc");
			});


			$("form div.tab-pane").each(function(){
				if($(this).find(":input.error:not(.no-valid)").size()){
					$(this).trigger("invalidtab.wcc");
				}else{
					$(this).trigger("validtab.wcc");
				}
			});

			$("form div.panel, form div.multi-panel-container").each(function(){
				if($(this).find(":input.error:not(.no-valid)").size()){
					$(this).trigger("invalidpanel.wcc");
				}else{
					$(this).trigger("validpanel.wcc");
				}
			});

		}, 1200);

		$(document).on("revalid.wcc",function(event){
			$this = $(event.target);
			$this.valid();	
		});

		$(document).on("invalidtab.wcc",function(event){
			$this = $(event.target);
			$this.addClass("tab-error");
			$("form ul.nav.nav-tabs li a[href='#"+ $this.attr("id") +"']").addClass("tab-error");	
		});

		$(document).on("validtab.wcc",function(event){
			$this = $(event.target);
			$this.removeClass("tab-error");
			$("form ul.nav.nav-tabs li a[href='#"+ $this.attr("id") +"']").removeClass("tab-error");	
		});

		$(document).on("invalidpanel.wcc",function(event){
			$this = $(event.target);
			$this.addClass("panel-error");
		});

		$(document).on("validpanel.wcc",function(event){
			$this = $(event.target);
			$this.removeClass("panel-error");
		});

		$(document).on("shown.bs.modal","div.modal",function(){
			refreshCodeMirrorInstances($(this));
		});

		$(document).on("shown.bs.tab","a[data-toggle='tab']",function(e){
			var tabRef = $(this).attr("href");
			var tabContent = $(tabRef);
			refreshCodeMirrorInstances(tabContent);
		});
		$(document).on("show.bs.tab", "a[data-toggle='tab']",function (e) {
            if($(e.target).hasClass("disabled")){
                return false;
            }
        });

		$(document).on("click","nav.navbar .navbar-first-row ul.navbar-right #btnUserManual",function(){
			downloadFile("${ctx}/mvc/common/downloadUserManual");  	
        });


		$(document).on("click","#ipFullExport a, #workFullExport a",function(e){
			e.preventDefault();
			e.stopImmediatePropagation();
			var linkValue = $(this).attr("href");
			executeAction({
				url : linkValue,
				success: function(data, status, xhr,formObj) {
					showSuccessMessage({message: "${msg['global.result-ok']}"});
				}
			});
	    });

		$(document).on("click",".bootstrap-dialog .bootstrap-dialog-screenshot-button .dropdown-menu a",function(){
			var $this = $(this);
			var modal = $this.closest("div.modal-content");

			setTimeout(function(){
				saveScreenshot(modal[0],$this.data("format"));
			},150);
        });

		$(document).on("click",".screenshot-menu .dropdown-menu a",function(){
			var $this = $(this);
			var modal = $("div.fullScreen-detail");

			setTimeout(function(){
				saveScreenshot(modal[0],$this.data("format"));
			},150);
        });
		
		
		$(document).on("mouseover", ".dropdown-submenu", function() {
	        var submenuPos = $(this).offset().left + $(this).width() + $(this).find('ul').actual("width") + 50;
	        var windowPos  = $(window).width();
			
	        if( submenuPos > windowPos ){
	            $(this).find('ul').css("right", "100%");
	            $(this).find('ul').css("left", "");
	        } else {
	            $(this).find('ul').css("right", "");
	            $(this).find('ul').css("left", "100%");
	        }
	    });
		
		
		//# sourceURL=main.js
	</script>

	<tiles:insertAttribute name="pageScript" />

    <tiles:insertAttribute name="navigation" />
	<div class="container">
		<div class="main-body content-body body-${currentMillis}">
			<tiles:insertAttribute name="body" />
		</div>
    </div>


</body>

</html>
