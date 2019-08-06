<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>


<c:set var="idSfx" value="BISrc" />

<c:set var="PARAM_TYPE_STRING" value="<%=QueryParameterTypeEnum.STRING.name()%>" />
<c:set var="PARAM_TYPE_INTEGER" value="<%=QueryParameterTypeEnum.INTEGER.name()%>" />
<c:set var="PARAM_TYPE_DATE" value="<%=QueryParameterTypeEnum.DATE.name()%>" />
<c:set var="PARAM_TYPE_BOOLEAN" value="<%=QueryParameterTypeEnum.BOOLEAN.name()%>" />
<c:set var="PARAM_TYPE_DECIMAL" value="<%=QueryParameterTypeEnum.DECIMAL.name()%>" />

<c:set var="FT_STRING" value="<%= FieldTypeEnum.STRING.name() %>"/>
<c:set var="FT_INTEGER" value="<%= FieldTypeEnum.INTEGER.name() %>"/>
<c:set var="FT_DECIMAL" value="<%= FieldTypeEnum.DECIMAL.name() %>"/>
<c:set var="FT_DATE" value="<%= FieldTypeEnum.DATE.name() %>"/>
<c:set var="FT_LIST" value="<%= FieldTypeEnum.LIST.name() %>"/>
<c:set var="FT_FILE" value="<%= FieldTypeEnum.FILE.name() %>"/>

<c:set var="pageTemplate" value="defaultTemplate" />

<c:choose>
	<c:when test="${favoriteSearch}">
		<c:set var="collapsePanel" value="collapsed" />
	</c:when>
	<c:otherwise>
		<c:set var="collapsePanel" value="" />
	</c:otherwise>
</c:choose>

<tiles:insertDefinition name="${pageTemplate}">

	<tiles:putAttribute name="title">${msg['bi.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['bi.page-title']}</tiles:putAttribute>
	
    <tiles:putAttribute name="pageScript">
    	<script type="text/javascript">
		    $(document).ready(function(){
	
	   		$(document).off(".${idSfx}"); //disable all old page specific events
	   		
	   		var favoriteSearch = "${favoriteSearch}" === "true";
	
	   		$(".container${idSfx} select.form-control").selectpicker();
	   		
	   		$("#form${idSfx}").validate({rules:{
				"queryCode": {"required":true}
		    }});
	   		addValidationRules();
	   	 	
	    		
	   		function addValidationRules(){
	   			
	   			$(".container${idSfx} form input[type-param]").each(function(){
	   				$(this).rules("remove");
	   			});
	
				$(".container${idSfx} form input[type-param='STRING']").each(function(){
					$(this).rules( "add", {required: true, minlength: 1}); 
				});
	
	    		$(".container${idSfx} form input[type-param='INTEGER']").each(function(){
	    			$(this).rules( "add", {required: true,digits: true});
	    		});
	
	    		$(".container${idSfx} form input[type-param='BOOLEAN']").each(function(){
	    			$(this).rules( "add", {required: true, digits: true,range:[0,1]});
	    		});
				
	    		$(".container${idSfx} form input[type-param='DECIMAL']").each(function(){
	    			$(this).rules( "add", {required: true, number: true});
	    		});
	
	    		$(".container${idSfx} form input[type-param='DATE']").each(function(){
	    			$(this).rules( "add", {required: true,date: true}); 
	    		});
	
	
		    	applyAutoNumeric();
	   		}
	    		
	   		$('#queryCode${idSfx}').change( function() {
				var selectedQueryCode = $(".container${idSfx} .search-panel select.queryCode-select option:selected").val();
				
				if (selectedQueryCode != "") {
				 	executeAction({
				 		url: "${ctx}/mvc/businessIntelligence/findQueryParam.json",
				 		data: {"selectedQueryCode":selectedQueryCode},
			        	success: function(data, status, xhr,formObj) {
			        		$(".param-section .templateQueryParam${idSfx}").remove();
			        		var paramMap=data.vo.queryParameterObject;
			        		for(key in paramMap){
								var paramFlat=paramMap[key];
			        			addQueryParamInput(paramFlat);
					        }
			        		addValidationRules();
			        		tblQueryResult.destroy();
			        		$('#tblQueryResult${idSfx}').empty();
			        		tblQueryResult = $('#tblQueryResult${idSfx}').DataTable(generateDtOptions(true));
			        	},
			        	error : function(data, status, xhr, formObj) {
							if(data === Object(data) && data.message){
			    		    	showWarnMessage({message:data.message});
			    		    }
							$(".param-section .templateQueryParam${idSfx}").remove();
							tblQueryResult.destroy();
			        		$('#tblQueryResult${idSfx}').empty();
			        		tblQueryResult = $('#tblQueryResult${idSfx}').DataTable(generateDtOptions(false));
			    		}
			 		});
				} else {
					$(".param-section .templateQueryParam${idSfx}").remove();
					tblQueryResult.destroy();
	        		$('#tblQueryResult${idSfx}').empty();
	        		tblQueryResult = $('#tblQueryResult${idSfx}').DataTable(generateDtOptions(false));
				}
				
			});
	
	   		function addQueryParamInput(param){
	   			var paramsDiv = $(".param-section .form-row");
	   			/* var rowCount = titleDiv.find(".row-dynamic").size(); */
	   			var newRow = $(".template${idSfx} .templateQueryParam${idSfx}." + param.type).clone();
	
	   			/* newRow.addClass(""+rowCount); */
	   			newRow.find(":input").each(function(){
	   				var newParamName = $(this).attr("name") || "";
	   				newParamName = newParamName.replace("%KEY%",param.code);
	   				$(this).attr("name",newParamName);
	   				
	   				var newId = $(this).attr("id") || "";
	   				newId = newId.replace("%KEY%",param.code);
	   				$(this).attr("id",newId);
	
	   				$(this).attr("code",param.code);
	   				$(this).attr("type-param",param.type);
	
	   				if(param.type === "INTEGER"){
	   					$(this).addClass("auto-integer");
	   	   			}else if(param.type === "DECIMAL"){
	   	   				$(this).addClass("auto-decimal");
	   	   			}else if(param.type === "DATE"){
	   	   				$(this).closest(".input-group.date").datepicker();
		   	   			var dateFormat = $.fn.datepicker.dates[$.fn.datepicker.defaults.language].format;
	  					$(this).attr("placeholder",dateFormat);
	  					var formatMask = dateFormat.replace(/dd|mm|yy/gi,"00");
	  					$(this).mask(formatMask,{placeholder:dateFormat});
	   	   			}
	   				
	   				/* $(this).attr("type",param.htmlType); */
	   				
	   			});
	   			newRow.find("label").text(param.description);
	   			paramsDiv.append(newRow);
	   			
	   		}
	
			/* ---------------------------------Datatable management (tblQueryResult)--------------------------------- */
				
			var tblQueryResult = $('#tblQueryResult${idSfx}').on('xhr.dt', function ( e, settings, json, xhr ) {
		        if (json && json.recordsTotal > 0) {
		        	$('.container${idSfx} button.bi-export-results').blockEdit('unblock');
		        } else {
		        	$('.container${idSfx} button.bi-export-results').blockEdit();
		        }
		    }).DataTable(generateDtOptions(favoriteSearch));
	
	
			function generateDtOptions(loadColumns){
	   			var dtOptions = {
					"dom": '<"multi-action-container"><"clear"><"top"i>r<"fix-overflow"t><"bottom"i><"clear">',
	    			"lengthMenu": [${configProperties['available-entries-table']}],
					"pageLength": ${configProperties['entries-value-default']},
					"paging": true,
					"deferRender": true,
					"processing": true,
					"serverSide": false,
					"order": false,
					"columns":[{"title":"&nbsp;", "orderable":false}],
					"columnDefs": [{ targets: "_all", "render": ellipsisColRender }],
					"drawCallback": function( settings ) {
						this.api().scroller.measure(false);
				    },
					"ajax": function (data, callback, settings) {

						var searchURL = "${ctx}/mvc/common/emptyData.json";
						var dataSrc = {};
						
						if (settings.showResults) {
							searchURL = "${ctx}/mvc/businessIntelligence/executeQuery.json"
							dataSrc = {
								'queryCode':$('#queryCode${idSfx}').val(),
								'getColumnsOnly':false
							};

							$.each($('.container${idSfx} .param-section :input'), function( key, value ) {
								  dataSrc[value.name] = value.value;
							});
							
							$.extend(dataSrc, data);
						}

						$.ajax({
							url: searchURL,
						    type: "POST",
						    data : dataSrc,
						    success:function(dataCallBack){
								data.recordsTotal = dataCallBack.data.length;
							    data.recordsFiltered = dataCallBack.data.length;
								data.data = dataCallBack.data;
						    	callback(data);
						    	
						    	if (dataCallBack.data && dataCallBack.data.length > 0) {
						    		$(".dataTables_scroll div.dataTables_scrollBody").css("min-height", 0);
						    		//$.fn.dataTable.Api(settings).scroller.measure();
						    	} else {
						    		settings.columnMaxWidth = [];
						    		$.each(settings.aoColumns, function( index, value ) {
						    			settings.columnMaxWidth[index] = $.fn.dataTable.Api(settings).columns(index).header()[0].style.width;
					    			});
						    		
						    		var $scrollDiv = $(".dataTables_scroll div.dataTables_scrollBody");
						    		var divScrollBarWidth = $scrollDiv.hasScrollBar().width;
						    		$scrollDiv.css("min-height", $('td.dataTables_empty').outerHeight() + divScrollBarWidth +1);
						    	}
						    }
						});
					}
	   			};	   			
	
	   			if (loadColumns) {
	   				
					var dataSrc = {
						'queryCode':$('#queryCode${idSfx}').val(),
						'getColumnsOnly':true
					};

					$.each($('.container${idSfx} .param-section :input'), function( key, value ) {
						  dataSrc[value.name] = value.value;
					});

	   				executeAction({
   	   					async: false,
			 			url: "${ctx}/mvc/businessIntelligence/executeQuery.json",
			 			data : dataSrc,
		        		success: function(res, status, xhr) {
		        			dtOptions.columns = res.columns;
		        			dtOptions.scrollCollapse = true;
							dtOptions.scrollY = 450;
							dtOptions.scrollX = true;
							dtOptions.scroller = { "displayBuffer": 100000, loadingIndicator: false };
							dtOptions.serverSide = false; //Change here
							dtOptions.order = [[0,"asc"]];
		        		}
   	   				});
	   	   	   	}
	
	   			return dtOptions;
	
			}
	
			$('.container${idSfx} #btnSearch${idSfx}').click( function() {
				var formObj = $("#form${idSfx}");
				if(!formObj.valid()) return;

				//tblQueryResult.ajax.url("${ctx}/mvc/businessIntelligence/executeQuery.json?" + formObj.serialize()).load();
				tblQueryResult.settings()[0].showResults = true;
				tblQueryResult.ajax.reload();
			});			
			
			
			$('.container${idSfx} button.bi-export-results').click( function() {
				if(!$("#form${idSfx}").valid()){
					return;
				}
				
				var dataSrc = { 'queryCode':$('#queryCode${idSfx}').val() };

				$.each($('.container${idSfx} .param-section :input'), function( key, value ) {
					  dataSrc[value.name] = value.value;
				});
				
				downloadFile("${ctx}/mvc/businessIntelligence/exportResults", dataSrc);
			});

	
	     	if("${autoSearchMode}" === "true"){
	     		$('#btnSearch${idSfx}').click();
	     	}
	     	
	     	$('.container${idSfx} form').on("keypress", function(e) {
	 			var code = (e.keyCode ? e.keyCode : e.which);
	 		    if (code == 13) {
	 		    	e.preventDefault();
	 		    	e.stopPropagation();
	 		    	$('#btnSearch${idSfx}').click();
	 		    }
	 		});
	     	
    		function ellipsisColRender(data, type, row, meta) {
				if(type !== "display") return data;
				if (data != null) {
					var colWidth = meta.settings.columnMaxWidth[meta.col];					
					return "<div class='dt-column-ellipsis' title='"+ escapeHtml(data) + "' style='max-width:"+ colWidth +"'>" + data + "</div>";
				}
				return "";
   			}

	    });
	   	//# sourceURL=${idSfx}.js
	   	</script>
    </tiles:putAttribute>

    <tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<div class="panel panel-default search-panel ${collapsePanel}">
				<div class="panel-heading">
			  		<h3 class="panel-title pull-left">${msg['bi.page-info']}</h3>
				</div>
				<div class="panel-body">
				  	<form:form id="form${idSfx}" modelAttribute="vo" cssClass="form-inline" method="POST" enctype="multipart/form-data">
				  		<div class="form-group big">
					  		<div class="form-row">
					  			<label>${msg['bi.query-name']}</label>
					    		<form:select path="queryCode" id="queryCode${idSfx}" cssClass="form-control queryCode-select">
					    			<form:option value="" />
					    			<form:options items="${queryCodeList}"/>
					    		</form:select>
					    	</div>
				    	</div>
						<div class="param-section">
						<div class="form-row">
							<c:forEach items="${vo.queryParameterObject}" var="mapEntry">
								<c:set value="${mapEntry.value}" var="item"/>
								<c:choose>
									<c:when test="${item.type == 'DATE'}">
										<div class="form-group templateQueryParam${idSfx} DATE">
											<label>${item.description}</label>
											<div class="input-group date">
												<form:input type="text" path="queryParameterObject[${item.code}].formValue" type-param="${item.type}" code="${item.code}" cssClass="form-control" />
												<span class="input-group-addon btn btn-primary"><i class="glyphicon glyphicon-calendar"></i></span>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="templateQueryParam${idSfx} form-group ${item.type}">
											<label>${item.description}</label>
											<form:input type="text" path="queryParameterObject[${item.code}].formValue" type-param="${item.type}" code="${item.code}" cssClass="form-control" />
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						</div>
				    	<div class="form-row">
						    <div class="form-row text-right">
								<div class="form-group">
									<button type="button" id="btnSearch${idSfx}" class="btn btn-primary">${msg['global.search']}</button>
								</div>
							</div>
					    </div>
					    
				  	</form:form>
				    
				</div>
			</div>
			
			<div id="queryResult${idSfx}" class="panel panel-default">
				<div class="panel-heading">
					<button type="button" class="btn btn-xs btn-primary bi-export-results pull-right">
						${msg['bi.export-results']}
					</button>
					<h3 class="panel-title">${msg['bi.query-result']}</h3>
				</div>
				
				<div class="panel-body">
					<table id="tblQueryResult${idSfx}"></table>
				</div>
			</div>
		
		</div>
		<jsp:include page="/WEB-INF/view/businessIntelligence/template.jsp" flush="true"></jsp:include>
    </tiles:putAttribute>
</tiles:insertDefinition>