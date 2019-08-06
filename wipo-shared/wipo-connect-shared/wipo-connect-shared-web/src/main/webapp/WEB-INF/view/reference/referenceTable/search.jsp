<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefTable" />

<tiles:insertDefinition name="defaultTemplate">

	
	<tiles:putAttribute name="title">${msg['reference.reference-table']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['reference.reference-table']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    	 	$(document).ready(function(){
    	 		var tblCreationClass = null;
    	 		var tblIpiRole = null;
    	 		var tblWorkRole = null;
    	 		var tblIpiRightType = null;
    	 		var tblRightType = null;
    			$(document).off(".${idSfx}");
    			//PAGE INIT
    			
        		tblCreationClass = $('#tblCreationClass${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"clear">',
        			"paging":false,
					"ajax" : "${ctx}/mvc/reference/referenceTable/findAllCreationClass.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['creationClass.col-code']}"},
    		        	{ "data": "name", "title":"${msg['creationClass.col-name']}"},
    		        	{ "data": "description", "title": "${msg['creationClass.col-description']}"},
    		        	{ "data": null, "title":"", "sortable":false,"render": actionRender, className:"text-center",width:"30px"}
    		        ]
    		    });
    			
    			
        		tblIpiRole = $('#tblIpiRole${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"clear">',
        			"paging":false,
					"ajax" : "${ctx}/mvc/reference/referenceTable/findAllIpiRole.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['role.col-code']}"},
    		        	{ "data": "name", "title":"${msg['role.col-name']}"},
    		        	{ "data": "description", "title": "${msg['role.col-description']}"},
    		        	{ "data": "fkCcList", "title": "${msg['role.col-creation-classes']}", "render": creationClassRender, className:"text-center"},
    		        	{ "data": null, "title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    } );
    			
        		tblWorkRole = $('#tblWorkRole${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"clear">',
        			"paging":false,
					"ajax" : "${ctx}/mvc/reference/referenceTable/findAllWorkRole.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['role.col-code']}"},
    		        	{ "data": "name", "title":"${msg['role.col-name']}"},
    		        	{ "data": "description", "title": "${msg['role.col-description']}"},
    		        	{ "data": "fkIpiRole", "title": "${msg['role.col-linked-ipi-role']}", "render":linkedIpiRoleRender},
    		        	{ "data": "fkCcList", "title": "${msg['role.col-creation-classes']}", "render": creationClassRender, className:"text-center"},
    		        	{ "data": null, "title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    } );
        		
        		
        		tblIpiRightType = $('#tblIpiRightType${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"clear">',
        			"paging":false,
					"ajax" : "${ctx}/mvc/reference/referenceTable/findAllIpiRightType.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['rightTypes.col-code']}"},
    		        	{ "data": "name", "title":"${msg['rightTypes.col-name']}"},
    		        	{ "data": "description", "title": "${msg['rightTypes.col-description']}"},
    		        	{ "data": "fkCcList", "title": "${msg['rightTypes.col-creation-classes']}", "render": creationClassRender, className:"text-center"},
    		        	{ "data": null, "title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    } );
    			
        		tblRightType = $('#tblRightType${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"clear">',
        			"paging":false,
					"ajax" : "${ctx}/mvc/reference/referenceTable/findAllRightType.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['rightTypes.col-code']}"},
    		        	{ "data": "name", "title":"${msg['rightTypes.col-name']}"},
    		        	{ "data": "description", "title": "${msg['rightTypes.col-description']}"},
    		        	{ "data": "fkCcList", "title": "${msg['rightTypes.col-creation-classes']}", "render": creationClassRender, className:"text-center"}, 
    		        	{ "data": null, "title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    } );

        		
        		function creationClassRender_filter(data,type,row,meta){
            		var sOut = "";
            		var ccMap = meta.settings.json.creationClassMap;
            		var ccList = [];
            		for(var i=0; i<data.length; i++){
           				var ccItem={};
           				ccItem.code = ccMap[data[i]].code;
           				ccItem.id = data[i];
           				ccList.push(ccItem);
               		}
       				
       				ccList.sort(function (a, b){
       				  var s1 = a.code.toLowerCase();
       				  var s2 = b.code.toLowerCase();
       				  return ((s1 < s2) ? -1 : ((s1 > s2) ? 1 : 0));
       				}); 
       					
					for(var i=0; i<ccList.length; i++){
						var ccCode = ccList[i].code;
						var ccId = ccList[i].id;
						sOut += ccCode + " " + ccMap[ccId].name + "\n";
					}
					return sOut;
        		}
        		
    		    function creationClassRender(data,type,row,meta){
    		    	if(type !== "display"){
						return creationClassRender_filter(data, type, row, meta);
					}
    		    	var ccMap = meta.settings.json.creationClassMap;
     				var ccOut = "<div>";
       				var ccList = []; 
       				

       				for(var i=0; i<data.length; i++){
           				var ccItem={};
           				ccItem.code = ccMap[data[i]].code;
           				ccItem.id = data[i];
           				ccList.push(ccItem);
               		}
       				
       				ccList.sort(function (a, b){
       				  var s1 = a.code.toLowerCase();
       				  var s2 = b.code.toLowerCase();
       				  return ((s1 < s2) ? -1 : ((s1 > s2) ? 1 : 0));
       				}); 	

       				for(var i=0; i<ccList.length; i++){
						var ccCode = ccList[i].code;
						var ccId = ccList[i].id;
						if(!ccCode && !ccId){
							continue;
						}
						ccOut += "<div class='creation-class-icon grid "+ ccCode + "' data-toggle='tooltip' title='" + ccCode + " ("+ escapeHtml(ccMap[ccId].name) + ")'></div>";
					}

					return ccOut+"</div>";
               	}
       			
        		function linkedIpiRoleRender(data,type,row,meta){
        			var ipiRoleMap = meta.settings.json.ipiRoleMap;
        			if(data && ipiRoleMap){
 						return ipiRoleMap[data].code;
         			}else{
               			return "";
               		}
        		}
        		
        		function actionRender(data, type, row, meta){
        			var htmlOut = "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
        			return htmlOut;
        		}
        		
        		function ipiRightTypeRender(data, type, row, meta){
        			var ipiCode = [];
        			var ipiRightTypeMap = meta.settings.json.ipiRightTypeMap;

        			var ipiRightTypeFlatList = row.ipiRightTypeFlat || [];
        			if (row != null && ipiRightTypeMap != null) {
	       				for(var i=0; i<ipiRightTypeFlatList.length; i++){
       						ipiCode.push(ipiRightTypeFlatList[i].code);
	       				}
       				
	       				return ipiCode.sort().join(", ");
       				}
       				return "Error";
        		}
        		
        		function rightTypeRender(data, type, row, meta){
        			var rightTypeMap = meta.settings.json.rightTypeMap;
         			if(data && rightTypeMap){
 						return rightTypeMap[data].code;
         			}else{
               			return "Error";
               		}
        		}
        		
        		
        		$('#tblCreationClass${idSfx}').on('click', 'button.btn-detail', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblCreationClass.row( tr ).data();
	
	       	         openModalDetail({
	       	        	"dialogTitle": "${msg['reference.creation-class']}",
	       	        	"wrapperClass": "modalValue${idSfx}",
	           	        "url":"${ctx}/mvc/reference/referenceTable/detailCreationClass",
	           	        "data":{ 
	           	        	"id":rowData.id,
	           	        	"insertMode":false
	           	         }
	           	     });
	       	         
	           	});
        		
        		
        		$('.container${idSfx}').on('click', 'button.add-creation-class', function () {	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['reference.creation-class']}",
	       	        	"wrapperClass": "modalValue${idSfx}",
	           	        "url":"${ctx}/mvc/reference/referenceTable/detailCreationClass",
	           	        "data":{
	           	        	"id":null,
	           	        	"insertMode":true
	           	        }
	           	     });
	       	         
	           	});
        		
        		
        		$('#tblWorkRole${idSfx}').on('click', 'button.btn-detail', function(event) {
	      	 		 var tr = $(this).closest('tr');
	      	         var rowData = tblWorkRole.row( tr ).data();

	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['roles.work-role']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/referenceTable/detailWorkRole",
	          	        "data":{ 
	          	        	"id":rowData.idRole,
	          	        	"insertMode":false
	          	         }
	          	     });
	      	         
	          	});
       		
       		
       			$('.container${idSfx}').on('click', 'button.add-work-role', function(event) {
	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['roles.work-role']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/referenceTable/detailWorkRole",
	          	        "data":{ 
	          	        	"id":null,
	          	        	"insertMode":true
	          	        }
	          	     });
	      	         
	          	});
       		
       		
       			$('#tblIpiRole${idSfx}').on('click', 'button.btn-detail', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblIpiRole.row( tr ).data();
	
	       	         openModalDetail({
	       	        	"dialogTitle": "${msg['roles.ipi-role-section']}",
	       	        	"wrapperClass": "modal${idSfx}",
	           	        "url":"${ctx}/mvc/reference/referenceTable/detailIpiRole",
	           	        "data":{ 
	           	        	"id":rowData.id,
	           	        	"insertMode":false
	           	         }
	           	     });
	           	});
      		
      		
      			$('.container${idSfx}').on('click', 'button.add-ipi-role', function () {	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['roles.ipi-role-section']}",
	       	        	"wrapperClass": "modal${idSfx}",
	           	        "url":"${ctx}/mvc/reference/referenceTable/detailIpiRole",
	           	        "data":{
	           	        	"id":null,
	           	        	"insertMode":true
	           	        }
	           	     }); 
	           	});
      			
      			
      			$('#tblIpiRightType${idSfx}').on('click', 'button.btn-detail', function(event) {
	      	 		 var tr = $(this).closest('tr');
	      	 	  	var rowData = tblIpiRightType.row( tr ).data();

	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['rightTypes.ipi-right-type-section']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/referenceTable/detailIpiRightType",
	          	        "data":{ 
	          	        	"id":rowData.id,
	          	        	"insertMode":false
	          	         }
	          	     });
	      	         
	          	});
       		
       		
       			$('.container${idSfx}').on('click', 'button.add-ipi-right-type', function(event) {
	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['rightTypes.ipi-right-type-section']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/referenceTable/detailIpiRightType",
	          	        "data":{ 
	          	        	"id":null,
	          	        	"insertMode":true
	          	         }
	          	     });
	          	});
       		
       		
       			$('#tblRightType${idSfx}').on('click', 'button.btn-detail', function(event) {
	      	 		 var tr = $(this).closest('tr');
	      	         var rowData = tblRightType.row( tr ).data();

	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['rightTypes.right-type-section']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/referenceTable/detailRightType",
	          	        "data":{ 
	          	        	"id":rowData.id,
	          	        	"insertMode":false
	          	         }
	          	     });
	      	         
	          	});
       		
       		
       			$('.container${idSfx}').on('click', 'button.add-right-type', function(event) {
	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['rightTypes.right-type-section']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/referenceTable/detailRightType",
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
			<div class="panel panel-default cc-panel"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-creation-class pull-right">${msg['creationClass.add-button']}</button>
					<h3 class="panel-title">${msg['reference.creation-class']}</h3>
				</div>
				<div class="panel-body">
					<table id="tblCreationClass${idSfx}"></table>
				</div>
			</div>
			
			<div class="panel panel-default ipi-role-panel"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-ipi-role pull-right">${msg['role.add-button']}</button>
					<h3 class="panel-title">${msg['roles.ipi-role-section']}</h3>
				</div>
				<div class="panel-body">
					 <table id="tblIpiRole${idSfx}"></table> 
				</div>
			</div>
			
			<div class="panel panel-default work-role-panel"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-work-role pull-right">${msg['work-role.add-button']}</button>
					<h3 class="panel-title">${msg['roles.work-role']}</h3>
				</div>
				<div class="panel-body">
					 <table id="tblWorkRole${idSfx}"></table> 
				</div>
			</div>
			
			<div class="panel panel-default ipi-right-type-panel"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-ipi-right-type pull-right">${msg['rightTypes.add-button']}</button>
					<h3 class="panel-title">${msg['rightTypes.ipi-right-type-section']}</h3>
				</div>
				<div class="panel-body">
					 <table id="tblIpiRightType${idSfx}"></table> 
				</div>
			</div>
			
			<div class="panel panel-default right-type-panel"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-right-type pull-right">${msg['rightCategoty.add-button']}</button>
					<h3 class="panel-title">${msg['rightTypes.right-type-section']}</h3>
				</div>
				<div class="panel-body">
					 <table id="tblRightType${idSfx}"></table> 
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>