/* 
 * CoreTheme.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import javax.persistence.Id;


/** 
 * Application Theme entity.
 * 
 * @author <a href="mailto:jmicah@library.tamu.edu">Micah Cooper</a>
 * @author <a href="mailto:jcreel@library.tamu.edu">James Creel</a>
 * @author <a href="mailto:huff@library.tamu.edu">Jeremy Huff</a>
 * @author <a href="mailto:jsavell@library.tamu.edu">Jason Savell</a>
 * @author <a href="mailto:wwelling@library.tamu.edu">William Welling</a>
 *
 */
@Entity
public class CoreTheme {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private Boolean active = false;
	
	@OneToMany(mappedBy="theme", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.EAGER, orphanRemoval = true)	
	private Set<ThemeProperty> properties = new HashSet<ThemeProperty>();
	
	
	/**
	 * Constructor for the theme
	 * 
	 */
	public CoreTheme() {}
	
	public CoreTheme(String name) {
		setName(name);
	}


	/**
	 * 
	 * @return      name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param      name        String
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean isActive() {
		return this.active;
	}
	
	public void setActive() {
		this.active = true;
	}

	public void setInActive() {
		this.active = false;
	}
	
	public Set<ThemeProperty> getProperties() {
		return properties;
	}

	public void setProperties(Set<ThemeProperty> properties) {
		this.properties = properties;
	}
	
	public void addProperty(ThemeProperty property) {
		properties.add(property);
	}
	
	public void removeProperty(ThemeProperty property) {
		properties.remove(property);
	}
	
	public void clearProperties() {
		properties = new HashSet<ThemeProperty>();
	}
}
