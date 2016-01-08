/* 
 * AppStompInterceptor.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.controller.interceptor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.tamu.app.model.AppUser;
import edu.tamu.app.model.repo.AppUserRepo;
import edu.tamu.framework.interceptor.CoreStompInterceptor;
import edu.tamu.framework.model.Credentials;

/**
 * Application Stomp interceptor. Checks command, decodes and verifies token,
 * either returns error message to frontend or continues to controller.
 * 
 * @author
 *
 */
@Component
public class AppStompInterceptor extends CoreStompInterceptor {

	@Autowired
	private AppUserRepo userRepo;

	@Value("${app.authority.admins}")
	private String[] admins;

	private static final Logger logger = Logger.getLogger(AppStompInterceptor.class);

	/**
	 * @see edu.tamu.framework.interceptor.CoreStompInterceptor#confirmCreateUser(edu.tamu.framework.model.Credentials)
	 * 
	 * @param shib
	 *            Credentials
	 * 
	 * @return shib
	 * 
	 */
	@Override
	public Credentials confirmCreateUser(Credentials shib) {

		AppUser user = userRepo.getUserByUin(Long.parseLong(shib.getUin()));

		String shibUin = shib.getUin();

		if (user == null) {

			if (shib.getRole() == null) {
				shib.setRole("ROLE_USER");
			}

			for (String uin : admins) {
				if (uin.equals(shibUin)) {
					shib.setRole("ROLE_ADMIN");
				}
			}

			user = new AppUser();

			user.setUin(Long.parseLong(shib.getUin()));
			user.setRole(shib.getRole());

			user.setFirstName(shib.getFirstName());
			user.setLastName(shib.getLastName());

			user = userRepo.save(user);

			logger.info("Created new user: " + shib.getFirstName() + " " + shib.getLastName() + " ("
					+ Long.parseLong(shib.getUin()) + ")");

		}

		shib.setRole(user.getRole());

		return shib;
	}

}
