package ra.academy.service;

import ra.academy.model.Category;

import java.util.List;

public interface ICategoryService extends IGenericService<Category,Integer>{
    List<Category> getListRender(Integer pageNumber, int pageSize, List<Category> list);
    List<Category> findByCategoryName(String name);
}
