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
package org.wipo.connect.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;


public class XSSStripperTest {

	@Test
	public void testHtmlTag() {
		String input = "<div\n    class =\"cls\"\n    custAttr=\"1\"\n    >\ninner text\n</div><h1>H1 TEXT</H1>";
		String out = XSSStripper.strip(input);

		Assert.assertNotEquals("Output should be different", input, out);
		Assert.assertTrue("Output should contain non printable chars", StringUtils.contains(out, XSSStripper.NON_PRINTABLE_CHAR));
	}

	@Test
	public void testScriptTag() {
		String input = "<script type=\"text/javascript\">alert('Hello XSS!!');\n</script>";
		String out = XSSStripper.strip(input);

		Assert.assertNotEquals("Output should be different", input, out);
		Assert.assertFalse("Output should not contain sctipt tag", StringUtils.contains(out, "<script"));
	}

}
