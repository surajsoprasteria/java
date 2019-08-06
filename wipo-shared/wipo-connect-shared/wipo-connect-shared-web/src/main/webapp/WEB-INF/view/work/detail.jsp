<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:choose>
	<c:when test="${readOnlyMode}">
		<c:set var="idSfx" value="WrkDetReadOnly" />
	</c:when>
	<c:otherwise>
		<c:set var="idSfx" value="WrkDet" />
	</c:otherwise>
</c:choose>

<c:set var="STATUS_VALID" value="<%= WorkStatusEnum.VALID.name() %>" />

<c:set var="OT_CODE" value="<%=TitleTypeCodeEnum.OT.name() %>" />
<c:set var="PUBLISHER" value="<%= RoleEnum.E.name() %>" />
<c:set var="SUB_PUBLISHER" value="<%= RoleEnum.SE.name() %>" />

<c:choose>
	<c:when test="${showInNewTab}">
		<c:set var="pageTemplate" value="fullScreenDetailTemplate" />
	</c:when>
	<c:otherwise>
		<c:set var="pageTemplate" value="contentTemplate" />
	</c:otherwise>
</c:choose>

<tiles:insertDefinition name="${pageTemplate}">

	<tiles:putAttribute name="title">${msg['functions.work']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['work.work-detail']}</tiles:putAttribute>

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}"); //disable all old page specific events
    			
				var thisDialog = getParentDialog(".container${idSfx}");   		
				var readOnlyMode = "${readOnlyMode}" === "true";
				var showInNewTab = "${showInNewTab}" === "true";

    			//Init UI Plugins
// 				$(".container${idSfx} select.form-control").selectpicker();
//             	$(".container${idSfx} .input-group.date").datepicker();
            	$(".container${idSfx} form").input2Span();

            	
            	$('#tblIdentifier${idSfx}').dataTable({
             		"dom": '<"multi-action-container">rt',
             		"paging": false,
                    "columnDefs": [
        		            { "data": "code", "targets": 0 },
        		            { "data": "value", "targets": 1 }
                    ]
                });
    			
    			$('#tblTitle${idSfx}').dataTable({
             		"dom": '<"multi-action-container">rt',
             		"paging": false,
                    "columnDefs": [
        		            { "data": "description", "targets": 0 },
        		            { "data": "typeCode", "targets": 1, "className":"dt-body-center" }
                    ],
                    "createdRow": function ( row, data, index ) {
                        if(data.typeCode === "${OT_CODE}"){
                            $('td', row).css("font-weight","bold");
                        }
                    }
                });
    			
    			$("#tblOwnership${idSfx}").dataTable({
    				"paging": false,
    	    	    "dom": '<"multi-action-container">rt',
    	    	    "order": [[0,"asc"]],
    	    	    "columnDefs": [
    	    	    	{"targets": 1, "render":colRender('caseConversion')},
        	    	    {"targets": '_all', "render":colRender('default')}
        			]
    			});
            	
    			$('#tblWorkDate${idSfx}').dataTable({
             		"dom": '<"multi-action-container">rt',
             		"paging": false,
                    "columnDefs": [
        		            { "data": "dateTypeCode", "targets": 0 },
        		            { "data": "dateValue", "targets": 1 }
                    ]
                });

    			$("#tblDerivedWork${idSfx}").dataTable({
        			"paging": false,
            	    "dom": 't',
            	    "columnDefs": [
						{ "targets": [0], "width": 40, className:"trackNumber-td text-center"},
						{ "targets": [5], "width": 40, className:"text-right", "orderable": false}
					]
		        });

    			$("#tblDerivedWorkParent${idSfx}").dataTable({
        			"paging": false,
            	    "dom": 't',
            	    "columnDefs": [{ targets: [1, 3], "render":colRender("default")}]
		        });

				function fillShareRow(panelBody, data){
					var row = panelBody.find(".share-row:last");
					var lastIndex = parseInt(panelBody.find(".share-row:eq(-2) .ref-index").val(),10) || 0;
					row.find(".name-id").val(data.id);
					row.find(".ip-name").val(data.fullName || "");
					row.find(".ip-name-number").val(data.ipin);
					row.find(".identifier-code").val("${sourceTypeDefaultValue}");
					row.find(".ref-index").val(lastIndex +1);
					row.find(".role").change();
				}
				
				function fillCmoField(rowMap, returnParam){
					for(var i=0; i<returnParam.data.respVO.length; i++){
						var $row = rowMap[i];
						var vo = returnParam.data.respVO[i];

						$row.find(".cmo-mechanical-share").val(vo.mrCmo || "");
						$row.find(".cmo-performing-share").val(vo.prCmo || "");
						$row.find(".mechanical-id").val(vo.idMrCmo || "");
						$row.find(".performing-id").val(vo.idPrCmo || "");
						$row.find(".identifier-code").val(vo.sourceTypeDefaultValue || "");
					}
				}

				function setDomesticWork(returnParam){
					var isEnable = false;
					for(var i=0; i<returnParam.data.respVO.length; i++){
						var vo = returnParam.data.respVO[i];
						isEnable = (isEnable || vo.isAffiliatedLocalCmo);
					}
					
					$(".container${idSfx} #domesticWork${idSfx}").prop("checked",isEnable);
					$(".container${idSfx} #domesticWorkHidd${idSfx}").val(isEnable);

				}

        		$("#cmdDelete${idSfx}").click(function(){
        			var message = "${msg['work.confirm-delete']}";
        			showConfirmMessage({
        				"message": "${msg['work.confirm-delete']}",
						"confirmAction":function(dialog){
							executeAction({
								url : "${ctx}/mvc/work/deleteWork.json",
								data : {idWork : "${vo.work.id}"},
								success: function(data, status, xhr,formObj) {
						   			if (!showInNewTab) {
						   				goToUrl("${ctx}/mvc/work/search");
						        	} else {
						        		window.location.reload();
						        	}
								}
							});
							dialog.close();
						}
        			});
		      	});

    	});
//# sourceURL=${idSfx}.js
</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx} <c:if test="${showInNewTab}">fullScreen-detail</c:if>">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" method="POST" enctype="multipart/form-data">
				<form:hidden path="work.id" id="id${idSfx} "/>
			    <form:hidden path="work.registrationDate" id="registrationDate${idSfx} "/>
				<form:hidden path="work.toBeSubmitted" id="toBeSubmitted${idSfx} "/>
				
				<c:if test="${vo.work.id != null}">
					<div class="header-panel">
						<div class="float-left">
						<c:set var="originalTitle" value="${vo.work.originalTitle}"/>
						<c:if test="${empty originalTitle}">
							<c:set var="originalTitle" value=""/>
						</c:if>
						<b>${originalTitle}</b> (${vo.work.mainId})
						</div>
						<div class="float-left panel-data-origin">
							<label>${msg['work.data-origin']}: <small>${vo.work.cmoOriginCode}</small></label>
						</div>
						<div class="float-right detail-status-label"
							style="background-color: ${statusColorMap[vo.work.statusCode]};">
							<label><small>${statusMap[vo.work.fkStatus].value}</small></label>
						</div>
						<div class="float-right">
							<div class="creation-class-icon ${vo.work.creationClassCode}" data-toggle="tooltip" title="${vo.work.creationClassCode} (${ccMap[vo.work.creationClassCode].name})"></div>
						</div>
						<div class="clear"></div>
					</div>
				</c:if>
			  
		  
		  	
		 
		    <div role="tabpanel" class="tab-pane fade in active" id="mainTab${idSfx}">
		   		<jsp:include page="/WEB-INF/view/work/mainInformation.jsp" flush="true"></jsp:include>
				<jsp:include page="/WEB-INF/view/work/identifier.jsp" flush="true"></jsp:include>
				<jsp:include page="/WEB-INF/view/work/ownership-view.jsp" flush="true"></jsp:include>
			   	<jsp:include page="/WEB-INF/view/work/general-info.jsp" flush="true"></jsp:include>
			    <jsp:include page="/WEB-INF/view/work/components.jsp" flush="true"></jsp:include>
			    <jsp:include page="/WEB-INF/view/work/composed-work.jsp" flush="true"></jsp:include>
			    <c:if test="${!hidePerformerSection}">
					<jsp:include page="/WEB-INF/view/work/performer.jsp" flush="true"></jsp:include>
				</c:if>
			   
				<c:if test="${!readOnlyMode}">
				   <div class="form-row text-right button-row">
						<button type="button" id="cmdDelete${idSfx}" class="btn btnSave btn-primary pull-left">${msg['work.delete']}</button>
				   </div>
				</c:if>
			   
			   
		    </div>
		    
		  
				
			</form:form>
		</div>	
	</tiles:putAttribute>
</tiles:insertDefinition>