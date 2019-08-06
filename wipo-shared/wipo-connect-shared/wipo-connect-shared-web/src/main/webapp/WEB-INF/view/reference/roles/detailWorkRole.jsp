<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="FT_STRING" value="<%= FieldTypeEnum.STRING %>"/>
<c:set var="FT_INTEGER" value="<%= FieldTypeEnum.INTEGER %>"/>
<c:set var="FT_DECIMAL" value="<%= FieldTypeEnum.DECIMAL %>"/>
<c:set var="FT_DATE" value="<%= FieldTypeEnum.DATE %>"/>
<c:set var="FT_LIST" value="<%= FieldTypeEnum.LIST %>"/>
<c:set var="FT_FILE" value="<%= FieldTypeEnum.FILE %>"/>

<sec:authorize access="hasAuthority('${REFERENCE_MGMT_CODE}')" var="canRender" />
<c:set var="idSfx" value="WorkRoleDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			var insertMode = "${insertMode}" === "true";
    			var canRender = "${canRender}" === "true";
    			
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");

				//Init UI Plugins
				$(".container${idSfx} select.form-control").selectpicker();
            	$(".container${idSfx} .input-group.date").datepicker(); 
				
            	
    			$.validator.addMethod("duplicateWorkCode", function(value, element) {
					if(insertMode) {
						var table = $('#tblRoleRefRoles').dataTable();
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
					"code" : {required:true, duplicateWorkCode:true},
					"name" : {required:true},
					"fkIpiRole" : {required:true},
					"fkCcList" : {required:true}
				}});
				

            	//Need for select input
            	if(!insertMode) {
            		$(".container${idSfx} form [name='fkIpiRole']").blockEdit();
            	}
            	$(".container${idSfx} form [name='fkCcList']").blockEdit();
             	

        		
        		//remember to add validation before init ajax form
        		initAjaxForm(".container${idSfx} form" ,{
           			success:function(data,status,xhr,formObj){
               			goToUrl("${ctx}/mvc/reference/roles/search");
               		}
        		
        		});
        		
        		
             	$("#btnSave${idSfx}").click(function(e){
             		if (!$("#form${idSfx}").valid()){
    	    			return;
    	    		}    
             		
             		$("#form${idSfx}").data("validation-type", "LOCAL"); 
           	    	$("#form${idSfx}").attr('action', "${ctx}/mvc/reference/roles/saveWorkRole.json");
           	    	$("#form${idSfx}").submit();
             	});
             	
             	
             	$(".container${idSfx} form [name='fkIpiRole']").change(function() {
             		var fkIpi = $(this).val();
             		$.get( "${ctx}/mvc/reference/roles/findCcByIpiRole.json", { "id": fkIpi } )
             			.done(function( data ) {
             				$(".container${idSfx} form [name='fkCcList']")
             				.val(data.data)
              				.selectpicker("refresh");
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
				<form:hidden path="fkName" id="fkName${idSfx}"/>
				<form:hidden path="fkDescription" id="fkDescription${idSfx}"/>
				
				
				 <div class="panel panel-default ">
					<div class="panel-body">
						<div class="form-row">
							<div class="form-group">
								<label for="code${idSfx}" >${msg['role.col-code']}</label>
								<form:input id="code${idSfx}" path="code" cssClass="form-control" readonly="${!insertMode}" />
							</div>
							<div class="form-group">
								<label for="name${idSfx}" >${msg['role.col-name']}</label>
								<form:input id="name${idSfx}" path="name" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label for="fkIpiRole${idSfx}" >${msg['role.col-linked-ipi-role']}</label>
								<form:select path="fkIpiRole" id="roleType${idSfx}" cssClass="form-control">
									<form:option value=""></form:option>
									<form:options items="${ipiRoleList}" itemValue="id" itemLabel="code"/>
								</form:select>

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