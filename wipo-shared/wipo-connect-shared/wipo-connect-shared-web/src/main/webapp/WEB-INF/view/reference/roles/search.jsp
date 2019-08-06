<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="RefRoles" />

<tiles:insertDefinition name="defaultTemplate">

	
	<tiles:putAttribute name="title">${msg['reference.roles']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['reference.roles']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    	 	$(document).ready(function(){
    	 		
    	 		//PAGE INIT
    			var canRender = "${canRender}" === "true";
    			
    			$(document).off(".${idSfx}");
    			
        		function readOnlyMode(){
        			if(!canRender){
        				$(".container${idSfx}").blockEdit();
        				$(".container${idSfx} .custom-validation-panel, .container${idSfx} .default-affiliation-panel").blockEdit("unblock");
        			}
        		}
    			
    			
    			
        		$('#tblIpiRole${idSfx}').dataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/roles/findAllIpiRole.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['role.col-code']}"},
    		        	{ "data": "name", "title":"${msg['role.col-name']}"},
    		        	{ "data": "description", "title": "${msg['role.col-description']}"},
    		        	{ "data": "fkCcList", "title": "${msg['role.col-creation-classes']}", "render": creationClassRender},
    		        	{ "data": null, "title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    } );
    			
        		$('#tblRole${idSfx}').dataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/roles/findAllRole.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['role.col-code']}"},
    		        	{ "data": "name", "title":"${msg['role.col-name']}"},
    		        	{ "data": "description", "title": "${msg['role.col-description']}"},
    		        	{ "data": "fkIpiRole", "title": "${msg['role.col-linked-ipi-role']}", "render":linkedIpiRoleRender},
    		        	{ "data": "fkCcList", "title": "${msg['role.col-creation-classes']}", "render": creationClassRender},
    		        	{ "data": null, "title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"30px"}
    		        ]
    		    } );
        		
        		
        		$('#tblRole${idSfx}').on('click', 'button.btn-detail', function(event) {
					 var tblObj = $('#tblRole${idSfx}').dataTable().api();
	      	 		 var tr = $(this).closest('tr');
	      	         var rowData = tblObj.row( tr ).data();

	      	         openModalDetail({
	      	        	"dialogTitle": "${msg['roles.work-role']}",
	      	        	"wrapperClass": "modal${idSfx}",
	          	        "url":"${ctx}/mvc/reference/roles/detailWorkRole",
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
	          	        "url":"${ctx}/mvc/reference/roles/detailWorkRole",
	          	        "data":{ 
	          	        	"id":null,
	          	        	"insertMode":true
	          	        }
	          	     });
	      	         
	          	});
        		
        		
        		$('#tblIpiRole${idSfx}').on('click', 'button.btn-detail', function () {
					 var tblObj = $('#tblIpiRole${idSfx}').dataTable().api();
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();
	
	       	         openModalDetail({
	       	        	"dialogTitle": "${msg['roles.ipi-role-section']}",
	       	        	"wrapperClass": "modal${idSfx}",
	           	        "url":"${ctx}/mvc/reference/roles/detailIpiRole",
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
	           	        "url":"${ctx}/mvc/reference/roles/detailIpiRole",
	           	        "data":{
	           	        	"id":null,
	           	        	"insertMode":true
	           	        }
	           	     });
	       	         
	           	});
        		
        		
       			function creationClassRender(data, type, row, meta) {
       				var ccCode = [];
       				var ccMap = meta.settings.json.creationClassMap;
       				
       				if (data != null && ccMap != null) {
	       				data.forEach(function(value, index) {
	       					ccCode.push(ccMap[value].code);
	       				});
	       				
	       				return ccCode.sort().join(", ");
       				}
       				return "Error";
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
    			

    		}); 
    		
    		
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<!-- Tab panels -->
			 <div class="tab-content">
				 <jsp:include page="/WEB-INF/view/reference/roles/ipi-role-section.jsp" flush="true"></jsp:include>
				 <jsp:include page="/WEB-INF/view/reference/roles/role-section.jsp" flush="true"></jsp:include>
			 </div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>