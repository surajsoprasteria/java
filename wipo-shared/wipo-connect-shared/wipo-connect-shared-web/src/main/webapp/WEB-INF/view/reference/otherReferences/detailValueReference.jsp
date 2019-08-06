<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="RefOtherValuesDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
        		
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
				var insertMode = "${insertMode}" === "true";
				var editValueDialog = getParentDialog(".modalRefOtherDet");
				
            	
    			$.validator.addMethod("duplicateReferenceCode", function(value, element) {
					if(insertMode) {
						var table = $('#tblValueReferencesRefOtherDet').dataTable();
	             		var tableData = table.api().rows().data();
             			for(var i = 0; i < tableData.length; ++i) {
             				var regex = new RegExp('^' + value + '$', 'i');
             				if (regex.test(tableData[i].code)) {
             					//return false;
             				}
             			}
             			return true;
             		}
					return true;
        		}, "${msg['reference.duplicate-code']}");
    			
            	
				$(".container${idSfx} form").validate({rules:{
					"code" : {required:true, duplicateReferenceCode:true},
					"name" : {required:true}
				}});
				
				
				
             	$("#btnSave${idSfx}").click(function(e){
             		var detailValueForm = $("#form${idSfx}");
           	    	var table = $('#tblValueReferencesRefOtherDet').dataTable()
           	    	
					detailValueForm.data("validation-type", "LOCAL");
    	    		if (!detailValueForm.valid()){
    	    			return;
    	    		}
    	    		
    	    		if(!checkDuplicateReferenceCode($('#code${idSfx}').val())) {
    	    			return;
    	    		}
           	    	
        	    	var newValue = new Object();
        	    	newValue.code = $('#code${idSfx}').val();
        	    	newValue.name = $('#name${idSfx}').val();
        	    	newValue.isEdit = true;
           	    	
         			var tableData = table.api().rows().data();
         			var indexes = table.api().rows().indexes();
           	    	
             		if(insertMode) {
             			table.fnAddData(newValue);
             		} else {
             			
             			for(var i = 0; i < tableData.length; ++i) {
             				if (tableData[i].code == newValue.code) {
             					tableData[i].name = newValue.name;
             					tableData[i].isEdit = true;

             					table.api().row(indexes[i]).invalidate().draw();
             					break;
             				}
             			}
             			
             		}
             		
             		editValueDialog.close();
             	});
             	
             	
             	function checkDuplicateReferenceCode(value) {
					if(insertMode) {
						var table = $('#tblValueReferencesRefOtherDet').dataTable();
	             		var tableData = table.api().rows().data();
             			for(var i = 0; i < tableData.length; ++i) {
             				var regex = new RegExp('^' + value + '$', 'i');
             				if (regex.test(tableData[i].code)) {
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
							<div class="form-group">
								<label for="code${idSfx}">${msg['otherReferences.col-code']}</label>
								<form:input path="code" id="code${idSfx}" cssClass="form-control" readonly="${!insertMode}" />
							</div>
							<div class="form-group">
								<label for="name${idSfx}">${msg['otherReferences.col-name']}</label>
								<form:input path="name" id="name${idSfx}" cssClass="form-control" />
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