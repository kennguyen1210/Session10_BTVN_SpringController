package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.academy.dto.ProductRequest;
import ra.academy.model.Category;
import ra.academy.model.Product;
import ra.academy.service.impl.CategoryService;
import ra.academy.service.impl.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home/product")
public class ProductController {
    public static final int PAGE_SIZE = 5;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/{id}")
    public String render(@PathVariable Integer id, Model model){
        if(id == null){
            id = 1;
        }
        List<Product> list;
        if(ProductService.list.isEmpty()){
            list = productService.findAll();
        } else {list = ProductService.list;}
        List<Category> listCategory = categoryService.findAll();
        model.addAttribute("listCategory",listCategory.stream().map(c->new Category(c.getCategoryId(),c.getCategoryName())).collect(Collectors.toList()));
        model.addAttribute("list",productService.getListRender(id, PAGE_SIZE,list));
        model.addAttribute("size",Math.ceil((double) list.size() /5));
        model.addAttribute("page",id);
        return "product";
    }
    @PostMapping("/add/{page}")
    public String doAdd(@PathVariable int page,
                        @RequestParam String productName, @RequestParam String categoryId,
                        @RequestParam double price, @RequestParam String description,
                        @RequestParam boolean status, @RequestParam List<MultipartFile> imageUrl){
        ProductRequest productRequest = new ProductRequest(categoryId,productName,imageUrl,description,price,status);
        productService.save(productService.createProduct(productRequest));
        productService.findAll();
        return "redirect:/home/product/"+page;
    }
    @GetMapping("/delete/{id}/{page}")
    public String doDelete(@PathVariable int id,@PathVariable int page){
        productService.delete(id);
        productService.findAll();
        return "redirect:/home/product/"+page;
    }
    @PostMapping("/update/{page}")
    public String doEdit(@RequestParam int productId,@RequestParam String oldImage, @RequestParam String productName, @RequestParam String categoryId,
                         @RequestParam double price, @RequestParam String description,
                         @RequestParam boolean status, @RequestParam List<MultipartFile> imageUrl, @PathVariable int page){
        ProductRequest productRequest = new ProductRequest(productName,imageUrl,description,price,status,categoryId,oldImage);
        Product p = productService.editProduct(productRequest);
        p.setProductId(productId);
        productService.save(p);
        productService.findAll();
        return "redirect:/home/product/"+page;
    }
    @PostMapping("/search")
    public String doSearch(@RequestParam String search, Model model){
        List<Product> list;
        if(search.isEmpty()){
            list = productService.findAll();
        }else {
            list = productService.findByCategoryName(search);
        }
        List<Category> listCategory = categoryService.findAll();
        model.addAttribute("listCategory",listCategory.stream().map(c->new Category(c.getCategoryId(),c.getCategoryName())).collect(Collectors.toList()));
        model.addAttribute("list", productService.getListRender(1, PAGE_SIZE, list));
        model.addAttribute("size",Math.ceil((double) list.size() /5));
        model.addAttribute("page", 1);
        return "product";
    }
}
