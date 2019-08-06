package org.wipo.connect.shared.exchange.business;

import java.util.List;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.menu.Menu;

public interface MenuBusiness {

    List<Menu> getHomeMenuList(List<String> permissionList, String ctx) throws WccException;

    List<Menu> getMainMenuList(List<String> permissionList, String ctx) throws WccException;

}
