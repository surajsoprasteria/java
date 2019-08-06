<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="ExternalSite" />

<tiles:insertDefinition name="defaultTemplate">

<tiles:putAttribute name="title">${msg['functions.external-site']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['functions.external-site']}</tiles:putAttribute>

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
        		
    			$(".container${idSfx} .input-group.date").datepicker();
        		$(".container${idSfx} select.form-control").selectpicker();


        		$(".container${idSfx} .btnTestConnection").click(function(){
					var $form = $(this).closest("form");
					if($form.valid()){
						var params = {
							host: $form.find(".hostname").val(),
							user: $form.find(".user").val(),
							password: $form.find(".password").val(),
							port: $form.find(".port").val(),
							code: $form.find(".code").val()
						};

						executeAction({
	      			        url : "${ctx}/mvc/administration/externalSite/testExtenalSite.json",
	      			        data : params,
	      			        success: function(data, status, xhr,formObj) {
	      			        	showSuccessMessage({"message":"${msg['externalSite.test-success']}"});
       			        }});
					}
            	});

        		$(".container${idSfx} .btnSave").click(function(){
					var $form = $(this).closest("form");
					if($form.valid()){
						var params = {
							host: $form.find(".hostname").val(),
							user: $form.find(".user").val(),
							password: $form.find(".password").val(),
							port: $form.find(".port").val(),
							code: $form.find(".code").val()
						};

						executeAction({
	      			        url : "${ctx}/mvc/administration/externalSite/updateExternalSiteFTP.json",
	      			        data : params,
	      			        success: function(data, status, xhr,formObj) {
      			        		showSuccessMessage({"message":"${msg['externalSite.test-success']}"});
       			        }});
					}
            	});
        		
    		});

    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			 <div class="tab-content">
				 <jsp:include page="/WEB-INF/view/administration/externalSite/ip-sftp-section.jsp" flush="true"></jsp:include>
				 <jsp:include page="/WEB-INF/view/administration/externalSite/ip-submission-sftp-section.jsp" flush="true"></jsp:include> 
				 <jsp:include page="/WEB-INF/view/administration/externalSite/work-sftp-section.jsp" flush="true"></jsp:include>
				 <jsp:include page="/WEB-INF/view/administration/externalSite/work-submission-sftp-section.jsp" flush="true"></jsp:include>
				 <jsp:include page="/WEB-INF/view/administration/externalSite/ip-massive-export-sftp-section.jsp" flush="true"></jsp:include>
				 <jsp:include page="/WEB-INF/view/administration/externalSite/work-massive-export-sftp-section.jsp" flush="true"></jsp:include>
				 <jsp:include page="/WEB-INF/view/administration/externalSite/query-import-sftp-section.jsp" flush="true"></jsp:include>
			 </div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>