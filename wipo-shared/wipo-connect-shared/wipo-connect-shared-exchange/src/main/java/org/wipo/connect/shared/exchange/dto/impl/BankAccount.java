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



package org.wipo.connect.shared.exchange.dto.impl;



import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;



/**
 * The Class BankAccount.
 *
 * @author Minervini
 */
public class BankAccount extends BaseDTO implements Identifiable, Creatable,
        Updatable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7785313727406869876L;

    /** The id bank account. */
    private Long idBankAccount;

    /** The bank name. */
    private String bankName;

    /** The branch. */
    private String branch;

    /** The address. */
    private String address;

    /** The account name. */
    private String accountName;

    /** The swift code. */
    private String swiftCode;

    /** The ac number. */
    private String acNumber;

    /** The type of payment. */
    private String typeOfPayment;

    /**
     * Gets the account name.
     *
     * @return the account name
     */
    public String getAccountName() {
        return this.accountName;
    }



    /**
     * Gets the ac number.
     *
     * @return the ac number
     */
    public String getAcNumber() {
        return this.acNumber;
    }



    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }



    /**
     * Gets the bank name.
     *
     * @return the bank name
     */
    public String getBankName() {
        return this.bankName;
    }



    /**
     * Gets the branch.
     *
     * @return the branch
     */
    public String getBranch() {
        return this.branch;
    }





    @Override
    public Long getId() {
        return this.idBankAccount;
    }



    /**
     * Gets the id bank account.
     *
     * @return the id bank account
     */
    public Long getIdBankAccount() {
        return this.idBankAccount;
    }



    /**
     * Gets the swift code.
     *
     * @return the swift code
     */
    public String getSwiftCode() {
        return this.swiftCode;
    }



    /**
     * Gets the type of payment.
     *
     * @return the type of payment
     */
    public String getTypeOfPayment() {
        return this.typeOfPayment;
    }



    /**
     * Sets the account name.
     *
     * @param accountName
     *            the new account name
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }



    /**
     * Sets the ac number.
     *
     * @param acNumber
     *            the new ac number
     */
    public void setAcNumber(String acNumber) {
        this.acNumber = acNumber;
    }



    /**
     * Sets the address.
     *
     * @param address
     *            the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }



    /**
     * Sets the bank name.
     *
     * @param bankName
     *            the new bank name
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }



    /**
     * Sets the branch.
     *
     * @param branch
     *            the new branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }





    @Override
    public void setId(Long id) {
        this.idBankAccount = id;
    }



    /**
     * Sets the id bank account.
     *
     * @param idBankAccount
     *            the new id bank account
     */
    public void setIdBankAccount(Long idBankAccount) {
        this.idBankAccount = idBankAccount;
    }



    /**
     * Sets the swift code.
     *
     * @param swiftCode
     *            the new swift code
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }



    /**
     * Sets the type of payment.
     *
     * @param typeOfPayment
     *            the new type of payment
     */
    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
