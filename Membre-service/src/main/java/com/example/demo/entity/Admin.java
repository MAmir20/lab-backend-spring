package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @DiscriminatorValue("Admin")
@NoArgsConstructor
@Getter @Setter
public class Admin extends Membre{

}
