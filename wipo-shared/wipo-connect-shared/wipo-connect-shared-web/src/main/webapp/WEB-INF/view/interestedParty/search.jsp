<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>
<%@ page import="org.wipo.connect.enumerator.OrderByExpressionEnum"%>

<c:set var="idSfx" value="IPSrc" />

<c:set var="TYPE_LEGAL" value="<%=TypeEnum.L.name()%>" />
<c:set var="TYPE_NATURAL" value="<%=TypeEnum.N.name()%>" />

<c:set var="STATUS_VALID" value="<%=InterestedPartyStatusEnum.VALID.name()%>"/>
<c:set var="STATUS_DELETED" value="<%=InterestedPartyStatusEnum.DELETED.name()%>"/>

<c:set var="RO_main_id" value="<%=OrderByExpressionEnum.RO_MAIN_ID.getField()%>" />
<c:set var="RO_type" value="<%=OrderByExpressionEnum.RO_TYPE.getField()%>" />
<c:set var="RO_status" value="<%=OrderByExpressionEnum.RO_STATUS.getField()%>" />
<c:set var="RO_score" value="<%=OrderByExpressionEnum.RO_SCORE.getField()%>" />
<c:set var="RO_patronym_name" value="<%=OrderByExpressionEnum.RO_MAIN_NAME.getField()%>" />

 
<tiles:insertDefinition name="defaultTemplate">


	<tiles:putAttribute name="title">${msg['interestedParty.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['interestedParty.page-info']}</tiles:putAttribute>
	
    <tiles:putAttribute name="pageScript">
    	<script type="text/javascript">

	    	$(document).ready(function(){
	    		//PAGE INIT
	       		$(".container${idSfx} select").selectpicker();
	       		$(".container${idSfx} .input-group.date").datepicker();
	
	    		var autoSearchMode = "${autoSearchMode}" === "true";

	    		var statusColorMap = ${statusColorMap};
	    		var statusMap = ${statusMap};
	    		
	    		var maxNumberOfName = parseInt("${configProperties['ip_grid.max_number_names']}");
				
				var searchURL = "${ctx}/mvc/common/emptyData.json";
	
				if(autoSearchMode){
					searchURL = "${ctx}/mvc/interestedParty/findInterestedParty.json?"+ $(".container${idSfx} #advancedSearchForm${idSfx}").serialize();
					$('.advanced-search-panel, .simple-search-panel').toggleClass('hidden');
				}
	
				//Formal search validation rules
				$(".container${idSfx} #advancedSearchForm${idSfx}").validate();
				$(".container${idSfx} #advancedSearchForm${idSfx}").find("div.input-group.date input").each(function(){
					$(this).rules("add",{date:true});
				});
	
	
				$(".container${idSfx} #simpleSearchForm${idSfx}").validate({
	        		rules:{ "fullText" : {
	   				 		minlength: "${configProperties['ip.simple-search-min-length']}"
	    				}
	        		},
	        		onfocusout : false
	    		});

				var tblObj = $('#tblResults${idSfx}').DataTable(generateDtOptions());
	
				function generateDtOptions(isSimpleSearch){
					var dtOptions = {
						"dom": '<"multi-action-container"><"clear"><"top"ilp>r<"fix-overflow"t><"bottom"ilp><"clear">',
		    			"lengthMenu": [${configProperties['available-entries-table']}],
						"pageLength": ${configProperties['entries-value-default']},
						"deferRender": true,
						"processing": true,
						"serverSide": true,
						"order":[[4,"asc"]],
						"ajax" : searchURL,
		   				"columns": [
		   			   		{ "data": null, "searchable": false, "title":"<input type='checkbox' class='select-all-rows' />", "width":"25px", "orderable":false, "render":colRender("checkboxId"), className:"text-center"} ,
		   			   		{ "data":"score", "searchable": false, "title":"${msg['global.col_score']}", visible: false, className:"text-right", "render":colRender("decimal",{scale:3})},
		   			   		{ "name":"cmo_orig.code","data": "cmoOriginCode","type":"string",visible:"${configProperties['ip_grid.data_origin_visible']}"=='true', "title":"${msg['interestedPartyDetail.data-origin']}", "width":"110px", className:"text-center"},
		   			   		{ "name":"${RO_main_id}","data": "mainId","type":"string",visible:"${configProperties['ip_grid.id_visible']}"=='true', "title":"${msg['interestedPartyDetail.wcc-id']}", "width":"110px", className:"text-center no-wrap"},
		   		       		{ "name":"${RO_patronym_name}","data": "listName",visible:"${configProperties['ip_grid.names_visible']}"=='true', "title":"${msg['interestedPartyDetail.names']}","render": ipNameRender},
		   		            { "orderable":false,"data": null, "type":"string",visible:"${configProperties['ip_grid.id_names_visible']}"=='true', "title":"${msg['interestedPartyDetail.ip-base-number-grid']}", className:"text-center no-wrap", "render":ipinRender},
		   		         	{ "name":"${RO_type}","data": "type","type":"string",visible:"${configProperties['ip_grid.type_visible']}"=='true', "title":"${msg['interestedParty.col-type']}", "width":"35px",  "render":ipTypeRender, className:"text-center"},
		   		         	{ "name":"interested_party.birth_date","data": "birthDate",visible:"${configProperties['ip_grid.birth_visible']}"=='true', "title":"${msg['interestedPartyDetail.birth-date-grid']}",className:"text-center","width":"110px","render": colRender("date"), orderable: !isSimpleSearch},
		   		         	{ "orderable":false,"data": "affiliatedCmos","type":"string",visible:"${configProperties['ip_grid.cmo_visible']}"=='true', "title":"${msg['interestedPartyAffiliation.cmo']}", className:"text-center",  "render": affiliatedCmosRender},
		   		         	{ "orderable":false,"data": "creationClassCodeList","type":"string", "type":"string",visible:"${configProperties['ip_grid.ip_creation_class_visible']}"=='true', "title":"${msg['interestedPartyDetail.creation-class-grid']}", "width":"90px","render":creationClassRender, className:"text-center"},
		   		         	{ "name":"${RO_status}","data": "statusCode","type":"string",visible:"${configProperties['ip_grid.status_visible']}"=='true', "title":"${msg['interestedPartyDetail.status']}", "width":"90px", className:"text-center", "render": ipStatusRender, "createdCell":ipStatusCreatedCell},
		   		            { "data": null, "searchable": false, "title":"" , "orderable":false, "width":"50px", "render": actionRender, className:"text-center"}
		   		        ],
		   		     	"initComplete": function( settings, json ) {
		 		        	$(".container${idSfx} .dataTables_wrapper .multi-action-container").html(actionTitleGenerator());
						}
		   		    };
		   		    
					if (isSimpleSearch) {
						dtOptions.columns[1].name = "${RO_score}";
	    		    	dtOptions.columns[1].visible = "${configProperties['ip_grid.score_visible']}"==="true";
	    		    	dtOptions.order = [[1,"desc"]];
	    		    }
	
		       		$.each([${configProperties['ip_grid.column_link']}], function(i, value) {
		       			if (value == 0 || value == "0") return true;
		       			if(typeof dtOptions.columns[value] === 'undefined') return true;
		       			dtOptions.columns[value].className = (dtOptions.columns[value].className || "") + " ip-grid-column-link";
		   			});

		   			return dtOptions;

				}
	

        		//enable or disable massive action buttons
        		$('#tblResults${idSfx}').on("row-select-change.wcc","input[type=checkbox].select-row", function(){
        			$(".container${idSfx} .multi-action-container .btn-delete-multi").prop("disabled",!getMassiveButtonStatus("#tblResults${idSfx}",".btn-delete"));
        			$(".container${idSfx} .multi-action-container .btn-download-multi").prop("disabled",!getMassiveButtonDownloadStatus("#tblResults${idSfx}"));
        		});


        		$(".container${idSfx} #btnSearch${idSfx}").on("click", simpleSearchRightOwner);
        		$('.container${idSfx} .simple-search-panel').on("keypress", simpleSearchRightOwner);

    			$(".container${idSfx} #btnAdvancedSearch${idSfx}").on("click", advancedSearchRightOwner);
         		$(".container${idSfx} .advanced-search-panel").on("keypress", advancedSearchRightOwner);

        		function simpleSearchRightOwner(e) {
					var code = (e.keyCode ? e.keyCode : e.which);
					if (e.type == 'keypress' && code != 13) return;

					e.preventDefault();
     		    	e.stopPropagation();
     		    	$(".container${idSfx} #isSimpleSearch${idSfx}").val(true);
        			var formObj = $(".container${idSfx} #simpleSearchForm${idSfx}");
        			if (!formObj.valid()) return;

					if (!tblObj.settings()[0].isSimpleSearch) {
						searchURL = "${ctx}/mvc/interestedParty/findInterestedParty.json?"+ formObj.serialize(); // global var
						tblObj.destroy();
						$('#tblResults${idSfx}').empty();
						tblObj = $('#tblResults${idSfx}').DataTable(generateDtOptions(true)); 
						tblObj.settings()[0].isSimpleSearch = true;
					} else {
						tblObj.ajax.url("${ctx}/mvc/interestedParty/findInterestedParty.json?"+ formObj.serialize()).load();
					}
        		}

        		function advancedSearchRightOwner(e) {
					var code = (e.keyCode ? e.keyCode : e.which);
					if (e.type == 'keypress' && code != 13) return;

					e.preventDefault();
     		    	e.stopPropagation();
     		    	$(".container${idSfx} #isSimpleSearch${idSfx}").val(false);
        			var formObj = $(".container${idSfx}  #advancedSearchForm${idSfx}");
        			if (!formObj.valid()) return;

					if (tblObj.settings()[0].isSimpleSearch) {
						searchURL = "${ctx}/mvc/interestedParty/findInterestedParty.json?"+ formObj.serialize(); // global var
						tblObj.destroy();
						$('#tblResults${idSfx}').empty();
						tblObj = $('#tblResults${idSfx}').DataTable(generateDtOptions(false)); 
						tblObj.settings()[0].isSimpleSearch = false;
					} else {
						tblObj.ajax.url("${ctx}/mvc/interestedParty/findInterestedParty.json?"+ formObj.serialize()).load();
					}
        		}
        		

        		function getMassiveButtonDownloadStatus(table){
        			var checked = getSelectedRows(table);
        			var countTot = checked.size();
        			var isDeleted=false;
        			checked.each(function(){
        				var rowData = this;
        				if(rowData.statusCode === "${STATUS_DELETED}"){
    						isDeleted=true;
        				}
        			});
        			if (isDeleted){
        				return false;
            		}else{
            			return (countTot>0) ;
            		}
        		}

        		$(".container${idSfx}").on("click", "button.btn-download-multi", function(e) {
					var dataObj = {};

					var selRows = getSelectedRows("#tblResults${idSfx}");
					if(selRows.length){
						selRows.each(function(i){
							var rowData = selRows[i];
							dataObj["idList["+i+"]"] = rowData.id;
							dataObj["domainList["+i+"]"] = rowData.domain;
						});
					}

					downloadFile("${ctx}/mvc/interestedParty/exportIp", dataObj);
    	      	});

        		$("#btnCancel${idSfx}").click(function(){
             		 if(insertMode){
            			goToUrl("${ctx}/mvc/interestedParty/search");
                	}else{
                		tblObj.ajax.url("${ctx}/mvc/common/emptyData.json").load();
                		tblObj.search("").columns().search("").draw();
                	 	clearForm(".container${idSfx}  #simpleSearchForm${idSfx}");
                    }
        		});

        		$("#btnAdvancedCancel${idSfx}").click(function(){
             		
                		tblObj.ajax.url("${ctx}/mvc/common/emptyData.json").load();
                		tblObj.search("").columns().search("").draw();
                	 	clearForm(".container${idSfx} #advancedSearchForm${idSfx}");
                	 	$(".container${idSfx} #advancedSearchForm${idSfx} #chkInternational${idSfx}").change();
                    
        		});
        		

        	 	$('#tblResults${idSfx}').on('mouseup', 'td.ip-grid-column-link, button.btn-detail', function (e) {
        	 		var tr = $(this).closest('tr');
       	         	var rowData = tblObj.row( tr ).data();

					if (e.which == 1 && e.ctrlKey || e.which == 2) {
						goToUrl("${ctx}/mvc/interestedParty/detail",{
            	 			"params":{ "showInNewTab":true, "mainId":rowData.mainId },
	    	             	"target": "_blank",
	    	             	"method": "GET"
		    	        });
        	 		} else {
	        	        openModalDetail({
	        	        	"dialogTitle": "${msg['interestedPartyDetail.interestedParty-detail-new']}",
	            	        "url":"${ctx}/mvc/interestedParty/detail",
	            	        "data":{"idInterestedParty":rowData.id },
	 	        	        "showScreenshotButton":true,
	            	    });
        	 		}
                	
				});


				$('#tblResults${idSfx}').on('click', 'button.btn-delete', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();

	       	         showConfirmMessage({
						"confirmAction":function(dialog){

							executeAction({
								url : "${ctx}/mvc/interestedParty/deleteIp.json",
								data : {idIp : rowData.id},
								success: function(data, status, xhr,formObj) {
									showSuccessMessage({message: "${msg['global.result-ok']}"});
								},
								complete: function(xhr, status){
									tblObj.ajax.reload();
								}
							});
														
							dialog.close();
						}
			       	 });	       	         
	           	 });


    			

    	      	
				function actionRender(data, type, row, meta) {
					if (type !== "display") return "";
					
					var htmlOut = "";

					var stDelete = "disabled";
					
					if(row.statusCode != "${STATUS_DELETED}"){
						stDelete = "";
					}

					htmlOut += "<div class='button-wrapper' title='${msg['interestedPartyDetail.delete']}'><button type='button' class='btn btn-xs btn-primary btn-delete' "+stDelete+" ><span class='glyphicon glyphicon-trash'></span></button></div>";

					return htmlOut;
				}

				$(".container${idSfx}").on("click", "button.btn-delete-multi", function(e) {
					var dataObj = {};

					var selRows = getSelectedRows("#tblResults${idSfx}");
					if(selRows.length){
						selRows.each(function(i){
							var rowData = selRows[i];
							dataObj["idList["+i+"]"] = rowData.id;
						});

						showConfirmMessage({
							"numberOfItems": selRows.length,
							"confirmAction":function(dialog){

								executeAction({
									url : "${ctx}/mvc/interestedParty/massiveAction/delete.json",
									data : dataObj,
									success: function(data, status, xhr,formObj) {
										showSuccessMessage({message: "${msg['global.result-ok']}"});
									},
									complete: function(xhr, status){
										tblObj.ajax.reload();
									}
								});
															
								dialog.close();
							}
				       	 });	
					}
        		});

				function actionTitleGenerator(){
					var htmlOut = "";

					var stDelete = "disabled";
					var stDownload = "disabled";
					htmlOut += "<label>${msg['global.massive-actions']}</label>&nbsp;";
					htmlOut += "<div class='button-wrapper' title='${msg['interestedPartyDetail.delete']}'><button type='button' class='btn btn-xs btn-primary btn-delete-multi' "+stDelete+" ><span class='glyphicon glyphicon-trash'></span></button></div>";
					htmlOut += "<div class='button-wrapper' title='${msg['interestedPartyDetail.download']}'><button type='button' class='btn btn-xs btn-primary btn-download-multi' "+stDownload+" ><span class='glyphicon glyphicon-save-file'></span></button></div>";
					return htmlOut;
				}
    			
       			function nameListRender(data, type, row, meta,column) {
       				var sOut="";
       				for(var i=0; i<row.nameList.length; i++){
       					if (row.nameList[i].nameType==="PA"){
       						sOut = (row.nameList[i][column]) || "";
       					}
       				}
       				return sOut;
       			}

				
       			function ipinRender(data, type, row, meta){
       				if(type !== "display") return "";
       				
       				var SEP = "\u0000\u0000\u0000";
       				var ids = [];
       				var nameFormatted = "";
       				var idList = row.nameList || [];
       				for(var i=0; i<idList.length; i++){
       					nameFormatted = escapeHtml(idList[i].fullName);
       					if(idList[i].nameType !== "PA"){
       						var ipin = idList[i].mainId || "";
       						ids.push(nameFormatted +SEP+ ipin);
       					}else {
       						var paIpin = (idList[i].mainId || "");
       					}
       				}
       				ids = ids.sort(compareCaseInsensitive);

       				var mainRow = "";
    				var rows = "";
    				var nameLength = ((ids.length < maxNumberOfName) ? ids.length : (maxNumberOfName-1));
    				
       				for(var i=0; i < nameLength; i++){
       					var split = ids[i].split(SEP);
       					rows += "<div class='cell code row'>" + split[1] + "</div>";
           			}
       				mainRow = "<div class='cell code row main-row'>" + paIpin + "</div>";

       				return "<div class='sub-table'>" + mainRow + rows + "</div>";
       			}
       			

       			function ipStatusRender(data, type, row, meta) {
   					return statusMap[row.statusCode];
       			}
       			
       			function ipStatusCreatedCell(td, cellData, rowData, row, col) {		
       				$(td).css("background-color",statusColorMap[rowData.statusCode]);
				}

        		
    		    function creationClassRender(data,type,row,meta){
    		    	if(type !== "display") return "";

					var ccOut = "";
					var ccList = row.creationClassCodeList;
					var ccMap = meta.settings.json.ccMap;
					ccList.sort(compareCaseInsensitive);
					for(var i=0; i<ccList.length; i++){
						ccOut += "<div class='creation-class-icon grid "+ ccList[i] + "' data-toggle='tooltip' title='" + ccList[i] + " ("+ escapeHtml(ccMap[ccList[i]].name) + ")'></div>";
					}
					
					return ccOut;
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
       			
           		
       			function ipNameRender(data, type, row, meta) {

					if(type !== "display") return ""; 
           			
       				var SEP = "\u0000\u0000";
       				var idList = row.nameList || [];
       				var patronim = "";
       				var patronimType = "";
       				var nameFormatted = "";
       				var names = []
       				for(var i=0; i<idList.length; i++){
       					nameFormatted = idList[i].fullName;

   						if(idList[i].nameType != "PA"){
							var type = idList[i].nameType ? idList[i].nameType : "";
   							names.push(nameFormatted +SEP+ type);
   						}else{
   							patronim = nameFormatted;
   							patronimType= idList[i].nameType;
   	   					}
               		}

       				names = names.sort(compareCaseInsensitive);

       				var nameLength = ((names.length < maxNumberOfName) ? names.length : (maxNumberOfName-1));
       				var mainRow = "";
    				var rows = "";
       				for(var i=0; i < nameLength; i++){
           				var split = names[i].split(SEP);
           				rows += "<div class='row'><div class='cell desc' title='"+ escapeHtml(split[0]) +"'>"+split[0]+"</div><div class='cell code'>"+split[1]+"</div></div>";
           			}
       				mainRow = "<div class='row main-row'><div class='cell desc' title='"+ escapeHtml(patronim) +"'>"+patronim+"</div><div class='cell code'>"+patronimType+"</div></div>";

       				var sOut = "<div class='sub-table'>" + mainRow + rows + "</div>";

       				return sOut;
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

			
				
       		 $(".container${idSfx} .btnSwitchSearch").click(function(){
       			$('.advanced-search-panel, .simple-search-panel').toggleClass('hidden');
       		 });

              
    	});
    		//# sourceURL=${idSfx}.js
    	</script>
    </tiles:putAttribute>



	<tiles:putAttribute name="body">
		<div class="container${idSfx}">

					<%-- --------------------- SIMPLE SEARCH --------------------- --%>
					<div class="panel panel-default simple-search-panel" data-linked-panel=".advanced-search-panel">
						<div class="panel-heading">
							<h3 class="panel-title">${msg['global.simpleSearch']}<small> <a href="#" class="btnSwitchSearch">${msg['functions.switch-advanced-search']}</a></small>
							</h3>
						</div>
						<div class="panel-body">
							<form:form cssClass="form-inline" modelAttribute="searchVO" id="simpleSearchForm${idSfx}">
							<form:hidden path="isSimpleSearch" id="isSimpleSearch${idSfx}" />
								<div class="form-row text-center">
									<div class="form-group x-large">
										<form:input path="fullText" id="fullText${idSfx}" cssClass="form-control" cssStyle="width:100%"
											placeholder="${msg['interestedParty.simple-search-hint']}" />
									</div>
									<div class="form-group x-small">
										<button type="button" id="btnSearch${idSfx}" class="btn btn-primary">${msg['global.search']}</button>	
									</div>
									
								</div>
							</form:form>
						</div>
					</div>
					<%-- --------------------- ADVANCED SEARCH --------------------- --%>
					<div class="panel panel-default advanced-search-panel hidden" data-linked-panel=".simple-search-panel">
						<div class="panel-heading">
							<h3 class="panel-title">${msg['global.advancedSearch']}<small> <a href="#" class="btnSwitchSearch">${msg['functions.switch-simple-search']}</a></small>
							</h3>
						</div>
						<div class="panel-body">
							<form:form cssClass="form-inline" modelAttribute="searchVO" id="advancedSearchForm${idSfx}">
								<div class="form-row">
									<div class="form-group">
										<label for="identifier${idSfx}">${msg['interestedParty.id']}</label>
										<div class="input-group">
											<form:input path="identifier" id="identifier${idSfx}" cssClass="form-control" />
											<span class="input-group-addon">
												<label style="font-weight: normal;">
													<span>${msg['global.main']}</span>
													<form:checkbox path="onlyMainId" />
												</label>
											</span>
										</div>
									</div>
									<div class="form-group">
										<label for="name${idSfx}">${msg['interestedPartyDetail.name']}</label>
										<form:input path="lastName" id="lastName${idSfx}" cssClass="form-control" />
									</div>
									<div class="form-group">
										<label for="firstName${idSfx}">${msg['interestedPartyDetail.first-name']}</label>
										<form:input path="firstName" id="firstName${idSfx}" cssClass="form-control" />
									</div>
									<div class="form-group">
										<label for="ipType${idSfx}">${msg['interestedPartyDetail.interestedPartyType']}</label>
										<form:select path="type" id="ipType${idSfx}" class="form-control">
											<form:option value=""></form:option>
											<c:forEach items="${interestedPartyTypeList}" var="item">
												<form:option value="${item}">${msg[item.msgCode]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								<!-- </div>
								<div class="form-row"> -->
								<div class="form-group">
										<label for="cmoOfAffiliation${idSfx}">${msg['interestedPartyDetail.cmo-of-affiliation']}</label>
										<form:select path="cmoAcronym" id="cmoOfAffiliation${idSfx}" class="form-control">
											<form:option value=""></form:option>
											<form:options items="${cmoList}" itemLabel="acronym" itemValue="code"></form:options>
										</form:select>
									</div>
									<div class="form-group">
										 <label for="title${idSfx}">${msg['interestedParty.creation-classes']}</label>
										 <form:select path="creationClassCodeList" cssClass="form-control">
											<form:options items="${creationClassList}" itemLabel="code" itemValue="code"></form:options>
                                         </form:select>
									</div>
									<div class="form-group">
										<label for="dateFrom">${msg['interestedParty.date-from']}</label>
										<div class="input-group date">
											<form:input path="dateFrom" id="dateFrom${idSfx}" cssClass="form-control" />
											<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="dateTo">${msg['interestedParty.date-to']}</label>
										<div class="input-group date">
											<form:input path="dateTo" id="dateTo${idSfx}" cssClass="form-control" />
											<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</div>
								<!-- </div>
								<div class="form-row"> -->
									<div class="form-group">
										<label for="title${idSfx}">${msg['interestedParty.search-status']}</label>
										<form:select path="statusCode" cssClass="form-control">
											<form:option value=""></form:option>
											<form:options items="${statusList}" itemValue="code" itemLabel="value" />
										</form:select>
									</div>
									<div class="form-group">
										<label for="birth">${msg['interestedPartyDetail.birth-date']}</label>
										<div class="input-group date">
											<form:input path="dateBirth" id="dateBirth${idSfx}" cssClass="form-control" />
											<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="countryOfBirth${idSfx}">${msg['interestedPartyDetail.birth-country']}</label>
										<form:select path="countryOfBirth" id="countryOfBirth${idSfx}" class="form-control">
											<form:option value=""></form:option>
											<form:options items="${territoryList}" itemLabel="name" itemValue="code"></form:options>
										</form:select>
									</div>
									<div class="form-group">
										<label for="countryCitizenship${idSfx}">${msg['interestedPartyDetail.countryOfCitizenship']}</label>
										<form:select path="countryCitizenship" id="countryCitizenship${idSfx}" class="form-control">
											<form:option value=""></form:option>
											<form:options items="${territoryList}" itemLabel="name" itemValue="code"></form:options>
										</form:select>
									</div>
								</div>
								
								<div class="form-row text-right">
									<div class="form-group">
										<button type="button" id="btnAdvancedCancel${idSfx}" class="btn btn-default">${msg['global.cancel']}</button>
										<button type="button" id="btnAdvancedSearch${idSfx}" class="btn btn-primary">${msg['global.search']}</button>
									</div>
								</div>

							</form:form>
						</div>
					</div>

			
			
			<div id="tooManyResults${idSfx}" class="bs-callout bs-callout-danger hidden">
				<h4></h4>
			</div>
			<div id="tooManySimpleSearchResults${idSfx}" class="bs-callout bs-callout-danger hidden">
				<h4></h4>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="float-left">
						<h3 class="panel-title">${msg['global.results']}</h3>
					</div>
					<div class="clear"></div>
				</div>

				<div class="panel-body">
					<table id="tblResults${idSfx}" class="auto-scroll-x"></table>
				</div>
			</div>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>