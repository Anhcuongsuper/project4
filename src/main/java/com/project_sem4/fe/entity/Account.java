package com.project_sem4.fe.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Account {
    @Id
    @Email
    @NotEmpty(message = "Can not empty")
    private String email;
    @NotEmpty(message = "Can not empty")
    private String password;
    @NotEmpty(message = "Can not empty")

    private String fullname;
    @NotEmpty(message = "Can not empty")

    private String address;
    @NotEmpty(message = "Can not empty")

    private String role;
    @NotEmpty(message = "Can not empty")

    public enum Role {
        ADMIN("admin"), USER("user");

        private String value;

        Role(String user) {
            this.value = user;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
