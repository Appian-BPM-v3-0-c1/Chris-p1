package com.revature.onlinestore.models;

public class Transaction {

    private int id;
    private String date;
    private int itemId;
    private int usersId;

    public Transaction() {
    }

    public Transaction(int id, int itemId, int usersId) {
        this.id = id;
        this.itemId = itemId;
        this.usersId = usersId;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }


    public int getItemId() {

        return itemId;
    }

    public void setItemId(int itemId) {

        this.itemId = itemId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {

        this.usersId = usersId;
    }

    public int getCartId() {
        return 0;
    }
}