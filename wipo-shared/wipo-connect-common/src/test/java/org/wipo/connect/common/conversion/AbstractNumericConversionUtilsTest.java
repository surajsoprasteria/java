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
package org.wipo.connect.common.conversion;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.dto.INumberFormatParam;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.CurrencyPositionEnum;


public class AbstractNumericConversionUtilsTest {

	private static final Logger LOGGER = WipoLoggerFactory.getLogger(AbstractNumericConversionUtilsTest.class);


	private AbstractNumericConversionUtils ncu;
	private INumberFormatParam numericFormatParams;

	private AbstractNumericConversionUtils ncuNg;
	private INumberFormatParam numericFormatParamsNoGroup;

	@Before
	public void setUp() throws Exception {
		numericFormatParams = new NumberFormatParam();
		ncu = new NumericConversionUtils(numericFormatParams);

		numericFormatParamsNoGroup = new NumberFormatParamNoGroup();
		ncuNg = new NumericConversionUtils(numericFormatParamsNoGroup);
	}

	@Test
	public void getCurrencySymbol_test() {
		Assert.assertEquals(numericFormatParams.getCurrencySymbol(), ncu.getCurrencySymbol());
	}

	@Test
	public void getDecimalSeparator_test() {
		Assert.assertEquals(numericFormatParams.getDecimalSeparator(), ncu.getDecimalSeparator());
	}

	@Test
	public void getGroupingSeparator_test() {
		Assert.assertEquals(numericFormatParams.getGroupingSeparator(), ncu.getGroupingSeparator());
	}

	@Test
	public void isCurrencyBefore_test() {
		boolean expectedBefore = CurrencyPositionEnum.BEFORE.name().equals(numericFormatParams.getCurrencyPosition());
		Assert.assertEquals(expectedBefore, ncu.isCurrencyBefore());
	}

	@Test
	public void isGroupSeparatorActive_test() {
		boolean expectedGroupSep = numericFormatParams.getGroupingSeparator() != null;
		Assert.assertEquals(expectedGroupSep, ncu.isGroupSeparatorActive());
	}

	@Test
	public void format_test_1() {
		BigDecimal input[] = {BigDecimal.valueOf(0),
				BigDecimal.valueOf(-1),
				BigDecimal.valueOf(-3210.123),
				BigDecimal.valueOf(9999.2),
				BigDecimal.valueOf(1_000_000.99999),
				BigDecimal.valueOf(42),
				BigDecimal.valueOf(-42.24)};

		String expected[] = {"0" + numericFormatParams.getDecimalSeparator() + "00",
				"-1" + numericFormatParams.getDecimalSeparator() + "00",
				"-3" + numericFormatParams.getGroupingSeparator() +"210" + numericFormatParams.getDecimalSeparator() + "123",
				"9" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getDecimalSeparator() + "20",
				"1" + numericFormatParams.getGroupingSeparator() +"000" + numericFormatParams.getGroupingSeparator() +"000" + numericFormatParams.getDecimalSeparator() + "99999",
				"42" + numericFormatParams.getDecimalSeparator() + "00",
				"-42" + numericFormatParams.getDecimalSeparator() + "24"};


		for(int i=0; i<input.length; i++){
			String result = ncu.format(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertEquals("loop " + i, expected[i], result);
		}
	}

	@Test
	public void format_test_1_ng() {
		BigDecimal input[] = {BigDecimal.valueOf(0),
				BigDecimal.valueOf(-1),
				BigDecimal.valueOf(-3210.123),
				BigDecimal.valueOf(9999.2),
				BigDecimal.valueOf(1_000_000.99999),
				BigDecimal.valueOf(42),
				BigDecimal.valueOf(-42.24)};

		String expected[] = {"0" + numericFormatParamsNoGroup.getDecimalSeparator() + "00",
				"-1" + numericFormatParamsNoGroup.getDecimalSeparator() + "00",
				"-3" + "" +"210" + numericFormatParamsNoGroup.getDecimalSeparator() + "123",
				"9" + "" +"999" + numericFormatParamsNoGroup.getDecimalSeparator() + "20",
				"1" + "" +"000" + "" +"000" + numericFormatParamsNoGroup.getDecimalSeparator() + "99999",
				"42" + numericFormatParamsNoGroup.getDecimalSeparator() + "00",
				"-42" + numericFormatParamsNoGroup.getDecimalSeparator() + "24"};


		for(int i=0; i<input.length; i++){
			String result = ncuNg.format(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertEquals("loop " + i, expected[i], result);
		}
	}

	@Test
	public void format_test_2() {
		BigDecimal input[] = {BigDecimal.valueOf(0),
				BigDecimal.valueOf(0.1),
				BigDecimal.valueOf(0.22),
				BigDecimal.valueOf(0.333),
				BigDecimal.valueOf(0.4444),
				BigDecimal.valueOf(0.55555),
				BigDecimal.valueOf(0.666666),
				BigDecimal.valueOf(0.0000001),
				BigDecimal.valueOf(0.11111111),
				BigDecimal.valueOf(0.777777777),
				BigDecimal.valueOf(0.9999999999)};

		String expected[] = {"0" + numericFormatParams.getDecimalSeparator() + "00",
				"0" + numericFormatParams.getDecimalSeparator() + "10",
				"0" + numericFormatParams.getDecimalSeparator() + "22",
				"0" + numericFormatParams.getDecimalSeparator() + "333",
				"0" + numericFormatParams.getDecimalSeparator() + "4444",
				"0" + numericFormatParams.getDecimalSeparator() + "55555",
				"0" + numericFormatParams.getDecimalSeparator() + "66667",
				"0" + numericFormatParams.getDecimalSeparator() + "00",
				"0" + numericFormatParams.getDecimalSeparator() + "11111",
				"0" + numericFormatParams.getDecimalSeparator() + "77778",
				"1" + numericFormatParams.getDecimalSeparator() + "00"};


		for(int i=0; i<input.length; i++){
			String result = ncu.format(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertEquals("loop " + i, expected[i], result);
		}
	}

	@Test
	public void format_test_3() {
		Number input[] = {Long.valueOf(42),
						  Integer.valueOf(42),
						  BigDecimal.valueOf(42),
						  Long.valueOf(-42),
						  Integer.valueOf(-42),
						  BigDecimal.valueOf(-42),
						  Long.valueOf(42_424_242),
						  Integer.valueOf(42_424_242),
						  BigDecimal.valueOf(42_424_242)};

		String expected[] = {"42",
							 "42",
							 "42" + numericFormatParams.getDecimalSeparator() + "00",
							 "-42",
							 "-42",
							 "-42" + numericFormatParams.getDecimalSeparator() + "00",
							 "42" + numericFormatParams.getGroupingSeparator() + "424" + numericFormatParams.getGroupingSeparator() + "242",
							 "42" + numericFormatParams.getGroupingSeparator() + "424" + numericFormatParams.getGroupingSeparator() + "242",
							 "42" + numericFormatParams.getGroupingSeparator() + "424" + numericFormatParams.getGroupingSeparator() + "242" + numericFormatParams.getDecimalSeparator() + "00"};


		for(int i=0; i<input.length; i++){
			String result = ncu.format(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertEquals("loop " + i, expected[i], result);
		}
	}

	@Test
	public void format_test_3_ng() {
		Number input[] = {Long.valueOf(42),
						  Integer.valueOf(42),
						  BigDecimal.valueOf(42),
						  Long.valueOf(-42),
						  Integer.valueOf(-42),
						  BigDecimal.valueOf(-42),
						  Long.valueOf(42_424_242),
						  Integer.valueOf(42_424_242),
						  BigDecimal.valueOf(42_424_242)};

		String expected[] = {"42",
							 "42",
							 "42" + numericFormatParamsNoGroup.getDecimalSeparator() + "00",
							 "-42",
							 "-42",
							 "-42" + numericFormatParamsNoGroup.getDecimalSeparator() + "00",
							 "42" + "" + "424" + "" + "242",
							 "42" + "" + "424" + "" + "242",
							 "42" + "" + "424" + "" + "242" + numericFormatParamsNoGroup.getDecimalSeparator() + "00"};


		for(int i=0; i<input.length; i++){
			String result = ncuNg.format(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertEquals("loop " + i, expected[i], result);
		}
	}

	@Test
	public void parse_test_decimal_1() {

		String input[] = {"0" + numericFormatParams.getDecimalSeparator() + "00",
				"-1" + numericFormatParams.getDecimalSeparator() + "00",
				"-3" + numericFormatParams.getGroupingSeparator() +"210" + numericFormatParams.getDecimalSeparator() + "123",
				"9" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getDecimalSeparator() + "20",
				"1" + numericFormatParams.getGroupingSeparator() +"000" + numericFormatParams.getGroupingSeparator() +"000" + numericFormatParams.getDecimalSeparator() + "99999",
				"42" + numericFormatParams.getDecimalSeparator() + "00",
				"-42" + numericFormatParams.getDecimalSeparator() + "24"};

		BigDecimal expected[] = {BigDecimal.valueOf(0),
				BigDecimal.valueOf(-1),
				BigDecimal.valueOf(-3210.123),
				BigDecimal.valueOf(9999.2),
				BigDecimal.valueOf(1_000_000.99999),
				BigDecimal.valueOf(42),
				BigDecimal.valueOf(-42.24)};

		for(int i=0; i<input.length; i++){
			BigDecimal result = ncu.parseDecimal(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertEquals("loop " + i, 0, result.compareTo(expected[i]));
		}
	}

	@Test
	public void parse_test_decimal_2() {

		String input[] = {"0" + numericFormatParams.getDecimalSeparator() + "0",
				"0" + numericFormatParams.getDecimalSeparator() + "1",
				"0" + numericFormatParams.getDecimalSeparator() + "22",
				"0" + numericFormatParams.getDecimalSeparator() + "333",
				"0" + numericFormatParams.getDecimalSeparator() + "4444",
				"0" + numericFormatParams.getDecimalSeparator() + "55555",
				"0" + numericFormatParams.getDecimalSeparator() + "666666",
				"0" + numericFormatParams.getDecimalSeparator() + "0000001",
				"0" + numericFormatParams.getDecimalSeparator() + "11111111",
				"0" + numericFormatParams.getDecimalSeparator() + "777777777",
				"0" + numericFormatParams.getDecimalSeparator() + "9999999999"};

		BigDecimal expected[] = {BigDecimal.valueOf(0.00),
				BigDecimal.valueOf(0.10),
				BigDecimal.valueOf(0.22),
				BigDecimal.valueOf(0.333),
				BigDecimal.valueOf(0.4444),
				BigDecimal.valueOf(0.55555),
				BigDecimal.valueOf(0.66667),
				BigDecimal.valueOf(0.00),
				BigDecimal.valueOf(0.11111),
				BigDecimal.valueOf(0.77778),
				BigDecimal.valueOf(1.00)};

		for(int i=0; i<input.length; i++){
			BigDecimal result = ncu.parseDecimal(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertEquals("loop " + i, 0, result.compareTo(expected[i]));
		}
	}

	@Test
	public void parse_test_long() {

		String input[] = {"42",
				"-42",
				"42" + numericFormatParams.getGroupingSeparator() + "424" + numericFormatParams.getGroupingSeparator() + "242"};

		Number expected[] = {Long.valueOf(42),
							 Long.valueOf(-42),
							 Long.valueOf(42_424_242)};


		for(int i=0; i<input.length; i++){
			Number result = ncu.parseLong(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertTrue("loop " + i, result.equals(expected[i]));
		}
	}

	@Test
	public void parse_test_integer() {

		String input[] = {"42",
				"-42",
				"42" + numericFormatParams.getGroupingSeparator() + "424" + numericFormatParams.getGroupingSeparator() + "242"};

		Number expected[] = {Integer.valueOf(42),
							 Integer.valueOf(-42),
							 Integer.valueOf(42_424_242)};


		for(int i=0; i<input.length; i++){
			Number result = ncu.parseInteger(input[i]);
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, expected[i]);
			Assert.assertTrue("loop " + i, result.equals(expected[i]));
		}
	}

	@Test
	public void doubleCheck_test_parse_decimal() {

		String input[] = {"0" + numericFormatParams.getDecimalSeparator() + "00",
				"-1" + numericFormatParams.getDecimalSeparator() + "00",
				"-3" + numericFormatParams.getGroupingSeparator() +"210" + numericFormatParams.getDecimalSeparator() + "123",
				"9" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getDecimalSeparator() + "20",
				"1" + numericFormatParams.getGroupingSeparator() +"000" + numericFormatParams.getGroupingSeparator() +"000" + numericFormatParams.getDecimalSeparator() + "99999",
				"42" + numericFormatParams.getDecimalSeparator() + "00",
				"-42" + numericFormatParams.getDecimalSeparator() + "24"};

		for(int i=0; i<input.length; i++){
			String result = ncu.format(ncu.parseDecimal(input[i]));
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, input[i]);
			Assert.assertEquals("loop " + i, input[i], result);
		}

	}

	@Test
	public void doubleCheck_test_parse_integer() {

		String input[] = {	"-3" + numericFormatParams.getGroupingSeparator() +"210" + numericFormatParams.getGroupingSeparator() + "123",
							"54" + numericFormatParams.getGroupingSeparator() +"280" + numericFormatParams.getGroupingSeparator() + "111",
							"999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() + "999",
							"-999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() + "999",
							"-355" + numericFormatParams.getGroupingSeparator() +"444",
							"-347" + numericFormatParams.getGroupingSeparator() +"871",
							"-7",
							"-42",
							"-871",
							"7",
							"42",
							"871",
							"2" + numericFormatParams.getGroupingSeparator() +"147" + numericFormatParams.getGroupingSeparator() +"483" + numericFormatParams.getGroupingSeparator() +"647",
							"-2" + numericFormatParams.getGroupingSeparator() +"147" + numericFormatParams.getGroupingSeparator() +"483" + numericFormatParams.getGroupingSeparator() +"648"};

		for(int i=0; i<input.length; i++){
			String result = ncu.format(ncu.parseInteger(input[i]));
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, input[i]);
			Assert.assertEquals("loop " + i, input[i], result);
		}

	}

	@Test
	public void doubleCheck_test_parse_long() {

		String input[] = {	"-3" + numericFormatParams.getGroupingSeparator() +"210" + numericFormatParams.getGroupingSeparator() + "123",
							"54" + numericFormatParams.getGroupingSeparator() +"280" + numericFormatParams.getGroupingSeparator() + "111",
							"999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() + "999",
							"-999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() + "999",
							"-355" + numericFormatParams.getGroupingSeparator() +"444",
							"-347" + numericFormatParams.getGroupingSeparator() +"871",
							"-7",
							"-42",
							"-871",
							"7",
							"42",
							"871",
							"2" + numericFormatParams.getGroupingSeparator() +"147" + numericFormatParams.getGroupingSeparator() +"483" + numericFormatParams.getGroupingSeparator() +"647",
							"-2" + numericFormatParams.getGroupingSeparator() +"147" + numericFormatParams.getGroupingSeparator() +"483" + numericFormatParams.getGroupingSeparator() +"648",
							"999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() +"999",
							"-999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() +"999" + numericFormatParams.getGroupingSeparator() +"999",
							"9" + numericFormatParams.getGroupingSeparator() +"223" + numericFormatParams.getGroupingSeparator() +"372" + numericFormatParams.getGroupingSeparator() +"036" + numericFormatParams.getGroupingSeparator() +"854" + numericFormatParams.getGroupingSeparator() +"775" + numericFormatParams.getGroupingSeparator() +"807",
							"-9" + numericFormatParams.getGroupingSeparator() +"223" + numericFormatParams.getGroupingSeparator() +"372" + numericFormatParams.getGroupingSeparator() +"036" + numericFormatParams.getGroupingSeparator() +"854" + numericFormatParams.getGroupingSeparator() +"775" + numericFormatParams.getGroupingSeparator() +"808"};

		for(int i=0; i<input.length; i++){
			String result = ncu.format(ncu.parseLong(input[i]));
			LOGGER.debug(WccUtils.getMethodName()+ ": result:{} , expected:{}", result, input[i]);
			Assert.assertEquals("loop " + i, input[i], result);
		}

	}

}

class NumberFormatParam implements INumberFormatParam{

		@Override
		public Character getGroupingSeparator() {
			return '_';
		}

		@Override
		public Character getDecimalSeparator() {
			return '|';
		}

		@Override
		public String getCurrencySymbol() {
			return "EUR";
		}

		@Override
		public String getCurrencyPosition() {
			return CurrencyPositionEnum.AFTER.name();
		}

}

class NumberFormatParamNoGroup implements INumberFormatParam{

	@Override
	public Character getGroupingSeparator() {
		return null;
	}

	@Override
	public Character getDecimalSeparator() {
		return '\'';
	}

	@Override
	public String getCurrencySymbol() {
		return "USD";
	}

	@Override
	public String getCurrencyPosition() {
		return CurrencyPositionEnum.BEFORE.name();
	}

}

class NumericConversionUtils extends AbstractNumericConversionUtils{

	private INumberFormatParam numericFormatParams;

	public NumericConversionUtils(INumberFormatParam numericFormatParams) {
		this.numericFormatParams = numericFormatParams;
	}

	@Override
	public void init() {
		super.init(numericFormatParams);
	}
}
