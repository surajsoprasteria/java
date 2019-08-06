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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.customvalidation.CustomValidationTypeEnum;
import org.wipo.connect.common.customvalidation.CustomValidator;
import org.wipo.connect.common.customvalidation.ICustomValidation;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccValidationException;
import org.wipo.connect.common.logging.WipoLoggerFactory;

public class CustomValidatorTest {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CustomValidatorTest.class);

    private Family familyOK;
    private Family familyKO;
    private Family familyFormalOK;
    private Family familyFormalKO;
    private List<ICustomValidation> rules;
    private Map<String, DynamicFieldMock> dynamicMapOK;
    private Map<String, DynamicFieldMock> dynamicMapKO;

    @Before
    public void initObjects() {
	rules = new ArrayList<>();
	CustomValidationMock r;

	// rules
	r = new CustomValidationMock();
	r.setCode("PERSON_NAME");
	r.setMandatory(true);
	r.setMaxLength(10);
	r.setInternational(true);
	r.setForced(true);
	rules.add(r);

	r = new CustomValidationMock();
	r.setCode("PERSON_GENRE");
	r.setMandatory(true);
	r.setRegularExpression("^(M|F)$");
	rules.add(r);

	r = new CustomValidationMock();
	r.setCode("PERSON_AGE");
	r.setMandatory(true);
	r.setMinValue(BigDecimal.valueOf(0L));
	r.setMaxValue(BigDecimal.valueOf(120L));
	rules.add(r);

	r = new CustomValidationMock();
	r.setCode("PERSON_DESC");
	r.setMandatory(false);
	r.setMaxLength(20);
	rules.add(r);

	r = new CustomValidationMock();
	r.setCode("FAMILY_DESC");
	r.setMandatory(false);
	r.setMaxLength(15);
	rules.add(r);

	r = new CustomValidationMock();
	r.setCode("ATTACH");
	r.setMandatory(false);
	r.setMaxFileSize(10L);
	rules.add(r);

	// family OK
	familyOK = new Family(new Person("Jessica", "F", 30, null), new Person("Brian", "m", 35, "Brian desc"),
		Arrays.asList(new Person("Zack", "M", 5, "desc AAA"), new Person("Vilma", "F", 3, "desc BBB")), "family OK");
	familyOK.setAttach(new byte[] { 1, 2, 3, 4, 5 });

	// family KO
	familyKO = new Family(new Person("Jessica", "F", -1, null), new Person("Brian", "M", 35, "TOO LONG DESC TOO LONG DESC TOO LONG DESC TOO LONG DESC TOO LONG DESC TOO LONG DESC "),
		Arrays.asList(new Person("Zack", "X", 5, null), new Person("  ", "F", 3, "desc BBB"), new Person(null, "F", 5, "desc CCC"), new Person("Vilma", "F", -1, null),
			new Person("Stacie", "F", 130, null), new Person("Lucy", "F", 0, null), new Person("Tom", "M", 120, null)),
		"family KO");
	familyKO.setAttach(new byte[] { 1, 2, 3, 4, 5 });

	dynamicMapOK = new HashMap<>();
	dynamicMapOK.put("DYN_1", new DynamicFieldMock("DYN_1", "STRING", true, 10, "QWERTY"));
	dynamicMapOK.put("DYN_2", new DynamicFieldMock("DYN_2", "NUMBER", true, null, Integer.valueOf(50)));
	dynamicMapOK.put("DYN_3", new DynamicFieldMock("DYN_3", "STRING", false, 5, ""));
	familyOK.getDad().setDynamicValueMap(dynamicMapOK);

	dynamicMapKO = new HashMap<>();
	dynamicMapKO.put("DYN_1", new DynamicFieldMock("DYN_1", "STRING", true, 10, "QWERTY_QWERTY_QWERTY_QWERTY_QWERTY"));
	dynamicMapKO.put("DYN_2", new DynamicFieldMock("DYN_2", "NUMBER", true, null, null));
	dynamicMapKO.put("DYN_3", new DynamicFieldMock("DYN_3", "STRING", false, 5, "QWERTY_QWERTY"));
	familyKO.getMom().setDynamicValueMap(dynamicMapKO);

	// formal
	familyFormalKO = new Family(new Person("", "F", 20, null), new Person("Brian", "M", 35, "TOO LONG DESC TOO LONG DESC TOO LONG DESC TOO LONG DESC TOO LONG DESC TOO LONG DESC "),
		Arrays.asList(new Person("Zack", "X", null, null)), "family KO");

	familyFormalOK = new Family(new Person("Jessica", "F", 20, null), new Person("Brian", "M", 35, "DESC"), Arrays.asList(new Person("Zack", "M", 1, null)), "family OK");
    }

    @Test
    public void validateFamilyOK() throws WccException {

	boolean res = CustomValidator.validate(familyOK, rules);

	Assert.assertTrue(res);

    }

    @Test
    public void validateFamilyKO() throws WccException {

	boolean res;

	try {
	    res = CustomValidator.validate(familyKO, rules);
	} catch (WccValidationException e) {
	    LOGGER.error("Invalid objet ", e);
	    res = false;
	}
	Assert.assertFalse(res);
    }

    @Test
    public void validateInner() throws WccException {

	boolean res;

	try {
	    res = CustomValidator.validate(Arrays.asList(familyKO, familyOK), rules, false, CustomValidationTypeEnum.LOCAL);
	} catch (WccValidationException e) {
	    LOGGER.error("Invalid objet ", e);
	    res = false;
	}
	Assert.assertTrue(res);

	try {
	    res = CustomValidator.validate(Arrays.asList(familyKO, familyOK), rules, true, CustomValidationTypeEnum.LOCAL);
	} catch (WccValidationException e) {
	    LOGGER.error("Invalid objet ", e);
	    res = false;
	}
	Assert.assertFalse(res);
    }

    @Test
    public void validateInternational() throws WccException {

	boolean res;

	try {
	    res = CustomValidator.validate(familyOK, rules, true, CustomValidationTypeEnum.INTERNATIONAL);
	} catch (WccValidationException e) {
	    LOGGER.error("Invalid objet ", e);
	    res = false;
	}
	Assert.assertTrue(res);

	try {
	    res = CustomValidator.validate(familyKO, rules, true, CustomValidationTypeEnum.INTERNATIONAL);
	} catch (WccValidationException e) {
	    LOGGER.error("Invalid objet ", e);
	    res = false;
	}
	Assert.assertFalse(res);
    }

    @Test
    public void validateFormal() throws WccException {

	boolean res;

	try {
	    res = CustomValidator.validate(familyFormalOK, rules, true, CustomValidationTypeEnum.FORMAL);
	} catch (WccValidationException e) {
	    LOGGER.error("Invalid objet ", e);
	    res = false;
	}
	Assert.assertTrue(res);

	try {
	    res = CustomValidator.validate(familyFormalKO, rules, true, CustomValidationTypeEnum.FORMAL);
	} catch (WccValidationException e) {
	    LOGGER.error("Invalid objet ", e);
	    res = false;
	}
	Assert.assertFalse(res);
    }

}
