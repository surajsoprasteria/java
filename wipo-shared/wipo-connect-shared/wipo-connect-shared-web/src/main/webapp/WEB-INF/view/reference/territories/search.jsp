<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefTerritory" />

<tiles:insertDefinition name="defaultTemplate">

	
	<tiles:putAttribute name="title">${msg['reference.territories']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['reference.territories']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    	 	$(document).ready(function(){
    			$(document).off(".${idSfx}"); //PAGE INIT
    			
        		var tblObj = $('#tblResults${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/territories/findAllTerritory.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['territory.col-tisn']}"},
    		        	{ "data": "tisa", "title":"${msg['territory.col-tisa']}"},
    		        	{ "data": "name", "title": "${msg['territory.col-name']}"},
    		        	{ "data": "fkType", "title": "${msg['territory.col-type']}", "render": territoryTypeRender},
    		        	{ "data": "startDate", "title": "${msg['territory.col-start-date']}", "render": colRender("date")},
    		        	{ "data": "endDate", "title": "${msg['territory.col-end-date']}", "render": colRender("date")},
    		        	{ "data": null, "title":"", "sortable":false,"render": actionRender, className:"text-center",width:"30px"}
    		        ]
    		    } );
    			
    			
        		function territoryTypeRender(data, type, row, meta){
        			var territoryTypeMap = meta.settings.json.territoryTypeMap;
         			if(data && territoryTypeMap){
 						return territoryTypeMap[data].value;
         			}else{
               			return "";
               		}
        		}
    			
        		function actionRender(data, type, row, meta) {
					var htmlOut = "";
					htmlOut += "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
					return htmlOut;
				}
        		
        		$('#tblResults${idSfx}').on('click', 'button.btn-detail', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();
	
	       	         openModalDetail({
	       	        	 "dialogTitle": "${msg['territory.detail']}",
	           	         "url":"${ctx}/mvc/reference/territories/detail",
	           	         "data":{ 
	           	        	"idTerritory":rowData.id,
	           	        	"insertMode":false
	           	         }
	           	     });
	       	         
	           	});
        		
        		$('.container${idSfx}').on('click', 'button.add-territory', function () {	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['territory.detail']}",
	       	        	"wrapperClass": "modalValue${idSfx}",
	           	        "url":"${ctx}/mvc/reference/territories/detail",
	           	        "data":{
	           	        	"idTerritory":null,
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
			<div class="panel panel-default territory-panel"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-territory pull-right">${msg['territory.add-button']}</button>
					<h3 class="panel-title">${msg['reference.territories']}</h3>
				</div>
				<div class="panel-body">
					<table id="tblResults${idSfx}"></table>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>