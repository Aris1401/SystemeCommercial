package com.commercial.gestion.aris.bdd.generic;

import java.lang.reflect.Type;
import java.sql.Timestamp;

public class SelectionObjectDAO {
	String name;
	String value;
	String operator;
	Type columnType;
	comparaisonType comparaison;
	
	public static enum comparaisonType {
		EQUALS,
		LESS,
		GREATER,
		LIKE
	};
	
	public SelectionObjectDAO(String name, String value, Type type, String operator) {
		setName(name);
		setValue(value);
		setOperator(operator);
		setColumnType(type);
	}
	
	public SelectionObjectDAO(String name, String value, Type type, String operator, comparaisonType comparator) {
		setName(name);
		setValue(value);
		setOperator(operator);
		setColumnType(type);
		setComparaison(comparator);
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Type getColumnType() {
		return columnType;
	}

	public void setColumnType(Type columnType) {
		this.columnType = columnType;
	}

	public comparaisonType getComparaison() {
		return comparaison;
	}

	public void setComparaison(comparaisonType comparaison) {
		this.comparaison = comparaison;
	}

	// Functions
	public String toString() {
		String result = "";
		
		// Getting the comparator
		String comparator = " = ";
		if (comparaison != null) {
			if (comparaison == comparaisonType.EQUALS) comparator = comparator;
			else if (comparaison == comparaisonType.LESS) comparator = " < ";
			else if (comparaison == comparaisonType.GREATER) comparator = " > ";
			else if (comparaison == comparaisonType.LIKE) comparator = " LIKE ";
		}
		
		if (getColumnType() == String.class || getColumnType() == java.util.Date.class || getColumnType() == java.sql.Date.class || getColumnType() == Timestamp.class) {
			result = name + comparator + "'" + value + "'";
		} else {
			result = name + comparator + value;
		}
		
		return getOperator() == null ? result : operator + " " + result;
	}
}
