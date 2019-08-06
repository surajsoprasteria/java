<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="DetailGroup" />

<tiles:insertDefinition name="contentTemplate">

<tiles:putAttribute name="pageScript">
	<script type="text/javascript">
		$(document).ready(function(){

			$(document).off(".${idSfx}");
			//Init UI Plugins
		 	$(".container${idSfx} select.form-control").selectpicker();
        	$(".container${idSfx} .input-group.date").datepicker(); 
        	
			var canRender = "${canRender}" === "true";

			var params = { idGroup:"${vo.idGroup}"};
			var	searchURL = "${ctx}/mvc/groupManagement/findDetailGroup.json?"+ jQuery.param(params);

			var thisDialog = getParentDialog(".container${idSfx}"); 

		 	if(!canRender){
				$(".container${idSfx}").blockEdit();
			}
			
			initAjaxForm(".container${idSfx} form", {
				success : function(data, status, xhr,formObj) {
					goToUrl("${ctx}/mvc/groupManagement/search",{
			       			params:{
			       				idName:"${vo.idGroup}",
			           			autoSearchMode:true
			           			}
			   			});
				}
			});   		 	


//----------------------------------------DATATABLE----------------------------------------//   

			$('#tblGroupDetail${idSfx}').dataTable( {
    			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
				"lengthMenu": [25,50,100,200],
				"pageLength": 50,
				"ajax" : searchURL,
				"columns": [
					{ "data": "name","type":"string", "title":"${msg['groupSummary.name']}"},
					{ "data": "firstName", "type":"string","title":"${msg['groupSummary.firstName']}"},
					{ "data": "nameType", "type":"string","title":"${msg['groupSummary.Type']}"},
					{ "data": "mainId", "type":"string","title":"${msg['groupSummary.nameNumber']}"}
		        ]
		    } );

			
			var tblNameGroupObj = $('#tblGroupDetail${idSfx}').dataTable().api();
			
    		
//----------------------------------------END DATATABLE----------------------------------------//   

		//# sourceURL=${idSfx}.js
	});
	</script>
</tiles:putAttribute>

<tiles:putAttribute name="body">
	<div class="container${idSfx}">
		<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" action="" method="POST">
			<form:hidden path="idGroup" id="id${idSfx}"/>
			 <div class="panel panel-default ">
				<div class="panel-heading">
					<h3 class="panel-title">${msg['groupManagement.group']}</h3>
				</div>
				<div class="panel-body">
					<div class="form-row">
					<div class="form-group">
							<label for="mainId${idSfx}">${msg['groupName.mainId']}</label>
							<form:input id="mainId${idSfx}" path="mainId" cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="name${idSfx}">${msg['groupName.name']}</label>
							<form:input id="name${idSfx}" path="name" cssClass="form-control CV_LAST_NAME" />
						</div>
						<div class="form-group">
							<label for="firstName${idSfx}">${msg['groupName.firstName']}</label>
							<form:input id="firstName${idSfx}" path="firstName" cssClass="form-control CV_FIRST_NAME" />
						</div>
						
						 <div class="form-group">
							<label for="nameType${idSfx}">${msg['groupSummary.Type']}</label>
							<form:select path="nameType" id="nameType${idSfx}" class="form-control">
							<form:options items="${groupNameTypeList}"/>
							</form:select>
						</div> 
					</div> 
				</div>		
				<div id="groupDetailNameSection${idSfx}">
				</div>
			</div>
			
			<div class="panel panel-default panel-name"> 
				<div class="panel-heading">
					<c:if test="${canRender}">
						<button type="button" class="btn btn-xs btn-primary add-name pull-right"><span class="glyphicon glyphicon-plus"></span></button>
					</c:if>
					<h3 class="panel-title">${msg['groupManagement.group-section']}</h3>
				</div>
				<div class="panel-body">
					 <table id="tblGroupDetail${idSfx}"></table> 
				</div>
			</div>
			
		 </form:form> 
	</div>
	
</tiles:putAttribute>
</tiles:insertDefinition>