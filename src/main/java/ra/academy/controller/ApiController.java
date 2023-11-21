package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.Category;
import ra.academy.model.Product;
import ra.academy.service.impl.CategoryService;
import ra.academy.service.impl.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping(value = "/category/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Category getCategory(@PathVariable int id){
        return categoryService.findById(id);
    }
    @GetMapping(value = "/product/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product getProduct(@PathVariable int id){
        return productService.findById(id);
    }
}
