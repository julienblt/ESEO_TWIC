package com;

import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dao.DAOFactory;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        try {
        	SpringApplication.run(App.class, args);
        	System.out.println("App démarrée");
        	Statement stmt = DAOFactory.connection();
    		ResultSet rst = stmt.executeQuery("SELECT Nom_commune FROM ville_france;");
    		
    		while (rst.next()) {
    			System.out.println(rst.getString("Nom_commune"));
    		}
        	
        	
        }catch (Exception e) {
			System.out.println("ERROR \n"+e);
		}
    }
}
