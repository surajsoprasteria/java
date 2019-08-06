<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefOther" />

<tiles:insertDefinition name="defaultTemplate">

	
	<tiles:putAttribute name="title">${msg['reference.other-references']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['reference.other-references']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    	 	$(document).ready(function(){
    			$(document).off(".${idSfx}"); //PAGE INIT
				$(".container${idSfx} select.form-control").selectpicker();
    			
				var tblObj = $('#tblResults${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/otherReferences/findAllOtherReference.json",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['otherReferences.col-code']}"},
    		        	{ "data": "name", "title":"${msg['otherReferences.col-name']}"},
    		        	{ "data": "managedBy", "title":"${msg['otherReferences.col-managed-by']}", "render": managedByRender},
    		        	{ "data": null, "title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"25px"}
    		        ]
    		    } );
    			
        		function actionRender(data,type,row,meta){
         			var htmlOut = "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
         			return htmlOut;
        		}
        		
        		
       			function managedByRender(data, type, row, meta) {
					<c:forEach items="${referenceManagedByEnum}" var="item">
					if (data == "${item}") {
						return "${msg[item.msgCode]}";
					}
					</c:forEach>
       				return "Error";
               	}
        		
        		$('#tblResults${idSfx}').on('click', 'button.btn-detail', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();
	
	       	         openModalDetail({
	       	        	"dialogTitle": "${msg['otherReferences.reference']}",
	       	        	"wrapperClass": "modalValue${idSfx}",
	           	        "url":"${ctx}/mvc/reference/otherReferences/detail",
	           	        "data":{ 
	           	       		"id":rowData.id,
	           	       		"insertMode":false
	           	        }
	           	     });
	       	         
	           	});
        		
        		
        		$('.container${idSfx}').on('click', 'button.add-reference-type', function () {	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['otherReferences.reference']}",
	       	        	"wrapperClass": "modalValue${idSfx}",
	           	        "url":"${ctx}/mvc/reference/otherReferences/detail",
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
			<div class="panel panel-default panel-class"> 
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary add-reference-type pull-right">${msg['otherReferences.add-button']}</button>
					<h3 class="panel-title">${msg['reference.other-references']}</h3>
				</div>
				<div class="panel-body">
					<table id="tblResults${idSfx}"></table>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>