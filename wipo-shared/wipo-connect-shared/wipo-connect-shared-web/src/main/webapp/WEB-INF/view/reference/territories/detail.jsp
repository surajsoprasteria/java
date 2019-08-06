<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="RefTerritoryDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="RefTerritoryDet" />
	</c:otherwise>
</c:choose>

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
				var insertMode = "${insertMode}" === "true";
				var readOnlyMode = "${readOnlyMode}" === "true";

				//Init UI Plugins
				$(".container${idSfx} select.form-control").selectpicker();
            	$(".container${idSfx} .input-group.date").datepicker(); 
            	
            	//Need for select input
            	if(!insertMode) {
            		$(".container${idSfx} form [name='fkType']").blockEdit();
            	}

            	if(readOnlyMode) {
            		$(".container${idSfx}").blockEdit();
            	}
            	
    			$.validator.addMethod("dateTerritory", function(value, element) {
    				var startDate = new Date($('#startDate${idSfx}').val());
    				var endDate = new Date($('#endDate${idSfx}').val());
    			
					if (startDate <= +endDate) {
						return true;
					}
					return false;
        		}, "${msg['territory.end-date-error-message']}");
    			
    			$.validator.addMethod("duplicateTISN", function(value, element) {
					if(insertMode) {
						var table = $('#tblResultsRefTerritory').dataTable();
	             		var tableData = table.api().rows().data();
             			for(var i = 0; i < tableData.length; ++i) {
             				if (tableData[i].code === value) {
             					//return false;
             				}
             			}
             			return true;
             		}
					return true;
        		}, "${msg['reference.duplicate-code']}");
            	
            	$(".container${idSfx} form").validate({rules:{
					"code" : {  required:true, 
								remote: function(element) {
			                		return {
			                			"url": globalParams.ctxMvc + "/reference/territories/checkTisnCodeUniqueness.json",
			                			"type": "post",
			                			"data": {
			                				"id": "${vo.id}",
			                				"code": $(element).val()
			                			}
			                		}
								}
					},
					"fkType" : {required:true},
					"startDate" : {required:true},
					"endDate" : {required:true, dateTerritory:true}
				}});
				
				
				$('#tblTerritoryNames${idSfx}').dataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/territories/findTerritoryNamesByCode.json?code=${vo.code}",
    				"columns": [
						{ "data": "tisa", "title":"${msg['territory.col-tisa']}"},
						{ "data": "name", "title": "${msg['territory.col-name']}"},
						{ "data": "startDate", "title": "${msg['territory.col-start-date']}", "render": colRender("date")},
    		        	{ "data": "endDate", "title": "${msg['territory.col-end-date']}", "render": colRender("date")},
    		            { "data": null, "title":"", "sortable":false,"render": actionRender, className:"text-center",width:"30px"}
    		        ]
    		    } );
				
        		function actionRender(data, type, row, meta) {
					var htmlOut = "";
					htmlOut += "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
					return htmlOut;
				}
				
        		
        		//remember to add validation before init ajax form
        		initAjaxForm(".container${idSfx} form" ,{
            			success:function(data,status,xhr,formObj){
                			if (formObj.attr('action')){
                				goToUrl("${ctx}/mvc/reference/territories/search");
                			}
                		}
        		
        		});
        		
        		
        		$('#tblTerritoryNames${idSfx}').on('click', 'button.btn-detail', function () {
					var tblObj = $('#tblTerritoryNames${idSfx}').dataTable().api();
	       	 		var tr = $(this).closest('tr');
	       	        var rowData = tblObj.row( tr ).data();
	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['territory.names']}",
	       	        	"wrapperClass": "modalDialog${idSfx}",
	           	        "url":"${ctx}/mvc/reference/territories/detailTerritoryName",
	           	        "data":{ 
	           	        	"territoryName.idTerritoryName":rowData.id,
	           	        	"territoryName.startDate":rowData.startDate.replace(/\-/g, "/"),
	           	        	"territoryName.endDate":rowData.endDate.replace(/\-/g, "/"),
	           	        	"territoryName.tisa":rowData.tisa,
	           	        	"territoryName.name":rowData.name,
	           	        	"territoryName.fkTerritory":rowData.fkTerritory,
	           	        	"insertMode":false
	           	         }
	           	     });
	       	         
	           	});
       		
       			$('.container${idSfx}').on('click', 'button.add-territory-name', function () {	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['territory.names']}",
	       	        	"wrapperClass": "modalDialog${idSfx}",
	           	        "url":"${ctx}/mvc/reference/territories/detailTerritoryName",
	           	        "data":{
	           	        	"territoryName.idTerritoryName":null,
	           	        	"insertMode":true
	           	        }
	           	     });
	       	         
	           	});
       			
       			
       			$("#btnSave${idSfx}").click(function(e){
             		var table = $('#tblTerritoryNames${idSfx}').dataTable();
             		var tableData = table.api().rows().data();
             		var indexes = table.api().rows().indexes();
             		var currentForm = $("#form${idSfx}");
             		var listIndex = 0;
             		
    	    		if (!currentForm.valid()){
    	    			return;
    	    		}
             		
             		$("#form${idSfx} .row-dynamic").remove();
             		
         			for(var i = 0; i < tableData.length; ++i) {
         				var tableDataRow = table.api().row(indexes[i]).data();
         				var newRow = $(".templateTerritoryName .row-dynamic").clone();
         			
         				if (tableDataRow.isEdit !== true ) { continue; }
         				
         				newRow.find(":input").each(function(){
        					var newName = $(this).attr("name") || "";
        					newName = newName.replace("%INDEX%",listIndex);
        					$(this).attr("name",newName);
        					
        					var newId = $(this).attr("id") || "";
        					newId = newId.replace("%INDEX%",listIndex);
        					$(this).attr("id",newId);
        				});
         				
         				newRow.find("input[name*='.id']").val(tableDataRow.id);
         				newRow.find("input[name*='.tisa']").val(tableDataRow.tisa);
         				newRow.find("input[name*='.name']").val(tableDataRow.name);
         				newRow.find("input[name*='.fkName']").val(tableDataRow.fkName);
         				newRow.find("input[name*='.startDate']").val(tableDataRow.startDate);
         				newRow.find("input[name*='.endDate']").val(tableDataRow.endDate);
         				
         				listIndex++;
         				$("#form${idSfx}").append(newRow);
         				
         			}
             		

         			currentForm.data("validation-type", "LOCAL"); 
         			currentForm.attr('action', "${ctx}/mvc/reference/territories/save.json");
         			currentForm.submit();
             	});

       			$('#tblTerritoryChildren${idSfx}').dataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/territories/findTerritoryChildrenByTisa.json?tisaCode=${vo.tisa}",
    				"columns": [
    					{ "data": "code", "title": "${msg['territoryChildren.tisn']}"},
						{ "data": "tisa", "title":"${msg['territoryChildren.tisa']}"},
						{ "data": "name", "title": "${msg['territoryChildren.name']}"},
						{ "data": "fkType", "title": "${msg['territory.col-type']}", "render": territoryTypeRender},
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
					var stDetail = "";
					if(readOnlyMode){
						stDetail = "disabled";
					}
					htmlOut += "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'"+stDetail+"><span class='glyphicon glyphicon-search'></span></button></div>";
					return htmlOut;
				}

        		$('#tblTerritoryChildren${idSfx}').on('click', 'button.btn-detail', function () {
					 var tblObj = $('#tblTerritoryChildren${idSfx}').dataTable().api();
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();
	
	       	         openModalDetail({
	       	        	 "dialogTitle": "${msg['territory.detail']}",
	           	         "url":"${ctx}/mvc/reference/territories/detail",
	           	         "data":{ "idTerritory" : rowData.id,
		           	         	  "readOnlyMode" : true }
	           	     });
	       	         
	           	});
       			

        		
    		});
    		
    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" action="" method="POST">
				<form:hidden path="id" id="id${idSfx}"/>
				 
					<jsp:include page="/WEB-INF/view/reference/territories/general-info.jsp" flush="true"></jsp:include>
					<jsp:include page="/WEB-INF/view/reference/territories/territory-names-section.jsp" flush="true"></jsp:include>
				 	<jsp:include page="/WEB-INF/view/reference/territories/territory-children-section.jsp" flush="true"></jsp:include>
				 
			 		<div class="form-row">
						<div class="pull-right">
							<button type="button" id="btnSave${idSfx}" class="btn btnSave btn-primary">${msg['global.save']}</button>
						</div>
						<div class="clear"></div> 
					</div>
				
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/view/reference/territories/template.jsp" flush="true"></jsp:include>
	</tiles:putAttribute>
</tiles:insertDefinition>