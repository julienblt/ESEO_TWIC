package com;

import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dao.DAOFactory;
import com.dao.VilleFranceDAO;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        try {
        	SpringApplication.run(App.class, args);
        	System.out.println("App démarrée");
        }catch (Exception e) {
			System.out.println("ERROR \n"+e);
		}
    }
}
