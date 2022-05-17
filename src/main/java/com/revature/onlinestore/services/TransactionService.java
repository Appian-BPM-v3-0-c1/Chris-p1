package com.revature.onlinestore.services;

import com.revature.onlinestore.daos.TransactionDAO;

public class TransactionService {
    public final TransactionDAO transactionDAO;


    public TransactionService(TransactionDAO transactionDAO) {

        this.transactionDAO = transactionDAO;

    }

    public TransactionDAO getTransactionDAO() {

        return transactionDAO;

    }
}