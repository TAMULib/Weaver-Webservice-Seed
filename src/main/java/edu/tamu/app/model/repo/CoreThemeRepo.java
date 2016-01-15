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

import edu.tamu.app.model.CoreTheme;
import edu.tamu.app.model.custom.CoreThemeRepoCustom;


/**
 * 
 * 
 * @author
 *
 */
@Repository
public interface CoreThemeRepo extends JpaRepository<CoreTheme, Long>, CoreThemeRepoCustom {
	
	public CoreTheme create(String name);
	
	public CoreTheme update(CoreTheme theme);
	
	@Override
	public void delete(CoreTheme theme);
	
	@Override
	public void deleteAll();
	
	/**
	 * Retrieve theme by name.
	 * 
	 * @param 		name				String
	 * 
	 * @return		CoreTheme
	 * 
	 */
	public CoreTheme findByName(String name);
	
	public CoreTheme findByActiveTrue();
	
	public long count();

	public void updateActiveTheme(CoreTheme theme);
	
}
