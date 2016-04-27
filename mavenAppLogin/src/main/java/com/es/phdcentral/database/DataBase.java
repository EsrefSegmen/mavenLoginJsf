/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.es.phdcentral.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class DataBase implements Serializable {

    private static final long serialVersionUID = 1L;

    public Connection getConnection() {
        Connection con = null;

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        try {

            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "jdbc:mysql://localhost/proedu";
        String user = "root";
        String password = "test32";
        try {
            con = DriverManager.getConnection(url, user, password);
//            System.out.println("Connection completed.");
            Logger.getLogger(DataBase.class.getName()).log(Level.INFO, null, "Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return con;
    }

    public ResultSet getResultSet(String sql) {

        try {
            ResultSet rs = null;
            PreparedStatement pst = null;
            Connection con = getConnection();
            pst = con.prepareStatement(sql);
            pst.execute();
            rs = pst.getResultSet();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
 
    }
    public boolean InsertSql(String sql) {

        try {
            
            PreparedStatement pst = null;
            Connection con = getConnection();
            pst = con.prepareStatement(sql);
           return pst.execute();
            
                    
                    
                    
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
