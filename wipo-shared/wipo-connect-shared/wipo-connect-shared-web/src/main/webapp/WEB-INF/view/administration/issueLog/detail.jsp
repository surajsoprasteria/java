<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="AdmIssueLogDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
        		
    			$(".container${idSfx} .input-group.date").datepicker();
        		$(".container${idSfx} select.form-control").selectpicker();
				

        		$(".container${idSfx} form").validate();

    		});

    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="formGroup${idSfx}" cssClass="form-inline" modelAttribute="vo" action="POST">

				<div class="form-row">
					<div class="form-group">
						<label >${msg['issueLog.date']}</label>
						<fmt:formatDate value="${vo.serverDate}" var="dateString" pattern="${msg['config.date-time-format']}" />
						<input type="text" id="dateInsert${idSfx}" value="${dateString}" class="form-control" readonly="readonly" />
					</div>
					<div class="form-group x-large">
						<label >${msg['issueLog.message']}</label>
						<form:input id="message${idSfx}" path="message" cssClass="form-control" readonly="true"/>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group full">
						<label >${msg['issueLog.trace']}</label>
						<form:textarea id="codeGroup${idSfx}" path="stackTrace" cssClass="form-control x-tall" rows="10" readonly="true"/>
					</div>
				</div>

			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>