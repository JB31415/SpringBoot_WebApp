package com.example;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
//Hiberinate Imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
//
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.ArrayList; 
import java.util.List; 

@SuppressWarnings("unused")
public class DatabaseManager {
    
    
    //Config object for hiberinate
    Configuration config; 

    //SessionFactory for creating and managing sessions. 
    SessionFactory sf; 


    //When initialized, create the config and sessionFactory. 
    DatabaseManager(){
        this.config = new Configuration(); 
        this.config.configure("hibernate.cfg.xml");
        this.config.addAnnotatedClass(Report.class);
        
        this.sf = config.buildSessionFactory();
    }


    //Sets config file to set string
    public void configFile(String configFile){
        config.configure(configFile);

    }

    
    //Saves report to database
    public void saveReport(Report report) {

        
        try(Session session = sf.openSession();){
            session.beginTransaction(); 

            //code 
            session.persist(report);

            //Commit the transaction to the database
            session.getTransaction().commit();

            session.close(); 

        }
    }

    public List<Report> getAllReports(){
        
        List<Report> returnList; 

        try(Session session = sf.openSession();){
            session.beginTransaction(); 
            returnList = session.createSelectionQuery("SELECT a FROM Report a", Report.class).getResultList();
            session.getTransaction().commit(); 
            
            returnList.forEach(System.out::println);

        }

        System.out.println(returnList);

        return returnList; 
    }

    //Delete Report

    //Retrieve Report

    //Update Report

    

    /* 
    //Builds session for the DatabaseManager object
    public void startSession(){
       
        
        //Create new config
        config = new Configuration(); 
        config.configure("hibernate.cfg.xml");
        //Add report class to config
        config.addAnnotatedClass(com.example.Report.class);
        //Specify explicitly which config file to use. 
        
        Report testReport = new Report(); 

        testReport.setBody("Henry Buys a bean 2");
        testReport.setDateNow();
        testReport.setUser("Henry");


        

        sf = config.buildSessionFactory(); 
        session = sf.openSession(); 

        Transaction transaction = session.beginTransaction(); 

        session.persist(testReport);

        transaction.commit();

    } */

   

    
}
