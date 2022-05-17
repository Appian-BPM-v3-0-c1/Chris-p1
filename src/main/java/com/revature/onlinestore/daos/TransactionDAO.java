package com.revature.onlinestore.daos;

import com.revature.onlinestore.connection.DatabaseConnection;
import com.revature.onlinestore.models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TransactionDAO implements CrudDAO<Transaction>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(Transaction obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO transactions (id, items_id, cart_id, users_id) VALUES (?, ?, ?, ?)");
            ps.setInt(1, obj.getId());
            ps.setInt(2, obj.getItemId());
            ps.setInt(3, obj.getCartId());
            ps.setInt(4, obj.getUsersId());


            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction findById(int id) {
        return null;
    }

    @Override
    public List<Transaction> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(Transaction updateObj) {
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

    public List<Transaction> sortDateAsc(int id) {
        List<Transaction> orderList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE users_id = ? ORDER BY date ASC");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();

                transaction.setId(rs.getInt("id"));
                transaction.setItemId(rs.getInt("items_id"));
                transaction.setUsersId(rs.getInt("users_id"));

                orderList.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Transaction> sortDateDesc(int id) {
        List<Transaction> orderList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE users_id = ? ORDER BY date DESC");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();

                transaction.setId(rs.getInt("id"));
                transaction.setItemId(rs.getInt("items_id"));
                transaction.setUsersId(rs.getInt("users_id"));

                orderList.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}