package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "School")
public class School {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "school_number")
    private int schoolNumber;
    @OneToOne
    @JoinColumn(name = "principal_id", referencedColumnName = "id")
    private Principal owner;

    public School() {
    }

    public School(int schoolNumber, Principal owner) {
        this.schoolNumber = schoolNumber;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public Principal getOwner() {
        return owner;
    }

    public void setOwner(Principal owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolNumber=" + schoolNumber +
                ", owner=" + owner +
                '}';
    }
}
