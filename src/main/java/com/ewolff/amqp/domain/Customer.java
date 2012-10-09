package com.ewolff.amqp.domain;

import java.io.Serializable;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = 8261634939151421635L;

	private String name;
	
	private String firstname;
	
	private int age;

	public Customer() {
		super();
	}

	public Customer(String firstname, String name, int age) {
		super();
		this.firstname = firstname;
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
