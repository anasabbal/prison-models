package com.example.crimmodels.model;


import com.example.crimmodels.util.CaseStatus;
import com.example.crimmodels.util.CaseType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "seqCriminalCaseGen", allocationSize = 1)
public class CriminalCase extends AbstractEntity{

    @NotEmpty
    @Column(name = "case_number", unique = true, nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "case_type")
    private CaseType caseType;

    @NotEmpty
    @Column(name = "sort_description")
    private String shortDescription;


    private String detailDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;


    private String notes;

    @OneToMany(mappedBy = "criminalCase", cascade = CascadeType.PERSIST)
    private Set<Evidence> evidenceSet;

    @ManyToOne
    @JoinColumn(name = "LEAD_INVESTIGATION", nullable = false)
    private Detective leadInvestigator;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "working_detective_case", joinColumns = @JoinColumn(
            name = "case_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "detective_id",
                    referencedColumnName = "id"))
    private Set<Detective> assigned = new HashSet<>();

    public CriminalCase(){
        super();
        this.caseStatus = CaseStatus.SUBMITTED;
        this.caseType = CaseType.UNCATEGORIZED;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CaseType getCaseType() {
        return caseType;
    }

    public void setCaseType(CaseType caseType) {
        this.caseType = caseType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(CaseStatus caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }

    public Detective getLeadInvestigator() {
        return leadInvestigator;
    }

    public void setLeadInvestigator(Detective leadInvestigator) {
        this.leadInvestigator = leadInvestigator;
    }

    public Set<Detective> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Detective> assigned) {
        this.assigned = assigned;
    }
    public void addDetective(Detective detective){
        detective.addCase(this);
        
    }
}
