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

<div class="panel panel-default identifier-panel">
	
	<div class="panel-heading">
		<h3 class="panel-title">${msg['work.identifier-section']}</h3>
	</div>
	
	<div class="panel-body">
		
		<table id="tblIdentifier${idSfx}">
			<thead>
				<tr>
					<th>${msg['work.identifier-code']}</th>
					<th>${msg['work.identifier-value']}</th>
				</tr>
			</thead>
			<tbody>
				<spring:eval expression="vo.work.workIdentifierList.size()" var="identifierListSize" />
				<c:if test="${identifierListSize>0}">
					<c:forEach begin="0" end="${identifierListSize - 1}" var="index">
						<tr>
							<td>${vo.work.workIdentifierList[index].acronym}</td>
							<td>${vo.work.workIdentifierList[index].value}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>	
	</div>
</div>