package com.cgi;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.controller.OrderController;
import com.cgi.model.Orders;
import com.cgi.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class OrderControllerTest {

	@Autowired
	MockMvc mockmvc;
	
	@InjectMocks
	OrderController ordctl;
	
	@MockBean
	OrderService ordservice;
	
	Orders orders;
	Orders orders1 = new Orders(1,"rebecca@gmail");
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(ordctl).build();
	}
	
	@Test
	public void getAllOrderssuccess() throws Exception {
		Mockito.when(ordservice.getOrders()).thenReturn((List<Orders>) orders);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/orders/allOrders").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getOrderByEmailsuccess() throws Exception {
		Mockito.when(ordservice.getOrderByEmail("rebecca@gmail.com")).thenReturn((List<Orders>) orders);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/orders/FindByEmail/rebecca@gmail.com").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getOrderByIdsuccess() throws Exception {
		Mockito.when(ordservice.getByOrderId(1)).thenReturn(orders);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/orders/order/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getOrderByOrderNumbersuccess() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.get("/api/orders/orderNumber/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
//	@Test
//	public void addOrderssuccess() throws Exception {
//		Mockito.when(ordservice.addOrder(orders1)).thenReturn(orders1);
//		mockmvc.perform(MockMvcRequestBuilders.get("/api/orders/order/newOrder").contentType(MediaType.APPLICATION_JSON).content(convertObjtoString(orders1)))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//	}
	
	public String convertObjtoString(Object obj) throws JsonProcessingException {
		ObjectMapper objmapper = new ObjectMapper();
		String result = objmapper.writeValueAsString(obj);
		return result;
	}
	
}
