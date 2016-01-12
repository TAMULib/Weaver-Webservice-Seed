/* 
 * AppUserRepoCustom.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo;

import edu.tamu.app.model.AppUser;

/**
 * 
 */
public interface AppUserRepoCustom {

	/**
	 * method to create user based on uin
	 * 
	 * @param uin
	 *            Long
	 */
	public AppUser create(Long uin);

}
