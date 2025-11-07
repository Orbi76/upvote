
package com.gabor.upvote.controller;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.gabor.upvote.model.Vote;
        import com.gabor.upvote.service.VoteService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mockito;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.setup.MockMvcBuilders;

        import java.util.List;

        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.when;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VoteControllerTest {

    private MockMvc mockMvc;
    private VoteService voteService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        voteService = Mockito.mock(VoteService.class);
        VoteController voteController = new VoteController(voteService);
        mockMvc = MockMvcBuilders.standaloneSetup(voteController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllVotes() throws Exception {
        List<Vote> votes = List.of(
                new Vote("John", "Alice"),
                new Vote("Jane", "Bob")
        );

        when(voteService.getAllVotes()).thenReturn(votes);

        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].voter").value("John"))
                .andExpect(jsonPath("$[1].candidate").value("Bob"));
    }

    @Test
    void testCreateVote() throws Exception {
        Vote vote = new Vote("John Doe", "Alice");

        when(voteService.createVote(any(Vote.class))).thenReturn(vote);

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.voter").value("John Doe"))
                .andExpect(jsonPath("$.candidate").value("Alice"));
    }
}
