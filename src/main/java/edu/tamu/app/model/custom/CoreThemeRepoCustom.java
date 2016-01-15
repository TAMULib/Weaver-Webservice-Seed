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

import edu.tamu.app.model.CoreTheme;


/**
 * 
 * 
 * @author
 *
 */
public interface CoreThemeRepoCustom {

	public CoreTheme create(String name);
	
	public CoreTheme update(CoreTheme theme);
	
	public void delete(CoreTheme theme);
	
	public void deleteAll();
	
}
