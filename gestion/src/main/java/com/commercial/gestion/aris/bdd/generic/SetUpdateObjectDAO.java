package com.commercial.gestion.aris.bdd.generic;

import java.lang.reflect.Type;

public class SetUpdateObjectDAO {
	String name;
	String value;
	Type type;
	
	public SetUpdateObjectDAO(String name, String value, Type type) {
		setName(name);
		setValue(value);
		setType(type);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	// Function
	public String toString() {
		if (getType() == String.class || getType() == java.util.Date.class || getType() == java.sql.Date.class) {
			return name + " = '" + value + "'";
		} else {
			return name + " = " + value;
		}
	}
}
