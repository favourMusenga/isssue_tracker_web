package com.favourmusenga.issuetracker.appuser;


public class AppUserLoginRequest {
    private String email;
    private String password;

    public AppUserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{ email: " + this.email + ", password: " + this.password + " }";
    }
}
