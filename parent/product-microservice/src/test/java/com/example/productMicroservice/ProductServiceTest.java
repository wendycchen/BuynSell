package com.example.productMicroservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productMicroservice.model.Product;
import com.example.productMicroservice.repository.ProductRepo;
import com.example.productMicroservice.service.ProductService;

@SpringBootTest
public class ProductServiceTest {


	@Mock
	private ProductRepo prodRepo;
	
	@InjectMocks
	private ProductService prodServe;
	
	LocalDate dateObj = LocalDate.of(2020, Month.JANUARY, 8);
	Product prod1 = new Product(12, 12.00, "keyboard", "new", "Samsung", "I'm desc", "Walmart", "Cat1", "xyz", dateObj);
	Product prod2 = new Product(13, 12.00, "keyboard", "new", "Samsung", "I'm desc", "Walmart", "Cat1", "xyz", dateObj);
	Product prod3 = new Product(14, 12.00, "keyboard", "new", "Samsung", "I'm desc", "Walmart", "Cat1", "xyz", dateObj);
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProductsSuccess() {
		List<Product> prodList = new ArrayList<Product>();
		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);
		when(prodRepo.findAll()).thenReturn(prodList);
		
		List<Product> result = prodServe.getProducts();
		assertEquals(3, result.size());
	}
	
//	@Test
//	public void getProductByIdSuccess() throws Exception {
//		List<Product> prodList = new ArrayList<Product>();
//		prodList.add(prod1);
//		prodList.add(prod2);
//		prodList.add(prod3);
//       // when(prodRepo.findById(12)).thenReturn(prod1);
//        Product result = prodServe.getProductById(123);
//        assertEquals(123, result.getProdId());
//        //assertEquals("new", result.getCondition());
//	}
	
	@Test
	public void addProductSuccess() {
		Product prod = new Product();
		when(prodRepo.save(prod)).thenReturn(prod);
		Product result = prodServe.addProduct(prod);
		assertEquals(0, result.getProdId());
	}
	
	@Test
	public void deleteProductSuccess() {
		Product prod = new Product();
		prodServe.deleteProduct(12);
	}
}
