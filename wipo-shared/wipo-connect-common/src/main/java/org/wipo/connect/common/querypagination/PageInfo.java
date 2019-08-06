/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.common.querypagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.list.LazyList;
import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.enumerator.OrderByExpressionEnum;

public class PageInfo implements Serializable {

    private static final long serialVersionUID = -756230016240889808L;

    private Integer draw;
    private Integer start;
    private Integer length;
    private List<Order> order;
    private List<Column> columns;
    private Search search;

    public PageInfo() {
	super();
    };

    public PageInfo(Integer start, Integer length) {
	this.start = start;
	this.length = length;
	this.draw = 0;
    }

    public Integer getDraw() {
	return draw;
    }

    public void setDraw(Integer draw) {
	this.draw = draw;
    }

    public Integer getStart() {
	return start;
    }

    public void setStart(Integer start) {
	this.start = start;
    }

    public Integer getLength() {
	return length;
    }

    public Integer getPageNumber() {
	if (start == null || length == null) {
	    return ConstantUtils.DEFAULT_LIMIT;
	} else if (start != 0 && length != 0) {
	    return Math.floorDiv(start, length);
	} else {
	    return 0;
	}
    }

    public void setLength(Integer length) {
	this.length = length;
    }

    public List<Order> getOrder() {
	if (order == null) {
	    Factory<Order> factory = new Factory<Order>() {
		@Override
		public Order create() {
		    return new Order();
		}
	    };
	    order = LazyList.lazyList(new ArrayList<>(), factory);
	}
	return order;
    }

    public void setOrder(List<Order> order) {
	this.order = order;
    }

    public List<Column> getColumns() {
	if (columns == null) {
	    Factory<Column> factory = new Factory<Column>() {
		@Override
		public Column create() {
		    return new Column();
		}
	    };
	    columns = LazyList.lazyList(new ArrayList<>(), factory);
	}
	return columns;
    }

    public void setColumns(List<Column> columns) {
	this.columns = columns;
    }

    public Search getSearch() {
	return search;
    }

    public void setSearch(Search search) {
	this.search = search;
    }

    public String getOrderByExpression() {
	if (getOrder().isEmpty()) {
	    return null;
	}

	List<String> fields = new ArrayList<>();
	for (Order o : getOrder()) {
	    String name = getColumns().get(o.getColumn()).getName();
	    if (StringUtils.isEmpty(name)) {
		continue;
	    }

	    fields.add((OrderByExpressionEnum.parseField(name, " " + o.getDir())));
	}

	String out = StringUtils.join(fields, ", ");
	out = StringUtils.replace(out, "'", "''");
	// out = ESAPI.encoder().encodeForSQL(new MySQLCodec(Mode.ANSI), out);
	out = StringUtils.trimToNull(out);
	return out;
    }

}
