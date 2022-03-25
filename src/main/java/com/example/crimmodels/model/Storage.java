package com.example.crimmodels.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "seqStorageGen", allocationSize = 1)
public class Storage extends AbstractEntity{

    @NotNull
    @Size(min = 8, max = 30)
    private String name;

    @NotNull
    @Size(min = 8, max = 150)
    private String location;

    @OneToMany(mappedBy = "storage")
    private Set<Evidence> evidenceSet = new HashSet<>();

    public Storage(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }
}
