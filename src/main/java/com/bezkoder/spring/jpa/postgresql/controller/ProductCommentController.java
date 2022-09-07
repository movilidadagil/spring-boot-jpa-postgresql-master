package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bezkoder.spring.jpa.postgresql.model.Product;
import com.bezkoder.spring.jpa.postgresql.model.ProductComment;
import com.bezkoder.spring.jpa.postgresql.model.User;
import com.bezkoder.spring.jpa.postgresql.repository.ProductCommentRepository;
import com.bezkoder.spring.jpa.postgresql.repository.ProductRepository;
import com.bezkoder.spring.jpa.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductCommentController {

	@Autowired
	ProductCommentRepository productCommentRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;


	@GetMapping("/comments/{product_name}")
	public ResponseEntity<List<ProductComment>> getCommentsForProduct(@PathVariable("product_name") String product_name) {
		Optional<Product> product = productRepository.findProductByProductname(product_name);
		List<ProductComment> productComments = productCommentRepository.findProductCommentsByProduct_id(product.get().getId());

		if (productComments.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(productComments, HttpStatus.OK);
	}

	@GetMapping("/comments/{product_name}/{start_date}/{end_date}")
	public ResponseEntity<List<ProductComment>> getTutorialById(
			@PathVariable("product_name") String product_name,
			@PathVariable("start_date") String start_date,
			@PathVariable("end_date") String end_date
	) {
		Optional<Product> product = productRepository.findProductByProductname(product_name);
		List<ProductComment> productCommentsBetweenGivenDate = productCommentRepository.findProductCommentsByComment_dateBetween(start_date,end_date);
		List<ProductComment> commentedProductOnDate= productCommentsBetweenGivenDate.stream()
				.filter(e -> product.get().getProductname().contains(e.toString()))
				.collect(Collectors.toList());
		if (commentedProductOnDate.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(commentedProductOnDate, HttpStatus.OK);
	}

	@GetMapping("/comments/{name}/{surname}")
	public ResponseEntity<List<ProductComment>> getCommentsByGivenUser(
			@PathVariable("name") String name,
			@PathVariable("surname") String surname) {
		Optional<User> user = userRepository.findUserByNameAAndSurname(name,surname);

		List<ProductComment> productCommentsByUser = productCommentRepository.findProductCommentsByUser_id(user.get().getId());
		if (productCommentsByUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(productCommentsByUser, HttpStatus.OK);

	}

	@GetMapping("/comments/{name}/{surname}/{start_date}/{end_date}")
	public ResponseEntity<List<ProductComment>> getCommentsByGivenUserOnDate(
			@PathVariable("name") String name,
			@PathVariable("surname") String surname,
			@PathVariable("start_date") String start_date,
			@PathVariable("end_date") String end_date
			) {
		Optional<User> user = userRepository.findUserByNameAAndSurname(name,surname);
		List<ProductComment> productCommentsBetweenGivenDate = productCommentRepository.findProductCommentsByComment_dateBetween(start_date,end_date);
		List<ProductComment> commentedProductOnDateByGivenUser= productCommentsBetweenGivenDate.stream()
				.filter(e -> user.get().getId() == e.getUser_id())
				.collect(Collectors.toList());
		if (commentedProductOnDateByGivenUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(commentedProductOnDateByGivenUser, HttpStatus.OK);
	}




}
