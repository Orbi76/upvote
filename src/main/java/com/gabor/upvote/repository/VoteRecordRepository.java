package com.gabor.upvote.repository;

        import com.gabor.upvote.model.VoteRecord;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
    Optional<VoteRecord> findByIdeaIdAndSessionId(Long ideaId, String sessionId);
    long countByIdeaId(Long ideaId);
}

