<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.enumerator.*" %>

<c:set var="idSfx" value="searchGroup" />

<tiles:insertDefinition name="defaultTemplate">

	<tiles:putAttribute name="title">${msg['groupManagement.title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['groupManagement.title']}</tiles:putAttribute>
	
	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    	 	$(document).ready(function(){
    	 		$(document).off(".${idSfx}");
    	 		
    	 		var canRender = "${canRender}" === "true";
    	 		var autoSearchMode = "${autoSearchMode}" === "true";
    	 		var searchURL;
    	 		if(autoSearchMode){
    				searchURL = "${ctx}/mvc/groupManagement/findGroup.json?"+ $(".container${idSfx} #simpleSearchForm${idSfx}").serialize();
    	 		}else{
    	 			 searchURL = "${ctx}/mvc/common/emptyData.json";
    	 		}
    			
    			
    			//----------------------------------------DATATABLE----------------------------------------//   
        		var tblObj = $('#tblGroupManagement${idSfx}').DataTable( {
        			"dom": '<"multi-action-container"><"clear"><"top"ilp>r<"fix-overflow"t><"bottom"ilp><"clear">',
	    			"lengthMenu": [${configProperties['available-entries-table']}],
					"pageLength": ${configProperties['entries-value-default']},
					"deferRender": true,
					"processing": true,
					"serverSide": true,
					"ajax" :searchURL,
    				"columns": [
						{ "data": null,"title":"<input type='checkbox' class='select-all-rows' />", "width":"25px", "orderable": false, "render":colRender("checkboxId"), className:"text-center"} ,
						{ "name":"PG_main_id", "data": "mainId", "title":"${msg['groupManagement.id']}"},
						{ "name":"PG_name", "data": "name", "title":"${msg['groupManagement.groupName']}","render":groupNameRender},
						{ "data": "listName",visible:"${configProperties['ip_grid.names_visible']}"=='true', "title":"${msg['interestedPartyDetail.names']}","render": ipNameRender, "orderable":false},
						{ "data": null, "title":"", "orderable":false, "render": actionGroupManagementRender, className:"text-center", "width":"60px"}
    		        ],
    		        "order": [[1,"asc"]]
    		    } );

        		var tblObj = $('#tblGroupManagement${idSfx}').dataTable().api();

        		function groupNameRender(data, type, row, meta) {
        			var outputName = "";
       				var name=row.fullName;
					var nameType = row.nameType ? row.nameType : "";
        			outputName += "<div class='row'><div class='col-sm-9 text-ellipsis'><b>"+name+"</b></div>"+"<div class='col-sm-3 text-ellipsis'><b>"+nameType+"</b></div></div>";

       				return outputName; 
       			}
        		
        		function ipNameRender(data, type, row, meta) {
        			var numberOfNameMax = parseInt("${configProperties['ip_group_grid.max_number_names']}");
       				var idOut = "";
       				var SEP = "\u0000\u0000";
       				var nameList = row.groupDetails || [];
       				var nameFormatted = "";
       				var names = []
       				for(var i=0; i<nameList.length; i++){
       					nameFormatted = nameList[i].fullName;
   						if(nameList[i].nameType == "PA"){
							var mainId = nameList[i].mainId ? nameList[i].mainId : "";
   							names.push(nameFormatted +SEP+ mainId);
   						}
               		}
       				names = names.sort(compareCaseInsensitive);
       				for(var i=0; i<names.length; i++){
           				var split = names[i].split(SEP);
           				if(i < numberOfNameMax){
           					idOut += "<div class='row'><div class='col-sm-10 text-ellipsis'>"+split[0]+"</div>"+
           							 "<div class='col-sm-2 text-ellipsis'>"+split[1]+"</b></div></div>";
           				}else{
           					idOut += "<div class='row hidden'><div class='col-sm-10 text-ellipsis'>"+split[0]+"</div>"+
  							 		 "<div class='col-sm-2 text-ellipsis'>"+split[1]+"</b></div></div>";
                   		}
           			}
       				return idOut; 
       			}
       			

        		function actionGroupManagementRender(data, type, row, meta){
        			var htmlOut = "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
        			return htmlOut;
        		}
        		

        		$("#btnSearch${idSfx}").click(function(){
   					tblObj.ajax.url("${ctx}/mvc/groupManagement/findGroup.json?"+ $(".container${idSfx} #simpleSearchForm${idSfx}").serialize()).load();
       			});
       			
        		
        		//----------------------------------------Actions----------------------------------------//   
				$('#tblGroupManagement${idSfx}').on('click', 'button.btn-detail', function () {
	  	 			var tr = $(this).closest('tr');
	  	         	var rowData = tblObj.row( tr ).data();

	       	        openModalDetail({
	       	       		"dialogTitle": "${msg['groupManagement.group']}",
	       	        	"wrapperClass": "modal-detail-group",
	           	        "url":"${ctx}/mvc/groupManagement/detailGroup",
	           	        "data":{insertMode:false,idGroup:rowData.idGroup}
	           	     });
	       	         
	           	});
				
				$("button.add-group").click(function(){
			    	openModalDetail({
	       	        	"dialogTitle": "${msg['groupManagement.group']}",
	       	        	"url":"${ctx}/mvc/groupManagement/detailGroup",
	       	        	"data":{insertMode:false}
	           	     });
				});

				//to submit simple search by pressing enter key
         		$('#valueForm${idSfx}').on("keypress", function(e) {
       		    	var code = (e.keyCode ? e.keyCode : e.which);
       		     	if (code == 13) {
         		    	e.preventDefault();
         		    	e.stopPropagation();
         		    	tblObj.ajax.url("${ctx}/mvc/groupManagement/findGroup.json?"+ $(".container${idSfx} #simpleSearchForm${idSfx}").serialize()).load();
					}
         		});
				//----------------------------------------Actions----------------------------------------//   

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
					<form:form cssClass="form-inline" id="simpleSearchForm${idSfx}" modelAttribute="searchVO">
						<form:hidden path="idName" id="idName${idSfx}" />
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<form:input path="valueForm" id="valueForm${idSfx}" cssClass="form-control" cssStyle="width:100%" placeholder="${msg['groupManagement.simple-search-hint']}" />
						</div>
						<div class="form-row text-right">
							<div class="form-group">
								<button type="button" id="btnSearch${idSfx}" class="btn btn-primary">${msg['global.search']}</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<div class="panel panel-default panel-class"> 
			<div class="panel-heading">
				<c:if test="${canRender}">
					<button type="button" class="btn btn-xs btn-primary add-group pull-right"><span class="glyphicon glyphicon-plus"></span></button>
				</c:if>
				<h3 class="panel-title">${msg['groupManagement.group-section']}</h3>
			</div>
			<div class="panel-body">
				 <table id="tblGroupManagement${idSfx}"></table> 
			</div>
	</div>	
	</div>		
	</tiles:putAttribute>
</tiles:insertDefinition>