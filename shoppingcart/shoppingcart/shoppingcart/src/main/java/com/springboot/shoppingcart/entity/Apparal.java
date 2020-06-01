package com.springboot.shoppingcart.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Apparal")
@Data
public class Apparal extends Product {

	public String type;
	public String brand;
	public String design;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Override
	public String toString() {
		return "Apparal [type=" + type + ", brand=" + brand + ", design=" + design + "]";
	}

}
