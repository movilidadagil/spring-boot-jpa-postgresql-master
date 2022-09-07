package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.Product;
import com.bezkoder.spring.jpa.postgresql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products/expired")
	public ResponseEntity<List<Product>> getAllProductsWhichHasExpired(@RequestParam(required = false) String product_name) {
		try {
			List<Product> products = new ArrayList<Product>();

			if (product_name == null)
				productRepository.findAllByExpire_date().forEach(products::add);
			else
				productRepository.findProductByExpire_date(product_name).forEach(products::add);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products/unexpired")
	public ResponseEntity<List<Product>> getAllProductsWhichHasntExpired() {
		try {
			List<Product> expiredProducts = new ArrayList<Product>();
			List<Product> unexpiredProducts = new ArrayList<Product>();
			List<Product> allProducts = new ArrayList<Product>();

			productRepository.findAllByExpire_date().forEach(expiredProducts::add);
			productRepository.findAll().forEach(allProducts::add);
			unexpiredProducts= allProducts.stream()
					.filter(e -> expiredProducts.contains(e))
					.collect(Collectors.toList());

			if (unexpiredProducts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(unexpiredProducts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
