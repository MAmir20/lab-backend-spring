package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.example.demo.entity.Admin;

@RepositoryRestController
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
