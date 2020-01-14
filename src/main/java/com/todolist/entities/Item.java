package com.todolist.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	
	public Item() {
		checked = false;
	}
	
	public Item(String value, boolean checked) {
		this.value = value;
		this.checked = checked;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	private String value;
	
	private Boolean checked;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
