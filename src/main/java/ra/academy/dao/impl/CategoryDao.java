package ra.academy.dao.impl;

import org.springframework.stereotype.Service;
import ra.academy.dao.ICategoryDao;
import ra.academy.dao.IGenericDao;
import ra.academy.model.Category;
import ra.academy.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryDao implements ICategoryDao {
    @Override
    public List<Category> findAll() {
        Connection conn = ConnectionDB.getConnection();
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareCall("select * from category");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Category c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                c.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
                list.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Category findById(Integer id) {
        Connection conn = ConnectionDB.getConnection();
        Category c = null;
        try {
            PreparedStatement pre = conn.prepareCall("select * from category where categoryId = ?");
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                c.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return c;
    }

    @Override
    public void save(Category category) {
        Connection conn = ConnectionDB.getConnection();
        if(category.getCategoryId() == 0){
            // them moi
            try {
                PreparedStatement pre = conn.prepareCall("insert into category(categoryName,description,createDate) values (?,?,?)");
                pre.setString(1,category.getCategoryName());
                pre.setString(2,category.getDescription());
                pre.setTimestamp(3,Timestamp.valueOf(category.getCreateDate()));
                pre.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // update
            try {
                PreparedStatement pre = conn.prepareCall("update category set categoryName = ?, description = ? where categoryId = ?");
                pre.setString(1,category.getCategoryName());
                pre.setString(2,category.getDescription());
                pre.setInt(3,category.getCategoryId());
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
            PreparedStatement pre = conn.prepareCall("delete from category where categoryId = ?");
            pre.setInt(1,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Category> findByName(String name) {
        Connection conn = ConnectionDB.getConnection();
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareCall("select * from category where categoryName like ?");
            pre.setString(1, "%"+name+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
               Category c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                c.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
                list.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }
}
