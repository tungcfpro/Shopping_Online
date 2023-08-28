package com.poly.controller.adminControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;

@CrossOrigin("*")
@RestController
public class ProductRestController2 {
//    @Autowired
//    ProductDAO dao;
//
//    @GetMapping("/rest/products")
//    public List<Product> getAll(Model model) {
//        return dao.findAll();
//    }
//
//    @GetMapping("/rest/products/{id}")
//    public Product getOne(@PathVariable("id") Integer id) {
//        return dao.findById(id).get();
//    }
//
//    @PostMapping("/rest/products")
//    public Product post(@RequestBody Product product) {
//        if(product.getQuantity()>0){
//            product.setAvailable(true);
//        }
//        return dao.save(product);
//    }
//
//    @PutMapping("/rest/products/{id}")
//    public Product put(@PathVariable("id") Integer id, @RequestBody Product product) {
//    	System.out.println(product);
//        if(product.getQuantity()>0){
//            product.setAvailable(true);
//        }
//        return dao.save(product);
//    }
//
//    @DeleteMapping("/rest/products/{id}")
//    public void delete(@PathVariable("id") Integer id) {
//        dao.deleteById(id);
//    }
}
