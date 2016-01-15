/* 
 * DocumentRepoImpl.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.app.model.ThemeProperty;
import edu.tamu.app.model.ThemePropertyName;
import edu.tamu.app.model.repo.ThemePropertyRepo;
import edu.tamu.app.model.custom.ThemePropertyRepoCustom;


/**
*
* 
* @author
*
*/
public class ThemePropertyRepoImpl implements ThemePropertyRepoCustom {
	
	@Autowired
	private ThemePropertyRepo propertyRepo;
	

	@Override
	public ThemeProperty create(ThemePropertyName propertyName, String value) {
		return propertyRepo.create(propertyName,value);
	}

	@Override
	public ThemeProperty update(ThemeProperty themeProperty) {
		return propertyRepo.save(themeProperty);
	}

	@Override
	public void delete(ThemeProperty themeProperty) {
		propertyRepo.delete(themeProperty);
	}

	@Override
	public void deleteAll() {
		propertyRepo.deleteAll();
	}
	
}
