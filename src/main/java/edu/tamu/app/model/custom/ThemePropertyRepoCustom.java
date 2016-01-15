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

import edu.tamu.app.model.ThemeProperty;
import edu.tamu.app.model.ThemePropertyName;

/**
 * 
 * 
 * @author
 *
 */
public interface ThemePropertyRepoCustom {

	public ThemeProperty create(ThemePropertyName propertyName, String name);
	
	public ThemeProperty update(ThemeProperty themeProperty);
	
	public void delete(ThemeProperty themeProperty);
	
	public void deleteAll();
	
}
