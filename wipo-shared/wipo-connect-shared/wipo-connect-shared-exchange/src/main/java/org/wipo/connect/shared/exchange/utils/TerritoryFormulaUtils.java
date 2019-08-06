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
package org.wipo.connect.shared.exchange.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.enumerator.TerritoryFormulaValidationResultEnum;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;

/**
 * The Class TerritoryFormulaUtils.
 */
public class TerritoryFormulaUtils {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(TerritoryFormulaUtils.class);

    /** The pattern. */
    private static final String PATTERN = "((\\+|-){1}([a-zA-Z0-9]{2,3}))*";
    private static final String PATTERN_TISN = "((E|I){1}([a-zA-Z0-9]{4}))*";
    private static final String TISN_ADD = "I";
    private static final String TISN_MINUS = "E";

    private static final String TISA_ADD = "+";
    private static final String TISA_MINUS = "-";

    /**
     * Decode tisn formula.
     *
     * @param territoryFormulaTisn
     *            the territory formula tisn
     * @param territoryList
     *            the territory list
     * @return the string
     * @throws WccException
     *             the wcc exception
     */
    public static String decodeTisnFormula(String territoryFormulaTisn, List<Territory> territoryList) throws WccException {
	String territoryFormulaTisa = "";

	if (!Pattern.compile(PATTERN_TISN).matcher(territoryFormulaTisn).matches()) {
	    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, "");
	}

	// 2) Sostituisco tutte le I con + e le E con -
	String tempTisa = StringUtils.replace(territoryFormulaTisn, TISN_ADD, TISA_ADD);
	tempTisa = StringUtils.replace(tempTisa, TISN_MINUS, TISA_MINUS);

	String[] tisnCodes = StringUtils.split(tempTisa, "(\\+|-)");
	String[] tisaCodes = new String[tisnCodes.length];
	// 3) Per ogni numero compreso tra i caratteri +/- (2136 e 0380 dell'esempio sopra):
	// Recupero il relativo id_territory (campo fk_territory sulla territory_identifier con value uguale al valore trovato);
	// Prendo il relativo id_territory_name valido ad oggi (o l'ultimo territory name valido, nel caso di territory non più valido) sulla territory_name (campo id_territory_name sulla
	// territory_name con fk_territory trovata al punto prima);
	// Recupero il relativo TIS-A andando sulla territory_name_identifier con fk_territory_name trovato al punto precedente;
	// Sostituisco quindi il valore numero iniziale (ad esempio 2136) con il codice trovato (2WL).
	int i = 0;
	for (String tisnCode : tisnCodes) {
	    tisaCodes[i] = territoryList.stream() // convert list to stream
		    .filter(x -> tisnCode.equals(x.getCode())) // filters the line, equals to Territory.code
		    .map(Territory::getTisa).findAny().orElse("");
	    i++;
	}

	territoryFormulaTisa = StringUtils.replaceEach(tempTisa, tisnCodes, tisaCodes);

	return territoryFormulaTisa;
    }

    /**
     * Validate territory formula.
     *
     * @param th
     *            the th
     * @param territoryFormula
     *            the territory formula
     * @return the territory formula validation result enum
     * @throws WccException
     *             the wcc exception
     */
    public static TerritoryFormulaValidationResultEnum validateTerritoryFormula(TerritoryHierarchyVO th, String territoryFormula) throws WccException {
	// TODO: verify if - must be preceded by widest area

	if (StringUtils.isEmpty(territoryFormula)) {
	    return TerritoryFormulaValidationResultEnum.INVALID_FORMULA_SYNTAX;
	}

	if (!Pattern.compile(PATTERN).matcher(territoryFormula).matches()) {
	    return TerritoryFormulaValidationResultEnum.INVALID_FORMULA_SYNTAX;
	}

	String[] territorySplitted = territoryFormula.split("(?=[+])|(?=[-])");
	List<String> territorySplittedList = new ArrayList<>(Arrays.asList(territorySplitted));

	List<String> codeToAddList = new ArrayList<>(territorySplittedList);
	List<String> codeToRemoveList = new ArrayList<>(territorySplittedList);

	filterTerritoryListBySplitChar(codeToAddList, "+");
	filterTerritoryListBySplitChar(codeToRemoveList, "-");

	Map<String, Territory> countryMap = new HashMap<>();
	Map<String, Territory> countryToRemoveMap = new HashMap<>();

	for (String formulaCode : codeToAddList) {
	    String tisa = StringUtils.removeStart(formulaCode, "+");
	    countryMap = getAllChildren(th, tisa);
	    if (countryMap.isEmpty()) {
		return TerritoryFormulaValidationResultEnum.INVALID_TERRITORY_CODE;
	    }
	}

	for (String formulaCode : codeToRemoveList) {
	    String tisa = StringUtils.removeStart(formulaCode, "-");
	    countryToRemoveMap = getAllChildren(th, tisa);
	    if (countryToRemoveMap.isEmpty()) {
		return TerritoryFormulaValidationResultEnum.INVALID_TERRITORY_CODE;
	    }

	    for (Territory t : countryToRemoveMap.values()) {
		if (!countryMap.containsKey(t.getTisa())) {
		    return TerritoryFormulaValidationResultEnum.INVALID_TERRITORY_CHAIN;
		}
	    }
	}

	if (CollectionUtils.subtract(countryMap.values(), countryToRemoveMap.values()).isEmpty()) {
	    return TerritoryFormulaValidationResultEnum.INVALID_TERRITORY_CHAIN;
	}

	return TerritoryFormulaValidationResultEnum.VALID;
    }

    /**
     * Validate territory formula bool.
     *
     * @param th
     *            the th
     * @param territoryFormula
     *            the territory formula
     * @return true, if successful
     * @throws WccException
     *             the wcc exception
     */
    public static boolean validateTerritoryFormulaBool(TerritoryHierarchyVO th, String territoryFormula) throws WccException {
	TerritoryFormulaValidationResultEnum result = validateTerritoryFormula(th, territoryFormula);
	LOGGER.debug("validateTerritoryFormulaBool: " + result);
	return TerritoryFormulaValidationResultEnum.VALID.equals(result);
    }

    /**
     * Checks if is overlapping.
     *
     * @param territoryFormula
     *            the territory formula
     * @param territoryFormula1
     *            the territory formula 1
     * @param th
     *            the th
     * @return true, if is overlapping
     */
    public static boolean isOverlapping(String territoryFormula, String territoryFormula1, TerritoryHierarchyVO th) {
	boolean isOverlapping = false;

	if (!StringUtils.isEmpty(territoryFormula) && !StringUtils.isEmpty(territoryFormula1)) {
	    Set<String> territoryChild = getTerritoryChildCodeFromFormula(territoryFormula, th);
	    Set<String> territoryChild1 = getTerritoryChildCodeFromFormula(territoryFormula1, th);
	    if (!territoryChild.retainAll(territoryChild1) && !territoryChild1.retainAll(territoryChild)) {
		return isOverlapping = true;
	    }
	}
	return isOverlapping;
    }

    private static Set<String> getTerritoryChildCodeFromFormula(String territoryFormula, TerritoryHierarchyVO th) {
	Set<String> territoryCodeSetToAdd = new HashSet<>();
	Set<String> territoryCodeSetToRemove = new HashSet<>();
	String[] territorySplitted = territoryFormula.split("(?=[+])|(?=[-])");
	List<String> territorySplittedList = new ArrayList<>(Arrays.asList(territorySplitted));

	List<String> codeToAddList = new ArrayList<>(territorySplittedList);
	List<String> codeToRemoveList = new ArrayList<>(territorySplittedList);

	filterTerritoryListBySplitChar(codeToAddList, "+");
	filterTerritoryListBySplitChar(codeToRemoveList, "-");

	for (String formulaCode : codeToAddList) {
	    territoryCodeSetToAdd.add(formulaCode);
	    territoryCodeSetToAdd.addAll(getAllChildren(th, formulaCode).keySet());
	}

	for (String formulaCode : codeToRemoveList) {
	    territoryCodeSetToRemove.add(formulaCode);
	    territoryCodeSetToRemove.addAll(getAllChildren(th, formulaCode).keySet());
	}

	territoryCodeSetToAdd.removeAll(territoryCodeSetToRemove);
	return new HashSet<>(territoryCodeSetToAdd);
    }

    private static List<String> filterTerritoryListBySplitChar(List<String> territorySplitted, String splitChar) {
	if (splitChar.equals("-")) {
	    CollectionUtils.filter(territorySplitted, new Predicate() {
		@Override
		public boolean evaluate(Object input) {
		    String regex = "([-]){1}([a-zA-Z0-9]{2,3})";
		    return Pattern.matches(regex, (String) input);
		}
	    });
	} else if (splitChar.equals("+")) {
	    CollectionUtils.filter(territorySplitted, new Predicate() {
		@Override
		public boolean evaluate(Object input) {
		    String regex = "([+]){1}([a-zA-Z0-9]{2,3})";
		    return Pattern.matches(regex, (String) input);
		}
	    });
	} else {
	    throw new NotImplementedException("NotImplementedException");
	}

	return territorySplitted;
    }

    private static Map<String, Territory> getAllChildren(TerritoryHierarchyVO th, String tisaCode) {
	return getAllChildren_inner(th, tisaCode, false);
    }

    private static Map<String, Territory> getAllChildren_inner(TerritoryHierarchyVO th, String tisaCode, boolean correctParent) {
	Map<String, Territory> childMap = new HashMap<>();

	boolean correctChain = correctParent || StringUtils.equals(tisaCode, th.getTisa());

	if (correctChain && th.getChildList().isEmpty() && StringUtils.equalsIgnoreCase(th.getTerritoryTypeCode(), ConstantUtils.REFERENCE_CODE_COUNTRY)) {
	    childMap.put(th.getTisa(), th.getTerritory());
	} else {
	    for (TerritoryHierarchyVO childTh : th.getChildList()) {
		childMap.putAll(getAllChildren_inner(childTh, tisaCode, correctChain));
	    }
	}

	return childMap;
    }

}
