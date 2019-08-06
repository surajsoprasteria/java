<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="WorkImport" />
<c:set var="pageTemplate" value="defaultTemplate" />

<tiles:insertDefinition name="${pageTemplate}">

	<tiles:putAttribute name="title">${msg['work.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['work.page-info']}</tiles:putAttribute>
	
    <tiles:putAttribute name="pageScript">
    	<script type="text/javascript">
	    	$(document).ready(function(){
	    		
	    		var tblObj = null;

	    		tblObj = $('#tblResults${idSfx}').DataTable( {
    				"dom": '<"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/workImport/findWorkImportList.json",
					"order":[[0,"desc"]],
    				"columns": [
						{ "data": "id", "title":"${msg['workImport.col-id']}"},
						{ "data": "importCode", "title":"${msg['workImport.col-code']}"},
						{ "data": "inputFile.fileName", "title":"${msg['workImport.col-filename']}"},
						{ "data": "fkStatus", "title":"${msg['workImport.col-status']}", "render":statusRender},
						{ "data": "startDate", "title":"${msg['workImport.col-start']}", "render":colRender("dateTime")},
						{ "data": "endDate", "title":"${msg['workImport.col-end']}", "render":colRender("dateTime")},
						{ "data": "rowCount", "title":"${msg['workImport.col-row-count']}",className:"text-center","render":colRender("integer_with_default_value")},
						{ "data": "insertedItems", "title":"${msg['workImport.col-inserted-items']}",className:"text-center","render":colRender("integer_with_default_value")},
						{ "data": "linesWithErrors", "title":"${msg['workImport.col-lines-with-errors']}",className:"text-center","render":colRender("integer_with_default_value")}
    		        ]
    		    } );
        		
        		function statusRender(data, type, row, meta) {
           			var statusMap = meta.settings.json.statusMap;
					return statusMap[data].value;
    			}
	    		
	    	});
    		//# sourceURL=${idSfx}.js
    	</script>
    </tiles:putAttribute>

    <tiles:putAttribute name="body">
		<div class="container${idSfx}">			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">${msg['workImport.import-list']}</h3>
				</div>
				
				<div class="panel-body">
					<table id="tblResults${idSfx}"></table>
				</div>
			</div>
		
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>