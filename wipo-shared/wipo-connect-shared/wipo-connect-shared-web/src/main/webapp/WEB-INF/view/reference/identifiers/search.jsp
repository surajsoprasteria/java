<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

<c:set var="idSfx" value="RefIdentifiers" />

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="title">${msg['reference.identifiers']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['reference.identifiers']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    	 	$(document).ready(function(){
				var canRender = "${canRender}" === "true";
        	 	
    			$(document).off(".${idSfx}"); //PAGE INIT
    			
        		
    			var tblObj = $('#tblResults${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/identifiers/findAllIdentifier.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['identifiers.col-code']}"},
    		        	{ "data": "acronym", "title":"${msg['identifiers.col-acronym']}"},
    		        	{ "data": "name", "title": "${msg['identifiers.col-name']}"},
    		        	{ "data": "fkCcList", "title": "${msg['identifiers.col-creation-classes']}", "render": creationClassRender,className:"text-center"},
    		        	{ "data": "linkedEntity", "title": "${msg['identifiers.col-linked-entity']}", "render": entityEnumRender},
    		        	{ "data": null, "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    });

        		
        		function creationClassRender_filter(data,type,row,meta){
            		var sOut = "";
            		var ccMap = meta.settings.json.creationClassMap;
            		var ccList = data; 
            		var ccMap = meta.settings.json.creationClassMap;      
					for(var i=0; i<ccList.length; i++){
						var ccCode = ccList[i];
						sOut += ccCode + " " + ccMap[ccCode].name + "\n";
					}
					return sOut;
        		}
	        		
    		    function creationClassRender(data,type,row,meta){
    		    	if(type !== "display"){
						return creationClassRender_filter(data, type, row, meta);
					}
				
          				var ccOut = "<div>";
          				var ccList = data;       	
   					var ccMap = meta.settings.json.creationClassMap;
   					for(var i=0; i<ccList.length; i++){
   						var id = ccList[i];
   						ccOut += "<div class='creation-class-icon grid "+ ccMap[id].code + "' data-toggle='tooltip' title='" + ccMap[id].code + " ("+ escapeHtml(ccMap[id].name) + ")'></div>";
   					}

   					return ccOut+"</div>";

           			
               	}
       			
       			function entityEnumRender(data, type, row, meta) {
					<c:forEach items="${linkedEntityEnum}" var="item">
					if (data == "${item}") {
						return "${msg[item.msgCode]}";
					}
					</c:forEach>
       				return "";
               	}
       			
       			
       			function actionRender(data, type, row, meta){
        			var htmlOut = "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
        			return htmlOut;
        		}
       			
       			
       			
        		$('#tblResults${idSfx}').on('click', 'button.btn-detail', function(event) {
	      	 		 var tr = $(this).closest('tr');
	      	         var rowData = tblObj.row( tr ).data();

	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['reference.identifiers']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/identifiers/detail",
	          	        "data":{ 
	          	        	"id":rowData.id,
	          	        	"insertMode":false
	          	         }
	          	     });
	      	         
	          	});
       		
       		
       			$('.container${idSfx}').on('click', 'button.add-identifier', function(event) {
	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['reference.identifiers']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/identifiers/detail",
	          	        "data":{ 
	          	        	"id":null,
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
			<div class="panel panel-default identifiers-panel"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-identifier pull-right">${msg['identifiers.add-button']}</button>
					<h3 class="panel-title">${msg['reference.identifiers']}</h3>
				</div>
				<div class="panel-body">
					<table id="tblResults${idSfx}"></table>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>