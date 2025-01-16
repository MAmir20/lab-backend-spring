package com.example.demo.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.bean.OutilBean;

@FeignClient(name = "OUTIL-SERVICE")
public interface OutilProxyService {
	@GetMapping("/outils/{id}")
	public OutilBean findOneOutilById(@PathVariable(name = "id") Long id);
	
	@PostMapping("/outils")
	public OutilBean addOutil(@RequestBody OutilBean outil);
	
	@DeleteMapping("/outils/{id}")
	public void deleteOutil(@PathVariable(name = "id") Long id);
}
