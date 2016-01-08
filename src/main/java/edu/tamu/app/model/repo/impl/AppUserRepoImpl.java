package edu.tamu.app.model.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.app.model.AppUser;
import edu.tamu.app.model.repo.AppUserRepo;
import edu.tamu.app.model.repo.AppUserRepoCustom;

public class AppUserRepoImpl implements AppUserRepoCustom {
	
	@Autowired
	private AppUserRepo appUserRepo;

	@Override
	public AppUser create(Long uin) {
		AppUser user = appUserRepo.getUserByUin(uin);
		if(user == null) {
			return appUserRepo.save(new AppUser(uin));
		}
		return user;
	}

}
