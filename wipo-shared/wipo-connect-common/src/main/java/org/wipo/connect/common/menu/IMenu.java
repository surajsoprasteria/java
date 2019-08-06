package org.wipo.connect.common.menu;

import java.util.List;

public interface IMenu<T extends IMenuItem> {

    public String getMenuType();

    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public String getCssClass();

    public void setCssClass(String cssClass);

    public String getData();

    public void setData(String data);

    public String getPermission();

    public void setPermission(String permission);

    public String getI18n();

    public void setI18n(String i18n);

    public List<T> getMenuItemList();

    public void setMenuItemList(List<T> menuItemList);

}
