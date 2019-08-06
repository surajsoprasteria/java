<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<c:set var="idSfx" value="Home" />

<tiles:insertDefinition name="defaultTemplate">

	<tiles:putAttribute name="title">${msg['home.page-title']}</tiles:putAttribute>
	<tiles:putAttribute name="pageInfo" cascade="true">${msg['home.page-info']}</tiles:putAttribute>

	<tiles:putAttribute name="pageScript">
		<script type="text/javascript">
			$(document).ready(function() {
				
				<c:if test="${clearStorage}">
					<c:remove var="clearStorage" scope="session" />
					sessionStorage.clear();
				</c:if>
			
				$.ajax({
				    type: "POST",
				    dataType: 'json',
				    url: "${ctxMvc}/common/homeMenu.json",
				    cache: true,
				    success: function (data) {
				    	menuManagment(data);
				    }
				});
				
				
				function menuManagment(data) {
					var itemForRow = 4;
					var $container = $("<div class='sec-list'>");
					
					if(typeof data.menuList === "undefined") { return; }
					$.each(data.menuList, function( i, menu ) {
						if(menu === null) { return; }
						
						var $menu = $("<div class='col-sm-3'></div>");
						var $menuRow = $("<div class='row'></div>");
						var $menuLink = $("<div class='sec " + menu.cssClass + "' id='" + menu.id + "' " + menu.data + "></div>");
						var $menuLabel = $("<label>" + globalParams.messages[menu.i18n] + "</label>");
						var $menuItem = subMenuManagment(menu);
		
						$menuLink.append($menuItem);
						$menu.append($menuLink);
						$menu.append($menuLabel);
						
						if (i % itemForRow == 0) {
							$menuRow.append($menu);
							$container.append($menuRow);
						} else {
							$container.find('.row').last().append($menu);
						}
						
						
					});
					
					$(".home-menu-container").append($container);
				}
				
				
				function subMenuManagment(menuObj) {
					
					var menu = Object.create( menuObj );
					if( menu.menuItemList == null || menu.menuItemList.length < 1 ) {
						return null;
					}
		
					var $menuItem = $("<ul></ul>");
					
					$.each(menu.menuItemList, function( j, menuItem ) {
						
						var $menuItemLink = $("<li id='" + menuItem.id + "' class='sec-func " + menuItem.cssClass + "'></li>");
						$menuItemLink.append("<a href='" + menuItem.url + "' " + menuItem.data + ">" + globalParams.messages[menuItem.i18n] + "</a>");
	
						$menuItemLink.append(subMenuManagment(menuItem));
						$menuItem.append($menuItemLink);
					});
	
					return $menuItem;
				}
				
			});
			
			//# sourceURL=homeMenu.js			
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<div class="container${idSfx} home-menu-container"></div>
	</tiles:putAttribute>
</tiles:insertDefinition>