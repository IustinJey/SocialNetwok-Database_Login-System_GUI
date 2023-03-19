package com.example.guiex1.repository.dbrepo;

import com.example.guiex1.domain.User;
import com.example.guiex1.domain.Validator;
import com.example.guiex1.repository.Repository;


import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserDbRepository implements Repository<Long, User> {
    private String url;
    private String username;
    private String password;
    private Validator<User> validator;

    public UserDbRepository(String url, String username, String password, Validator<User> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }
    @Override
    public User findOne(User userS) throws SQLException {
        Iterable<User> allUsers = this.findAll();
        List<User> usersList = StreamSupport.stream(allUsers.spliterator(), false)
                .collect(Collectors.toList());
        for(User u:usersList)
        {
            if(Objects.equals(u.getId(),userS.getId())) return u;
        }
        return null;
    }

    public User findByEmail(String email) throws SQLException {
        Iterable<User> allUsers = this.findAll();
        List<User> usersList = StreamSupport.stream(allUsers.spliterator(), false)
                .collect(Collectors.toList());
        for(User u:usersList)
        {
            if(Objects.equals(email,u.getEmail())) return u;
        }
        return null;
    }


    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("passw");

                User utilizator = new User(email, firstName, lastName, password);
                utilizator.setId(id);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> save(User entity) {
        String sql = "insert into users (email, firstname, lastname, passw) values (?,?,?,?)";
        validator.validate(entity);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, entity.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            return Optional.ofNullable(entity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long aLong) {
        String sql = "delete from users where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, aLong);
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(User entity) {
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(String email) {
        System.out.println("repooo");
        String sql = "delete from users where email = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User entity) {
        System.out.println("repooo");
        String sql = "update users set firstname = ?, lastname = ? where email = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return Optional.empty();
    }
}
