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
package org.wipo.connect.common.customvalidation.test;

import java.util.List;

import org.wipo.connect.common.customvalidation.CustomValidatedField;

public class Family {

    @CustomValidatedField(innerValidation=true)
    private Person mom;

    @CustomValidatedField(innerValidation=true)
    private Person dad;

    @CustomValidatedField(innerValidation=true)
    private List<Person> children;

    @CustomValidatedField(fieldCode="FAMILY_DESC")
    private String description;

    @CustomValidatedField(fieldCode="ATTACH")
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

}
