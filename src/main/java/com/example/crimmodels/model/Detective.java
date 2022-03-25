package com.example.crimmodels.model;


import com.example.crimmodels.util.EmployeeStatus;
import com.example.crimmodels.util.Rank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@SequenceGenerator(name = "seqDetectiveGen", allocationSize = 1)
public class Detective extends AbstractEntity{

    @OneToOne
    @NotNull
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String badgeNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Rank rank;


    private Boolean armed = false;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "working_detective_case",
            joinColumns = @JoinColumn(name = "detective_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id"))
    private Set<CriminalCase> criminalCases = new HashSet<>();

    @OneToMany(mappedBy = "detective")
    private Set<TrackEntry> trackEntries;

    public Detective(){
        super();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean getArmed() {
        return armed;
    }

    public void setArmed(Boolean armed) {
        this.armed = armed;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public Set<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    public void setCriminalCases(Set<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
    }

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    public void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }
    public boolean addTrackEntry(TrackEntry trackEntry){
        trackEntry.setDetective(this);
        return trackEntries.add(trackEntry);
    }
    boolean addCase(CriminalCase criminalCase){
        return criminalCases.add(criminalCase);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detective detective = (Detective) o;
        return Objects.equals(person, detective.person) && Objects.equals(badgeNumber, detective.badgeNumber) && rank == detective.rank && Objects.equals(armed, detective.armed) && status == detective.status && Objects.equals(criminalCases, detective.criminalCases) && Objects.equals(trackEntries, detective.trackEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, badgeNumber, rank, armed, status, criminalCases, trackEntries);
    }
}
