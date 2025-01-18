package com.example.demo.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.bean.EventBean;

@FeignClient(name = "EVENEMENT-SERVICE")
public interface EventProxyService {
	@GetMapping("/evenements/{id}")
	public EventBean findOneEventById(@PathVariable(name = "id") Long id);
	
	@PostMapping("/evenements")
	public EventBean addEvent(@RequestBody EventBean event);
	
	@DeleteMapping("/evenements/{id}")
	public void deleteEvent(@PathVariable(name = "id") Long id);

	@PutMapping("/evenements/{id}")
	public void updateEvent(@PathVariable(name = "id") Long id, @RequestBody EventBean event);
}
