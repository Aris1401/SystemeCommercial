/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.commercial.gestion.dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Henintsoa & Hery
 */
public class ConnectTo {
    public static Connection postgreS() throws Exception{
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/systemecommercial","postgres","root");
  
        } catch (Exception e) {
            throw e;
        }  
       return connection;
    }
}
