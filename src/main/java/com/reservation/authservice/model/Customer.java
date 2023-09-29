package com.reservation.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id", nullable = false)
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "password", nullable = false, length = 512)
    private String password;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "age")
    private Integer age;

    @JsonIgnore
    @Column(name = "is_deleted")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isDeleted = false;
}
