<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>
<%@ page import="org.wipo.connect.enumerator.OrderByExpressionEnum"%>

<c:set var="pageTemplate" value="contentTemplate" />
<c:set var="idSfx" value="NameSrc" />

<c:set var="TYPE_LEGAL" value="<%=TypeEnum.L.name() %>" />
<c:set var="TYPE_NATURAL" value="<%=TypeEnum.N.name() %>" />
<c:set var="PA" value="<%=NameTypeEnum.PA.name()%>" />
<c:set var="NAME_main_name" value="<%=OrderByExpressionEnum.NAME_MAIN_NAME.getField()%>" />

<tiles:insertDefinition name="${pageTemplate}">


	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
		
    		$(document).ready(function(){
        		
    			var singleSelectMode = "${singleSelectMode}" === "true";
				var multipleSelectMode = "${multipleSelectMode}" === "true";
    			
        		$(".container${idSfx} .input-group.date").datepicker();
        		$(".container${idSfx} select").selectpicker();


        		var tblObj = $('#tblResults${idSfx}').DataTable( {
        			"dom": '<"multi-action-container"><"clear"><"top"ilp>r<"fix-overflow"t><"bottom"ilp><"clear">',
	    			"lengthMenu": [${configProperties['available-entries-table']}],
					"pageLength": ${configProperties['entries-value-default']},
					"deferRender": true,
					"processing": true,
					"serverSide": true,
					"order":[[2,"asc"]],
					"ajax" : "${ctx}/mvc/common/emptyData.json",
    				"columns": [
    		            { "data": null, visible:multipleSelectMode, "title":"<input type='checkbox' class='select-all-rows' />", "width":"25px", className:"text-center", "sortable":false, "render":colRender("checkboxId")},
    		            { "data": null, "title":"", "sortable":false, visible:singleSelectMode, "render": colRender("buttonIco",{icon:"glyphicon-share-alt",cssClass:"btn-select select-rows"})},
    		            { "name":"ip_main_id","data":"ipMainId",visible:"${configProperties['ip_grid.id_visible']}"=='true',"type":"string", "title":"${msg['interestedPartyDetail.wcc-id']}", "width":"6%", className:"text-center no-wrap"},
       		       		{ "name":"${NAME_main_name}", "data": null,visible:"${configProperties['ip_grid.names_visible']}"=='true', "title":"${msg['interestedPartyDetail.names']}","render": ipNameRender},
       		          	{ "name":"NAME_main_id","data":null, "type":"string",visible:"${configProperties['ip_grid.id_names_visible']}"=='true', "title":"${msg['interestedPartyDetail.ip-base-number-grid']}", "width":"6%", className:"text-center no-wrap", "render":ipinRender},
       		        	{ "name":"type","data": "type",visible:"${configProperties['ip_grid.type_visible']}"=='true', "title":"${msg['interestedParty.col-type']}", "width":"6%",  "render":ipTypeRender, className:"text-center"},
    		         	{ "name":"birth_date","data":"birthDate",visible:"${configProperties['ip_grid.birth_visible']}"=='true', "title":"${msg['interestedPartyDetail.birth-date-grid']}","width":"10%",className:"text-center","render": colRender("date")},
       		          	{ "sortable":false,"data": "affiliatedCmos",visible:"${configProperties['ip_grid.cmo_visible']}"=='true', "title":"${msg['interestedPartyAffiliation.cmo']}", className:"text-center",  "render": affiliatedCmosRender},
       		         	{ "sortable":false,"data": "creationClassCodeList", "type":"string",visible:"${configProperties['ip_grid.ip_creation_class_visible']}"=='true', "title":"${msg['interestedPartyDetail.creation-class-grid']}", "width":"13%","render": creationClassRender,className:"text-center"},
       		            { "name":"IP_STATUS_code","data": "fkStatus",visible:"${configProperties['ip_grid.status_visible']}"=='true', "title":"${msg['interestedPartyDetail.status']}", "width":"8%", className:"text-center", "render": ipStatusRender, "createdCell":ipStatusCreatedCell}
    		        ],"order": [[2,"desc"]]
    		    } );

        		$("#btnSearch${idSfx}").click(function(){
    				tblObj.ajax.url("${ctx}/mvc/name/find.json?"+ $(".container${idSfx} form").serialize()).load();
        		});

        		//to submit simple search by pressing enter key
         		$('#valueForm${idSfx}').on("keypress", function(e) {
       		    	var code = (e.keyCode ? e.keyCode : e.which);
       		     	if (code == 13) {
         		    	e.preventDefault();
         		    	e.stopPropagation();
         		    	tblObj.ajax.url("${ctx}/mvc/name/find.json?"+  $(".container${idSfx} #searchName${idSfx}").serialize()).load();
         			}
         		});

    			$("#btnCancel${idSfx}").click(function(){
    				tblObj.ajax.url("${ctx}/mvc/common/emptyData.json").load();
    				/* tblObj.search("").columns().search("").draw(); */
    				clearForm(".container${idSfx} form"); 
        		});

        		
    		    function creationClassRender(data,type,row,meta){
    		    	if(type !== "display") return "";

       				var ccOut = "<div>";
					var ccMap = meta.settings.json.ccMap;
					var ccList = row.creationClassCodeList;
					ccList.sort(compareCaseInsensitive);
					for(var i=0; i<ccList.length; i++){
						var ccCode = ccList[i];
						ccOut += "<div class='creation-class-icon grid "+ ccCode + "' data-toggle='tooltip' title='" + ccCode + " ("+ escapeHtml(ccMap[ccCode].name) + ")'></div>";
					}

					return ccOut+"</div>";
               	}
    			
    		  
	       		function ipNameRender(data, type, row, meta) {
	       			var idOut = "";
	       			var bOpen = "";
	       			var bClose = "";
	       			if(row.nameType == "${PA}"){
	    				bOpen = "<b>";
	    				bClose= "</b>";
	    			}
	    			idOut = "<div class='row'><div class='col-sm-10 text-ellipsis'>" + bOpen + row.fullName + bClose + "</div>"+
	    				"<div class='col-sm-2 text-ellipsis'>" + bOpen + row.nameType + bClose + "</div></div>";
	    			return idOut;
	   			}
	       		
	       		function affiliatedCmosRender(data, type, row, meta) {
	   				if(!data || !data.length){
						return "";
	       			}else if(data.length == 1){
						return data[0];
	           		}else{
						return "${msg['interestedPartyAffiliation.multiple']}";
	               	}
	             }
	       		
	       		function ipStatusRender(data, type, row, meta) {
	       			var statusMap = meta.settings.json.statusMap;
					return statusMap[data].value;      		
				}
				
				function ipStatusCreatedCell(td, cellData, rowData, row, col) {				
					$(td).css("background-color",getStatusColor(rowData.statusCode));
				}
	
				function getStatusColor(statusCode){
					var colorMap = {};
					<c:forEach var="entry" items="${statusColorMap}">
						colorMap['${entry.key}'] = "${entry.value}";
					</c:forEach>
					return colorMap[statusCode];
				}
	
				function ipTypeRender(data, type, row, meta) {
	   			var desc;
	   			if(data == "${TYPE_NATURAL}"){
	   				desc = "${msg['interestedParty.type-natural-short']}";
	           	}else{
	           		desc = "${msg['interestedParty.type-legal-short']}";
	            }
	
	            return "<span title='"+desc+"'>"+data+"</span>";
				}
				
				function ipinRender(data, type, row, meta){
	   				var idOut = "";
	       			var bOpen = "";
	       			var bClose = "";
	       			if(row.nameType == "${PA}"){
	    				bOpen = "<b>";
	    				bClose= "</b>";
	    			}
	    			idOut = bOpen + row.mainId + bClose;
	   				return idOut;
	   			}
	    			
	    			
	    	});
    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">${msg['global.search']}</h3>
				</div>
				<div class="panel-body">
					<form:form cssClass="form-inline" modelAttribute="searchVO" id="searchName${idSfx}">
						<input type="hidden" name="onlyRegistered" value="true" />
						<form:hidden path="isOnlyPatronym" id="isOnlyPatronym${idSfx}"/>
						<form:hidden path="isOnlyLegalEntity" id="isOnlyLegalEntity${idSfx}"/>
						<form:hidden path="excludeId" id="excludeId${idSfx} "/>
						<%-- <div class="form-row">
							<div class="form-group">
								<label for="name${idSfx}">${msg['name.id-name']}</label>
								<form:input path="valueForm" id="valueForm${idSfx}" cssClass="form-control" />
							</div>
						</div> --%>
						<div class="form-row">
							<div class="col-sm-3">
							</div>
							<div class="col-sm-6">
								<form:input path="valueForm" id="valueForm${idSfx}" cssClass="form-control" cssStyle="width:100%"
									placeholder="${msg['name.simple-search-hint']}" />
							</div>
							<div class="col-sm-1">
								<button type="button" id="btnSearch${idSfx}" class="btn btn-primary">${msg['global.search']}</button>
							</div>
							</div>
					</form:form>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading big-header">
					<div class="float-left">
						<h3 class="panel-title">${msg['global.results']}</h3>
					</div>
					<c:if test="${multipleSelectMode}">
					<div class="float-right">
						<button type="button" class="btn btn-primary select-rows">${msg['generic.select']}</button>
					</div>
					</c:if>
					<div class="clear"></div> 
				</div>
				<div class="panel-body">

					<table id="tblResults${idSfx}"></table>
					
				</div>
			</div>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>