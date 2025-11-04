package com.gabor.upvote.service;

        import com.gabor.upvote.model.Vote;
        import com.gabor.upvote.repository.VoteRepository;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }
    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }
}
