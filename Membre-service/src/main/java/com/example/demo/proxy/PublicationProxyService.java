package com.example.demo.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;

@FeignClient(name = "PUBLICATION-SERVICE")
public interface PublicationProxyService {
	@GetMapping("/publications/{id}")
	public PublicationBean findOnePublicationById(@PathVariable(name = "id") Long id);
	
	@PostMapping("/publications")
	public PublicationBean addPublication(@RequestBody PublicationBean pub);
	
	@DeleteMapping("/publications/{id}")
	public void deletePublication(@PathVariable(name = "id") Long id);
	
	@PutMapping("/publications/{id}")
	public void updatePublication(@PathVariable(name = "id") Long id, @RequestBody PublicationBean pub);
}
