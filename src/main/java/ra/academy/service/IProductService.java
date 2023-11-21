package ra.academy.service;

import ra.academy.dto.ProductRequest;
import ra.academy.model.Category;
import ra.academy.model.Product;

import java.util.List;

public interface IProductService extends IGenericService<Product,Integer> {
    List<Product> getListRender(Integer pageNumber, int pageSize, List<Product> list);
    List<Product> findByCategoryName(String name);
    Product createProduct(ProductRequest p);
    Product editProduct(ProductRequest p);
}
