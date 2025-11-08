package com.gabor.upvote.service;

        import com.gabor.upvote.model.Vote;
        import com.gabor.upvote.repository.VoteRepository;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.annotation.DirtiesContext;

        import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void testCreateVote() {
        // Arrange
        Vote vote = new Vote("John Tester", "Alice");

        // Act
        Vote savedVote = voteService.createVote(vote);

        // Assert
        Assertions.assertNotNull(savedVote.getId(), "The saved vote should have an ID");
        Assertions.assertEquals("John Tester", savedVote.getVoter());
        Assertions.assertEquals("Alice", savedVote.getCandidate());
    }

    @Test
    void testGetAllVotes() {
        // Arrange
        voteService.createVote(new Vote("User1", "CandidateA"));
        voteService.createVote(new Vote("User2", "CandidateB"));

        // Act
        List<Vote> votes = voteService.getAllVotes();

        // Assert
        Assertions.assertEquals(2, votes.size(), "There should be two votes in the database");
    }
}
