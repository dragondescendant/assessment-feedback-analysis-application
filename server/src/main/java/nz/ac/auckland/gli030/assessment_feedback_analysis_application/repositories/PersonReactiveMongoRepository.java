package nz.ac.auckland.gli030.assessment_feedback_analysis_application.repositories;

import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonReactiveMongoRepository extends ReactiveMongoRepository<Person, String> {
}
