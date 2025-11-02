package com.gabor.upvote.controller;

import com.gabor.upvote.model.Vote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/votes")
@Tag(name = "Votes", description = "Operations related to voting")
public class VoteController {

    private final List<Vote> votes = new ArrayList<>();

    @GetMapping
    @Operation(summary = "Get all votes", description = "Returns a list of all votes")
    public List<Vote> getAllVotes() {
        return votes;
    }

    @PostMapping
    @Operation(summary = "Create a vote", description = "Adds a new vote")
    public Vote createVote(@RequestBody Vote vote) {
        votes.add(vote);
        return vote;
    }
}
