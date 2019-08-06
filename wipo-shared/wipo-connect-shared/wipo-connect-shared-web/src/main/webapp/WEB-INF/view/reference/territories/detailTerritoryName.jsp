<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="RefTerritoryNameDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
    			
    			var insertMode = "${insertMode}" === "true";
    			var tblObj = $('#tblTerritoryNamesRefTerritoryDet').dataTable().api();
    			var editValueDialog = getParentDialog(".modalDialogRefTerritoryDet");
    			
				//Init UI Plugins
				$(".container${idSfx} select.form-control").selectpicker();
            	$(".container${idSfx} .input-group.date").datepicker(); 
    			
    			$.validator.addMethod("dateName", function(value, element) {    				
    				var startDate = new Date($('#startDate${idSfx}').val());
    				var endDate = new Date($('#endDate${idSfx}').val());
    			
					if (startDate <= +endDate) {
						return true;
					}
					return false;
        		}, "${msg['territory.end-date-error-message']}");
    			
    			
    			/* $.validator.addMethod("duplicateTISA", function(value, element) {
					if(insertMode) {
						var table = $('#tblTerritoryNamesRefTerritoryDet').dataTable();
	             		var tableData = table.api().rows().data();
	             		for(var i = 0; i < tableData.length; ++i) {
             				var regex = new RegExp('^' + value + '$', 'i');
             				if (regex.test(tableData[i].tisa)) {
             					//return false;
             				}
             			}
             			return true;
             		}
					return true;
        		}, "${msg['reference.duplicate-code']}"); */
             	
             	
				$(".container${idSfx} form").validate({rules:{
					"tisa" : {	required:true, 
								remote: function(element) {
			                		return {
			                			"url": globalParams.ctxMvc + "/reference/territories/checkTisaCodeUniqueness.json",
			                			"type": "post",
			                			"data": {
			                				"id": "${vo.id}",
			                				"code": $(element).val()
			                			}
			                		}
								}},
					"name" : {required:true},
					"startDate" : {required:true},
					"endDate" : {required:true, dateName:true}
				}});

				
             	$("#btnSave${idSfx}").click(function(e){
             		var detailValueForm = $("#form${idSfx}");
           	    	var table = $('#tblTerritoryNamesRefTerritoryDet').dataTable()
           	    	
					detailValueForm.data("validation-type", "LOCAL");
    	    		if (!detailValueForm.valid()){
    	    			return;
    	    		}
    	    		
    	    		if(!checkDuplicateTISA($('#tisa${idSfx}').val())) {
    	    			return;
    	    		}
           	    	
        	    	var newValue = new Object();
        	    	newValue.tisa = $('#tisa${idSfx}').val();
        	    	newValue.name = $('#name${idSfx}').val();
        	    	newValue.startDate = $('#startDate${idSfx}').val();
        	    	newValue.endDate = $('#endDate${idSfx}').val();
        	    	newValue.isEdit = true;
           	    	
         			var tableData = table.api().rows().data();
         			var indexes = table.api().rows().indexes();
           	    	
             		if(insertMode) {
             			table.fnAddData(newValue);
             		} else {
             			
             			for(var i = 0; i < tableData.length; ++i) {
             				if (tableData[i].tisa == newValue.tisa) {
             					tableData[i].name = newValue.name;
             					tableData[i].startDate = newValue.startDate;
             					tableData[i].endDate = newValue.endDate;
             					tableData[i].isEdit = true;

             					table.api().row(indexes[i]).invalidate().draw();
             					break;
             				}
             			}
             			
             		}
             		
             		editValueDialog.close();
             	});
             	
             	
             	function checkDuplicateTISA(value) {
					if(insertMode) {
						var table = $('#tblTerritoryNamesRefTerritoryDet').dataTable();
	             		var tableData = table.api().rows().data();
	             		for(var i = 0; i < tableData.length; ++i) {
             				var regex = new RegExp('^' + value + '$', 'i');
             				if (regex.test(tableData[i].tisa)) {
             					showWarnMessage({message:"${msg['reference.duplicate-code']}"});
             					return false;
             				}
             			}
             			return true;
             		}
					return true;
             	}

             	
			
    		});
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" action="" method="POST">
				<form:hidden path="id" id="id${idSfx}"/>
				
					<div class="panel-body">
						
						<div class="form-row">
							<div class="form-group small">
								<label for="tisa${idSfx}">${msg['territory.col-tisa']}</label>
								<form:input path="tisa" id="tisa${idSfx}" cssClass="form-control" readonly="${!insertMode}" />
							</div>
							
							<div class="form-group medium">
								<label for="name${idSfx}">${msg['territory.col-name']}</label>
								<form:input path="name" id="name${idSfx}" cssClass="form-control" />
							</div>
							
							<div class="form-group">
								<label for="startDate${idSfx}">${msg['territory.col-start-date']}</label>
								<div class="input-group date">
									<form:input path="startDate" id="startDate${idSfx}" cssClass="form-control" />
									<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
							
							<div class="form-group">
								<label for="endDate${idSfx}">${msg['territory.col-end-date']}</label>
								<div class="input-group date">
									<form:input path="endDate" id="endDate${idSfx}" cssClass="form-control" />
									<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
							
						</div>
						
						<div class="form-row">
							<div class="pull-right">
								<button type="button" id="btnSave${idSfx}" class="btn btnSave btn-primary">${msg['global.save']}</button>
							</div>
						</div>
					</div>
					
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>