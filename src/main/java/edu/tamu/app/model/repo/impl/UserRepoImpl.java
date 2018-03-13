/* 
 * AppUserRepoImpl.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.app.model.User;
import edu.tamu.app.model.repo.UserRepo;
import edu.tamu.app.model.repo.UserRepoCustom;
import edu.tamu.weaver.data.model.repo.impl.AbstractWeaverRepoImpl;

/**
 * 
 */
public class UserRepoImpl extends AbstractWeaverRepoImpl<User, UserRepo> implements UserRepoCustom {

    @Autowired
    private UserRepo userRepo;

    /**
     * Creates application user in the user repository
     * 
     * @param uin
     *            Long
     * 
     * @see edu.tamu.app.model.repo.custom.UserRepoCustom#create(java.lang.Long)
     */
    @Override
    public synchronized User create(String uin) {
        Optional<User> user = userRepo.findByUsername(uin);
        return user.isPresent() ? user.get() : userRepo.save(new User(uin));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized User create(String uin, String firstName, String lastName, String role) {
        Optional<User> user = userRepo.findByUsername(uin);
        return user.isPresent() ? user.get() : userRepo.save(new User(uin, firstName, lastName, role));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected String getChannel() {
        return "/channel/user";
    }

}
