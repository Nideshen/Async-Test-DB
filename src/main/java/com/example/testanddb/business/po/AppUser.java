package com.example.testanddb.business.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "APP_USER", schema = "mydemodb")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @ColumnDefault("1")
    @Column(name = "STATUS", nullable = false)
    private Boolean status = true;

    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public AppUser() {

    }
}