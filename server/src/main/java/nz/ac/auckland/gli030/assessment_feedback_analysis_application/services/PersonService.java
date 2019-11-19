package nz.ac.auckland.gli030.assessment_feedback_analysis_application.services;

import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.Person;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.repositories.PersonReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PersonService {
    private PersonReactiveMongoRepository repository;

    public PersonService(PersonReactiveMongoRepository repository) {
        this.repository = repository;
    }

    public Flux<Person> get(Flux<String> ids) {
        return null;
    }

    public Flux<Person> getAll() {
        return repository.findAll();
    }

    public Flux<Person> save(Flux<Person> persons) {
        return null;
    }
}
