package com.rbc.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.io.Serializable;
 
@Entity
@NamedQuery(name = "Pet.findByCategory", query = "SELECT p FROM Pet p WHERE LOWER(p.categoryName) = LOWER(?1)")
@Table(name = "pets")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
public class Pet  implements Serializable {
	private static final long serialVersionUID = -3009157732242241606L;
	
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String status;
    
    @NotBlank
    private String photoUrl;
    
    @NotBlank
    private String categoryName;
    
 
    public Pet() {
    	
    	super();
    }
    
    public Pet (String name, String status, String cat, String url) {
    	super(); 
    	this.name = name;
    	this.status = status;
    	this.photoUrl = url;
    	this.categoryName = cat;
    }
 
    
    public Pet (long id, String name, String status, String cat, String url) {
    	super();
    	this.id = id;
    	this.name = name;
    	this.status = status;
    	this.photoUrl = url;
    	this.categoryName = cat;
    }
     
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String url) {
        this.photoUrl = url;
    }
    
    
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }
    
    @Override
	public String toString() {
		return String.format("Pet id=%d, Name='%s', Status='%s',  Category ='%s'", id, name, status, categoryName);
	}
    
    
     
}
