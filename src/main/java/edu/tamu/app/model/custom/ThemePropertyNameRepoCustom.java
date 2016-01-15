/* 
 * CustomDocumentRepo.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.custom;

import edu.tamu.app.model.ThemePropertyName;

/**
 * 
 * 
 * @author
 *
 */
public interface ThemePropertyNameRepoCustom {

	public ThemePropertyName create(String name);
	
	public ThemePropertyName update(ThemePropertyName propertyName);
	
	public void delete(ThemePropertyName propertyName);
	
	public void deleteAll();
	
}
