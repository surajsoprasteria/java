<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="AdmIssueLogSrc" />

<tiles:insertDefinition name="defaultTemplate">

	
	<tiles:putAttribute name="title">${msg['issuelog.page-info']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['issuelog.page-info']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
    			//PAGE INIT
				
    			$(".container${idSfx} select.form-control").selectpicker();
    			
        		$('#tblResults${idSfx}').dataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/administration/issueLog/find.json",
    				"columns": [
    		           	{ "data": "serverDate", "title": "${msg['issueLog.date-insert-col']}","width":"150px", "render":colRender("dateTime") },
    		            { "data": "message", "title": "${msg['issueLog.message-col']}"}, 
    		        	{ "data": null, "title":"", "sortable":false,"render": actionRender, "className":"text-center","width":"50px"} 
    		        ],
    		    	"order": [[0,"desc"]]
		    	});
				var tblObj = $('#tblResults${idSfx}').dataTable().api();
        		
        		function actionRender(data, type, row, meta) {
					var htmlOut = "";
					htmlOut += "<div class='button-wrapper' title='${msg['global.show-details']}'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
					return htmlOut;
				}

				$(".container${idSfx} #tblResults${idSfx}").on("click",".btn-detail",function(){
					var tr = $(this).closest("tr");
					var rowData = tblObj.row(tr).data();

					openModalDetail({
	       	        	 "dialogTitle": "${msg['issueLog.summary']}",
	           	         "url":"${ctx}/mvc/administration/issueLog/detail",
	           	         "data":{ "id":rowData.id }
	           	     });
					
				});

				$("#btnDeleteAll${idSfx}").click(function() {
				    showConfirmMessage( {
				        "confirmAction": function(dialog) {
				            executeAction( {
				                url: "${ctx}/mvc/administration/issueLog/deleteAll.json",
				                data:
				                {},
				                success: function(data, status, xhr, formObj) {
				                    goToUrl("${ctx}/mvc/administration/issueLog/search");
				                }
				            });
				        }
				    });
				});

        	});
    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">

		<div class="panel panel-default groups-panel"> 
				<div class="panel-heading">
					<div class="float-left">
						<h3 class="panel-title">${msg['global.results']}</h3>
					</div>
					<div class="float-right">
						<button type="button" id="btnDeleteAll${idSfx}" class="btn btn-primary pull-right">${msg['issueLog.delete-all']}</button>
					</div>
					<div class="clear"></div> 
				</div>
				<div class="panel-body">
					<table id="tblResults${idSfx}"></table>
				</div>
			</div>


		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>