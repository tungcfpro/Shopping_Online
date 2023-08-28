package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Product;
import com.poly.service.ProductService;



@Controller
public class ProductController2 {
	@Autowired
	ProductService productService;
	
//	@RequestMapping("/product/list")
//	public String list(Model model, @RequestParam("cid") Optional<String> cid) {
//		if(cid.orElse("").isEmpty()) {
//			List<Product> list = productService.findAll();
//			model.addAttribute("items", list);
//		}
//		else {
//			List<Product> list = productService.findByCategoryId(cid.get());
//			model.addAttribute("items", list);
//		}
//		return "user/product";
//	}
//		
//	@RequestMapping("/product/detail/{id}")
//	public String detail(Model model, @PathVariable("id") Integer id) {
//		Product item = productService.findById(id);
//		model.addAttribute("item", item);
//		return "user/product-detail";
//	}
}