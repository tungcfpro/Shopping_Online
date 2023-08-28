package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO dao;

	@Override
	public List<Product> findAll(){
		return dao.findAll();
	}
	@Override
	public Product findByID(Integer id){
		return dao.findById(id).get();
	}

	public List<Product> findbyCategoryId(String cid, Pageable pageable) {
		return dao.findByCategoryId(cid, pageable);
	}

	@Override
	public List<Product> findByPriceAndCategoryId(String categoryId, Double minPrice, Double maxPrice) {
		return dao.findByPriceAndCategoryId(categoryId, minPrice, maxPrice);
	}
	@Override
	public List<Product> findPaginated(Pageable pageable) {
		Page<Product> productPage = dao.findAll(pageable);
		return productPage.getContent();
	}

	@Override
	public long getTotalProducts() {
		return dao.count();
	}
	@Override
	public List<Product> searchByKeyword(String keyword, Pageable pageable) {
		return dao.searchByKeyword(keyword, pageable);
	}
	@Override
	public List<Product> getProductsByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
		return dao.findByPriceBetween(minPrice, maxPrice, pageable);
	}

	public Product create(Product product) {
		return dao.save(product);
	}

	public Product update(Product product) {
		return dao.save(product);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}


}
