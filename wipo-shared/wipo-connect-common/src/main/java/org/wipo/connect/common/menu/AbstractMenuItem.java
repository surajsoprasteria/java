package org.wipo.connect.common.menu;

import java.io.Serializable;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractMenuItem implements Serializable, IMenuItem {

    private static final long serialVersionUID = -2595366169975332137L;

    private String id;

    private String name;

    private String cssClass;

    private String url;

    private String data;

    private String permission;

    private String i18n;

    private String ctx;

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
	return name;
    }

    @Override
    public void setName(String name) {
	this.name = name;
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
    public String getUrl() {
	if (StringUtils.trimToNull(url) == null) {
	    return "#";
	} else if (!StringUtils.startsWith(url, "/")) {
	    return url;
	} else if (StringUtils.trimToNull(ctx) == null) {
	    return url;
	} else {
	    return ctx + url;
	}
    }

    @Override
    public void setUrl(String Url) {
	this.url = Url;
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
    public void setCtx(String ctx) {
	this.ctx = ctx;
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
