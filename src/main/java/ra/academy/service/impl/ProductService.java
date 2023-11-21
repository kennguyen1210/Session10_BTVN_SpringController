package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.impl.ProductDao;
import ra.academy.dto.ProductRequest;
import ra.academy.model.Product;
import ra.academy.service.IProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    public static List<Product> list = new ArrayList<>();
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UploadService uploadService;
    @Override
    public List<Product> getListRender(Integer pageNumber, int pageSize, List<Product> list) {
        int endIndex = pageSize*pageNumber;
        if(list.size() < endIndex){
            endIndex = list.size();
        }
        int startIndex = (pageNumber - 1)*pageSize;

        return list.subList(startIndex,endIndex);
    }

    @Override
    public List<Product> findByCategoryName(String name) {
        return list = productDao.findByName(name);
    }

    @Override
    public List<Product> findAll() {
        return list = productDao.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public Product createProduct(ProductRequest p) {
        List<String> imageUrls = p.getImageUrl().stream().map(e->uploadService.uploadFile(e)).collect(Collectors.toList());
        String imageUrl = imageUrls.stream().collect(Collectors.joining(String.valueOf(" , ")));
        return new Product(Integer.parseInt(p.getCategoryId()),p.getProductName(),imageUrl, LocalDateTime.now(),p.getDescription(),p.getPrice(),p.isStatus());
    }

    @Override
    public Product editProduct(ProductRequest p) {
        Product pro = null;
        if(p.getImageUrl().get(0).getSize() == 0){
            // khong up file moi
            pro = new Product(Integer.parseInt(p.getCategoryId()),p.getProductName(),p.getOldImage(), null,p.getDescription(),p.getPrice(),p.isStatus());
        } else {
            // up file moi
            List<String> imageUrls = p.getImageUrl().stream().map(e->uploadService.uploadFile(e)).collect(Collectors.toList());
            String imageUrl = imageUrls.stream().collect(Collectors.joining(String.valueOf(" , ")));
            return new Product(Integer.parseInt(p.getCategoryId()),p.getProductName(),imageUrl, null,p.getDescription(),p.getPrice(),p.isStatus());
        }
        return pro;
    }
}
