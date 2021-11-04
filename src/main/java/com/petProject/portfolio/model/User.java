package com.petProject.portfolio.model;

import java.util.Objects;

public class User {

    private String name;
    private String password;
    private Portfolio userPortfolio;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Portfolio getUserPortfolio() {
        return userPortfolio;
    }

    public void setUserPortfolio(Portfolio userPortfolio) {
        this.userPortfolio = userPortfolio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User userDAO = (User) o;
        return Objects.equals(name, userDAO.name) &&
                Objects.equals(password, userDAO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
