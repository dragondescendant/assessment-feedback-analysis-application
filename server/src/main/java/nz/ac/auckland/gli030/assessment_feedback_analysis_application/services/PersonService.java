package nz.ac.auckland.gli030.assessment_feedback_analysis_application.services;

import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.Person;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PersonService {
    private ReactiveCrudRepository<Person, Long> personRepository;

    public PersonService(ReactiveCrudRepository<Person, Long> personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> get(Publisher<Long> ids) {
        return null;
    }

    public Flux<Person> getAll() {
        return null;
    }

    public Flux<Person> save(Publisher<Person> persons) {
        return null;
    }
}
