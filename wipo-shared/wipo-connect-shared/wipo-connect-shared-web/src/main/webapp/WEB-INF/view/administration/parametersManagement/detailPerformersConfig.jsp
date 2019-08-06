<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

<c:set var="idSfx" value="PerformersConfigDet" />
<c:set var="YES" value="<%= YesNoEnum.YES %>"/>
<c:set var="NO" value="<%= YesNoEnum.NO %>"/>

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
			
    		$(document).ready(function(){
        		$(".container${idSfx} select.form-control").selectpicker();
        	 	var thisDialog = getParentDialog(".container${idSfx}");
        	 	var canRender = "${canRender}" === "true";

         		$(".container${idSfx} form").validate({
    			    rules: {
    			    	"hidePerformers": {required:true}
    	            }
    		    });
         		

             	initAjaxForm(".container${idSfx} form" , {	
             		success:function(data,status,xhr,formObj){
         				$(document).trigger("save.ParamManagDet",data);
		            	thisDialog.close();
             		}
         		});

             	$("#btnSavePerformersConfiguration").click(function(){
              		$("#form${idSfx}").data("validation-type", "LOCAL");
            		$("#form${idSfx}").attr('action', "${ctx}/mvc/administration/parametersManagement/savePerformersConfig.json").submit();
              	});

    		});

    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" method="POST" action="">
				<form:hidden path="id" id="id${idSfx}" />
				<div class="aff-panel">
					<div class="panel-body">
						<div class="form-row">
							<div class="form-group">
								<label for="fkCreationClass${idSfx}">${msg['paramManagment.creation-class']}</label>
								<form:hidden path="fkCreationClass" />
								<form:select path="fkCreationClass" id="fkCreationClass${idSfx}" cssClass="form-control" disabled="true">
									<form:option value=""></form:option>
									<form:options items="${creationClassList}" itemLabel="code" itemValue="id" />
								</form:select>
							</div>
							<div class="form-group">
								<label for="fkCreationClass${idSfx}">${msg['paramManagment.hide-performers']}</label>
								<form:select path="hidePerformers" id="hidePerformers${idSfx}" cssClass="form-control">
									<form:option value="${YES.getValue()}" label="${YES.getLabel()}"/>
   									<form:option value="${NO.getValue()}" label="${NO.getLabel()}"/>
								</form:select>
							</div>
						</div>
						<div class="form-row text-right">
							<button type="button" id="btnSavePerformersConfiguration" class="btn btnSave btn-primary">${msg['global.save']}</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>