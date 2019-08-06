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
<sec:authorize access="hasAuthority('${IP_MGMT_CODE}')" var="canRender"/>


<tiles:insertDefinition name="contentTemplate">

<tiles:putAttribute name="pageScript">
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#table${idSfx}").dataTable({
				"paging": false,
	     		"lengthMenu": [25,50,100,200],
				"pageLength": 50,
	    	    "dom": '<"multi-action-container"><"clear">',
	    	    "order": [[0,"asc"]]
			});

		});	    
	//# sourceURL=${idSfx}.js
	</script>
</tiles:putAttribute>
	
<tiles:putAttribute name="body">
	<div class="container${idSfx}">
			<div class="panel panel-default panel">
				<div class="panel-heading">
					<h3 class="panel-title">${msg['interestedPartyDetail.identifiers']}</h3>
				</div>
				<div class="panel-body">
					<table id="table${idSfx}">
						<thead>
							<tr>
								<th>${msg['interestedPartyDetail.identifiers-code']}</th>
								<th>${msg['interestedPartyDetail.identifiers-values']}</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${vo.interestedParty.interestedPartyIdentifierFlatList}" var="identifier">
								<tr>
									<td class="text-center">${identifier.code}</td>
									<td class="text-center">${identifier.value}</td>
								</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
	</div>		
</tiles:putAttribute>
</tiles:insertDefinition>