package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.impl.CategoryDao;
import ra.academy.model.Category;
import ra.academy.service.ICategoryService;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    public static List<Category> listCategory = new ArrayList<>();
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        listCategory = categoryDao.findAll();
        return listCategory;
    }

    @Override
    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryDao.delete(id);
    }

    @Override
    public List<Category> getListRender(Integer pageNumber, int pageSize , List<Category> list) {
        int endIndex = pageSize*pageNumber;
        if(list.size() < endIndex){
            endIndex = list.size();
        }
        int startIndex = (pageNumber - 1)*pageSize;

        return list.subList(startIndex,endIndex);
    }

    @Override
    public List<Category> findByCategoryName(String name) {
        return listCategory = categoryDao.findByName(name);
    }
}
