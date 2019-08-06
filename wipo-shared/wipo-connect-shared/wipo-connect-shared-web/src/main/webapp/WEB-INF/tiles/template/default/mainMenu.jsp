<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/tags/tags.jspf"%>

<script type="text/javascript">

	<!-- Navigation -->
	$(document).ready(function() {
		
		$.ajax({
		    type: "POST",
		    dataType: 'json',
		    url: "${ctxMvc}/common/mainMenu.json",
		    cache: true,
		    success: function (data) {
		    	menuManagment(data);
		    }
		});
		
		
		function menuManagment(data) {
			var $container = $("<ul class='nav navbar-nav navbar-right'></ul>");
			
			if(typeof data.menuList === "undefined") { return; }
			$.each(data.menuList, function( i, menu ) {
				if(menu === null) { return; }
				
				var $menu = $("<li id='" + menu.id + "' class='dropdown'></li>");
				
				var $menuLink = $("<a href='#' " + menu.data + " class='dropdown-toggle' data-toggle='dropdown' role='button' aria-expanded='false'></a>");
				$menuLink.append("<span class='main-menu-ico " + menu.cssClass + "' title='" + globalParams.messages[menu.i18n] + "'></span>");
				$menuLink.append("<span class='main-menu-desc'>" + globalParams.messages[menu.i18n] + "</span>");
				$menuLink.append("<span class='caret'></span>");
				
				$menu.append($menuLink);
				
				var $menuItem = subMenuManagment(menu);
				$menu.append($menuItem);
				
				var $selectedItem = $container.find('li a[href="' + window.location.pathname + '"]');
				$selectedItem.addClass('selected');
				$selectedItem.closest(".dropdown").find('.main-menu-ico').addClass('selected');
				
				$container.append($menu);
			});
			
			$("#navbar-collapse").append($container);
		}
		
		
		function subMenuManagment(menuObj) {
			
			var menu = Object.create( menuObj );
			if( menu.menuItemList == null || menu.menuItemList.length < 1 ) {
				return null;
			}

			var $menuItem = $("<ul class='dropdown-menu' role='menu'></ul>");
			$menuItem.append("<li role='presentation' class='dropdown-header'>" + globalParams.messages[menu.i18n] + "</li>");
			
			$.each(menu.menuItemList, function( j, menuItem ) {
				var $menuItemLink = $("<li id='" + menuItem.id + "' class='" + menuItem.cssClass + "'></li>");
				$menuItemLink.append("<a href='" + menuItem.url + "' " + menuItem.data +" >" + globalParams.messages[menuItem.i18n] + "</a>");						
				$menuItemLink.append(subMenuManagment(menuItem));
				
				$menuItem.append($menuItemLink);
			});

			return $menuItem;

		}
		
	});
	
//# sourceURL=mainMenu.js
</script>