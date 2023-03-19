package com.example.guiex1.domain;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        //TODO: implement method validate
        if(entity.getFirstName() == null || entity.getLastName() == null)
            throw new ValidationException("Names cannot be null");
        if(entity.getPassword() == null)
            throw new ValidationException("Password cannot be null");
    }
}
