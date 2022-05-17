package com.revature.onlinestore.daos;

import com.revature.onlinestore.connection.DatabaseConnection;
import com.revature.onlinestore.models.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ItemDAO implements CrudDAO<Item> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(Item obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO items (id, name, price, stock) VALUES (?, ?, ?, ?)");
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setDouble(3, obj.getPrice());
            ps.setInt(4, obj.getStock());


            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getFloat("price"));
                item.setStock(rs.getInt("stock"));


                itemList.add(item);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;

    }

    public List<Item> findAllInStock(int userId) {
        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE stock = 5 AND id != ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));

                itemList.add(item);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return itemList;

    }
    @Override
    public Item findById(int id) {
        return null;
    }

    @Override
    public List<Item> findAllById(int id) {
        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));

                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public boolean update(Item updateObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

    @Override
    public boolean removeById(int usersId) {
        return false;
    }


    public List<Item> findUserItems(int id) {
        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE id = ? AND stock = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setUsersId(rs.getInt("users_id"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));


                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
    public Item findItemById(int id) {
        Item item = new Item();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                item.setId(rs.getInt("id"));
                item.setUsersId(rs.getInt("users_id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

}