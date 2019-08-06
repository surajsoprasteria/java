<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*"%>
<%@ page import="org.wipo.connect.enumerator.OrderByExpressionEnum"%>

<c:set var="idSfx" value="WrkSrc" />

<c:set var="STATUS_VALID" value="<%= WorkStatusEnum.VALID.name() %>" />
<c:set var="STATUS_DELETED" value="<%= WorkStatusEnum.DELETED.name() %>" />

<c:set var="WRK_main_id" value="<%= OrderByExpressionEnum.WORK_MAIN_ID.getField() %>" />
<c:set var="WRK_creation_class" value="<%= OrderByExpressionEnum.WORK_CREATION_CLASS.getField() %>" />
<c:set var="WRK_status" value="<%= OrderByExpressionEnum.WORK_STATUS.getField() %>" />
<c:set var="WRK_score" value="<%= OrderByExpressionEnum.WORK_SCORE.getField() %>" />
<c:set var="WRK_original_title" value="<%= OrderByExpressionEnum.WORK_MAIN_TITLE.getField()%>" />

<c:choose>
	<c:when test="${singleSelectMode}">
		<c:set var="pageTemplate" value="contentTemplate" />
		<c:set var="idSfx" value="WrkSrcSingle" />
	</c:when>
	<c:when test="${multipleSelectMode}">
		<c:set var="pageTemplate" value="contentTemplate" />
		<c:set var="idSfx" value="WrkSrcMultiple" />
	</c:when>
	<c:otherwise>
		<c:set var="pageTemplate" value="defaultTemplate" />
	</c:otherwise>
</c:choose>

<tiles:insertDefinition name="${pageTemplate}">

	<tiles:putAttribute name="title">${msg['work.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['work.page-info']}</tiles:putAttribute>

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">

    		$(document).ready(function(){
    			$(document).off(".${idSfx}"); //disable all old page specific event

				//PAGE INIT
				var singleSelectMode = "${singleSelectMode}" === "true";
				var multipleSelectMode = "${multipleSelectMode}" === "true";
				var autoSearchMode = "${autoSearchMode}" === "true";
				var insertMode = "${insertMode}" === "true";
				var maxNumberOfTitle = parseInt("${configProperties['work_grid.max_number_title']}");

				var territoryMap = ${territoryMap};
				var workStatusMap = ${workStatusMap};
				var statusColorMap = ${statusColorMap};

				var searchURL;
				if(autoSearchMode){
					searchURL = "${ctx}/mvc/work/find.json?"+ $(".container${idSfx} #advancedSearchForm${idSfx}").serialize();
					$('.advanced-search-panel, .simple-search-panel').toggleClass('hidden');
				}else{
					searchURL = "${ctx}/mvc/common/emptyData.json";
				}
    			//PAGE SCRIPTS
        		$(".container${idSfx} .input-group.date").datepicker();
        		$(".container${idSfx} select").selectpicker();

        		//Formal search validation rules
        		$(".container${idSfx} #advancedSearchForm${idSfx}").validate();
				$(".container${idSfx} #advancedSearchForm${idSfx}").find("div.input-group.date input").each(function(){
					$(this).rules("add",{date:true});
				});

				$(".container${idSfx} #simpleSearchForm${idSfx}").validate({
            		rules:{
        				"fullText" : {
       				 		minlength: "${configProperties['work.simple-search-min-length']}"
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
						"order":[[5,"asc"]],
						"ajax" : searchURL,
	    				"columns": [
							{ "data": null, "searchable": false, visible:(!singleSelectMode),"title":"<input type='checkbox' class='select-all-rows' />", "width":"25px", "orderable":false, "render":colRender("checkboxId"), className:"text-center"} ,
							{ "data": null, "searchable": false, visible:singleSelectMode,"title":"", "orderable":false, "render": colRender("buttonIco",{icon:"glyphicon-share-alt",cssClass:"btn-select"})},
							{ "data":"score", "searchable": false, "title":"${msg['global.col_score']}", visible: false, className:"text-right", "render":colRender("decimal",{scale:3})},
							{ "name":"cmo_orig.code","data": "cmoOriginCode",visible:"${configProperties['work_grid.id_visible']}"=='true',"type":"string", "title":"${msg['work.data-origin']}", "width":"13%", className:"text-center"},
							{ "name":"${WRK_main_id}","data": "mainId",visible:"${configProperties['work_grid.id_visible']}"=='true',"type":"string", "title":"${msg['work.col-wcc-id']}", className:"text-center no-wrap"},
	    		            { "name":"${WRK_original_title}","data": null,visible:"${configProperties['work_grid.title_visible']}"=='true', "title":"${msg['work.col-title']}", "render": workTitleRender},
	    		            { "orderable":false,"data": null,visible:"${configProperties['work_grid.interested_parties_visible']}"=='true', "title":"${msg['work.col-interested-party']}", "render": workIpRender},
	    		            { "name":"${WRK_creation_class}","data": "creationClassCode", "type":"string",visible:"${configProperties['work_grid.creation_class_visible']}"=='true', "title":"${msg['work.col-creation-class']}", "width":"8%", "render": creationClassRender, className:"text-center"},
	    		            { "name":"work.catalogue_number","data": "catalogueNumber",visible:"${configProperties['work_grid.catalogue_number_visible']}"=='true',"type":"string", "title":"${msg['work.catalogue-number']}", className:"text-center", orderable: !isSimpleSearch},
	    		            { "name":"country_of_prod.code","data": "countryOfProductionCode",visible:"${configProperties['work_grid.country_production_visible']}"=='true',"type":"string", "title":"${msg['work.country-production']}", "render": countryProductionRender, orderable: !isSimpleSearch},
	    		            { "name":"work.label","data": "label",visible:"${configProperties['work_grid.label_visible']}"=='true',"type":"string", "title":"${msg['work.label']}", className:"text-center", orderable: !isSimpleSearch},
	    		            { "orderable":false,"data": null,visible:"${configProperties['work_grid.date_visible']}"=='true', "title":"${msg['work.date']}", "render": workDateRender},
	    		            { "name":"${WRK_status}","data":"fkStatus",visible:"${configProperties['work_grid.status_visible']}"=='true', "title":"${msg['work.col-status']}", className:"text-center", "render": workStatusRender, "createdCell":workStatusCreatedCell},
	       		            { "data": null, "searchable": false, visible:(!singleSelectMode && !multipleSelectMode), "title":"" , "orderable":false, "render": actionRender, className:"text-center"}
	       		    	],
	       		     	"initComplete": function( settings, json ) {
	    		        	if(!singleSelectMode && !multipleSelectMode){
	    	        			$(".container${idSfx} .dataTables_wrapper .multi-action-container").html(actionTitleGenerator());
	    	    		    }
						}
	    		    };

					if (isSimpleSearch) {
						dtOptions.columns[2].name = "${WRK_score}";
	    		    	dtOptions.columns[2].visible = "${configProperties['work_grid.score_visible']}"==="true";
	    		    	dtOptions.order = [[2,"desc"]];
		    		}
		    		
		    		$.each([${configProperties['work_grid.column_link']}], function(i, value) {
	       				if (value == 0 || value == 1) return true;
	       				if(typeof dtOptions.columns[value] === 'undefined') return true;
	       				dtOptions.columns[value].className = (dtOptions.columns[value].className || "") + " work-grid-column-link";
	   				});

					return dtOptions;
				}
        		

				$('#tblResults${idSfx}').on("row-select-change.wcc","input[type=checkbox].select-row", function(){
        			$(".container${idSfx} .multi-action-container .btn-delete-multi").prop("disabled",!getMassiveButtonStatus("#tblResults${idSfx}",".btn-delete"));
        			$(".container${idSfx} .multi-action-container .btn-download-multi").prop("disabled",!getMassiveButtonDownload("#tblResults${idSfx}",".btn-download")); 
        		});

        		function workDateRender(data, type, row, meta) {
    				var sOut="";
    				for(var i=0; i<row.workDateList.length; i++){
    					var date = colRender("date")(row.workDateList[i].dateValue, type, row, meta)
    					sOut += row.workDateList[i].dateTypeCode + ": " + date + "<br>";
    				}

    				return sOut;
    			}

    			function countryProductionRender (data, type, row, meta) {
    				if(data!=null){
    					return territoryMap[data];
        			}else{
            			return "";
            		}
    			}

	    		function creationClassRender(data, type, row, meta) {
	    			if(type !== "display") return "";
					
					var ccMap = meta.settings.json.ccMap;
					return "<div class='creation-class-icon grid "+ data + "' data-toggle='tooltip' title='" + data + " ("+ escapeHtml(ccMap[data].name) + ")'></div>";
				}



    			$(".container${idSfx}").on("click","button.ro-remove",removeRightOwnerRow);
    			$(".container${idSfx} .ro-add").click(function(event){
					openModalDetail({
	       	        	 "dialogTitle": "${msg['work.select-ip-name']}",
	       	        	 "wrapperClass": "src-search-name",
	           	         "url":"${ctx}/mvc/name/search",
	           	         "data":{singleSelectMode:true },
	           	         "srcEvent":event
	           	     });
				});


    			function getMassiveButtonDownload(table){
        			var checked = getSelectedRows(table);
        			var countTot = checked.size();
        			var isShared=false;
        			var isDeleted=false;
        			checked.each(function(){
        				var rowData = this;
        				if(rowData.domain === "${DOMAIN_INTERNATIONAL}"){
        					isShared= true;
    					}else if(rowData.statusCode === "${STATUS_DELETED}"){
    						isDeleted=true;
        				}
        			});
        			if (isShared || isDeleted){
        				return false;
            		}else{
            			return (countTot>0) ;
            		}
        		}

    			$(document).on("click.${idSfx}", ".src-search-name button.select-rows", function(){
					var searchContainer = $(".src-search-name");
					var searchDialog = getParentDialog(".src-search-name");
					var searchTbl = searchContainer.find("table.dataTable").dataTable().api();

					var tr = $(this).closest('tr');
					var rowData = searchTbl.row( tr ).data();
					addRightOwnerRow(searchDialog.srcOptions.srcEvent);
					fillRightOwnerRow($(".container${idSfx} .src-ro-panel .ro-row:last"),rowData);
					searchDialog.close();

				});

    			function addRightOwnerRow(event){
					var btn = event.target || this;
					btn = $(btn);

					var panelBody = btn.closest(".src-ro-panel");
					var rowCount = panelBody.find(".ro-row").size();
					var newRow = $(".template${idSfx} .ro-row").clone();

					newRow.find(":input").each(function(){
						var newName = $(this).attr("name") || "";
						newName = newName.replace("%INDEX%",rowCount);
						$(this).attr("name",newName);
					});

					newRow.find("select.form-control").selectpicker();

					panelBody.append(newRow);
				}
				
				function removeRightOwnerRow(event){
					var btn = event.target || this;
					btn = $(btn);

					var panelBody = btn.closest(".src-ro-panel");
					var row = btn.closest(".ro-row");

					row.hide();
					row.find(".ro-deleted").val(true);
					row.find(":input").addClass("no-valid");
				}


				function fillRightOwnerRow(row, data){
					row.find(".ro-id-name").val(data.id);
					row.find(".ro-name").val(data.fullName || "");
					row.find(".ro-ipin").val(data.ipin || "");
				}

    			$(".container${idSfx} #btnSearch${idSfx}").on("click", simpleSearchWork);
         		$(".container${idSfx} .simple-search-panel").on("keypress", simpleSearchWork);

    			$(".container${idSfx} #btnAdvancedSearch${idSfx}").on("click", advancedSearchWork);
         		$(".container${idSfx} .advanced-search-panel").on("keypress", advancedSearchWork);

         		function simpleSearchWork(e) {
					var code = (e.keyCode ? e.keyCode : e.which);
					if (e.type == 'keypress' && code != 13) return;

					e.preventDefault();
     		    	e.stopPropagation();
     		    	$(".container${idSfx} #isSimpleSearch${idSfx}").val(true);
					var formObj = $(".container${idSfx} #simpleSearchForm${idSfx}");
					if (!formObj.valid()) return;

					if (!tblObj.settings()[0].isSimpleSearch) {
						searchURL = "${ctx}/mvc/work/find.json?"+ formObj.serialize(); // global var
						tblObj.destroy();
						$('#tblResults${idSfx}').empty();
						tblObj = $('#tblResults${idSfx}').DataTable(generateDtOptions(true)); 
						tblObj.settings()[0].isSimpleSearch = true;
					} else {
						tblObj.ajax.url("${ctx}/mvc/work/find.json?"+ formObj.serialize()).load();
					}
         		}

				function advancedSearchWork(e) {
					var code = (e.keyCode ? e.keyCode : e.which);
					if (e.type == 'keypress' && code != 13) return;

					e.preventDefault();
     		    	e.stopPropagation();
     		    	$(".container${idSfx} #isSimpleSearch${idSfx}").val(false);
					var formObj = $(".container${idSfx} #advancedSearchForm${idSfx}");

					if (tblObj.settings()[0].isSimpleSearch) {
						searchURL = "${ctx}/mvc/work/find.json?"+ formObj.serialize(); // global var
						tblObj.destroy();
						$('#tblResults${idSfx}').empty();
						tblObj = $('#tblResults${idSfx}').DataTable(generateDtOptions(false)); 
						tblObj.settings()[0].isSimpleSearch = false;
					} else {
						tblObj.ajax.url("${ctx}/mvc/work/find.json?"+ formObj.serialize()).load();
					}
				}

    			$("#btnCancel${idSfx}").click(function(){
                	 	tblObj.ajax.url("${ctx}/mvc/common/emptyData.json").load();
                	 	tblObj.search("").columns().search("").draw();
                	 	clearForm(".container${idSfx} #simpleSearchForm${idSfx}");
                	 	$(".container${idSfx} #simpleSearchForm${idSfx} .src-ro-panel .ro-row").remove();
        		});

    			$("#btnAdvancedCancel${idSfx}").click(function(){
    				if(insertMode){
            			goToUrl("${ctx}/mvc/work/search");
                	}else{
                	 	tblObj.ajax.url("${ctx}/mvc/common/emptyData.json").load();
                	 	tblObj.search("").columns().search("").draw();
                	 	clearForm(".container${idSfx} #advancedSearchForm${idSfx}");
                	 	$(".container${idSfx} #advancedSearchForm${idSfx} .ro-panel-search .ro-row-src").remove();
                	 	$(".container${idSfx} #advancedSearchForm${idSfx}").find(".ro-panel-search").each(function(event){
							removeAllRightOwnerRowSearch(event);
						});
                	 	$(".container${idSfx} #advancedSearchForm${idSfx} #chkInternational${idSfx}").change();
                    }
        		});

			function removeAllRightOwnerRowSearch(event){

					var rows = $(".container${idSfx} #advancedSearchForm${idSfx}").find(".ro-panel-search .ro-row-src");

					$(rows).each(function() {
						var row = $(this);
						row.hide();
						row.find(".ro-deleted-src").val(true);
						row.find(".rightOwnerValue-src").prop('readonly', false);
						row.find(":input").addClass("no-valid");
					})

					addRightOwnerRowIns();

				}


				//for clear

				function addRightOwnerRowIns(){
					var panelBody = $(".ro-panel-search");
					var rowCount = panelBody.find(".ro-row-src").size();
					var newRow = $(".templateRO${idSfx} .ro-row-src").clone();

					newRow.find(":input").each(function(){
						if($(this).attr("class")=="index"){
							var newValue = $(this).attr("value") || "";
							newValue = newValue.replace("%INDEX%",rowCount);
							$(this).attr("value",newValue);
						}else if($(this).attr("class")=="ro-deleted-src"){
							var newName = $(this).attr("name") || "";
							newName = newName.replace("%INDEX%",rowCount);
							$(this).attr("name",newName);
							var newValue = $(this).attr("value") || "";
							$(this).attr("value",false);
						}else{

							var newName = $(this).attr("name") || "";
							newName = newName.replace("%INDEX%",rowCount);
							$(this).attr("name",newName);
						}

					});

					newRow.find("select.form-control").selectpicker();
					panelBody.append(newRow);
				}


    			$('#tblResults${idSfx}').on('mouseup', 'td.work-grid-column-link, button.btn-detail', function (e) {
	       	 		var tr = $(this).closest('tr');
	       	        var rowData = tblObj.row( tr ).data();

		     	    if (e.which == 1 && e.ctrlKey || e.which == 2) {
		  	 			goToUrl("${ctx}/mvc/work/detail",{
		      	 			"params":{ "showInNewTab":true, "mainId":rowData.mainId },
		            		"target": "_blank",
		            		"method": "GET"
			            });
		  	 		} else {
		       	        openModalDetail({
		       	        	"dialogTitle": "${msg['work.work-detail']}",
		           	        "url":"${ctx}/mvc/work/detail",
		           	        "data":{ "id":rowData.id, "domain":rowData.domain },
			       	        "showScreenshotButton":true
		           	    });
		  	 		}
	           	});

	           	
		/* 	-----------------MASSIVE------------------------------------------------------------------ */

    			$('#tblResults${idSfx}').on('click', 'button.btn-delete', function () {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObj.row( tr ).data();

	       	      	 var message = "${msg['work.confirm-delete']}";

	       	         showConfirmMessage({
						"confirmAction":function(dialog){
							
							executeAction({
								url : "${ctx}/mvc/work/deleteWork.json",
								data : {idWork : rowData.id},
								success: function(data, status, xhr,formObj) {
									showSuccessMessage({message: "${msg['global.result-ok']}"});
								},
								complete: function(xhr, status){
									tblObj.ajax.reload();
								}
							});

							dialog.close();
						},
						"message" : message
			       	 });
	           	 });


    			$(".container${idSfx}").on("click", "button.btn-delete-multi", function(e) {
					var dataObj = {};
					var message = "${msg['work.confirm-delete']}";

					var selRows = getSelectedRows("#tblResults${idSfx}");
					if(selRows.length){
						selRows.each(function(i){
							var rowData = selRows[i];

							dataObj["idList["+i+"]"] = rowData.id;
							dataObj["domainList["+i+"]"] = rowData.domain;
						});

						showConfirmMessage({
							"numberOfItems": selRows.length,
							"confirmAction":function(dialog){

								executeAction({
									url : "${ctx}/mvc/work/massiveAction/delete.json",
									data : dataObj,
									success: function(data, status, xhr,formObj) {
										showSuccessMessage({message: "${msg['global.result-ok']}"});
									},
									complete: function(xhr, status){
										tblObj.ajax.reload();
									}
								});

								dialog.close();
							},
						 	"message" : message
				       	 });
					}
        		});

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

 					downloadFile("${ctx}/mvc/work/exportWork", dataObj);
     	      	});

				function actionRender(data, type, row, meta) {
					if (type !== "display") return "";
					var htmlOut = "";

					var stDelete = "disabled";

					//enable action button by ip status
					if(row.statusCode != "${STATUS_DELETED}"){
						stDelete = "";
					}

					htmlOut += "<div class='button-wrapper' title='${msg['work.delete']}'><button type='button' class='btn btn-xs btn-primary btn-delete' "+stDelete+" ><span class='glyphicon glyphicon-trash'></span></button></div>";

					return htmlOut;
				}

				function actionTitleGenerator(){
					var htmlOut = "";
					var stDownload = "disabled";
					var stDelete = "disabled";
					htmlOut += "<label>${msg['global.massive-actions']}</label>&nbsp;";
					htmlOut += "<div class='button-wrapper' title='${msg['work.delete']}'><button type='button' class='btn btn-xs btn-primary btn-delete-multi' "+stDelete+" ><span class='glyphicon glyphicon-trash'></span></button></div>";
					htmlOut += "<div class='button-wrapper' title='${msg['work.download']}'><button type='button' class='btn btn-xs btn-primary btn-download-multi' "+stDownload+" ><span class='glyphicon glyphicon-save-file'></span></button></div>";

					return htmlOut;
				}

				function workTitleRender (data, type, row, meta) {
	    			if(type !== "display") return "";
						
    				var SEP = "\u0000\u0000";
					var titles = [];
					var OTtype = "";
					var OTtitleDescription = "";
    				for(var i=0; i<row.titleList.length; i++){
    					var title = row.titleList[i];
    					if(title.typeCode != "OT"){
    						var type = title.typeCode ? title.typeCode : "";
   							titles.push(title.description +SEP+ type);
        				}
    					else{
    						OTtitleDescription = title.description;
    						OTtype= title.typeCode;
    					}
    				}

    				titles = titles.sort(compareCaseInsensitive);

    				var mainRow = "";
    				var rows = "";
    				var titleLength = ((titles.length < maxNumberOfTitle) ? titles.length : (maxNumberOfTitle-1));
    				for(var i=0; i<titleLength; i++){
           				var split = titles[i].split(SEP);
           				rows += "<div class='row'><div class='cell desc'><div title='"+ escapeHtml(split[0]) +"'>"+split[0]+"</div></div><div class='cell code'>"+split[1]+"</div></div>";
           			}
    				mainRow = "<div class='row main-row'><div class='cell desc'><div title='"+ escapeHtml(OTtitleDescription) +"'>"+OTtitleDescription+"</div></div><div class='cell code'>"+OTtype+"</div></div>";

    				return "<div class='sub-table'>" + mainRow + rows + "</div>";
				}

    			function workStatusRender(data, type, row, meta) {
    				return workStatusMap[data].value;
    			}

    			function workStatusCreatedCell(td, cellData, rowData, row, col) {
        			$(td).css("background-color",statusColorMap[rowData.statusCode]);
				}

    			function iswIdListRender(data, type, row, meta) {
    				var idOut = "";
					var SEP = "<br/>";
    				var iswca =  row.iswcIdList || [];
					var isw =  row.iswcList || [];


					isw = isw.sort(compareCaseInsensitive);
    				for(var i=0; i<isw.length; i++){
    					if(i===0){
							idOut += "<b>" + isw[i] + "</b>" + SEP;
    					}else{
    						idOut += isw[i] + SEP;
    					}
            		}

    				iswca = iswca.sort(compareCaseInsensitive);
    				for(var i=0; i<iswca.length; i++){
						idOut += iswca[i] +  SEP;
            		}


    				return idOut;
    			}

    			function workIpRender(data, type, row, meta){
    				if(type !== "display") {
        				return "";
        			}
					
					var ipMap = {};
					var roleMap = meta.settings.json.roleMap;
					var nameFormatted = "";

					var mainRow = "";
    				var rows = "";
    				var hiddenData = "";
					for(var i=0; i<row.derivedViewList.length; i++){
						var dv = row.derivedViewList[i];

						for(var j=0; j<dv.derivedViewNameList.length; j++){
							var dvn = dv.derivedViewNameList[j];

							var cmoDesc = affiliatedCmosRender(dvn.affiliatedCmos) || "";

							dvn.name = dvn.name || {};
							nameFormatted = dvn.name.fullName;

							var key = dvn.name.id + "#" + dvn.fkRole;


							if(!ipMap[key]){
								var value = "<div class='row'><div class='cell desc'>";

								if(row.domain=='${DOMAIN_LOCAL}') {
									value += "<div class='right-owner-link' data-name-id='"+dvn.name.id+"' title='" + escapeHtml(nameFormatted + " " + cmoDesc) + "'>"+nameFormatted + " " + cmoDesc + "</div></div>";
								}else{
									value += "<div title='" + escapeHtml(nameFormatted) + "'>"+nameFormatted +"</div></div>";
								}

								if(dvn.roleCode){
									value += "<div class='cell code'>" + dvn.roleCode + "</div></div>";
								}else{
									value += "<div class='cell code'></div></div>";
								}

								ipMap[key] = true;
								rows += value;
							}
						}
					}

				return "<div class='sub-table'>" + rows + "</div>";
        	}

    			function affiliatedCmosRender(data) {
					var stringOut = "";

       				if(!data || !data.length){
						return stringOut;
           			}else if(data.length == 1){
               			if(data[0] != null){
               				stringOut = "(" + data[0] + ")";
    					}
    					return stringOut;
               		}else{
						return "(${msg['interestedPartyAffiliation.multiple']})";
                   	}
                 }


    			$('#tblResults${idSfx}').on('click', '.right-owner-link', function () {
	       	 		 var id = $(this).data('name-id');
	       	 		 var tr=$(this).closest('tr[role=row]');
	       	         var rowData = tblObj.row( tr ).data();

	       	         openModalDetail({
	       	        	 "dialogTitle": "${msg['interestedPartyDetail.interestedParty-detail-new']}",
	           	         "url":"${ctx}/mvc/interestedParty/detail",
	           	         "data":{ "nameId":id, "domain":rowData.domain,"readOnlyMode":true },
 						 "showScreenshotButton":true
	           	     });

	           	});


         		$(".container${idSfx} .btnSwitchSearch").click(function(){
         			$('.advanced-search-panel, .simple-search-panel').toggleClass('hidden');
         		});


         		$(".container${idSfx}").on("click",".ro-add-search-src",findIP);
	       		function findIP(event){
	       			var btn = event.target || this;
					btn = $(btn);

					var panelBody = btn.closest(".ro-panel-search");
					var row = btn.closest(".ro-row-src");

	       			var index=row.find(".index").val();
					event.index=index;
					openModalDetail({
	       	        	 "dialogTitle": "${msg['work.select-ip-name']}",
	       	        	 "wrapperClass": "src-search-name-${idSfx}",
	           	         "url":"${ctx}/mvc/name/search",
	           	         "data":{singleSelectMode:true},
	           	         "srcEvent":event
	           	     });
				}

	       		$(document).on("click", ".src-search-name-${idSfx} button.select-rows", function(event){
					var searchContainer = $(".src-search-name-${idSfx}");
					var searchDialog = getParentDialog(".src-search-name-${idSfx}");
					var searchTbl = searchContainer.find("table.dataTable").dataTable().api();
					var i = searchDialog.srcOptions.srcEvent.index;

					var tr = $(this).closest('tr');
					var rowData = searchTbl.row( tr ).data();
					 var row=$(".container${idSfx} :input[value="+i+"]").closest(".ro-row-src");
					fillRightOwnerRowSearch(row,rowData);
					searchDialog.close();
					row.find(".rightOwnerValue-src").focus();
				});

	       		function fillRightOwnerRowSearch(row, data){
	       			row.find(".rightOwnerValue-src").val(data.fullName || "");
					row.find(".nameMainId-src").val(data.mainId || "");

					if(row.find(".rightOwnerValue-src").val()!=""){
	       				row.find(".rightOwnerValue-src").prop('readonly', true);
	       			}
				}



				$(".container${idSfx}").on("click","button.btn-add-rightOwner-search",addRightOwnerRowSearch);
				function addRightOwnerRowSearch(event){
					var btn = event.target || this;
					btn = $(btn);

					var panelBody = btn.closest(".ro-panel-search");
					var rowCount = panelBody.find(".ro-row-src").size();
					var newRow = $(".templateRO${idSfx} .ro-row-src").clone();

					newRow.find(":input").each(function(){
						if($(this).attr("class")=="index"){
							var newValue = $(this).attr("value") || "";
							newValue = newValue.replace("%INDEX%",rowCount);
							$(this).attr("value",newValue);
						}if($(this).attr("class")=="ro-deleted-src"){
							var newName = $(this).attr("name") || "";
							newName = newName.replace("%INDEX%",rowCount);
							$(this).attr("name",newName);
							var newValue = $(this).attr("value") || "";
							$(this).attr("value",false);
						}else{

							var newName = $(this).attr("name") || "";
							newName = newName.replace("%INDEX%",rowCount);
							$(this).attr("name",newName);
						}

					});

					newRow.find("select.form-control").selectpicker();
					panelBody.append(newRow);
				}

				$(".container${idSfx}").on("click","button.ro-remove-src",removeRightOwnerRowSearch);
				function removeRightOwnerRowSearch(event){
					var btn = event.target || this;
					btn = $(btn);

					var panelBody = btn.closest(".ro-panel-search");
					var row = btn.closest(".ro-row-src");

					row.hide();
					row.find(".ro-deleted-src").val(true);
					row.find(":input").addClass("no-valid");

					var rows = $(".container${idSfx} #advancedSearchForm${idSfx}").find(".ro-panel-search .ro-row-src:visible");

					if(rows.size()==0){

						addRightOwnerRowSearch(event);
					}
				}

    		});
    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<div class="panel panel-default simple-search-panel" data-linked-panel=".advanced-search-panel">
			  <div class="panel-heading">
			    <h3 class="panel-title">${msg['global.simpleSearch']}<small> <a href="#" class="btnSwitchSearch">${msg['functions.switch-advanced-search']}</a></small></h3>
			  </div>
				<div class="panel-body">
					<form:form cssClass="form-inline" modelAttribute="searchVO" id="simpleSearchForm${idSfx}">
					<form:hidden path="isSimpleSearch" id="isSimpleSearch${idSfx}" />
						<input type="hidden" name="onlyRegistered" id="onlyRegistered${idSfx}" value="${singleSelectMode || multipleSelectMode ||onlyRegistered}"/>
						<div class="form-row text-center">
							<div class="form-group x-large">
								<form:input path="fullText" id="fullText${idSfx}" cssClass="form-control" cssStyle="width:100%" placeholder="${msg['work.simple-search-hint']}" />
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
								<label>${msg['work.wccId']}</label>
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
								<label for="title${idSfx}">${msg['work.title-search']}</label>
								<div class="input-group">
									<form:input path="title" id="title${idSfx}" cssClass="form-control" />
									<span class="input-group-addon"> <label style="font-weight: normal;"> <span>${msg['work.original-titles']}</span> <form:checkbox path="originalTitles" />
									</label>
									</span>
								</div>
							</div>
							<div class="form-group">
								<label for="title${idSfx}">${msg['work.creation-classes']}</label>
								<form:select path="creationClassCodeList" cssClass="form-control">
									<form:options items="${managedCreationClassList}" itemLabel="code" itemValue="code"></form:options>
								</form:select>
							</div>
							<div class="form-group">
								<label for="title${idSfx}">${msg['work.search-status']}</label>
								<form:select path="statusCode" cssClass="form-control">
									<form:option value=""></form:option>
									<form:options items="${statusList}" itemValue="code" itemLabel="value" />
								</form:select>
							</div>
						<!-- </div>
						<div class="form-row"> -->
							<div class="form-group">
								<label>${msg['work.performer']}</label>
								<form:input path="performer" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label>${msg['work.registration-date']}</label>
								<div class="input-group date">
									<form:input path="registrationDate" cssClass="form-control" />
									<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						<!--  </div> -->
						<!-- additional-fields -->
							<!-- <div class="form-row"> -->
								 <div class="form-group">
									<label>${msg['work.catalogue-number']}</label>
									<form:input path="catalogueNumber" id="catalogueNumber${idSfx}" cssClass="form-control" />
								</div>
								<div class="form-group">
									<label>${msg['work.category']}</label>
									<form:input path="categoryCode" id="categoryCode${idSfx}" cssClass="form-control" />
								</div>
								 <div class="form-group">
									<label>${msg['work.label']}</label>
									<form:input path="label" id="label${idSfx}" cssClass="form-control" />
								</div>
								<div class="form-group">
									<label>${msg['work.country-production']}</label>
									<form:select path="countryOfProductionCode" cssClass="form-control">
										<form:option value=""></form:option>
										<form:options items="${countryOfProductionList}" itemLabel="name" itemValue="code"/>
									</form:select>
								</div>
							<!-- </div>

							<div class="form-row"> -->
								<div class="form-group">
									<label>${msg['work.date-type']}</label>
									<form:input path="dateTypeCode" id="dateTypeCode${idSfx}" cssClass="form-control" />
								</div>
								<div class="form-group">
									<label>${msg['work.date-value']}</label>
									<div class="input-group date">
										<form:input path="dateValue" cssClass="form-control"/>
								  		<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							 </div> 
						<div class="form-row ro-panel-search" id="src-ro-pane${idSfx}">
							<div class="form-row">
								<div class="form-group small"><label>${msg['interested-party.search-right-owner']}</label></div>
								<div class="form-group"><button type="button" class="btn btn-xs btn-primary btn-add-rightOwner-search"><span class="glyphicon glyphicon-plus"></span></button></div>
							</div>
	
							<spring:eval expression="searchVO.rightOwnerListSearch == null ? 0: searchVO.rightOwnerListSearch.size()" var="roListSize" />
							<c:if test="${roListSize > 0}">
							<c:forEach begin="0" end="${roListSize - 1}" var="i">
								<div class="form-row ro-row-src">
									<input type="hidden" name="index" class="index" value="${i}"/>
									<form:hidden path="rightOwnerListSearch[${i}].nameMainId" cssClass="nameMainId-src"/>
									<form:hidden path="rightOwnerListSearch[${i}].execDelete" cssClass="ro-deleted-src" />
									<div class="form-group small">
										<div class="input-group">
											<form:input path="rightOwnerListSearch[${i}].rightOwnerValue"
												cssClass="form-control CV_RO_VALUE rightOwnerValue-src ${i}" />
												<span class="input-group-addon btn btn-primary ro-add-search-src index"><i
													class="glyphicon glyphicon-search"></i></span>
										</div>
									</div>
									<div class="form-group auto">
										<button type="button" class="btn btn-xs btn-default ro-remove-src ${i}"><span class="glyphicon glyphicon-minus"></span></button>
									</div>
								</div>
							</c:forEach>
							</c:if>
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
			<%-- --------------------- SIMPLE SEARCH --------------------- --%>


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
					<div class="float-right">
						<c:choose>
							<c:when test="${multipleSelectMode}">
								<button type="button" class="btn btn-primary select-rows">${msg['generic.select']}</button>
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">

					<table id="tblResults${idSfx}" class="auto-scroll-x"></table>

				</div>
			</div>

		</div>
		<div class="template${idSfx} hidden">
			<div class="form-row ro-row">
				<input type="hidden" name="rightOwnerList[%INDEX%].idName" class="ro-id-name" />
				<input type="hidden" name="rightOwnerList[%INDEX%].execDelete" class="ro-deleted" />
				<input type="hidden" name="rightOwnerList[%INDEX%].ipin" class="ro-ipin" />
				<div class="form-group">
					<input type="text" name="rightOwnerList[%INDEX%].name" class="form-control ro-name" readonly="readonly"/>
				</div>
				<div class="form-group auto">
					<button type="button" class="btn btn-xs btn-default ro-remove"><span class="glyphicon glyphicon-minus"></span></button>
				</div>
			</div>
		</div>

		<div class="templateRO${idSfx} hidden">
		<div class="form-row ro-row-src">
				<input type="hidden" class="index" value="%INDEX%"/>
				<input type="hidden" name="rightOwnerListSearch[%INDEX%].nameMainId" class="nameMainId-src"/>
				<input type="hidden" name="rightOwnerListSearch[%INDEX%].execDelete" class="ro-deleted-src" />
				<div class="form-group small">
					<div class="input-group">
						<input type="text" name="rightOwnerListSearch[%INDEX%].rightOwnerValue" class="form-control CV_RO_VALUE rightOwnerValue-src" />
						<span class="input-group-addon btn btn-primary ro-add-search-src"><i class="glyphicon glyphicon-search"></i></span>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-xs btn-default ro-remove-src"><span class="glyphicon glyphicon-minus"></span></button>
				</div>
			</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>