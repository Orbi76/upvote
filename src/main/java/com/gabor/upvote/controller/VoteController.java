package com.gabor.upvote.controller;

import com.gabor.upvote.model.Vote;
import com.gabor.upvote.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/votes")
@Tag(name = "Votes", description = "Operations related to voting")
public class VoteController {

    /*private final List<Vote> votes = new ArrayList<>();

     */
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    @Operation(summary = "Get all votes", description = "Returns a list of all votes")
    //public List<Vote> getAllVotes() {
    public ResponseEntity<List<Vote>> getAllVotes() {

        //return votes;
        //return voteService.getAllVotes();
        return ResponseEntity.ok(voteService.getAllVotes());
    }

    @PostMapping
    @Operation(summary = "Create a vote", description = "Adds a new vote")
    //public Vote createVote(@RequestBody Vote vote) {
    public  ResponseEntity<Vote> createVote(@RequestBody Vote vote) {
        //votes.add(vote);
        //return vote;
        //return voteService.createVote(vote);
        return ResponseEntity.ok(voteService.createVote(vote));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a vote by ID", description = "Removes a specific vote (admin only)")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}
