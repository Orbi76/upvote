package com.gabor.upvote.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "votes")
@Schema(description = "Vote entity representing a user vote")
public class Vote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID of the vote", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Name of the voter", example = "John Doe")
    private String voter;

    @NotBlank
    @Schema(description = "Candidate voted for", example = "Alice")
    private String candidate;


    public Vote() {}

    public Vote(String voter, String candidate){
        this.voter = voter;
        this.candidate = candidate;
    }

    // Getters és Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVoter() { return voter; }
    public void setVoter(String voter) { this.voter = voter; }
    public String getCandidate() { return candidate; }
    public void setCandidate(String candidate) { this.candidate = candidate; }
}
