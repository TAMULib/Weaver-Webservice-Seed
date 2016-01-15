/* 
 * CoreThemeRepo.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.app.model.ThemePropertyName;
import edu.tamu.app.model.custom.ThemePropertyNameRepoCustom;


/**
 * 
 * 
 * @author
 *
 */
@Repository
public interface ThemePropertyNameRepo extends JpaRepository<ThemePropertyName, Long>, ThemePropertyNameRepoCustom {
	
	public edu.tamu.app.model.ThemePropertyName create(String name);
	
	public ThemePropertyName update(ThemePropertyName propertyName);
	
	@Override
	public void delete(ThemePropertyName propertyName);
	
	@Override
	public void deleteAll();
	
	/**
	 * Retrieve propertyName by name.
	 * 
	 * @param 		name				String
	 * 
	 * @return		ThemePropertyName
	 * 
	 */
	public ThemePropertyName findByName(String name);
	
	public long count();
	
}
