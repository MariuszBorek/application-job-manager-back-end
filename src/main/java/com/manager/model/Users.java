package com.manager.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Users {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Project> projects;

    public Users() {
    }

    public Users(String name,
                 String surname,
                 String email,
                 String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(projects, user.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, projects);
    }
}
