<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>


<c:choose>
	<c:when test="${singleSelectMode}">
		<c:set var="pageTemplate" value="contentTemplate" />
		<c:set var="idSfx" value="RefCmoSingle" />
	</c:when>
	<c:when test="${multipleSelectMode}">
		<c:set var="pageTemplate" value="contentTemplate" />
		<c:set var="idSfx" value="RefCmoMultiple" />
	</c:when>
	<c:otherwise>
		<c:set var="pageTemplate" value="defaultTemplate" />
		<c:set var="idSfx" value="RefCmo" />
	</c:otherwise>
</c:choose>

<tiles:insertDefinition name="${pageTemplate}">

	
	<tiles:putAttribute name="title">${msg['reference.cmo']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['reference.cmo']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    	 	$(document).ready(function(){
    			$(document).off(".${idSfx}");
    			//PAGE INIT
				var singleSelectMode = "${singleSelectMode}" === "true";
				var multipleSelectMode = "${multipleSelectMode}" === "true";
    			
        		$(".container${idSfx} .input-group.date").datepicker();
        		$(".container${idSfx} select").selectpicker();

        		var formObj = $(".container${idSfx} #cmoForm${idSfx} input#exclusionList${idSfx}");
        		var searchURL = "${ctx}/mvc/reference/cmo/find.json?"+ formObj.serialize();
        		
    			
        		var tblObj = $('#tblResults${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" :searchURL,
    				"columns": [
    					{ "data": null, visible:multipleSelectMode, "title":"<input type='checkbox' class='select-all-rows' />", "width":"30", className:"text-center", "sortable":false, "render":colRender("checkboxId")},
    					{ "data": null, "title":"", "sortable":false, visible:singleSelectMode,"width":"30", "render": colRender("buttonIco",{icon:"glyphicon-share-alt",cssClass:"btn-select select-rows"})},
    		           	{ "data": "code", "title": "${msg['cmo.col-code']}"},
    		            { "data": "acronym", "title": "${msg['cmo.col-acronym']}"}, 
    		        	{ "data": "name", "title":"${msg['cmo.col-name']}"},
    		        	{ "data": "fkOriginCountry", "title":"${msg['cmo.col-country-origin']}", "render":countryOriginRender},
    		        	{ "data": "fkType", "title":"${msg['cmo.col-type']}", "render":cmoTypeRender},
    		        	{ "data": null, "title":"" , visible:!(multipleSelectMode || singleSelectMode), "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    } );
    			
        		function countryOriginRender(data,type,row,meta){
         			var territoryMap = meta.settings.json.territoryMap;
         			if(data && territoryMap){
 						return territoryMap[data].name;
         			}else{
               			return "";
               		}
        		}
        		
        		function cmoTypeRender(data,type,row,meta){
         			var typeMap = meta.settings.json.typeMap;
         			if(data && typeMap){
 						return typeMap[data].code;
         			}else{
               			return "";
               		}
        		}
        		
        		function activeRender(data,type,row,meta){
        			var isActive = row.isActive;
         			if(isActive){
 						return "${msg['global.yes']}";
         			}else{
               			return "${msg['global.no']}";
               		}
        		}
        		
        		function actionRender(data, type, row, meta){
        			var htmlOut = "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
        			return htmlOut;
        		}
        		
        		$('#tblResults${idSfx}').on('click', 'button.btn-detail', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();
	
	       	         openModalDetail({
	       	        	"dialogTitle": "${msg['reference.cmo']}",
	           	        "url":"${ctx}/mvc/reference/cmo/detail",
	           	        "data":{ 
	           	     		"cmo.id":rowData.id,
	           	         	"insertMode": false	
	           	        }
	           	     });
	       	         
	           	});
        		
        		
        		
        		$('.container${idSfx}').on('click', 'button.add-cmo', function () {	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['reference.cmo']}",
	       	        	"wrapperClass": "modalValue${idSfx}",
	           	        "url":"${ctx}/mvc/reference/cmo/detail",
	           	        "data":{
	           	        	"cmo.id":null,
	           	        	"insertMode":true
	           	        }
	           	     });
	       	         
	           	});

    		}); 
    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="cmoForm${idSfx}" cssClass="form-inline" modelAttribute="searchVO">
				<form:hidden path="onlyDataSources" id="onlyDataSources${idSfx}"/>
				<form:hidden path="exclusionList" id="exclusionList${idSfx}"/>
				<form:hidden path="filterClientInfo" id="filterClientInfo${idSfx}"/>
				<form:hidden path="currentClientInfo" id="currentClientInfo${idSfx}"/>
			</form:form>
			
			<div class="panel panel-default cmo-panel"> 
				<c:if test="${multipleSelectMode}">
					<c:set var="headerClass" value="big-header"/>
				</c:if>
				<div class="panel-heading ${headerClass}">
					<div class="float-left">
						<h3 class="panel-title">${msg['reference.cmo']}</h3>
					</div>
					<c:if test="${multipleSelectMode}">
						<div class="float-right">
							<button type="button" class="btn btn-primary select-rows">${msg['generic.select']}</button>
						</div>
					</c:if>
					<c:if test="${!(multipleSelectMode or singleSelectMode)}">
						<div class="float-right">
							<button type="button" class="btn btn-xs btn-primary add-cmo">${msg['cmo.add-button']}</button>
						</div>
					</c:if>
					<div class="clear"></div> 
				</div>
				<div class="panel-body">
					<table id="tblResults${idSfx}"></table>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>