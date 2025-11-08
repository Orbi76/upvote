package com.gabor.upvote.controller;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.gabor.upvote.model.Vote;
        import com.gabor.upvote.repository.VoteRepository;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.security.test.context.support.WithMockUser;
        import org.springframework.test.annotation.DirtiesContext;
        import org.springframework.test.web.servlet.MockMvc;

        import static org.hamcrest.Matchers.hasSize;
        import static org.hamcrest.Matchers.is;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = {"USER"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VoteRepository voteRepository;

    @BeforeEach
    void setup() {

        voteRepository.deleteAll();
        //voteRepository.save(new Vote("John Doe", "Alice"));
        //voteRepository.save(new Vote("Jane Smith", "Bob"));

    }

    @Test
    void testCreateVote() throws Exception {
        Vote vote = new Vote("TestUser", "CandidateA");

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.voter").value("TestUser"))
                .andExpect(jsonPath("$.candidate").value("CandidateA"));
    }

    @Test
    void testGetAllVotes() throws Exception {
        // Create a few votes
        Vote vote1 = new Vote("User1", "Alice");
        Vote vote2 = new Vote("User2", "Bob");

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote2)))
                .andExpect(status().isOk());

        // Retrieve all
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].voter").value("User1"))
                .andExpect(jsonPath("$[1].voter").value("User2"));
    }
}
