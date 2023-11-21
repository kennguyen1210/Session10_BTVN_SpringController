package ra.academy.dao.impl;

import org.springframework.stereotype.Service;
import ra.academy.dao.IProductDao;
import ra.academy.model.Product;
import ra.academy.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductDao implements IProductDao {
    @Override
    public List<Product> findAll() {
        Connection conn = ConnectionDB.getConnection();
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareCall("select * from product");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setImageUrl(rs.getString("imageUrl"));
                p.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
                p.setStatus(rs.getBoolean("status"));
                p.setCategoryId(rs.getInt("categoryId"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Connection conn = ConnectionDB.getConnection();
        Product p = null;
        try {
            PreparedStatement pre = conn.prepareCall("select * from product where productId = ?");
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setImageUrl(rs.getString("imageUrl"));
                p.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
                p.setStatus(rs.getBoolean("status"));
                p.setCategoryId(rs.getInt("categoryId"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return p;
    }

    @Override
    public void save(Product product) {
        Connection conn = ConnectionDB.getConnection();
        if(product.getProductId()==0){
            // them moi
            try {
                PreparedStatement pre = conn.prepareCall("insert into product(categoryId,productName,description,imageUrl,price,status,createDate) values (?,?,?,?,?,?,?)");
                pre.setInt(1,product.getCategoryId());
                pre.setString(2,product.getProductName());
                pre.setString(3,product.getDescription());
                pre.setString(4,product.getImageUrl());
                pre.setDouble(5,product.getPrice());
                pre.setBoolean(6,product.isStatus());
                pre.setTimestamp(7, Timestamp.valueOf(product.getCreateDate()));
                pre.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // update
            try {
                PreparedStatement pre = conn.prepareCall("update product set categoryId = ?,productName = ?,description = ?,imageUrl = ?,price = ?,status = ? where productId = ?");
                pre.setInt(1,product.getCategoryId());
                pre.setString(2,product.getProductName());
                pre.setString(3,product.getDescription());
                pre.setString(4,product.getImageUrl());
                pre.setDouble(5,product.getPrice());
                pre.setBoolean(6,product.isStatus());
                pre.setInt(7, product.getProductId());
                pre.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        ConnectionDB.closeConnection(conn);
    }

    @Override
    public void delete(Integer id) {
        Connection conn = ConnectionDB.getConnection();
        try {
            PreparedStatement pre = conn.prepareCall("delete from product where productId = ?");
            pre.setInt(1,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Product> findByName(String name) {
        Connection conn = ConnectionDB.getConnection();
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareCall("select * from product where productName like ?");
            pre.setString(1,"%"+name+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setImageUrl(rs.getString("imageUrl"));
                p.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
                p.setStatus(rs.getBoolean("status"));
                p.setCategoryId(rs.getInt("categoryId"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }
}
