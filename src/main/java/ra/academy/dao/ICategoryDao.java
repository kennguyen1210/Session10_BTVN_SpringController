package ra.academy.dao;

import ra.academy.model.Category;

import java.util.List;

public interface ICategoryDao extends IGenericDao<Category,Integer>{
    List<Category> findByName(String name);
}
