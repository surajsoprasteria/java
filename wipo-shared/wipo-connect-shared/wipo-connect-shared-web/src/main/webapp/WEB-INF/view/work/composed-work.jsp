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

<div class="panel panel-default derivedWorkParent-panel">
	<div class="panel-heading">
		<h3 class="panel-title">${msg['work.composed-work-section']}</h3>
	</div>
	<div class="panel-body">
	
		<spring:eval expression="vo.work.derivedWorkParentList.size()" var="derivedWorkParentListSize" />
		<table id="tblDerivedWorkParent${idSfx}" data-last-index="${derivedWorkParentListSize}">
			<thead>
				<tr>
					<th>${msg['work.derivedWork-col-id']}</th>
					<th>${msg['work.derivedWork-col-class']}</th>
					<th>${msg['work.derivedWork-col-title']}</th>
					<th>${msg['work.derivedWork-col-ro']}</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${derivedWorkParentListSize > 0}">
					<c:forEach begin="0" end="${derivedWorkParentListSize-1}" var="index">
						<tr class="dw-row dw-row-${vo.work.derivedWorkParentList[index].fkWork}">
							<td>
								${vo.work.derivedWorkParentList[index].work.mainId}
							</td>
							<td>
							<div style="text-align: center">
								<div class="creation-class-icon grid '+ ${vo.work.derivedWorkParentList[index].work.creationClassCode} + '" 
											data-toggle='tooltip' 
											title='${vo.work.derivedWorkParentList[index].work.creationClassCode} (${ccMap[vo.work.derivedWorkParentList[index].work.creationClassCode].name})'></div>
											</div>
							</td>
							<td>
								${vo.work.derivedWorkParentList[index].work.originalTitle}
							</td>
							<td>
								<c:forEach items="${vo.work.derivedWorkParentList[index].work.derivedViewList}" var="dv">
									<c:forEach items="${dv.derivedViewNameList}" var="dvn">
										<div class="row"><div class="col-sm-10">${dvn.name.fullName}</div><div class="col-sm-2">${dvn.roleCode}</div> </div>
									</c:forEach>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
