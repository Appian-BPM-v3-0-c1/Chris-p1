package com.revature.onlinestore.services;

import com.revature.onlinestore.daos.ItemDAO;

public class ItemService {

    private final ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }
}