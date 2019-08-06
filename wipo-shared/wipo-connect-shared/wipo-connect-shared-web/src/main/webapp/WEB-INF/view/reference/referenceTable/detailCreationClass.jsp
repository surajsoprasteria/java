<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefCreatClassDet" />

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
            	
            	
				$(".container${idSfx} form").validate({rules:{
					"code" : {required:true, remote: function(element) {
                		return {
                			"url": globalParams.ctxMvc + "/reference/referenceTable/checkCcCodeUniqueness.json",
                			"type": "post",
                			"data": {
                				"id": "${vo.id}",
                				"code": $(element).val()
                			}
                		}
                	}},
					"name" : {required:true}
				}});
				
				
             	$("#btnSave${idSfx}").click(function(e){
             		var table = $('#tblResultsRefCreatClass').dataTable();
             		var tableData = table.api().rows().data();
             		var indexes = table.api().rows().indexes();
             		
    	    		if (!$("#form${idSfx}").valid()){
    	    			return;
    	    		}             		

             		$("#form${idSfx}").data("validation-type", "LOCAL"); 
           	    	$("#form${idSfx}").attr('action', "${ctx}/mvc/reference/referenceTable/saveCreationClass.json");
           	    	$("#form${idSfx}").submit();
           	    	
             	});         	
        		
        		
        		//remember to add validation before init ajax form
        		initAjaxForm('.container${idSfx} form' ,{
            			success:function(data,status,xhr,formObj){
                			if (formObj.attr('action')){            				
                				goToUrl('${ctx}/mvc/reference/referenceTable/search');
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
							<label for="code${idSfx}">${msg['creationClass.col-code']}</label>
							<form:input id="code${idSfx}" path="code" cssClass="form-control" readonly="${!insertMode}" />
						</div>
						<div class="form-group medium">
							<label for="name${idSfx}">${msg['creationClass.col-name']}</label>
							<form:input id="name${idSfx}" path="name" cssClass="form-control" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group extra">
							<label for="description${idSfx}" >${msg['creationClass.col-description']}</label>
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