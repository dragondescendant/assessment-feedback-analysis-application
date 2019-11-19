package nz.ac.auckland.gli030.assessment_feedback_analysis_application.services;

import java.util.*;
import javax.validation.ConstraintViolationException;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.*;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.repositories.PersonReactiveMongoRepository;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.services.PersonService;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest {
    PersonReactiveMongoRepository repository = mock(PersonReactiveMongoRepository.class);
    PersonService service = new PersonService(repository);
    Teacher teacherOne = Teacher.builder()
        .id("a")
        .emailAddress("b@c.co.nz")
        .firstName("d")
        .lastName("e")
        .idsFeedbackGiven(new HashSet<String>(Arrays.asList("f", "g"))).build();
    Teacher teacherTwo = Teacher.builder()
        .id("h")
        .emailAddress("i@j.co.nz")
        .firstName("k")
        .lastName("l")
        .idsFeedbackGiven(new HashSet<String>(Arrays.asList("m", "n"))).build();

    @Test
    void getWithOneId() {
        var ids = Flux.just("a");
        given(repository.findAllById(ids)).willReturn(Flux.just(teacherOne));

        var persons = service.get(ids);

        then(repository).should(times(1)).findAllById(ids);
        then(repository).shouldHaveNoMoreInteractions();
        StepVerifier.create(persons)
            .expectNext(teacherOne)
            .expectComplete()
            .verify();
    }

    @Test
    void getWithNullId() {
        String id = null;

        var persons = service.get(Flux.just(id));

        then(repository).shouldHaveNoInteractions();
        StepVerifier.create(persons)
            .expectError(NullPointerException.class)
            .verify();
    }

    @Test
    void getWithNullFlux() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            service.get(null);
        });
    
        then(repository).shouldHaveNoInteractions();
    }

    @Test
    void getAll() {
        given(repository.findAll()).willReturn(Flux.just(teacherOne, teacherTwo));

        var persons = service.getAll();

        then(repository).should(times(1)).findAll();
        then(repository).shouldHaveNoMoreInteractions();
        StepVerifier.create(persons)
            .expectNext(teacherOne)
            .expectNext(teacherTwo)
            .expectComplete()
            .verify();
    }

    @Test
    void saveNewPerson() {
        var emailAddress = Flux.just(teacherOne.getEmailAddress());
        Flux<Person> teacher = Flux.just(teacherOne);
        given(repository.findAllByEmailAddress(emailAddress)).willReturn(Flux.empty());
        given(repository.saveAll(teacher)).willReturn(teacher);

        var persons = service.save(teacher);

        then(repository).should(times(1)).findAllByEmailAddress(emailAddress);
        then(repository).should(times(1)).saveAll(teacher);
        then(repository).shouldHaveNoMoreInteractions();
        StepVerifier.create(persons)
            .expectNext(teacherOne)
            .expectComplete()
            .verify();
    }

    @Test
    void saveExistingPerson() {
        Person teacherOneUpdate = Teacher.builder()
            .id("o")
            .emailAddress("p@q.co.nz")
            .firstName("r")
            .lastName("s")
            .idsFeedbackGiven(new HashSet<String>(Arrays.asList("t", "u"))).build();
        Person teacherOneUpdated = Teacher.builder()
            .id(teacherOne.getId())
            .emailAddress(teacherOneUpdate.getEmailAddress())
            .firstName(teacherOneUpdate.getFirstName())
            .lastName(teacherOneUpdate.getLastName())
            .idsFeedbackGiven(teacherOneUpdate.getIdsFeedbackGiven()).build();
        Flux<Person> teacher = Flux.just(teacherOneUpdated);
        var emailAddress = Flux.just(teacherOneUpdate.getEmailAddress());
        given(repository.findAllByEmailAddress(emailAddress)).willReturn(Flux.just(teacherOne));
        given(repository.saveAll(teacher)).willReturn(teacher);

        var persons = service.save(Flux.just(teacherOneUpdate));

        then(repository).should(times(1)).findAllByEmailAddress(emailAddress);
        then(repository).should(times(1)).saveAll(teacher);
        then(repository).shouldHaveNoMoreInteractions();
        StepVerifier.create(persons)
            .expectNext(teacherOneUpdated)
            .expectComplete()
            .verify();
    }

    @Test
    void saveInvalidPerson() {
        var teacherInvalidEmailAddress = Teacher.builder()
            .id("v")
            .emailAddress("wx")
            .firstName("y")
            .lastName("z")
            .idsFeedbackGiven(new HashSet<String>(Arrays.asList("a", "b"))).build();

        var persons = service.save(Flux.just(teacherInvalidEmailAddress));

        then(repository).shouldHaveNoInteractions();
        StepVerifier.create(persons)
            .expectError(ConstraintViolationException.class)
            .verify();
    }

    @Test
    void saveNullPerson() {
        Person person = null;

        var persons = service.save(Flux.just(person));
        
        then(repository).shouldHaveNoInteractions();
        StepVerifier.create(persons)
            .expectError(NullPointerException.class)
            .verify();
    }

    @Test
    void saveNullFlux() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            service.save(null);
        });
    
        then(repository).shouldHaveNoInteractions();
    }
}
