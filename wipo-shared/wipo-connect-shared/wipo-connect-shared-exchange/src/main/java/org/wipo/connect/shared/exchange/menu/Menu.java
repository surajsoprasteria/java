package org.wipo.connect.shared.exchange.menu;

import org.wipo.connect.common.menu.AbstractMenu;
import org.wipo.connect.shared.exchange.enumerator.DomainEnum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Menu extends AbstractMenu<MenuItem> {

    private static final long serialVersionUID = 6853751518994067103L;

    private final String ROLE_APP_USER = "ROLE_APP_USER";

    @JsonIgnore
    @Override
    public String getMenuType() {
	return DomainEnum.INTERNATIONAL.name();
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
