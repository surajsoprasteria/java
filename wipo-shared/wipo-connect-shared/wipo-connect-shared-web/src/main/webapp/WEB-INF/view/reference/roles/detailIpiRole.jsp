<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefRolesIpiDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
				var insertMode = "${insertMode}" === "true";
    			
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
				
    			//Init UI Plugins
				$(".container${idSfx} select.form-control").selectpicker();
            	$(".container${idSfx} .input-group.date").datepicker(); 
            	
            	//Need for select input
            	if(!insertMode) {
            		$(".container${idSfx} form [name='fkCcList']").blockEdit();
            	}
            	
    			$.validator.addMethod("duplicateIpiCode", function(value, element) {
					if(insertMode) {
						var table = $('#tblIpiRoleRefRoles').dataTable();
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
					"code" : {required:true, duplicateIpiCode:true},
					"name" : {required:true},
					"fkCcList" : {required:true}
				}});
				
				
             	$("#btnSave${idSfx}").click(function(e){             		
    	    		if (!$("#form${idSfx}").valid()){
    	    			return;
    	    		}             		

             		$("#form${idSfx}").data("validation-type", "LOCAL"); 
           	    	$("#form${idSfx}").attr('action', "${ctx}/mvc/reference/roles/saveIpiRole.json");
           	    	$("#form${idSfx}").submit();
           	    	
             	});         	
        		
        		
        		//remember to add validation before init ajax form
        		initAjaxForm('.container${idSfx} form' ,{
            			success:function(data,status,xhr,formObj){
                			if (formObj.attr('action')){            				
                				goToUrl('${ctx}/mvc/reference/roles/search');
                			}
                		}
        		
        		});

    		});
        		
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" action="" method="POST">
				<form:hidden path="id" id="id${idSfx}"/>
				<form:hidden path="fkName" id="fkName${idSfx}"/>
				<form:hidden path="fkDescription" id="fkDescription${idSfx}"/>
				<div>
					<div class="form-row">
						<div class="form-group">
							<label for="code${idSfx}">${msg['role.col-code']}</label>
							<form:input id="code${idSfx}" path="code" cssClass="form-control" readonly="${!insertMode}" />
						</div>
						<div class="form-group medium">
							<label for="name${idSfx}">${msg['role.col-name']}</label>
							<form:input id="name${idSfx}" path="name" cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="fkCcList${idSfx}" >${msg['role.col-creation-classes']}</label>
							<form:select path="fkCcList" id="fkCcList${idSfx}" cssClass="form-control">
								<form:options items="${ccList}" itemValue="id" itemLabel="code"/>
							</form:select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group extra">
							<label for="description${idSfx}" >${msg['role.col-description']}</label>
							<form:textarea id="description${idSfx}" path="description" cssClass="form-control" />
						</div>
					</div>
							
				</div>
				
				<div class="form-row">
					<div class="pull-right">
						<button type="button" id="btnSave${idSfx}" class="btn btnSave btn-primary">${msg['global.save']}</button>
					</div>
					<div class="clear"></div> 
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>