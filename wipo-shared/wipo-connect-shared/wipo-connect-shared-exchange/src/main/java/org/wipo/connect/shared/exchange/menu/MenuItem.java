package org.wipo.connect.shared.exchange.menu;

import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.common.menu.AbstractMenuItem;
import org.wipo.connect.shared.exchange.enumerator.DomainEnum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuItem extends AbstractMenuItem {

    private static final long serialVersionUID = 6853751518994067103L;

    private List<MenuItem> menuItemList;

    private final String ROLE_APP_USER = "ROLE_APP_USER";

    @JsonIgnore
    @Override
    public String getMenuType() {
	return DomainEnum.INTERNATIONAL.name();
    }

    public List<MenuItem> getMenuItemList() {
	if (menuItemList == null) {
	    menuItemList = new ArrayList<>();
	}
	return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
	this.menuItemList = menuItemList;
    }

    @JsonIgnore
    @Override
    public String getPermission() {
	return ROLE_APP_USER;
    }

    @Override
    @JsonProperty
    public void setPermission(String permission) {
	super.setPermission(permission);
    }

}
