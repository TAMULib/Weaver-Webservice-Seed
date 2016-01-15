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

import edu.tamu.app.model.CoreTheme;
import edu.tamu.app.model.repo.CoreThemeRepo;
import edu.tamu.app.model.custom.CoreThemeRepoCustom;


/**
*
* 
* @author
*
*/
public class CoreThemeRepoImpl implements CoreThemeRepoCustom {
	
	@Autowired
	private CoreThemeRepo themeRepo;
	

	@Override
	public CoreTheme create(String name) {
		return themeRepo.create(name);
	}

	@Override
	public CoreTheme update(CoreTheme theme) {
		return themeRepo.save(theme);
	}

	@Override
	public void delete(CoreTheme theme) {
		themeRepo.delete(theme);
	}

	@Override
	public void deleteAll() {
		themeRepo.deleteAll();
	}
	
	public void updateActiveTheme(CoreTheme theme) {
		CoreTheme activeTheme = themeRepo.findByActiveTrue();
		if (activeTheme != null) {
			activeTheme.setInActive();
			themeRepo.save(activeTheme);
		}
		theme.setActive();
		themeRepo.save(theme);
	}
	
	public long count() {
		return themeRepo.count();
	}

}
