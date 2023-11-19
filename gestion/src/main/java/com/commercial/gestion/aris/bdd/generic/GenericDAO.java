package com.commercial.gestion.aris.bdd.generic;

import java.io.Serializable;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.commercial.gestion.aris.bdd.annotations.ExcludeFromInsertion;
import com.commercial.gestion.aris.bdd.annotations.ExcludeFromSelection;
import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.annotations.Table;

public class GenericDAO<T> implements Serializable {
	private Map<String, SelectionObjectDAO> selection = new HashMap<>();
	private Map<String, SetUpdateObjectDAO> setUpdate = new HashMap<>();
	private Class<T> currentClass = null;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		setCurrentClass((Class<T>) getClass());
	}

	public GenericDAO(Class<T> tClass){
		setCurrentClass(tClass);
	}
	
	// Setting the current class
	private GenericDAO<T> setCurrentClass(Class<T> givenClass){
		this.currentClass = givenClass;
		
		return this;
	}
	
	private String getCurrentClassName() {
		if (currentClass.isAnnotationPresent(Table.class)) {
			Table table = currentClass.getAnnotation(Table.class);
			
			return table.tableName();
		} 
		
		return currentClass.getSimpleName();
	}

	public GenericDAO<T> setFieldValue(String fieldName, Object value) {
		Field[] fields = currentClass.getDeclaredFields();

		// Getting the field
		Field searchedField = null;
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				searchedField = field;
				break;
			}
		}

		if (searchedField == null) {
			try {
				throw new Exception("Attribut '" + fieldName +  "' introuvable dans la classe: " + currentClass.getSimpleName());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		searchedField.setAccessible(true);
		try {
			searchedField.set(this, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		return this;
	}
	
	// Where clause selection
	public GenericDAO addToSelection(String columnName, String value, String operator) throws Exception {
		Field[] objectFields = currentClass.getDeclaredFields();
		Type fieldType = null;
		
		int correspondance = 0;
		for (Field field : objectFields) {
			if (field.getName().toLowerCase().equals(columnName.toLowerCase())) {
				fieldType = field.getType();
				
				correspondance++;
			}
		}
		
		if (correspondance == 0) throw new Exception("Attribut '" + columnName + "' introuvable dans la classe: " + currentClass.getSimpleName());
		
		if (selection.size() >= 1) {
			if (operator == null) throw new Exception("La selection de '" + columnName + "' a besoin d'un operateur");
		}
		SelectionObjectDAO selected = new SelectionObjectDAO(columnName, value, fieldType, operator);
		
		selection.put(columnName, selected);
		
		return this;
	}
	
	// ----------------------------------------------------------------------------
	public GenericDAO addToSelection(String name, int value, String operator) throws Exception {
		return addToSelection(name, String.valueOf(value), operator);
	}
	public GenericDAO addToSelection(String name, float value, String operator) throws Exception {
		return addToSelection(name, String.valueOf(value), operator);
	}
	public GenericDAO addToSelection(String name, double value, String operator) throws Exception {
		return addToSelection(name, String.valueOf(value), operator);
	}
	// ----------------------------------------------------------------------------
	
	
	public GenericDAO addToSelection(String columnName, String value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		Field[] objectFields = currentClass.getDeclaredFields();
		Type fieldType = null;
		
		int correspondance = 0;
		for (Field field : objectFields) {
			if (field.getName().toLowerCase().equals(columnName.toLowerCase())) {
				fieldType = field.getType();
				
				correspondance++;
			}
		}
		
		if (correspondance == 0) throw new Exception("Attribut '" + columnName + "' introuvable dans la classe: " + this.getClass().getSimpleName());
		
		if (selection.size() >= 1) {
			if (operator == null) throw new Exception("La selection de '" + columnName + "' a besoin d'un operateur");
		}
		SelectionObjectDAO selected = new SelectionObjectDAO(columnName, value, fieldType, operator, comparaison);
		
		selection.put(columnName, selected);
		
		return this;
	}
	
	public GenericDAO addToSelection(String name, int value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		return addToSelection(name, String.valueOf(value), operator, comparaison);
	}
	public GenericDAO addToSelection(String name, float value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		return addToSelection(name, String.valueOf(value), operator, comparaison);
	}
	public GenericDAO addToSelection(String name, double value, String operator, SelectionObjectDAO.comparaisonType comparaison) throws Exception {
		return addToSelection(name, String.valueOf(value), operator, comparaison);
	}

	/**
	 * Clears the current selection
	 */
	public void freeSelection() {
		selection.clear();
	}
	
	public GenericDAO removeFromSelection(String columnName) throws Exception {
		SelectionObjectDAO selectedColumn = selection.get(columnName);
		
		if (selectedColumn == null) throw new Exception("La colonne " + columnName + " n'est pas encore selectionnee");
		
		selection.remove(columnName);
		
		return this;
	}
	
	private String getCurrentSelection() {
		if (selection.size() == 0) return "";
		
		String selectionString = "WHERE";
		
		for (Map.Entry<String, SelectionObjectDAO> selected : selection.entrySet()) {
			SelectionObjectDAO selectedInstance = selected.getValue();
			
			selectionString += " " + selectedInstance.toString();
		}
		
		return selectionString;
	}
	
	// Update methods
	public GenericDAO addToSetUpdate(String name, String value) throws Exception {
		Field[] objectFields = currentClass.getDeclaredFields();
		Type fieldType = null;
		
		int correspondance = 0;
		for (Field field : objectFields) {
			if (field.getName().toLowerCase().equals(name.toLowerCase())) {
				fieldType = field.getType();
				
				correspondance++;
			}
		}
		
		if (correspondance == 0) throw new Exception("Attribut '" + name + "' introuvable dans la classe: " + this.getClass().getSimpleName());
		
		SetUpdateObjectDAO setUpdateObject = new SetUpdateObjectDAO(name, value, fieldType);
		setUpdate.put(name, setUpdateObject);
		
		return this;
	}
	
	// ----------------------------------------------
	public GenericDAO addToSetUpdate(String name, int value) throws Exception {
		return addToSetUpdate(name, String.valueOf(value));
	}
	public GenericDAO addToSetUpdate(String name, float value) throws Exception {
		return addToSetUpdate(name, String.valueOf(value));
	}
	public GenericDAO addToSetUpdate(String name, double value) throws Exception {
		return addToSetUpdate(name, String.valueOf(value));
	}
	// ----------------------------------------------
	
	public void freeSetUpdate() {
		setUpdate.clear();
	}
	
	public GenericDAO removeFromSetUpdate(String name) throws Exception {
		SetUpdateObjectDAO selectedColumn = setUpdate.get(name);
		
		if (selectedColumn == null) throw new Exception("La colonne " + name + " n'est pas encore selectionnee");
		
		setUpdate.remove(name);
		
		return this;
	}
	
	private String getSetUpdate() {
		StringBuilder setUpdateString = new StringBuilder(" SET");
		
		int i = 0;
		for (Map.Entry<String, SetUpdateObjectDAO> update : setUpdate.entrySet()) {
			SetUpdateObjectDAO setUpdateSelected = update.getValue();
			
			setUpdateString.append(" ").append(setUpdateSelected.toString());
			
			if (i < setUpdate.size() - 1) setUpdateString.append(",");
			
			i++;
		}
		
		return setUpdateString.toString();
	}

	public static boolean areTypesEqual(Class<?> type1, Class<?> type2) {
		if (type1.isPrimitive()) {
			return checkPrimitiveAndWrapper(type1, type2);
		} else if (type2.isPrimitive()) {
			return checkPrimitiveAndWrapper(type2, type1);
		} else {
			return type1.equals(type2);
		}
	}

	private static boolean checkPrimitiveAndWrapper(Class<?> primitiveType, Class<?> wrapperType) {
		if (primitiveType == int.class && wrapperType == Integer.class) {
			return true;
		} else if (primitiveType == double.class && wrapperType == Double.class) {
			return true;
		} else if (primitiveType == float.class && wrapperType == Float.class) {
			return true;
		} // Add more cases as needed

		return false;
	}

	private Field getPKField() {
		Field[] currentObjectFields = currentClass.getDeclaredFields();

		// Obtenir l'attribut avec annotation primary key
		Field pkField = null;
		for (Field field : currentObjectFields) {
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				pkField = field;
				break;
			}
		}

		return pkField;
	}
	
	// Insert - Select - Update
	public T getFromDatabaseById(Connection c, Object value) {
		Field pkField = getPKField();

		// Si aucun pk est specifiee
		if (pkField == null) try {
			throw new Exception("Aucune cle primaire specifiee");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// Check types pk et valeur
		if (!areTypesEqual(pkField.getType(), value.getClass()))
			try {
				throw new Exception("Type valeur specifier en conflit avec type PK ( " + pkField.getType().getTypeName() + " // " + value.getClass().getTypeName() + ")");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		// Ajout a la selection
		try {
			if (pkField.getType() == int.class) {
				addToSelection(pkField.getName(), (int) value, "");
			} else if (pkField.getType() == double.class) {
				addToSelection(pkField.getName(), (double) value, "");
			} else if (pkField.getType() == float.class) {
				addToSelection(pkField.getName(), (double) value, "");
			} else {
				addToSelection(pkField.getName(), (String) value, "");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		try {
			var values = getFromDatabase(c);

			return values.isEmpty() ? null : values.get(0);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<T> getFromDatabase(Connection c) throws SQLException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String selectionString = "SELECT * FROM " + getCurrentClassName();
		
		if (!selection.isEmpty()) {
			selectionString += " " + getCurrentSelection();
		}
		
		System.out.println(selectionString);
		
		ArrayList<T> databaseResults = new ArrayList<>();
		
		Statement stat = c.createStatement();
		ResultSet results = stat.executeQuery(selectionString);
		
		while (results.next()) {
			Field[] rowFields = currentClass.getDeclaredFields();
			T newObj = currentClass.getDeclaredConstructor().newInstance();

            for (Field rowField : rowFields) {
                if (rowField.isAnnotationPresent(ExcludeFromSelection.class)) continue;

                rowField.setAccessible(true);

                //String fieldCapitalizedName = rowFields[i].getName().substring(0, 1).toUpperCase() + rowFields[i].getName().substring(1);
                //Method setterMethod = newObj.getClass().getDeclaredMethod("set" + fieldCapitalizedName, rowFields[i].getType());

                if (rowField.getType() == int.class) {
                    rowField.set(newObj, results.getInt(rowField.getName()));
                } else if (rowField.getType() == double.class) {
                    rowField.set(newObj, results.getDouble(rowField.getName()));
                    //setterMethod.invoke(newObj, results.getDouble(rowFields[i].getName()));
                } else if (rowField.getType() == String.class) {
                    rowField.set(newObj, results.getString(rowField.getName()));
                    //setterMethod.invoke(newObj, results.getString(rowFields[i].getName()));
                } else if (rowField.getType() == Date.class) {
                    rowField.set(newObj, results.getDate(rowField.getName()));
                    //setterMethod.invoke(newObj, results.getDate(rowFields[i].getName()));
                } else if (rowField.getType() == Timestamp.class) {
                    rowField.set(newObj, results.getTimestamp(rowField.getName()));
                    //setterMethod.invoke(newObj, results.getTimestamp(rowFields[i].getName()));
                }
            }
			
			databaseResults.add(newObj);
		}
		
		return databaseResults;
	}
	
        public void updateInDatabase(Connection c) throws Exception {
            if (c == null) throw  new Exception("Connection null");
            
            // Creation de statement
            Statement stat = c.createStatement();
            
            // Requete
            String requete = "UPDATE " + getCurrentClassName() + getSetUpdate() + " " +  getCurrentSelection();
            System.out.println(requete);
            stat.executeUpdate(requete);
        }
        
        public void deleteFromDatabase(Connection c) throws SQLException, Exception {
            if (c == null) throw new Exception("Connexion est null");
            
            // Creation de statement
            Statement stat = c.createStatement();
            
            // Creation de la requete
            String requete = "DELETE FROM " + getCurrentClassName() + " WHERE " + getCurrentSelection();
            stat.executeUpdate(requete);
        }
        
	public int saveInDatabse(Connection c) throws SQLException {
		if (c == null) return -1;
		
		Statement stat = c.createStatement();
		
		StringBuilder requete = new StringBuilder("INSERT INTO " + getCurrentClassName() + "(");
		
		Field[] objectFields = currentClass.getDeclaredFields();
		
		for (int i = 0; i < objectFields.length; i++) {
			if (objectFields[i].isAnnotationPresent(ExcludeFromInsertion.class)) {
				if (requete.charAt(requete.length() - 1) == ',') {
					requete = new StringBuilder(requete.substring(0, requete.length() - 1));
				}
				continue; 
			}
			
			requete.append(objectFields[i].getName());
			
			if (i < objectFields.length - 1) requete.append(",");
		}
		
		requete.append(") VALUES (");
		
		for (int i = 0; i < objectFields.length; i++) {
			if (objectFields[i].isAnnotationPresent(ExcludeFromInsertion.class)) {
				if (requete.charAt(requete.length() - 1) == ',') {
					requete = new StringBuilder(requete.substring(0, requete.length() - 1));
				}
				continue;
			}
			
			objectFields[i].setAccessible(true);

			try {
//				String fieldCapitalizedName = objectFields[i].getName().substring(0, 1).toUpperCase() + objectFields[i].getName().substring(1);
//				Method getterMethod = this.getClass().getDeclaredMethod("get" + fieldCapitalizedName);
				
				if (objectFields[i].get(this) == null) {
					requete.append("NULL");
				} else {
					if (objectFields[i].getType() == int.class) {
						requete.append(objectFields[i].get(this));
					} if (objectFields[i].getType() == String.class) {
						requete.append("\'").append(objectFields[i].get(this)).append("\'");
						
//						requete += "\'" + getterMethod.invoke(this) + "\'";
					} if (objectFields[i].getType() == double.class) {
						requete.append(objectFields[i].get(this));
					} if (objectFields[i].getType() == Date.class) {
						String valeur = new SimpleDateFormat("dd-MM-YYYY").format(((Date) objectFields[i].get(this)));
						requete.append("'").append(valeur).append("'");
					} if (objectFields[i].getType() == Timestamp.class) {
						String valeur = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Timestamp) objectFields[i].get(this)));
						requete.append("'").append(valeur).append("'");
					} 
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (i < objectFields.length - 1) requete.append(",");
		}
		
		requete.append(")");

        for (Field objectField : objectFields) {
            if (objectField.isAnnotationPresent(PrimaryKey.class)) {
                requete.append(" returning ").append(objectField.getName());
            }
        }

		int generatedKey = -1;
		try {
			generatedKey = stat.executeUpdate(requete.toString(), Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Field pkField = getPKField();
		if (pkField != null) {
			setFieldValue(pkField.getName(), generatedKey);
		}
		
		return generatedKey;
	}
}
