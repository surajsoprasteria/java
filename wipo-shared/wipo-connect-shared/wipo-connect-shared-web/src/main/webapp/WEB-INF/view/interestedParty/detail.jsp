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


<c:set var="STATUS_VALID" value="<%= InterestedPartyStatusEnum.VALID.name() %>"/>
<c:set var="L" value="<%=TypeEnum.L.name() %>" />
<c:set var="N" value="<%=TypeEnum.N.name() %>"  />

<c:choose>
	<c:when test="${showInNewTab}">
		<c:set var="pageTemplate" value="fullScreenDetailTemplate" />
	</c:when>
	<c:otherwise>
		<c:set var="pageTemplate" value="contentTemplate" />
	</c:otherwise>
</c:choose>

<tiles:insertDefinition name="${pageTemplate}">
	
	<tiles:putAttribute name="title">${msg['functions.interestedParty']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['interestedPartyDetail.interestedParty-detail-new']}</tiles:putAttribute>

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
			$(document).ready(function() {
				$(document).off(".${idSfx}"); //disable all old page specific events
				
				var thisDialog = getParentDialog(".container${idSfx}");
				var readOnlyMode = "${readOnlyMode}" === "true";
				var showInNewTab = "${showInNewTab}" === "true";
				
				$(".container${idSfx} .input-group.date").datepicker();
				$(".container${idSfx} select.form-control").selectpicker();		
				$(".container${idSfx} form").input2Span();
				
				$(".container${idSfx} form").on("change", "#ipType${idSfx}", changeLabelDate);
				changeLabelDate();

				
				$('#tblName${idSfx}').DataTable({
		     		"dom": '<"multi-action-container"><"clear">',
		     		"paging": false,
		            "columnDefs": [
		                { "data": "index", "className":"hide_column", "targets": 0,"orderable": false, "searchable": false},
			            { "data": "id", "className":"hide_column", "targets": 1,"orderable": false, "searchable": false },
			            { "data": "name", "className":"dt-body-left",  "targets": 2 },
			            { "data": "firstName", "className":"dt-body-left","targets": 3 },
			            { "data": "nameType", "className":"dt-body-center", "targets": 4 },
			            { "data": "mainId", "className":"dt-body-center",   "targets": 5 }
		            ],
		            "createdRow": function ( row, data, index ) {
		                if(index === 0){
		                    $('td', row).css("font-weight","bold");
		                }
		            }
		        });
				
				
				$('#affiliationTable${idSfx}').DataTable( {
					"paging": false,
		     		"lengthMenu": [25,50,100,200],
					"pageLength": 50,
		    	    "dom": 'f<"multi-action-container"><"clear">',
		    	    "ajax": {
		                "url": "${ctx}/mvc/interestedParty/findIpAffiliations.json",
		                "type": "POST",
		                "data": {"ipId" : "${vo.interestedParty.id}"}
		            },
					"columns": [
						{ "data": "fkCreationClass", "title":"${msg['interestedPartyAffiliation.creation-class']}", "render": renderCreationClass, className:"text-center"},
						{ "data": "fkIpiRole", "title":"${msg['interestedPartyAffiliation.role']}", "render": renderRole, className:"text-center"},
						{ "data": "fkIpiRightType","title":"${msg['interestedPartyAffiliation.right-type']}", "render": renderRightType, className:"text-center"},
						{ "data": "cmoAcronym","title":"${msg['interestedPartyAffiliation.cmo']}", className:"text-center"},
						{ "data": "startDate","title":"${msg['interestedPartyAffiliation.start-date']}", "render":colRender("date"), className:"text-center"},
						{ "data": "endDate","title":"${msg['interestedPartyAffiliation.end-date']}", "render":colRender("date"), className:"text-center"},
						{ "data": "shareValue","title":"${msg['interestedPartyAffiliation.share']}" , "render":colRender("decimal"), className:"text-right"},
						{ "data": "territoryFormula","title":"${msg['interestedPartyAffiliation.territory']}", className:"text-center"},
			        ],
			        "order": [[1,"asc"]]
			    });
				
				
				function renderCreationClass(data, type, row, meta) {
					var ccOut = "<div>";
					var creationClassMap = meta.settings.json.creationClassMap;
					ccOut += "<div class='creation-class-icon grid "+ creationClassMap[data].code + "' data-toggle='tooltip' title='" + creationClassMap[data].code + " ("+ escapeHtml(creationClassMap[data].name) + ")'></div>";
					return ccOut+"</div>";
				}
		
				function renderRightType(data, type, row, meta) {
					var ipiRightTypeMap = meta.settings.json.ipiRightTypeMap;
					return ipiRightTypeMap[data].code+" ("+ipiRightTypeMap[data].name+")";
				}
		
				function renderRole(data, type, row, meta) {
					var roleMap = meta.settings.json.roleMap;
					return roleMap[data].code+" ("+roleMap[data].name+")";
				}
				
				
				$("#cmdDelete${idSfx}").click(function(){
					showConfirmMessage({"confirmAction":function(dialog){
						executeAction({
					    	url : "${ctx}/mvc/interestedParty/deleteIp.json",
					   		data : {idIp : "${vo.interestedParty.id}"},
					   		success: function(data, status, xhr,formObj) {
					   			if (!showInNewTab) {
					   				goToUrl("${ctx}/mvc/interestedParty/search");
					        	} else {
					        		window.location.reload();
					        	}
		 		        	}
					    });
					    dialog.close(); 
					}});
		      	});
		
				
		      	$(".container${idSfx} button.btn-show-affiliation").click(function(event){
					openModalDetail({
		 	        	 "dialogTitle": "${msg['interestedPartyAffiliation.affiliation-summary']}",
		 	        	 "wrapperClass": "affiliation-summary-${idSfx}",
		     	         "url":"${ctx}/mvc/interestedParty/affiliationDetail",
		     	         "data":{id:"${vo.interestedParty.id}"},
		     	         "srcEvent":event
		     	     });
		        });
		      	
		           	
		      	//manage dataTable linked-interested party        	
		      	$('#tblResults${idSfx}').DataTable( {
		      		"paging": false,
		    	    "dom": 't',
		            "columnDefs": [
		                { "targets": [0], "visible": false, "searchable": false },
		                { "targets": [1], "visible": false, "searchable": false },
		                { "targets": [6], "orderable": false, "searchable": false }
		            ]
		        } );
		     	
		     	 function chanceLabelGeneralInfo(){
		     		if(tariff == "${idFixed}"){
						 $(".container${idSfx} .amount-panel").hide();
						 $(".container${idSfx} .number-seats").val("");
						 $(".container${idSfx} .invoice-amount").val(amountMolt);
		
						 $(".container${idSfx} .usage-log-request-row").show();
					}else if(tariff == "${idDynamic}"){
						 $(".container${idSfx} .amount-panel").show();
		
						 $(".container${idSfx} .usage-log-request-row").hide();
					}
		    	}
		
		     	function changeLabelDate(){
		         	var type=$(".container${idSfx} #ipType${idSfx}").val();
		  			if(type == "L"){
				  		 $(".container${idSfx} .typeL").show();
				  		 $(".container${idSfx} .maritalStatus").hide();
				  		 $(".container${idSfx} .typeN").hide();
		
					  	$(".container${idSfx}").find("#birthPlace${idSfx}, #maritalStatus${idSfx}, #sex${idSfx}").each(function(){
							$(this).val("").addClass("no-valid").closest(".form-group").hide();
					        if($(this).data("selectpicker")){
					        	$(this).selectpicker('refresh');
					        }
						});
				  		 
					} else{
						$(".container${idSfx} .typeN").show();
				  		$(".container${idSfx} .typeL").hide();
		
				  		$(".container${idSfx}").find("#birthPlace${idSfx}, #maritalStatus${idSfx}, #sex${idSfx}").each(function(){
							$(this).removeClass("no-valid").closest(".form-group").show();
						});
			  		 }
		     	} 
		
			});	    
		//# sourceURL=${idSfx}.js
	</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<div class="container${idSfx} <c:if test="${showInNewTab}">fullScreen-detail</c:if>">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" method="POST">
				<form:hidden path="interestedParty.id" id="id${idSfx}" />
				<input name="pendingMode" value="${pendingMode}" type="hidden" />
				<c:if test="${vo.interestedParty.id != null}">
					<div class="header-panel">
						<div class="float-left">
							<b>${vo.interestedParty.getPatronymName()}</b> (${vo.interestedParty.mainId})
							
						</div>
						<div class="col-xs-offset-1 float-left panel-data-origin">
							<label>${msg['interestedPartyDetail.data-origin']}: <small>${vo.interestedParty.cmoOriginCode}</small></label>
						</div>
						<div class="float-right detail-status-label" style="background-color: ${statusColorMap[vo.interestedParty.statusCode]};">
							<label><small id="fkStatus${idSfx}">${statusMap[vo.interestedParty.fkStatus].value}</small> <form:hidden
									path="interestedParty.fkStatus" id="fkStatus${idSfx}" />
							</label>
						</div>
						<div class="float-right">
						<c:forEach items="${vo.interestedParty.creationClassCodeList}" var="item">
							<div class="creation-class-icon ${item}" data-toggle="tooltip" title="${item} (${ccMap[item].name})"></div>
						</c:forEach>
						</div>
						<div class="clear"></div>
					</div>
				</c:if>
				

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active" id="mainTab${idSfx}">
						<jsp:include page="/WEB-INF/view/interestedParty/general-info.jsp" flush="true"></jsp:include>
						<%-- <jsp:include page="/WEB-INF/view/interestedParty/name.jsp" flush="true"></jsp:include> --%>
						<jsp:include page="/WEB-INF/view/interestedParty/identifier.jsp" flush="true"></jsp:include>
						<jsp:include page="/WEB-INF/view/interestedParty/affiliation.jsp" flush="true"></jsp:include>
						<c:if test="${!readOnlyMode}">
							<div class="form-row text-right button-row">
								<button type="button" id="cmdDelete${idSfx}" class="btn btnSave btn-primary pull-left">${msg['interestedPartyDetail.delete']}</button>
							</div>
						</c:if>

					</div>
				</div>

			</form:form>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>