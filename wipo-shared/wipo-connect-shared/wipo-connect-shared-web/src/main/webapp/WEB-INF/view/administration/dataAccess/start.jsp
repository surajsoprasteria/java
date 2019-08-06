<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="DtAcc" />

<tiles:insertDefinition name="defaultTemplate">

<tiles:putAttribute name="title">${msg['functions.data-access']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['functions.data-access']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
        		
    			//$(".container${idSfx} .input-group.date").datepicker();
        		//$(".container${idSfx} select.form-control").selectpicker();

        		var searchURL = "${ctx}/mvc/administration/dataAccess/find.json";

        		var tblObj = $('#tblResults${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : searchURL,
    				"columns": [
    		    		{"data": "cmo.code", "title":"${msg['dataAccess.col-cmo-code']}"},
    		    		{"data": "cmo.acronym", "title":"${msg['dataAccess.col-cmo-acronym']}"},
    		    		{"data": "dataOriginCodeList[, ]", "title":"${msg['dataAccess.col-sources']}"},
        		        {"data": null, "title":"" , "sortable":false, "width":"50", "render": actionRender, className:"text-center"}
					]
    		    });
        		
        		function actionRender(data, type, row, meta) {
					var htmlOut = "";

					htmlOut += "<div class='button-wrapper' title='${msg['dataAccess.show-details']}'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
					htmlOut += "<div class='button-wrapper' title='${msg['global.delete']}'><button type='button' class='btn btn-xs btn-primary btn-delete'><span class='glyphicon glyphicon-trash'></span></button></div>";

					return htmlOut;
				}

        		$('#tblResults${idSfx}').on('click', 'button.btn-detail', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();
	
	       	         openModalDetail({
	       	        	"dialogTitle": "${msg['dataAccess.detail']}",
	           	        "url":"${ctx}/mvc/administration/dataAccess/detail",
	           	        "data":{ "id":rowData.id },
	           	        "reloadDtTarget": "#tblResults${idSfx}"
	           	     });
	       	         
	           	});

        		$('.container${idSfx}').on('click', 'button.client-info-add', function () {
	       	         openModalDetail({
	       	        	"dialogTitle": "${msg['dataAccess.detail']}",
	           	        "url":"${ctx}/mvc/administration/dataAccess/detail",
						"reloadDtTarget": "#tblResults${idSfx}"
	           	     });
	       	         
	           	});


        		$('#tblResults${idSfx}').on('click', 'button.btn-delete', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();

	       	         showConfirmMessage({
						"confirmAction":function(dialog){

							executeAction({
								url : "${ctx}/mvc/administration/dataAccess/delete.json",
								data : {id : rowData.id},
								success: function(data, status, xhr,formObj) {
									showSuccessMessage({message: "${msg['global.result-ok']}"});
								},
								complete: function(xhr, status){
									tblObj.ajax.reload();
								}
							});
														
							dialog.close();
						}
			       	 });	       	         
	           	 });
				
    		});

    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary pull-right client-info-add">${msg['dataAccess.add-button']}</button>
					<h3 class="panel-title">${msg['global.results']}</h3>
				</div>
				<div class="panel-body">
					<table id="tblResults${idSfx}"></table>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>