package com.example.guiex1.repository.dbrepo;

import com.example.guiex1.domain.Friendship;
import com.example.guiex1.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FriendshipDbRepository implements Repository<Long, Friendship> {
    private String url;
    private String username;
    private String password;

    public FriendshipDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Friendship findByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public Friendship findOne(Friendship f) throws SQLException {
        Iterable<Friendship> friendships = this.findAll();
        List<Friendship> friendshipsList = StreamSupport.stream(friendships.spliterator(), false)
                .collect(Collectors.toList());
        for(Friendship u:friendshipsList)
        {
            if((Objects.equals(u.getUser1(),f.getUser1()) && Objects.equals(u.getUser2(),f.getUser2()) || (Objects.equals(u.getUser2(),f.getUser1()) && Objects.equals(u.getUser1(),f.getUser2())))) return u;
        }
        return null;
    }

    @Override
    public Iterable<Friendship> findAll() {
        Set<Friendship> friendships = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String user1 =  resultSet.getString("user1");
                String user2 =  resultSet.getString("user2");
                Boolean status = resultSet.getBoolean("status");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();

                Friendship friendship = new Friendship(user1, user2, status, date);
                friendship.setId(id);
                friendships.add(friendship);
            }
            return friendships;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Optional<Friendship> save(Friendship entity) {
        System.out.println("Repo account");
        String sql = "insert into friendships (user1, user2, status, date) values (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getUser1());
            ps.setString(2, entity.getUser2());
            ps.setBoolean(3, entity.getStatus());
            ps.setTimestamp(4, Timestamp.valueOf(entity.getDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            return Optional.ofNullable(entity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Friendship> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Friendship> delete(Friendship entity) {
        System.out.println(" ");
        System.out.println(entity.getUser1());
        System.out.println(entity.getUser2());
        String sql = "delete from friendships where user1 = ? and user2 = ?; delete from friendships where user2 = ? and user1 = ? ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getUser1());
            ps.setString(2, entity.getUser2());
            ps.setString(3, entity.getUser1());
            ps.setString(4, entity.getUser2());
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Friendship> delete(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Friendship> update(Friendship entity) {
        String sql = "update friendships set status = ?, date = ? where user1 = ? and user2 = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, entity.getStatus());
            ps.setString(3, entity.getUser1());
            ps.setString(4, entity.getUser2());
            ps.setTimestamp(2, Timestamp.valueOf(entity.getDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            return Optional.ofNullable(entity);
        }
        return Optional.empty();
    }
}
