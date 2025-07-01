package com.example;

//Used for store Data values
//import java.util.Date;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

//Used for Java Hiberinate annotations
import jakarta.persistence.*;



/*Report represents a basic report for a web application. It contains the date of the report, the user who made that report, and the report body. 

*/
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    //Date of the report
    @Column(name = "date")
    @JsonSerialize(using = LocalDateSerializer.class)  
    private LocalDate date; 
    //User who signed the report
    @JsonDeserialize()  
    @JsonSerialize()  
    @Column(name = "user")
    private String user; 
    //Body of the report
    @Column(name = "body")
    private String body; 

    //Must include empty constructor for hiberinate
    public Report(){};


    /* 
     * SETTERS
    */
    public void setId(int id){
        this.id = id; 

    }

      public void setDate(Long date){
        this.date = LocalDate.ofEpochDay(date); 
    }

      public void setDateNow(){
        this.date = LocalDate.now(); 
      }

      public void setUser(String user){
        this.user = user;
        
    }

      public void setBody(String body){
        this.body = body; 
        
    }

    public int getID(){
      return this.id; 
    }

    @Override
    public String toString(){
        return this.id + " " + this.date.toString() + " " + this.user + " " + this.body;
    }




}
