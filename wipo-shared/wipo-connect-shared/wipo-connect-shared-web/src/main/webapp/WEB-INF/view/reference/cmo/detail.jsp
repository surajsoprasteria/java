<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="RefCmoDet" />

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
                			"url": globalParams.ctxMvc + "/reference/cmo/checkCodeUniqueness.json",
                			"type": "post",
                			"data": {
                				"id": "${vo.id}",
                				"code": $(element).val()
                			}
                		}
                	}},
					"acronym" : {required:true},
					"name" : {required:true},
					"fkType" : {required:true}
				}});
				
				
             	$("#btnSave${idSfx}").click(function(e){             		
    	    		if (!$("#form${idSfx}").valid()){
    	    			return;
    	    		}             		

             		$("#form${idSfx}").data("validation-type", "LOCAL"); 
           	    	$("#form${idSfx}").attr('action', "${ctx}/mvc/reference/cmo/saveCmo.json");
           	    	$("#form${idSfx}").submit();
           	    	
             	});
        		
        		
        		//remember to add validation before init ajax form
        		initAjaxForm('.container${idSfx} form' ,{
            			success:function(data,status,xhr,formObj){
                			if (formObj.attr('action')){
                				//$('#tblResultsRefCmo').DataTable().ajax.reload();                				
                				goToUrl('${ctx}/mvc/reference/cmo/search');
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
				<div>
					<div class="form-row">
						<div class="form-group">
							<label for="code${idSfx}">${msg['cmo.col-code']}</label>
							<form:input id="code${idSfx}" path="code" cssClass="form-control" readonly="${!insertMode}" />
						</div>
						<div class="form-group">
							<label for="acronym${idSfx}">${msg['cmo.col-acronym']}</label>
							<form:input id="acronym${idSfx}" path="acronym" cssClass="form-control" />
						</div>
						<div class="form-group big">
							<label for="name${idSfx}">${msg['cmo.col-name']}</label>
							<form:input id="name${idSfx}" path="name" cssClass="form-control" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="fkOriginCountry${idSfx}" >${msg['cmo.col-country-origin']}</label>
							<form:select path="fkOriginCountry" id="fkOriginCountry${idSfx}" cssClass="form-control">
								<form:option value=""></form:option>
								<form:options items="${territoryList}" itemValue="id" itemLabel="name"/>
							</form:select>
						</div>
						<div class="form-group">
							<label for="fkType${idSfx}" >${msg['cmo.col-type']}</label>
							<form:select path="fkType" id="fkType${idSfx}" cssClass="form-control">
								<form:option value=""></form:option>
								<form:options items="${typeList}" itemValue="id" itemLabel="code" />
							</form:select>
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