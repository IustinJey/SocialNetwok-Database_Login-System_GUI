package com.example.guiex1.services;



import com.example.guiex1.domain.Friendship;
import com.example.guiex1.repository.Repository;
import com.example.guiex1.utils.events.ChangeEventType;
import com.example.guiex1.utils.events.FriendshipEntityChangeEvent;
import com.example.guiex1.utils.observer.Observable;
import com.example.guiex1.utils.observer.Observer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendshipService implements Observable<FriendshipEntityChangeEvent> {
    private Repository<Long, Friendship> repo;
    private List<Observer<FriendshipEntityChangeEvent>> observers=new ArrayList<>();

    public FriendshipService(Repository<Long, Friendship> repo) {
        this.repo = repo;
    }


    public Friendship addFriendship(Friendship friendship) {
        System.out.println(friendship.getUser1());
        System.out.println(friendship.getUser2());
        if(repo.save(friendship).isEmpty()){
            FriendshipEntityChangeEvent event = new FriendshipEntityChangeEvent(ChangeEventType.ADD, friendship);
            notifyObservers(event);
            return null;
        }
        return friendship;
    }

    public Friendship updateFriendship(Friendship friendship) {
        System.out.println(friendship);
        repo.update(friendship);
        return friendship;
    }

    public Friendship deleteFriendship(String user1, String user2) throws SQLException {
        Friendship friendship = new Friendship(user1, user2, true, null);
        repo.delete(friendship);
        //friendship = repo.findOne(friendship);
        //Optional<Friendship> friendship1=repo.delete(friendship.getId());
        //if (friendship1.isPresent()) {
         //   notifyObservers(new FriendshipEntityChangeEvent(ChangeEventType.DELETE, friendship1.get()));
         //   return friendship1.get();}
         return null;
    }

    public Iterable<Friendship> getAll(){
        return repo.findAll();
    }

    public Friendship getOne(String user1, String user2) throws SQLException {
        Friendship friendship = new Friendship(user1, user2, false, null);
        return repo.findOne(friendship);
    }


    @Override
    public void addObserver(Observer<FriendshipEntityChangeEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<FriendshipEntityChangeEvent> e) {
        //observers.remove(e);
    }

    @Override
    public void notifyObservers(FriendshipEntityChangeEvent t) {

        observers.stream().forEach(x-> {
            try {
                x.update(t);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
