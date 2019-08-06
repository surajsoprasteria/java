<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="idSfx" value="DtAccDet" />

<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}"); //disable all old page specific events
    			
				var thisDialog = getParentDialog(".container${idSfx}");   		

    			//Init UI Plugins
				$(".container${idSfx} select.form-control").selectpicker();
            	$(".container${idSfx} .input-group.date").datepicker();

            	$(".container${idSfx} form").validate({rules:{
					"cmo.code" : {required:true, remote: function(element){
               			return {
		                	"url": globalParams.ctxMvc + "/administration/dataAccess/checkCodeUniqueness.json",
		                	"type": "post",
		                	"data": {"id":"${vo.id}", "code":$(element).val() }
		                }
               		}},
					"cmo.acronym" : {required:false},
					"notes" : {maxlength:2048},
					"clientKey" : {required:{depends:function(){ return "${vo.id == null}" === "true"; }}},
        			"clientKeyRep": {equalTo:"#clientKey${idSfx}"}
                }});
            	
            	initAjaxForm(".container${idSfx} form" ,{
        			success:function(data,status,xhr,formObj){
            			if (formObj.attr('action')){
            				thisDialog.getModalDialog().data("reload-grid-on-exit",true);
            				thisDialog.close();
            			}
            		}
				
				});

            	var sourceTbl = $("#sourceTbl${idSfx}").DataTable({
            		"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
    	    	    "columnDefs": [
	   	                { targets: [0, 1, 2], visible: true},
	   	                { targets: [2], width:"50", sortable: false, className:"text-center", render:actionRender},
	   	                { targets: '_all', visible: true }
    	    	    ],
    	    	    "order": [[0,"asc"]]
    			});


            	function actionRender(data, type, row, meta) {
					return "<div class='button-wrapper' title='${msg['global.remove']}'><button type='button' class='btn btn-xs btn-primary btn-remove-origin'><span class='glyphicon glyphicon-minus'></span></button></div>";
				}


            	$(".container${idSfx} .source-panel").on("click","button.btn-add-origin", function(event){

            		var dataOriginContainer = $(".container${idSfx} div.data-origin");
					var postData = { multipleSelectMode:true, onlyDataSources:true };
					var cont=0;
            		dataOriginContainer.find(".src-data").each(function(){
            			var isDeleted = $(this).find("input.src-delete").val() === "true";
            			if(!isDeleted){
            				postData["exclusionList[" + cont + "]"] = $(this).find("input.src-id").val();
            				cont++;
                		}
                	});

            		
            		openModalDetail({
	       	        	 "dialogTitle": "${msg['dataAccess.select-sources']}",
	       	        	 "wrapperClass": "dv-search-source-cmo",
	           	         "url":"${ctx}/mvc/reference/cmo/search",
	           	         "data":postData,
	           	         "srcEvent":event
	           	     });

               	});

            	$(".container${idSfx}").on("click", ".btn-select-client", function(event){

            		openModalDetail({
	       	        	 "dialogTitle": "${msg['dataAccess.select-sources']}",
	       	        	 "wrapperClass": "dv-search-client-cmo",
	           	         "url":"${ctx}/mvc/reference/cmo/search",
	           	         "data":{
	           	        	singleSelectMode:true,
	           	        	filterClientInfo:true,
	           	        	currentClientInfo:"${vo.id}"
		           	      },
	           	         "srcEvent":event
	           	     });

                });
               	

            	$(document).on("click.${idSfx}", ".dv-search-source-cmo button.select-rows", function(){
            		var searchContainer = $(".dv-search-source-cmo");
	   				var searchDialog = getParentDialog(".dv-search-source-cmo");
	   				var searchTbl = searchContainer.find("table.dataTable").dataTable().api();

	   				var selRows = getSelectedRows(".dv-search-source-cmo table");
	   				if(selRows.length){
						selRows.each(function(i){
							var rowData = selRows[i];
	   						addNewSource(rowData.id, rowData.code, rowData.acronym);	   						
	   					});
	   				}

	   				searchDialog.close();
            	});

            	$(document).on("click.${idSfx}", ".dv-search-client-cmo button.select-rows", function(){
            		var searchContainer = $(".dv-search-client-cmo");
	   				var searchDialog = getParentDialog(".dv-search-client-cmo");
	   				var searchTbl = searchContainer.find("table.dataTable").dataTable().api();
  					var tr = $(this).closest('tr');
  					var rowData = searchTbl.row( tr ).data();

  					changeCurrentClient(rowData.id, rowData.code, rowData.acronym);

	   				searchDialog.close();
            	});

            	$(".container${idSfx} .source-panel").on("click","button.btn-remove-origin", removeSourceRow );

            	$("#btnDelete${idSfx}").click(function(){
        			showConfirmMessage({
        				"confirmAction":function(dialog){
        					thisDialog.getModalDialog().data("reload-grid-on-exit",true);
        			    	executeAction({
        			        	url : "${ctx}/mvc/administration/dataAccess/delete.json",
        			   			data : {id : "${vo.id}"},
        			   			success: function(data, status, xhr,formObj) {
                    				thisDialog.close();
         		        		}});
        			    		dialog.close(); 
        		        	}
        			});
              	});
            	
            	$(".container${idSfx} .btn-save").click(function(){
            		$("#form${idSfx}").attr('action', "${ctx}/mvc/administration/dataAccess/save.json").submit();
              	});
            	

            	function changeCurrentClient(idCmo, cmoCode, cmoAcronym){
					var container = $(".container${idSfx}");

					container.find("#fkCmo${idSfx}").val(idCmo);
					container.find("#cmoCode${idSfx}").val(cmoCode);
					container.find("#cmoAcronym${idSfx}").val(cmoAcronym);
                }
            	
				function addNewSource(idCmo, cmoCode, cmoAcronym){
					var dataOriginContainer = $(".container${idSfx} div.data-origin");
					var index= dataOriginContainer.find(".src-data").size();

					var newSrc = $("<div>")
									.attr("data-row-index", index)
									.addClass("src-data");

					newSrc.append($("<input>")
									.attr("type","hidden")
									.attr("name", "dataOriginList[" + index +"].id")
									.addClass("src-id")
									.val(idCmo));

					newSrc.append($("<input>")
							.attr("type","hidden")
							.attr("name", "dataOriginList[" + index +"].execDelete")
							.addClass("src-delete")
							.val(false));

					dataOriginContainer.append(newSrc);

					var newRow = sourceTbl.row.add([cmoCode,cmoAcronym]).draw().node();

					$(newRow).attr("data-row-index", index);
				}

				function removeSourceRow(){
					var btn = $(this);
					var thisRow = btn.closest("tr");
					var index = thisRow.data("row-index");
					var dataOriginContainer = $(".container${idSfx} div.data-origin");
					
					sourceTbl.row(thisRow).remove().draw();
					dataOriginContainer.find(".src-data[data-row-index="+ index +"] input.src-delete").val(true);
				}

				

    	});
		//# sourceURL=${idSfx}.js
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" action="" method="POST" enctype="multipart/form-data">
				<form:hidden path="id" id="id${idSfx}"/>
				<form:hidden path="fkCmo" id="fkCmo${idSfx}"/>
		  
		  		<div class="panel panel-default">
					<div class="panel-heading">
						<div class="float-left">
							<h3 class="panel-title">${msg['dataAccess.general-information']}</h3>
						</div>
						<div class="float-right">
						</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
						<div class="form-row">
							<div class="form-group">
								<label for="cmoCode">${msg['dataAccess.cmo-code']}</label>
								<div class="input-group">
									<form:input path="cmo.code" id="cmoCode${idSfx}" cssClass="form-control" readonly="true"/>
									<span class="input-group-addon btn btn-primary btn-select-client"> <i class="glyphicon glyphicon-search"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="cmoAcronym${idSfx}">${msg['dataAccess.cmo-acronym']}</label>
								<form:input path="cmo.acronym" id="cmoAcronym${idSfx}" cssClass="form-control" readonly="true"/>
							</div>
							<div class="form-group">
								<c:if test="${vo.id!=null}">
									<c:set var="passPlaceholder" value="${msg['dataAccess.type-new-password']}" />
								</c:if>
								<label for="clientKey${idSfx}">${msg['dataAccess.key']}</label>
								<input type="password" name="clientKey" id="clientKey${idSfx}" class="form-control" placeholder="${passPlaceholder}" />
							</div>
							<div class="form-group">
								<label for="clientKeyRep${idSfx}">${msg['dataAccess.key-repeat']}</label>
								<input type="password" name="clientKeyRep" id="clientKeyRep${idSfx}" class="form-control">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group big">
								<label for="notes${idSfx}">${msg['dataAccess.notes']}</label>
								<form:input path="notes" id="notes${idSfx}" cssClass="form-control" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default source-panel">
					<div class="panel-heading">
						<button type="button" class="btn btn-xs btn-primary btn-add-origin pull-right">${msg['dataAccess.add-client-button']}</button>
						<h3 class="panel-title">${msg['dataAccess.sources']}</h3>
					</div>
					<div class="panel-body">
						<table id="sourceTbl${idSfx}">
							<thead>
								<tr>
									<th>${msg['dataAccess.col-cmo-code']}</th>
									<th>${msg['dataAccess.col-cmo-acronym']}</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${vo.dataOriginList}" var="src" varStatus="vs">
								<tr data-row-index="${vs.index}">
									<td>${src.code}</td>
									<td>${src.acronym}</td>
									<td>
										
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<div class="hidden data-origin">
							<c:forEach items="${vo.dataOriginList}" var="src" varStatus="vs">
								<div class="src-data" data-row-index="${vs.index}">
									<input class="src-id" type="hidden" name="dataOriginList[${vs.index}].id" value="${src.id}" />
									<input class="src-delete" type="hidden" name="dataOriginList[${vs.index}].execDelete" value="${src.execDelete}" />
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			   
				<div class="form-row">
					<c:choose>
						<c:when test="${vo.id == null}">
							<div class="pull-right">
								<button type="button" class="btn btn-primary btn-save">${msg['global.save']}</button>
							</div>	
						</c:when>
						<c:otherwise>
							<div class="pull-right">
								<button type="button" class="btn btn-primary btn-save">${msg['global.save']}</button>
							</div>
							<div class="pull-left">
								<button type="button" id="btnDelete${idSfx}" class="btn btnDelete btn-primary">${msg['global.delete']}</button>
							</div>	
						</c:otherwise>
					</c:choose>
					<div class="clear"></div> 
				</div>			
			</form:form>
		</div>	
	</tiles:putAttribute>
</tiles:insertDefinition>