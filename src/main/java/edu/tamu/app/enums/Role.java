package edu.tamu.app.enums;

import edu.tamu.weaver.user.model.IRole;

public enum Role implements IRole {

    ROLE_ANONYMOUS(0), 
    ROLE_USER(1), 
    ROLE_MANAGER(2), 
    ROLE_ADMIN(3);

    private int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name();
    }

}