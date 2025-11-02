package com.gabor.upvote.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vote entity representing a user vote")
public class Vote {

    @Schema(description = "ID of the vote", example = "1")
    private Long id;

    @Schema(description = "Name of the voter", example = "John Doe")
    private String voter;

    @Schema(description = "Candidate voted for", example = "Alice")
    private String candidate;

    // Getters és Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVoter() { return voter; }
    public void setVoter(String voter) { this.voter = voter; }
    public String getCandidate() { return candidate; }
    public void setCandidate(String candidate) { this.candidate = candidate; }
}
