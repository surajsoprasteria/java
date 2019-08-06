<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.enumerator.*" %>

<c:set var="idSfx" value="RefOtherDet" />

<div class="panel panel-default general-info-panel">
		
	<div class="panel-heading">
		<h3 class="panel-title">${msg['otherReferences.general-information']}</h3>
	</div>
	
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
			
			<div class="form-group">
				<label for="managedBy${idSfx}">${msg['otherReferences.col-managed-by']}</label>
				<form:select path="managedBy" id="managedBy${idSfx}" cssClass="form-control" disabled="true" >
					<form:option value=""></form:option>
					<c:forEach items="${managedByTypeList}" var="item">
						<form:option value="${item.code}">${msg[item.msgCode]}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		
		<div class="form-row">
			<div class="form-group extra">
				<label for="description${idSfx}" >${msg['otherReferences.col-description']}</label>
				<form:textarea id="description${idSfx}" path="description" cssClass="form-control" />
			</div>
		</div>
		
	</div>
</div>