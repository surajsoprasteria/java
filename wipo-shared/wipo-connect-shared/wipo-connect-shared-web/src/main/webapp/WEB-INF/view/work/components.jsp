<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>
<%@ page import="org.wipo.connect.shared.exchange.dto.impl.DerivedWork"%>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="WrkDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="WrkDet" />
	</c:otherwise>
</c:choose>

<c:set var="STATUS_VALID" value="<%= WorkStatusEnum.VALID %>" />

<c:set var="PUBLISHER" value="<%= RoleEnum.E %>" />
<c:set var="SUB_PUBLISHER" value="<%= RoleEnum.SE %>" />


<div class="panel panel-default derivedWork-panel">
	<div class="panel-heading">
		<h3 class="panel-title">${msg['work.derivedWork-section']}</h3>
	</div>
	<div class="panel-body">
		<div class="form-row">
			<div class="form-group">
				<label for="email${idSfx}">${msg['work.derivedWork-components-perc']}</label>
				<form:input path="work.componentPerc" id="componentPerc${idSfx}" cssClass="form-control CV_COMPONENT_PERC auto-decimal" />
			</div>
		</div>
	
		<spring:eval expression="vo.work.derivedWorkList.size()" var="derivedWorkListSize" />
		<table id="tblDerivedWork${idSfx}" data-last-index="${derivedWorkListSize}">
			<thead>
				<tr>
					<th>${msg['work.derivedWork-col-track-num']}</th>
					<th>${msg['work.derivedWork-col-id']}</th>
					<th>${msg['work.derivedWork-col-class']}</th>
					<th>${msg['work.derivedWork-col-title']}</th>
					<th>${msg['work.derivedWork-col-ro']}</th>
					<th>${msg['work.derivedWork-col-weight']}</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${derivedWorkListSize > 0}">
					<c:forEach begin="0" end="${derivedWorkListSize-1}" var="index">
						<tr class="dw-row dw-row-${vo.work.derivedWorkList[index].fkWork}">
							<td>
								${vo.work.derivedWorkList[index].trackNumber}
							</td>
							<td>
								${vo.work.derivedWorkList[index].work.mainId}
							</td>
							<td>
							<div style="text-align: center">
								<div class="creation-class-icon grid '+ ${vo.work.derivedWorkList[index].work.creationClassCode} + '" 
											data-toggle='tooltip' 
											title='${vo.work.derivedWorkList[index].work.creationClassCode} (${ccMap[vo.work.derivedWorkList[index].work.creationClassCode].name})'></div>
											</div>
							</td>
							<td>
								${vo.work.derivedWorkList[index].work.originalTitle}
							</td>
							<td>
								<c:forEach items="${vo.work.derivedWorkList[index].work.derivedViewList}" var="dv">
									<c:forEach items="${dv.derivedViewNameList}" var="dvn">
										<div class="row"><div class="col-sm-10">${dvn.name.fullName}</div><div class="col-sm-2">${dvn.roleCode}</div> </div>
									</c:forEach>
								</c:forEach>
							</td>
							<td> 
								${vo.work.derivedWorkList[index].weight}
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="hidden to-remove"></div>
	</div>
</div>
