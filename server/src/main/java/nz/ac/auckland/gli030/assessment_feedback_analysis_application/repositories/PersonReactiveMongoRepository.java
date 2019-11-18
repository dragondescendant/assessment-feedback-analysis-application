package nz.ac.auckland.gli030.assessment_feedback_analysis_application.repositories;

import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.Person;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PersonReactiveMongoRepository extends ReactiveMongoRepository<Person, Long> {
    Flux<Person> findAllByEmailAddress(Publisher<String> emailAddressStream);
}
