package com.example.guiex1.services;



import com.example.guiex1.domain.User;
import com.example.guiex1.repository.Repository;
import com.example.guiex1.utils.events.ChangeEventType;
import com.example.guiex1.utils.events.UserEntityChangeEvent;
import com.example.guiex1.utils.observer.Observable;
import com.example.guiex1.utils.observer.Observer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements Observable<UserEntityChangeEvent> {
    private Repository<Long, User> repo;
    private List<Observer<UserEntityChangeEvent>> observers=new ArrayList<>();

    public UserService(Repository<Long, User> repo) {
        this.repo = repo;
    }


    public User addUtilizator(User user) {
        System.out.println("Service account");
        System.out.println(repo);
        repo.save(user);
        if(repo.save(user).isEmpty()){
            UserEntityChangeEvent event = new UserEntityChangeEvent(ChangeEventType.ADD, user);
            notifyObservers(event);
            return null;
        }
        return user;
    }

    public User updateUtilizator(User user) {
        System.out.println(user);
        repo.update(user);
        return user;
    }

    public User deleteUtilizator(Long id){
        Optional<User> user=repo.delete(id);
        if (user.isPresent()) {
            notifyObservers(new UserEntityChangeEvent(ChangeEventType.DELETE, user.get()));
            return user.get();}
        return null;
    }

    public User deleteUser(String email)
    {
        repo.delete(email);
        return null;
    }

    public Iterable<User> getAll(){
        return repo.findAll();
    }

    public User getOne(Long id) throws SQLException {
        User user = new User("test", "test", "test", "test");
        user.setId(id);
        return repo.findOne(user);
    }

    public User getOne(String email) throws SQLException {
        return repo.findByEmail(email);
    }

    @Override
    public void addObserver(Observer<UserEntityChangeEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<UserEntityChangeEvent> e) {
        //observers.remove(e);
    }

    @Override
    public void notifyObservers(UserEntityChangeEvent t) {

        observers.stream().forEach(x-> {
            try {
                x.update(t);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
