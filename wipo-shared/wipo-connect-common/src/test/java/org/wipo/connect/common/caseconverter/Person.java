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

public class Person {

	@CaseConversion
    private String name;

    private GenreEnum genre;

    private Integer age;

    private String description;



    public Person(){
        super();
    }

    public Person(String name,
    		GenreEnum genre,
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
    public GenreEnum getGenre() {
        return genre;
    }
    public void setGenre(GenreEnum genre) {
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


}
