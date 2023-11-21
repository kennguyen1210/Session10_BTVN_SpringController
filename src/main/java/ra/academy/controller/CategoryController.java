package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.Category;
import ra.academy.service.impl.CategoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home/category")
public class CategoryController {
    public static final int PAGE_SIZE = 5;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/{id}")
    public String getPage(@PathVariable Integer id, Model model){
        if(id == null){
            id = 1;
        }
        List<Category> list;
        if(CategoryService.listCategory.isEmpty()){
            list = categoryService.findAll();
        } else {list = CategoryService.listCategory;}
        model.addAttribute("list",categoryService.getListRender(id, PAGE_SIZE,list));
        model.addAttribute("size",Math.round((float) list.size() /5));
        model.addAttribute("page",id);
        return "category";
    }
    @PostMapping("/add/{page}")
    public String doAdd(@RequestParam String name, @RequestParam String des,@PathVariable int page){
        categoryService.save(new Category(name,des, LocalDateTime.now()));
        categoryService.findAll();
        return "redirect:/home/category/"+page;
    }
    @GetMapping("/delete/{id}/{page}")
    public String doDelete(@PathVariable int id,@PathVariable int page){
        categoryService.delete(id);
        categoryService.findAll();
        return "redirect:/home/category/"+page;
    }
    @PostMapping("/update/{page}")
    public String doEdit(@RequestParam int id, @RequestParam String name, @RequestParam String des, @PathVariable int page){
        categoryService.save(new Category(id,name,des,null));
        categoryService.findAll();
        return "redirect:/home/category/"+page;
    }
    @PostMapping("/search")
    public String doSearch(@RequestParam String search, Model model){
        List<Category> list;
        if(search.isEmpty()){
            list = categoryService.findAll();
        }else {
            list = categoryService.findByCategoryName(search);
        }
        model.addAttribute("list", categoryService.getListRender(1, PAGE_SIZE, list));
        model.addAttribute("size",Math.round((float) list.size() /5));
        model.addAttribute("page", 1);
        return "category";
    }
}
