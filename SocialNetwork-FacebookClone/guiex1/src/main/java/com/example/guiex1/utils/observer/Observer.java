package com.example.guiex1.utils.observer;


import com.example.guiex1.utils.events.Event;

import java.sql.SQLException;

public interface Observer<E extends Event> {
    void update(E e) throws SQLException;
}