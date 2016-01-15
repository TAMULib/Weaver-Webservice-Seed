/* 
 * ThemePropertyNameRepoImpl.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.app.model.ThemePropertyName;
import edu.tamu.app.model.repo.ThemePropertyNameRepo;
import edu.tamu.app.model.custom.ThemePropertyNameRepoCustom;


/**
*
* 
* @author
*
*/
public class ThemePropertyNameRepoImpl implements ThemePropertyNameRepoCustom {
	
	@Autowired
	private ThemePropertyNameRepo propertyNameRepo;
	

	@Override
	public ThemePropertyName create(String name) {
		return propertyNameRepo.create(name);
	}

	@Override
	public ThemePropertyName update(ThemePropertyName propertyName) {
		return propertyNameRepo.save(propertyName);
	}

	@Override
	public void delete(ThemePropertyName propertyName) {
		propertyNameRepo.delete(propertyName);
	}

	@Override
	public void deleteAll() {
		propertyNameRepo.deleteAll();
	}
	
	public long count() {
		return propertyNameRepo.count();
	}
}
