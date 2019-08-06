package org.wipo.connect.common.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractMenu<T extends IMenuItem> implements Serializable, IMenu<T> {

    private static final long serialVersionUID = 1807537165769652708L;

    private String id;

    private String name;

    private String cssClass;

    private String data;

    private String permission;

    private String i18n;

    private List<T> menuItemList;

    @Override
    public String getId() {
	if (StringUtils.trimToNull(id) == null) {
	    id = UUID.randomUUID().toString();
	}

	return id;
    }

    @Override
    public void setId(String id) {
	this.id = id;
    }

    @Override
    public String getName() {
	if (StringUtils.trimToNull(name) == null) {
	    name = "myName";
	}
	return name;
    }

    @Override
    public void setName(String Name) {
	this.name = Name;
    }

    @Override
    public String getCssClass() {
	return StringUtils.trimToEmpty(cssClass);
    }

    @Override
    public void setCssClass(String cssClass) {
	this.cssClass = cssClass;
    }

    @Override
    public String getData() {
	return StringUtils.trimToEmpty(data);
    }

    @Override
    public void setData(String data) {
	this.data = data;
    }

    @Override
    public String getPermission() {
	return permission;
    }

    @Override
    public void setPermission(String permission) {
	this.permission = permission;
    }

    @Override
    public String getI18n() {
	return i18n;
    }

    @Override
    public void setI18n(String i18n) {
	this.i18n = i18n;
    }

    @Override
    public List<T> getMenuItemList() {
	if (menuItemList == null) {
	    menuItemList = new ArrayList<>();
	}
	return menuItemList;
    }

    @Override
    public void setMenuItemList(List<T> menuItemList) {
	this.menuItemList = menuItemList;
    }

    public void addCssClass(String className) {
	cssClass = StringUtils.trimToEmpty(cssClass);
	className = StringUtils.trimToNull(className);

	if (className != null) {
	    cssClass = cssClass + " " + className;
	    cssClass = Stream.of(StringUtils.split(cssClass))
		    .distinct().collect(Collectors.joining(" "));
	}
    }

}
