package net.larichan.nlw_connect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "tbl_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(max = 255)
    @Column(name = "user_name")
    private String userName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 255)
    @Column(name = "user_email", unique = true)
    private String userEmail;
}
