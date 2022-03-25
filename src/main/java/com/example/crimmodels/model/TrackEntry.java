package com.example.crimmodels.model;


import com.example.crimmodels.util.DateProcessor;
import com.example.crimmodels.util.TrackAction;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "seqTrackEntryGen", allocationSize = 1)
@Table(name = "TRACK_ENTRY")
public class TrackEntry extends AbstractEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateProcessor.DATE_FORMAT)
    @NotNull
    @Column(name = "track_date", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDateTime date;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "evidence_fk", nullable = false)
    private Evidence evidence;

    @ManyToOne
    @JoinColumn(name = "detective_fk", nullable = false)
    private Detective detective;

    @Enumerated
    @NotNull
    private TrackAction trackAction;

    @NotEmpty
    @Column
    private String reason;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public Detective getDetective() {
        return detective;
    }

    public void setDetective(Detective detective) {
        this.detective = detective;
    }

    public TrackAction getTrackAction() {
        return trackAction;
    }

    public void setTrackAction(TrackAction trackAction) {
        this.trackAction = trackAction;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
