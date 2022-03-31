package Dto;

import java.util.List;

import com.cgi.model.OrderItems;

import lombok.Data;

@Data
public class OrderDto {
	private List<OrderItems> orderItemsList;
}
