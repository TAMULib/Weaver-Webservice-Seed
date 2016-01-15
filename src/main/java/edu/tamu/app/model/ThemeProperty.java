/* 
 * MetadataFields.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * 
 * @author 
 *
 */
@Entity
public class ThemeProperty {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = CoreTheme.class, property = "name") 
	@JsonIdentityReference(alwaysAsId = true)
	private CoreTheme theme;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = ThemePropertyName.class, property = "name") 
	@JsonIdentityReference(alwaysAsId = true)
	private ThemePropertyName propertyName;
	
	@Column
	String value;
	
	public ThemeProperty() { }
	
	public ThemeProperty(ThemePropertyName propertyName, String value) {
		setPropertyName(propertyName);
		setValue(value);
	}
	
	public void setPropertyName(ThemePropertyName propertyName) {
		this.propertyName = propertyName;
	}

	public ThemePropertyName getPropertyName() {
		return this.propertyName;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
