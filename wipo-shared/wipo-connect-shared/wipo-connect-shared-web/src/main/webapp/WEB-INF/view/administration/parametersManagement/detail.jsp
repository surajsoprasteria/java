<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>

<c:set var="idSfx" value="ParamManagDet" />
<c:set var="ID_TYPE_USER" value="<%= IdentifierTypeEnum.WCC_ACCOUNT_ID %>" />
<c:set var="FT_STRING" value="<%= FieldTypeEnum.STRING %>"/>
<c:set var="FT_INTEGER" value="<%= FieldTypeEnum.INTEGER %>"/>
<c:set var="FT_DECIMAL" value="<%= FieldTypeEnum.DECIMAL %>"/>
<c:set var="FT_DATE" value="<%= FieldTypeEnum.DATE %>"/>
<c:set var="FT_LIST" value="<%= FieldTypeEnum.LIST %>"/>
<c:set var="FT_FILE" value="<%= FieldTypeEnum.FILE %>"/>

<c:set var="YES" value="<%= YesNoEnum.YES.getLabel() %>"/>
<c:set var="NO" value="<%= YesNoEnum.NO.getLabel() %>"/>


<tiles:insertDefinition name="defaultTemplate">

	<tiles:putAttribute name="title">${msg['functions.parameters_managemenet']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['functions.parameters_managemenet']}</tiles:putAttribute>

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
        		
    			$(".container${idSfx} .input-group.date").datepicker();
        		$(".container${idSfx} select.form-control").selectpicker();

        		$(document).on("save.${idSfx}", function () {
    	        	var tblObjPerformerConfig = $('#tblPerformersConfig${idSfx}').dataTable().api();
    	        	tblObjPerformerConfig.ajax.reload();
    	        }); 

        		 $('#tblPerformersConfig${idSfx}').dataTable( {
           			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
       				"lengthMenu": [25,50,100,200],
       				"pageLength": 50,
     					"ajax" : "${ctx}/mvc/administration/parametersManagement/findPerformersConfig.json",
       				"columns": [
       		           	{ "data": "fkCreationClass", "title": "${msg['paramManagment.def-aff-creation-class']}","render": actionRenderCreationClass,className:"text-center"},
       		           	{ "data": "hidePerformers", "title": "${msg['paramManagment.hide-performers']}", className:"text-center", "render": hidePerformerRender},
       		          	{ "data": null, "sortable":false, "render": actionRenderPerformerConfig, className:"text-center"} 
       		        ]
       		    	} );

     	    	  var tblObjPerformersConfig = $('#tblPerformersConfig${idSfx}').dataTable().api();

     		    	function actionRenderPerformerConfig(data, type, row, meta){
     		    		var htmlOut = "";
     					htmlOut += "<button type='button' class='btn btn-xs btn-primary btn-detail-performer-config'><span class='glyphicon glyphicon-search'></span></button>";
     					return htmlOut;
     				}

     		    	function hidePerformerRender(data, type, row, meta){
     		    		if(data){
     						return "${YES}";
     				    }else {
     						return "${NO}";
     					}
     				}

     		    	function actionRenderCreationClass(data, type, row, meta) {
    					
    					var creationClassMap = meta.settings.json.creationClassMap;
    					return creationClassMap[data].code;
    				
    				}

				
        		$('#tblPerformersConfig${idSfx}').on('click', '.btn-detail-performer-config', function (event) {
	       	 		 var tr = $(this).closest('tr');
	       	         var rowData = tblObjPerformersConfig.row( tr ).data();
	       	         openModalDetail({
	       	        	 "dialogTitle": "${msg['paramManagment.performers-configuration']}",
	           	         "url":"${ctx}/mvc/administration/parametersManagement/detailPerformersConfig",
	           	         "data":{ "id":rowData.id, "fkCreationClass":rowData.fkCreationClass, "hidePerformers":rowData.hidePerformers },
	       	      		 "srcEvent":event
	           	     });
	       	         
	           	 }); 
	           	 
    		});
    		
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
				
				<!-- Tab panels -->
				 <div class="tab-content">
					 <jsp:include page="/WEB-INF/view/administration/parametersManagement/number-format-section.jsp" flush="true"></jsp:include>
				 	 <jsp:include page="/WEB-INF/view/administration/parametersManagement/performers-configuration.jsp" flush="true"></jsp:include>
				 </div>
				
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>