package com.t2404e.wellcometojavaweb.entity;

public class Account {
    private long id;
    private String username;
    private String passwordHash;
    private int status;

    // ctor không id (dùng khi tạo mới)
    public Account() {
    }

    public Account(long id, String username, int status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

    // ctor đầy đủ (dùng khi đọc từ DB)
    public Account(Long id, String username, String passwordHash, int status) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.status = status;
    }

    public Account(String username, String passwordHash, int status) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    @Override
    public String toString() {
        return "Account{id=" + id + ", username='" + username + "', status=" + status + "}";
    }
}
