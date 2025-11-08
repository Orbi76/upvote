
package com.gabor.upvote.service;

        import com.gabor.upvote.model.Vote;
        import com.gabor.upvote.repository.VoteRepository;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mockito;

        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class VoteServiceTestB {

    private VoteRepository voteRepository;
    private VoteService voteService;

    @BeforeEach
    void setUp() {
        voteRepository = Mockito.mock(VoteRepository.class);
        voteService = new VoteService(voteRepository);
    }

    @Test
    void testCreateVote() {
        Vote vote = new Vote("John Doe", "Alice");

        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        Vote savedVote = voteService.createVote(vote);

        assertNotNull(savedVote);
        assertEquals("John Doe", savedVote.getVoter());
        assertEquals("Alice", savedVote.getCandidate());
        verify(voteRepository, times(1)).save(any(Vote.class));
    }

    @Test
    void testGetAllVotes() {
        List<Vote> mockVotes = List.of(
                new Vote("John", "Alice"),
                new Vote("Jane", "Bob")
        );

        when(voteRepository.findAll()).thenReturn(mockVotes);

        List<Vote> votes = voteService.getAllVotes();

        assertEquals(2, votes.size());
        assertEquals("Jane", votes.get(1).getVoter());
        verify(voteRepository, times(1)).findAll();
    }
}
