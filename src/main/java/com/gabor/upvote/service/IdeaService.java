package com.gabor.upvote.service;

        import com.gabor.upvote.model.Idea;
        import com.gabor.upvote.model.VoteRecord;
        import com.gabor.upvote.repository.IdeaRepository;
        import com.gabor.upvote.repository.VoteRecordRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.NoSuchElementException;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final VoteRecordRepository voteRecordRepository;

    public IdeaService(IdeaRepository ideaRepository, VoteRecordRepository voteRecordRepository) {
        this.ideaRepository = ideaRepository;
        this.voteRecordRepository = voteRecordRepository;
    }

    public Idea submitIdea(Idea idea) {
        idea.setApproved(false);
        idea.setVotes(0);
        return ideaRepository.save(idea);
    }

    public List<Idea> listApprovedIdeas() {
        return ideaRepository.findByApprovedTrueOrderByVotesDescCreatedAtDesc();
    }

    public List<Idea> listPendingIdeas() {
        return ideaRepository.findByApprovedFalseOrderByCreatedAtDesc();
    }

    @Transactional
    public Idea approveIdea(Long ideaId) {
        Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> new NoSuchElementException("Idea not found"));
        idea.setApproved(true);
        return ideaRepository.save(idea);
    }

    @Transactional
    public void rejectIdea(Long ideaId) {
        ideaRepository.deleteById(ideaId);
    }

    @Transactional
    public Idea vote(Long ideaId, String sessionId) {
        voteRecordRepository.findByIdeaIdAndSessionId(ideaId, sessionId).ifPresent(v -> {
            throw new IllegalStateException("This session already voted for this idea");
        });

        Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> new NoSuchElementException("Idea not found"));

        VoteRecord record = new VoteRecord(ideaId, sessionId);
        voteRecordRepository.save(record);

        idea.setVotes(idea.getVotes() + 1);
        return ideaRepository.save(idea);
    }

    public long votesForIdea(Long ideaId) {
        return voteRecordRepository.countByIdeaId(ideaId);
    }
}
