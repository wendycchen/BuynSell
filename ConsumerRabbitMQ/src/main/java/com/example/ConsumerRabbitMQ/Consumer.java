package com.example.ConsumerRabbitMQ;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope=Consumer.class)
public class Consumer implements Serializable {

	private String id;

	public Consumer() {
	}

	public Consumer(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Consumer [id=" + id + "]";
	}

}
