package com.revature.onlinestore.models;

public class Cart {
    private int id;
    private int userId;
    private int itemId;


    public Cart() {
    }

    public Cart(int id, int usersId, int itemId) {
        this.id = id;
        this.userId = usersId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUsersId(int usersId) {
        this.userId = usersId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemQty() {
        return 0;
    }

}