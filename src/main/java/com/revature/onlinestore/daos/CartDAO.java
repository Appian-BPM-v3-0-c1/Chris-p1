package com.revature.onlinestore.daos;

import com.revature.onlinestore.connection.DatabaseConnection;
import com.revature.onlinestore.models.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements CrudDAO<Cart> {
    Connection con = DatabaseConnection.getCon();


    @Override
    public int save(Cart obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO cart (id, user_id, item_id, item_qty) VALUES (?, ?, ?, ?)");
            ps.setInt(1, obj.getId());
            ps.setInt(2, obj.getUserId());
            ps.setInt(3, obj.getItemId());
            ps.setInt(4, obj.getItemQty());

            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(int id) {
        return null;
    }

    @Override
    public List<Cart> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(Cart updateObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    public List<Cart> viewCart(int usersId) {
        List<Cart> cartList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cart WHERE id = ?");
            ps.setInt(1, usersId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUsersId(rs.getInt("user_id"));
                cart.setItemId(rs.getInt("item_id"));


                cartList.add(cart);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    public Cart findCartById(int id) {
        Cart cart = new Cart();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carts WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cart.setId(rs.getInt("id"));
                cart.setUsersId(rs.getInt("users_id"));
                cart.setItemId(rs.getInt("items_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

}