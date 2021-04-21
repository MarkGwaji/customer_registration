package com.legal8.customer_registration.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, message = "First name should have at least 2 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last name should have at least 2 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Please enter date if birth (dd-MM-yyyy)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Email
    //@UniqueElements(message = "Email already exists")
    @NotBlank
    @Size(max = 100)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    //@UniqueElements(message = "Phone number already exists")
    @Size(min=10, max=10, message = "Phone number must be 10 digits")
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @NotBlank(message = "Please enter country")
    @Column(name = "country", nullable = false)
    private String country;

    @NotBlank(message = "Please enter currency")
    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "status", nullable = false)
    private String status = "Active";

    @Column(name = "date_created", nullable = false)
    private ZonedDateTime dateCreated = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Africa/Johannesburg"));

    @Column(name = "date_deactivated")
    private ZonedDateTime dateDeactivated = null;

    public User(String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber, String address, String country, String currency) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.currency = currency;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ZonedDateTime getDateDeactivated() {
        return dateDeactivated;
    }

    public void setDateDeactivated(ZonedDateTime dateDeactivated) {
        this.dateDeactivated = dateDeactivated;
    }

}
