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



package org.wipo.connect.ruleengine.rules.calculation;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;



/**
 * The Class Exp.
 */
public final class Exp {

    /**
     * The Enum Operator.
     */
    public enum Operator {

        /** The equal. */
        EQUAL,
        /** The greater than. */
        GREATER_THAN,
        /** The less than. */
        LESS_THAN,
        /** The greater or equal to. */
        GREATER_OR_EQUAL_TO,
        /** The less or equal to. */
        LESS_OR_EQUAL_TO,
        /** The not equal. */
        NOT_EQUAL;
    }

    /** The Constant ALL. */
    private static final String ALL = "-";



    /**
     * Accuracy.
     *
     * @param list
     *            the list
     * @param name
     *            the name
     * @param value
     *            the value
     * @param conditions
     *            the conditions
     * @param result
     *            the result
     * @return the double
     * @throws RulesException
     *             the rules exception
     */
    public final static Double accuracy(List<Element> list, String name,
            Number value, Map<String, Object> conditions, String result)
            throws RulesException {
        final String tmpName = name;
        final List<Element> items = conditions == null || conditions.isEmpty() ? list
                : findInList(list, conditions);
        if (items == null || items.isEmpty()) {
            return 0d;
        }
        if (!(items.get(0).get(name) instanceof Number)) {
            throw new RulesException("campo " + name + " deve essere numerico");
        }
        if (!(items.get(0).get(result) instanceof Number)) {
            throw new RulesException("campo " + result
                    + " deve essere numerico");
        }
        Collections.sort(items, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                Object a = e1.get(tmpName);
                Object b = e2.get(tmpName);
                if (a == null && b != null) {
                    return -1;
                }
                if (a != null && b == null) {
                    return 1;
                }
                if (a == b) {
                    return 0;
                }
                double d = toDouble(a) - toDouble(b);
                return d > 0 ? 1 : (d < 0 ? -1 : 0);
            }

        });
        int k = 0;
        int n = items.size();
        for (Element e : items) {
            Object a = e.get(name);
            if (a == null) {
                continue;
            }
            Double d = toDouble(a) - toDouble(value);
            if (d.compareTo(0D) == 0) {
                return toDouble(e.get(result));
            } else if (d > 0) {
                break;
            }
            k++;
        }
        // approssimazione, prendo valore più vicino tra valori k e k-1 e value;
        // non è presente l'elemento inferiore, l'elemento ricercato sarà dunque
        // il primo elemento della lista
        if (k == 0) {
            return toDouble(items.get(k).get(result));
        }
        if (k >= n) {
            return toDouble(items.get(n - 1).get(result));
        }

        // è presente un elemento minore posso approssimare
        Element succ = items.get(k);
        Element prec = items.get(k - 1);
        Double succValResult = toDouble(succ.get(result));
        Double precValResult = toDouble(prec.get(result));
        Double succValName = toDouble(succ.get(name));
        Double precValName = toDouble(prec.get(name));
        Double valName = toDouble(value);
        double ds = Math.abs(valName - succValName);
        double dp = Math.abs(valName - precValName);
        if (dp < ds) {
            return precValResult;
        }
        return succValResult;

    }



    /**
     * Accuracy.
     *
     * @param items
     *            the items
     * @param name
     *            the name
     * @param value
     *            the value
     * @param result
     *            the result
     * @return the double
     * @throws RulesException
     *             the rules exception
     */
    public final static Double accuracy(List<Element> items, String name,
            Number value, String result) throws RulesException {
        return accuracy(items, name, value, null, result);
    }



    /**
     * Find in list.
     *
     * @param items
     *            the items
     * @param conditions
     *            the conditions
     * @return the list
     */
    public final static List<Element> findInList(List<Element> items,
            Map<String, Object> conditions) {
        List<Element> ret = new ArrayList<Element>();
        if (items == null) {
            return ret;
        }
        boolean trovato = false;
        final String attr = sortAttribute(conditions);
        if (attr != null) {
            Collections.sort(items, new Comparator<Element>() {
                @Override
                public int compare(Element e1, Element e2) {
                    Object a = e1.get(attr);
                    Object b = e2.get(attr);
                    if (a == null && b != null) {
                        return -1;
                    }
                    if (a != null && b == null) {
                        return 1;
                    }
                    if (a == b) {
                        return 0;
                    }
                    if (a.toString().equals(ALL) && !b.toString().equals(ALL)) {
                        return 1;
                    }
                    if (!a.toString().equals(ALL) && b.toString().equals(ALL)) {
                        return -1;
                    }
                    if (a == b) {
                        return 0;
                    }
                    return a.toString().compareTo(b.toString());
                }

            });
        }
        for (Element v : items) {
            trovato = true;
            for (String ke : v.keySet()) {
                Object e = v.get(ke);
                for (String kc : conditions.keySet()) {
                    if (!ke.equals(kc)) {
                        continue;
                    }
                    Object c = conditions.get(kc);
                    if (c instanceof Collection) {
                        Collection<?> collection = (Collection<?>) c;
                        Operator operator = operator(collection);
                        if (operator != null) {
                            if (!found(collection, e)) {
                                trovato = false;
                                break;
                            }
                        } else if (e instanceof Number) {
                            boolean contenuto = false;
                            for (Object o : collection) {
                                if (toDouble(e).equals(toDouble(o))) {
                                    contenuto = true;
                                    break;
                                }
                            }
                            if (!contenuto) {
                                trovato = false;
                                break;
                            }
                        } else if (!collection.contains(e)) {
                            trovato = false;
                            break;
                        }
                    } else {
                        if (!found(e, c, Operator.EQUAL)) {
                            trovato = false;
                            break;
                        }

                    }

                }
            }
            if (trovato) {
                ret.add(v);
            }
        }
        return ret;
    }



    /**
     * Found.
     *
     * @param collection
     *            the collection
     * @param e
     *            the e
     * @return true, if successful
     */
    private static final boolean found(Collection<?> collection, Object e) {
        int k = 0;
        Operator operator = null;
        for (Object object : collection) {
            if (k % 2 == 0) {
                operator = operator(object);
            } else {
                if (!found(e, object, operator)) {
                    return false;
                }
            }
            k++;
        }
        return true;
    }



    /**
     * Found.
     *
     * @param e
     *            the e
     * @param c
     *            the c
     * @param op
     *            the op
     * @return true, if successful
     */
    private static final boolean found(Object e, Object c, Operator op) {
        switch (op) {
        case EQUAL:
            if (e instanceof Number) {
                if (!toDouble(e).equals(toDouble(c))) {
                    return false;
                }
            } else if (!e.equals(c)) {
                return false;
            }
            break;
        case NOT_EQUAL:
            if (e instanceof Number) {
                if (!toDouble(e).equals(toDouble(c))) {
                    return false;
                }
            } else if (e.equals(c)) {
                return false;
            }
            break;
        case GREATER_THAN:
            if (e instanceof Number) {
                if (toDouble(e) <= toDouble(c)) {
                    return false;
                }
            } else if (e.toString().compareTo(c.toString()) <= 0) {
                return false;
            }
            break;
        case LESS_THAN:
            if (e instanceof Number) {
                if (toDouble(e) >= toDouble(c)) {
                    return false;
                }
            } else if (e.toString().compareTo(c.toString()) >= 0) {
                return false;
            }
            break;
        case GREATER_OR_EQUAL_TO:
            if (e instanceof Number) {
                if (toDouble(e) < toDouble(c)) {
                    return false;
                }
            } else if (e.toString().compareTo(c.toString()) < 0) {
                return false;
            }
            break;
        case LESS_OR_EQUAL_TO:
            if (e instanceof Number) {
                if (toDouble(e) > toDouble(c)) {
                    return false;
                }
            } else if (e.toString().compareTo(c.toString()) > 0) {
                return false;
            }
        }

        return true;
    }



    /**
     * Interpolation.
     *
     * @param list
     *            the list
     * @param name
     *            the name
     * @param value
     *            the value
     * @param conditions
     *            the conditions
     * @param result
     *            the result
     * @return the double
     * @throws RulesException
     *             the rules exception
     */
    public final static Double interpolation(List<Element> list, String name,
            Number value, Map<String, Object> conditions, String result)
            throws RulesException {
        final String tmpName = name;
        final List<Element> items = conditions == null || conditions.isEmpty() ? list
                : findInList(list, conditions);
        if (items == null || items.isEmpty()) {
            return 0d;
        }
        if (!(items.get(0).get(name) instanceof Number)) {
            throw new RulesException("campo " + name + " deve essere numerico");
        }
        if (!(items.get(0).get(result) instanceof Number)) {
            throw new RulesException("campo " + result
                    + " deve essere numerico");
        }
        Collections.sort(items, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                Object a = e1.get(tmpName);
                Object b = e2.get(tmpName);
                if (a == null && b != null) {
                    return -1;
                }
                if (a != null && b == null) {
                    return 1;
                }
                if (a == b) {
                    return 0;
                }
                double d = toDouble(a) - toDouble(b);
                return d > 0 ? 1 : (d < 0 ? -1 : 0);
            }

        });
        int k = 0;
        int n = items.size();
        for (Element e : items) {
            Object a = e.get(name);
            if (a == null) {
                continue;
            }
            Double d = toDouble(a) - toDouble(value);
            if (d.compareTo(0D) == 0) {
                return toDouble(e.get(result));
            } else if (d > 0) {
                break;
            }
            k++;
        }
        // interpolazione valori k e k-1;
        // non è presente l'elemento inferiore, l'elemento ricercato sarà dunque
        // il primo elemento della lista
        if (k == 0) {
            return toDouble(items.get(k).get(result));
        }
        if (k >= n) {
            return toDouble(items.get(n - 1).get(result));
        }

        // è presente un elemento minore posso interpolare
        Element succ = items.get(k);
        Element prec = items.get(k - 1);
        Double succValResult = toDouble(succ.get(result));
        Double precValResult = toDouble(prec.get(result));
        Double succValName = toDouble(succ.get(name));
        Double precValName = toDouble(prec.get(name));
        Double valName = toDouble(value);
        double d = succValResult - precValResult;
        double d1 = succValName - precValName;
        double d2 = valName - precValName;
        return precValResult + (d / d1 * d2);

    }



    /**
     * Interpolation.
     *
     * @param items
     *            the items
     * @param name
     *            the name
     * @param value
     *            the value
     * @param result
     *            the result
     * @return the double
     * @throws RulesException
     *             the rules exception
     */
    public final static Double interpolation(List<Element> items, String name,
            Number value, String result) throws RulesException {
        return interpolation(items, name, value, null, result);
    };



    /**
     * Months to days.
     *
     * @param months
     *            the months
     * @return the int
     */
    public final static int monthsToDays(int months) {
        return months % 12 == 0 ? (months / 12) * 365 : months * 30;
    };



    // public static boolean isNull(Object o) {
    // return o == null;
    // };
    //
    // public static boolean isNotNull(Object o) {
    // return o != null;

    /**
     * Operator.
     *
     * @param collection
     *            the collection
     * @return the operator
     */
    private static final Operator operator(Collection<?> collection) {
        Object c = value(collection, 0);
        return operator(c);
    }



    /**
     * Operator.
     *
     * @param collection
     *            the collection
     * @param pos
     *            the pos
     * @return the operator
     */
    @SuppressWarnings("unused")
    private static final Operator operator(Collection<?> collection, int pos) {
        Object c = value(collection, pos);
        return operator(c);
    }



    /**
     * Operator.
     *
     * @param c
     *            the c
     * @return the operator
     */
    private static final Operator operator(Object c) {
        if (c == null) {
            return null;
        }
        String op = c.toString();
        if (op.equals("=")) {
            return Operator.EQUAL;
        }
        if (op.equals("<")) {
            return Operator.LESS_THAN;
        }
        if (op.equals(">")) {
            return Operator.GREATER_THAN;
        }
        if (op.equals("<=")) {
            return Operator.LESS_OR_EQUAL_TO;
        }
        if (op.equals(">=")) {
            return Operator.GREATER_OR_EQUAL_TO;
        }
        if (op.equals("<>")) {
            return Operator.NOT_EQUAL;
        }
        return null;
    }



    /**
     * Search in list.
     *
     * @param items
     *            the items
     * @param conditions
     *            the conditions
     * @param result
     *            the result
     * @param df
     *            the df
     * @return the object
     */
    public final static Object searchInList(List<Element> items,
            Map<String, Object> conditions, String result, Object df) {
        List<Element> list = findInList(items, conditions);
        return list == null || list.isEmpty() ? df : list.get(0).get(result);
    }



    /**
     * Sort attribute.
     *
     * @param conditions
     *            the conditions
     * @return the string
     */
    public final static String sortAttribute(Map<String, Object> conditions) {
        if (conditions == null) {
            return null;
        }
        for (String kc : conditions.keySet()) {
            Object c = conditions.get(kc);
            if (!(c instanceof Collection)) {
                continue;
            }
            Collection<?> collection = (Collection<?>) c;
            for (Object val : collection) {
                if (val != null && val.equals(ALL)) {
                    return kc;
                }
            }
        }
        return null;
    }



    /**
     * Sum.
     *
     * @param numbers
     *            the numbers
     * @return the double
     */
    public final static Double SUM(List<Number> numbers) {

        Double sum = 0d;
        for (Number d : numbers) {
            if (d == null) {
                continue;
            }
            sum += d.doubleValue();
        }
        return sum;
    }



    /**
     * To double.
     *
     * @param object
     *            the object
     * @return the double
     */
    private static final Double toDouble(Object object) {
        return object == null || !(object instanceof Number) ? 0d
                : ((Number) object).doubleValue();
    }



    // public static Number ABS(int number) throws RulesException {
    // return Math.abs(number);
    // }
    //
    // public static long ABS(long number) throws RulesException {
    // return Math.abs(number);
    // }
    //
    // public static double ABS(double number) throws RulesException {
    // return Math.abs(number);
    // }
    //
    // public static float ABS(float number) throws RulesException {
    // return Math.abs(number);
    // }

    /**
     * Value.
     *
     * @param collection
     *            the collection
     * @param pos
     *            the pos
     * @return the object
     */
    private static final Object value(Collection<?> collection, int pos) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        int k = 0;
        for (Object object : collection) {
            if (k == pos) {
                return object;
            }
            k++;
        }
        return null;
    }
}
