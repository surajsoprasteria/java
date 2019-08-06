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

import java.util.HashMap;
import java.util.Map;

import org.wipo.connect.common.customvalidation.CustomValidatedField;

public class Person {

    @CustomValidatedField(fieldCode="PERSON_NAME")
    private String name;

    @CustomValidatedField(fieldCode="PERSON_GENRE")
    private String genre;

    @CustomValidatedField(fieldCode="PERSON_AGE")
    private Integer age;

    @CustomValidatedField(fieldCode="PERSON_DESC")
    private String description;

    @CustomValidatedField(dynamicFields=true)
    private Map<String, DynamicFieldMock> dynamicValueMap;


    public Person(){
        super();
    }

    public Person(String name,
            String genre,
            Integer age,
            String description) {
        super();
        this.name = name;
        this.genre = genre;
        this.age = age;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, DynamicFieldMock> getDynamicValueMap() {
        if(dynamicValueMap == null){
            dynamicValueMap = new HashMap<String, DynamicFieldMock>();
        }
        return dynamicValueMap;
    }

    public void setDynamicValueMap(Map<String, DynamicFieldMock> dynamicValueMap) {
        this.dynamicValueMap = dynamicValueMap;
    }

}
