<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.enumerator.ReferenceManagedByEnum" %>	
<c:set var="SYSTEM" value="<%=ReferenceManagedByEnum.SYSTEM.getCode()%>" />
<c:set var="SHARED" value="<%=ReferenceManagedByEnum.SHARED.getCode()%>" />
<c:set var="idSfx" value="RefOtherDet" />

	<div class="panel panel-default "> 
		<div class="panel-heading">
			<c:if test="${vo.managedBy.equals(SHARED)}">
				<button type="button" class="btn btn-xs btn-primary add-value-reference pull-right"><span class="glyphicon glyphicon-plus"></span></button>
			</c:if>
			<h3 class="panel-title">${msg['otherReferences.values']}</h3>
		</div>
		<div class="panel-body">
			 <table id="tblValueReferences${idSfx}"></table> 
		</div>
	</div>