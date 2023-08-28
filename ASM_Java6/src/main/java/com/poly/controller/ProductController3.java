package com.poly.controller;

import com.poly.entity.Product;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProductController3 {
    @Autowired
    ProductService productService;
    private final int pageSize = 20;
    @GetMapping("/user/home")
    public String list(@RequestParam(defaultValue = "0") int page,Model model){

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        List<Product> list = productService.findPaginated(pageable);

        int totalPages = (int) Math.ceil((double) productService.getTotalProducts() / pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("items", list);
        return "user/index";
    }

    @RequestMapping("user/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Product item = productService.findByID(id);
        model.addAttribute("item", item);
        List<Product> list = productService.findAll();
        model.addAttribute("items", list);

        return "user/product-detail";
    }

    //Shop
    @RequestMapping("/user/shop")
    public String listShop(@RequestParam(defaultValue = "0") int page,Model model, @RequestParam("cid") Optional<String> cid){
        if (cid.isPresent()){
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
            List<Product> list = productService.findbyCategoryId(cid.get(),pageable);
            int totalPages = (int) Math.ceil((double) productService.getTotalProducts() / pageSize);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("items", list);
        }else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
            List<Product> list = productService.findPaginated(pageable);

            int totalPages = (int) Math.ceil((double) productService.getTotalProducts() / pageSize);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("items", list);
        }

        return "user/product";
    }
    @GetMapping("/search")
    public String searchByKeyword(@RequestParam(defaultValue = "0") int page,@RequestParam("keyword") String keyword, Model model) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        List<Product> list = productService.searchByKeyword(keyword,pageable);
        int totalPages = (int) Math.ceil((double) productService.getTotalProducts() / pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("items", list);
        return "user/product";
    }
    @GetMapping("/price")
    public String filterByPrice(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                Model model,@RequestParam(defaultValue = "0") int page) {
        List<Product> list;
        int sizePage;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        if (minPrice != null && maxPrice != null) {
            list = productService.getProductsByPriceRange(minPrice, maxPrice ,pageable);
            sizePage = list.size();
        } else {

            list = productService.findPaginated(pageable);
            sizePage = (int) Math.ceil((double) productService.getTotalProducts() / pageSize);
        }
        int totalPages = (int) Math.ceil((double) sizePage / pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("items", list);
        return "user/product"; // Thymeleaf template name
    }
    @GetMapping("/products/sort")
    public String listProducts( @RequestParam(defaultValue = "0") int page, @RequestParam("cid") Optional<String> cid,@RequestParam("order") String order, Model model) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        List<Product> list;

        int totalPages = (int) Math.ceil((double) productService.getTotalProducts() / pageSize);


        if ("asc".equals(order)) {
            list = productService.findPaginated(pageable).stream()
                    .sorted(Comparator.comparingDouble(Product::getPrice))
                    .collect(Collectors.toList());
        } else if ("desc".equals(order)) {
            list = productService.findPaginated(pageable).stream()
                    .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                    .collect(Collectors.toList());
        } else {
            list = productService.findPaginated(pageable);
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("items", list);
        return "user/product";
    }
}
