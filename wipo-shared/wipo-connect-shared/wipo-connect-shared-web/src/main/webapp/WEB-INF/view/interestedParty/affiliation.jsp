<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="IPDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="IPDet" />
	</c:otherwise>
</c:choose>



<div class="container${idSfx}">
	<div class="panel panel-default panel collapsed">
		<div class="panel-heading">
			<h3 class="panel-title">Affiliation: ${cmoDesc}</h3>
		</div>
		<div class="panel-body">
			<table id="affiliationTable${idSfx}"></table>
		</div>
	</div>
</div>
