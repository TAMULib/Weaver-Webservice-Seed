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

import edu.tamu.app.model.ThemeProperty;
import edu.tamu.app.model.ThemePropertyName;
import edu.tamu.app.model.custom.ThemePropertyRepoCustom;


/**
 * 
 * 
 * @author
 *
 */
@Repository
public interface ThemePropertyRepo extends JpaRepository<ThemeProperty, Long>, ThemePropertyRepoCustom {
	
	public ThemeProperty create(ThemePropertyName propertyName, String name);
	
	public ThemeProperty update(ThemeProperty themeProperty);
	
	@Override
	public void delete(ThemeProperty themeProperty);
	
	@Override
	public void deleteAll();
	
	
}
