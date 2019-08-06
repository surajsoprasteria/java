<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>
<%@ page import="org.wipo.connect.shared.exchange.enumerator.*" %>
<%@ page import="org.wipo.connect.enumerator.ReferenceManagedByEnum" %>

<c:set var="idSfx" value="RefOtherDet" />
<c:set var="SYSTEM" value="<%=ReferenceManagedByEnum.SYSTEM.getCode()%>" />
<c:set var="SHARED" value="<%=ReferenceManagedByEnum.SHARED.getCode()%>" />
<tiles:insertDefinition name="contentTemplate">

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
    		$(document).ready(function(){
    			$(document).off(".${idSfx}");
				var thisDialog = getParentDialog(".container${idSfx}");
				var insertMode = "${insertMode}" === "true";
				var isSystemManaged = "${vo.managedBy}" === "${SYSTEM}";

				//Init UI Plugins
				$(".container${idSfx} select.form-control").selectpicker();
            	$(".container${idSfx} .input-group.date").datepicker();
            	
            	
				$('#tblValueReferences${idSfx}').DataTable( {
        			"dom": 'f<"multi-action-container"><"clear"><"top"ilp>rt<"bottom"ilp><"clear">',
    				"lengthMenu": [25,50,100,200],
    				"pageLength": 50,
					"ajax" : "${ctx}/mvc/reference/otherReferences/findAllValuesReference.json?code=${vo.code}",
    				"columns": [
    		           	{ "data": "code", "title": "${msg['otherReferences.col-code']}"},
    		        	{ "data": "name", "title":"${msg['otherReferences.col-name']}"},
    		        	{ "data": null, "visible":!isSystemManaged,"title":"" , "sortable":false, "render": actionRender, className:"text-center", "width":"25px"}
    		        ]
    		    });
    			
            	
				$(".container${idSfx} form").validate({rules:{
					"code" : {required:true, remote: function(element) {
                		return {
                			"url": globalParams.ctxMvc + "/reference/otherReferences/checkCodeUniqueness.json",
                			"type": "post",
                			"data": {
                				"id": "${vo.id}",
                				"code": $(element).val()
                			}
                		}
                	}},
					"name" : {required:true},
					"managedBy" : {required:true}
				}});

				if(isSystemManaged==true){
		     		$(".container${idSfx} form .general-info-panel").blockEdit();
				}
				
				
				$('#tblValueReferences${idSfx}').on('click', 'button.btn-detail', function () {
					var tblObj = $('#tblValueReferences${idSfx}').dataTable().api();
	       	 		var tr = $(this).closest('tr');
	       	        var rowData = tblObj.row( tr ).data();
	
	       	        openModalDetail({
	       	        	"dialogTitle": "${msg['otherReferences.values']}",
	       	        	"wrapperClass": "modal${idSfx}",
	           	        "url":"${ctx}/mvc/reference/otherReferences/detailValueReference",
	           	        "data":{ 
	           	       		"id":rowData.id,
	           	       		"code":rowData.code,
	           	       		"name":rowData.name,
	           	       		"insertMode":false
	           	        }
	           	    });
	       	         
	           	});
       		
       		
       			$('.container${idSfx}').on('click', 'button.add-value-reference', function () {	      	         
	       	     	openModalDetail({
	       	        	"dialogTitle": "${msg['otherReferences.values']}",
	       	        	"wrapperClass": "modal${idSfx}",
	           	        "url":"${ctx}/mvc/reference/otherReferences/detailValueReference",
	           	        "data":{
	           	        	"id":null,
	           	        	"insertMode":true
	           	        }
	           	     });
	           	});
				
				
				function actionRender(data,type,row,meta){
					var htmlOut="";
					if(isSystemManaged==false){
         				htmlOut = "<div class='button-wrapper'><button type='button' class='btn btn-xs btn-primary btn-detail'><span class='glyphicon glyphicon-search'></span></button></div>";
					}
         			return htmlOut;
        		}
				
				
             	$("#btnSave${idSfx}").click(function(e){
             		var table = $('#tblValueReferences${idSfx}').dataTable();
             		var tableData = table.api().rows().data();
             		var indexes = table.api().rows().indexes();
             		var currentForm = $("#form${idSfx}");
             		var listIndex = 0;
             		
    	    		if (!currentForm.valid()){
    	    			return;
    	    		}
             		
             		$("#form${idSfx} .row-dynamic").remove();
             		
         			for(var i = 0; i < tableData.length; ++i) {
         				var tableDataRow = table.api().row(indexes[i]).data();
         				var newRow = $(".templateReferenceValue .row-dynamic").clone();
         			
         				if (tableDataRow.isEdit !== true ) { continue; }
         				
         				newRow.find(":input").each(function(){
        					var newName = $(this).attr("name") || "";
        					newName = newName.replace("%INDEX%",listIndex);
        					$(this).attr("name",newName);
        					
        					var newId = $(this).attr("id") || "";
        					newId = newId.replace("%INDEX%",listIndex);
        					$(this).attr("id",newId);
        				});
         				
         				newRow.find("input[name*='.id']").val(tableDataRow.id);
         				newRow.find("input[name*='.code']").val(tableDataRow.code);
         				newRow.find("input[name*='.name']").val(tableDataRow.name);
         				newRow.find("input[name*='.fkName']").val(tableDataRow.fkName);
         				
         				listIndex++;
         				$("#form${idSfx}").append(newRow);
         			}
             		
             		$("#form${idSfx}").data("validation-type", "LOCAL");
           	    	$("#form${idSfx}").attr('action', "${ctx}/mvc/reference/otherReferences/saveReferenceType.json")
           	    	$("#form${idSfx}").submit();
             	});
             	
             	//Need for select input
             	/* if(!insertMode||insertMode==false){
             		$(".container${idSfx} form [name='managedBy']").blockEdit();
             	} */
             	
				//remember to add validation before init ajax form
        		initAjaxForm('.container${idSfx} form' ,{
            			success:function(data,status,xhr,formObj){
                			if (formObj.attr('action')){            				
                				goToUrl('${ctx}/mvc/reference/otherReferences/search');
                			}
                		}
        		
        		});
    			
    		});
    		
    		//# sourceURL=${idSfx}.js
    	</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx}">
			<form:form id="form${idSfx}" cssClass="form-inline" modelAttribute="vo" action="" method="POST">
				<form:hidden path="id" id="id${idSfx}"/>
				<form:hidden path="fkName" id="fkName${idSfx}"/>
				<form:hidden path="fkDescription" id="fkDescription${idSfx}"/>
				<form:hidden path="managedBy" id="managedBy${idSfx}"/>
				 <jsp:include page="/WEB-INF/view/reference/otherReferences/general-info.jsp" flush="true"></jsp:include>
			 	 <jsp:include page="/WEB-INF/view/reference/otherReferences/reference-values-section.jsp" flush="true"></jsp:include>
			 	 
			 	 <div class="form-row">
					<c:if test="${vo.managedBy.equals(SHARED)}">
					<div class="pull-right">
						<button type="button" id="btnSave${idSfx}" class="btn btnSave btn-primary">${msg['global.save']}</button>
					</div>
					</c:if>
					<div class="clear"></div> 
				</div>
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/view/reference/otherReferences/template.jsp" flush="true"></jsp:include>
	</tiles:putAttribute>
</tiles:insertDefinition>