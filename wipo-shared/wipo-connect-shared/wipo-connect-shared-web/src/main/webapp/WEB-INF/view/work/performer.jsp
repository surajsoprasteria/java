<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="WrkDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="WrkDet" />
	</c:otherwise>
</c:choose>

 <div class="panel panel-default performer-panel">
	<div class="panel-heading">
		<h3 class="panel-title">${msg['work.performer-section']}</h3>
	</div>
	<div class="panel-body">
		
		<div class="form-row">
			<div class="form-group">
				<label for="">${msg['work.performer-name']}</label>
			</div>
		</div>
		
		<spring:eval expression="vo.work.workPerformerList.size()" var="performerListSize" />
		<div class="form-row performer-row">
		<c:if test="${performerListSize>0}">
			<c:forEach begin="0" end="${performerListSize - 1}" var="i">
				<div class="form-group-performer" style="display:inline-block;">
					<form:hidden path="work.workPerformerList[${i}].id" cssClass="performer-id"/>
					<form:hidden path="work.workPerformerList[${i}].execDelete" cssClass="performer-deleted"/>
					
					<div class="form-group medium">
						<form:input path="work.workPerformerList[${i}].performerName"  cssClass="form-control performer-name CV_PERFORMER_NAME" />
					</div>
					
				</div>
			</c:forEach>
		</c:if>
		</div>
	</div>
</div>