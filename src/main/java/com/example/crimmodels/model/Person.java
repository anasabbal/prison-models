package com.example.crimmodels.model;




import com.example.crimmodels.util.DateProcessor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "seqPersonGen", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = Person.FIND_BY_COMPLETE_NAME,
                query = "SELECT p from Person p where p.firstName=:fn and p.lastName=:ln"),
        @NamedQuery(name = Person.FIND_BY_LAST_NAME,
                query = "SELECT p FROM Person p WHERE p.lastName = ?1")
})
public class Person extends AbstractEntity{
    public static final String FIND_BY_COMPLETE_NAME = "findByCompleteName";
    public static final String FIND_BY_LAST_NAME = "findAllByLastName";

    public interface BasicValidation{}

    @NotNull(groups = BasicValidation.class)
    @Size(min = 3, max = 30, groups = BasicValidation.class)
    @Column(unique = true, nullable = false)
    private String userName;

    @NotNull(groups = BasicValidation.class)
    @Size(min = 3, max = 30, groups = BasicValidation.class)
    @Column(unique = true, nullable = false)
    private String firstName;

    @NotNull(groups = BasicValidation.class)
    @Size(min = 3, max = 30, groups = BasicValidation.class)
    @Column(unique = true, nullable = false)
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 4, max = 50)
    @Column(nullable = false)
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateProcessor.DATE_FORMAT)
    @NotNull(groups = BasicValidation.class)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    @Column(nullable = false)
    private LocalDateTime hiringDate;

    @JsonIgnore
    @Transient
    private String newPassword;

    public Person(){
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(hiringDate.toLocalDate(), person.hiringDate.toLocalDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, hiringDate.toLocalDate());
    }
    @Override
    public String toString() {
        return String.format("Person[username='%s', firstName='%s', lastName='%s', hiringDate='%s']\n",
                userName, firstName, lastName, hiringDate == null? "" : hiringDate.toString());
    }
}
