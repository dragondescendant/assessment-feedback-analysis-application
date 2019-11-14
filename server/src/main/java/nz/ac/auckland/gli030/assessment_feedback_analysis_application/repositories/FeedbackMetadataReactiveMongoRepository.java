package nz.ac.auckland.gli030.assessment_feedback_analysis_application.repositories;

import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.FeedbackMetadata;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FeedbackMetadataReactiveMongoRepository extends ReactiveMongoRepository<FeedbackMetadata, Long> {
}
