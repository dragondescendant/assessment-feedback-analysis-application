package nz.ac.auckland.gli030.assessment_feedback_analysis_application.services;

import javax.validation.Validator;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.Person;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.repositories.PersonReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.*;

@Service
public class PersonService {
    private PersonReactiveMongoRepository repository;
    private Validator validator;

    public PersonService(PersonReactiveMongoRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Mono<Person> get(String id) {
        return null;
    }

    public Flux<Person> getAll() {
        return repository.findAll();
    }

    public Flux<Person> save(Flux<Person> persons) {
        return null;
    }
}
