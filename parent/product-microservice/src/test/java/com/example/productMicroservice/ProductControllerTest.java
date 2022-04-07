package com.example.productMicroservice;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.productMicroservice.controller.ProductController;
import com.example.productMicroservice.model.Product;
import com.example.productMicroservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class ProductControllerTest {

	@Autowired
	MockMvc mockmvc;
	
	@InjectMocks
	ProductController prodctl;
	
	@MockBean
	ProductService prodservice;
		
	LocalDate dateObj = LocalDate.of(2020, Month.JANUARY, 8);
	Product prod1 = new Product(12, 12.00, "keyboard", "new", "Samsung", "I'm desc", "Walmart", "ImgUrl", "Cat1", null);
	Product prod2 = new Product();
	
	Product product;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(prodctl).build();
	}
	
	@Test
	public void addProductsuccess() throws Exception {
		Mockito.when(prodservice.addProduct(prod1)).thenReturn(prod1);
		mockmvc.perform(MockMvcRequestBuilders.post("/productMicro/products").contentType(MediaType.APPLICATION_JSON).content(convertObjtoString(prod1))).
		andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void getProductsuccess() throws Exception {
		Mockito.when(prodservice.getProducts()).thenReturn((List<Product>) product);
		mockmvc.perform(MockMvcRequestBuilders.get("/productMicro/products").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getProductsByIdSuccess() throws Exception{
		Mockito.when(prodservice.getProductById(12)).thenReturn(product);
		mockmvc.perform(MockMvcRequestBuilders.get("/productMicro/products/12").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
//	@Test
//	public void updateProduct() throws Exception {
//		Mockito.when(prodservice.updateProduct(12, prod1)).thenReturn(prod1);
//		mockmvc.perform(MockMvcRequestBuilders.put("/productMicro/products/12").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//	}
	
	@Test
	public void deleteProductSuccess() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.delete("/productMicro/products/12").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	
	public String convertObjtoString(Object obj) throws JsonProcessingException {
		ObjectMapper objmapper = new ObjectMapper();
		String result = objmapper.writeValueAsString(obj);
		return result;
	}
}
