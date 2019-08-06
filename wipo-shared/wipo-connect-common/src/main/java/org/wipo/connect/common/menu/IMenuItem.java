package org.wipo.connect.common.menu;

public interface IMenuItem {

    public String getMenuType();

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String getCssClass();

    public void setCssClass(String cssClass);

    public String getUrl();

    public void setUrl(String url);

    public String getData();

    public void setData(String data);

    public String getPermission();

    public void setPermission(String permission);

    public String getI18n();

    public void setI18n(String i18n);

    public void setCtx(String ctx);

}
