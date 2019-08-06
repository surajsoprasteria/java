<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
	
<c:set var="idSfx" value="IpMassiveExportSftpDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
			
    		$(document).ready(function(){
    			$(".container${idSfx} .input-group.date").datepicker();
        		$(".container${idSfx} select.form-control").selectpicker();
        	 	var thisDialog = getParentDialog(".container${idSfx}");

        	 	$(".container${idSfx} #formIpMassiveExportSftp${idSfx}").validate({
					rules:{
						"ipMassiveExportFTP.host": {required:true},
						"ipMassiveExportFTP.port": {required:true,digits:true},
						"ipMassiveExportFTP.user": {required:true},
						"newPassword":  {required:false}						
						}
             	});
         		
             	initAjaxForm(".container${idSfx} #formIpMassiveExportSftp${idSfx}" ,
                 		{	
             			success:function(data,status,xhr,formObj){
             				if (data !== Object(data) || (data === Object(data) && data.type === "ERROR")) {
                				showErrorMessage({message:"${msg['global.result-ko']}"});
                			}else{
                				showSuccessMessage({message:"${msg['global.result-ok']}"});
                    		}
                 		}
         		});


    		});

    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="formIpMassiveExportSftp${idSfx}" cssClass="form-inline" modelAttribute="vo" action="${ctx}/mvc/administration/externalSite/updateExternalSiteFTP.json" method="POST" enctype="multipart/form-data">
				<div class="panel panel-default ip-massive-export-sftp-panel">
				<div class="panel-heading">
						<h3 class="panel-title">${msg['externalSite.ip-massive-export-sftp']}</h3>
					</div>
					<div class="panel-body">
					<div class="form-row">
						<form:hidden path="ipMassiveExportFTP.code" cssClass="code"/>
						<div class="form-group">
							<label for="host${idSfx}">${msg['externalSite.host']}</label>
							<form:input id="host${idSfx}" path="ipMassiveExportFTP.host" cssClass="form-control hostname" />
						</div>
						<div class="form-group small">
							<label for="port${idSfx}">${msg['externalSite.port']}</label>
							<form:input id="port${idSfx}" path="ipMassiveExportFTP.port" cssClass="form-control port" />
						</div>
						<div class="form-group">
							<label for="user${idSfx}">${msg['externalSite.user']}</label>
							<form:input id="user${idSfx}" path="ipMassiveExportFTP.user" cssClass="form-control user" />
						</div>
						<div class="form-group">
							<label for="newPassword${idSfx}">${msg['externalSite.pass']}</label>
							<input type="password" name="newPassword" id="newPassword${idSfx}" class="form-control password" />
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-primary btnTestConnection">${msg['externalSite.test']}</button> 
							<button type="button" class="btn btn-primary btnSave" class="btn btn-primary">${msg['global.save']}</button> 
						</div>
					</div>
			  	</div>
		  		</div>
	</form:form>
	</div>	
	</tiles:putAttribute>
</tiles:insertDefinition>