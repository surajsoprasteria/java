<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="Debug" />

<tiles:insertDefinition name="defaultTemplate">

	<tiles:putAttribute name="title">${msg['functions.debug-information']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['functions.debug-information']}</tiles:putAttribute>

    <tiles:putAttribute name="pageScript">
    	<script type="text/javascript">
	  		$(document).ready(function(){
	  			$(document).off(".${idSfx}"); //disable all old page specific event
	  			$('#tblInfo${idSfx}').DataTable({
		   			  "ordering": false
		   		});
	  		});
    	//# sourceURL=${idSfx}.js
    	</script>
    </tiles:putAttribute>


    <tiles:putAttribute name="body">
    	<div class="container">
			
				<table id="tblInfo${idSfx}">
					<thead>
						<tr><th>Key</th><th>Value</th></tr>
					</thead>
					<tbody>
						<tr><td>CMO Code</td><td>${configProperties['cmo.code']}</td></tr>
						<tr><td>Application Version</td><td>${applicationProperties['application.version']}</td></tr>
						<tr><td>Application Build</td><td>${applicationProperties['application.build']}</td></tr>
						<tr><td>DB Username</td><td>${dbInfo.username}</td></tr>
						<tr><td>DB Catalog</td><td>${dbInfo.catalog}</td></tr>
						<tr><td>Datasource URL</td><td>${dbInfo.url}</td></tr>
						<tr><td>DB Name</td><td>${dbInfo.productName}</td></tr>
						<tr><td>DB Version</td><td>${dbInfo.productVersion}</td></tr>
						<tr><td>Driver Name</td><td>${dbInfo.driverName}</td></tr>
						<tr><td>Driver Version</td><td>${dbInfo.driverVersion}</td></tr>
					</tbody>
				</table>
		</div>

    </tiles:putAttribute>
</tiles:insertDefinition>
