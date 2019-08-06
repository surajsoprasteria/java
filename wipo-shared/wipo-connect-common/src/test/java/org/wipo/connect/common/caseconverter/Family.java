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
package org.wipo.connect.common.caseconverter;

import java.util.List;

public class Family {

    private Person mom;

    private Person dad;

    private List<Person> children;

    private List<Integer> intList;

    private String description;

    private byte[] attach;

    public Family(){
        super();
    }

    public Family(Person mom,
            Person dad,
            List<Person> children,
            String description) {
        super();
        this.mom = mom;
        this.dad = dad;
        this.children = children;
        this.description = description;
    }

    public Person getMom() {
        return mom;
    }
    public void setMom(Person mom) {
        this.mom = mom;
    }
    public Person getDad() {
        return dad;
    }
    public void setDad(Person dad) {
        this.dad = dad;
    }
    public List<Person> getChildren() {
        return children;
    }
    public void setChildren(List<Person> children) {
        this.children = children;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAttach() {
        if(attach == null){
            attach = new byte[0];
        }
        return attach;
    }

    public void setAttach(byte[] attach) {
        this.attach = attach;
    }

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

}
