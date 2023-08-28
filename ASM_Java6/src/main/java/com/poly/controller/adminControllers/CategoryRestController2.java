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

import com.poly.dao.CategoryDAO;
import com.poly.entity.Category;

@CrossOrigin("*")
@RestController
public class CategoryRestController2 {
	@Autowired
	CategoryDAO dao;

	@GetMapping("/rest/categories")
	public List<Category> getAll(Model model) {
		return dao.findAll();
	}

	@GetMapping("/rest/categories/{id}")
	public Category getOne(@PathVariable("id") String id) {
		return dao.findById(id).get();
	}

	@PostMapping("/rest/categories")
	public Category post(@RequestBody Category product) {
		return dao.save(product);
	}

	@PutMapping("/rest/categories/{id}")
	public Category put(@PathVariable("id") String id, @RequestBody Category product) {
		return dao.save(product);
	}

	@DeleteMapping("/rest/categories/{id}")
	public void delete(@PathVariable("id") String id) {
		dao.deleteById(id);
	}
}
