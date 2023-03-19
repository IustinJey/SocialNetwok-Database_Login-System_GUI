package com.example.guiex1.domain;

import java.time.LocalDateTime;


public class Friendship extends Entity<Long> {
    private String user1;
    private String user2;
    private Boolean status;
    private
    LocalDateTime date;
    public Friendship(String user1, String user2, Boolean status, LocalDateTime date) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
      this.date = date;
    }
    public Boolean getStatus(){
        return status;
    }
    public String getUser2() {
        return user2;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friendship)) return false;
        Friendship that = (Friendship) o;
        return getId().equals(that.getId());
    }

    /**
     *
     * @return the date when the friendship was created
     */
    public LocalDateTime getDate() {
        return date;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

