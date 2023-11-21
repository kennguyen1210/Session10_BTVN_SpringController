package ra.academy.dao;

import ra.academy.model.Product;

import java.util.List;

public interface IProductDao extends IGenericDao<Product,Integer>{
    List<Product> findByName(String name);
}
