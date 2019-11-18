package nz.ac.auckland.gli030.assessment_feedback_analysis_application.services;

import java.util.*;
import javax.validation.ConstraintViolationException;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.models.*;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.repositories.PersonReactiveMongoRepository;
import nz.ac.auckland.gli030.assessment_feedback_analysis_application.services.PersonService;
import org.junit.jupiter.api.*;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest {
    PersonReactiveMongoRepository repository = mock(PersonReactiveMongoRepository.class);
    PersonService service = new PersonService(repository);
    Teacher teacherOne = Teacher.builder()
        .id(1L)
        .emailAddress("a@b.co.nz")
        .firstName("c")
        .lastName("d")
        .idsFeedbackGiven(new HashSet<Long>(Arrays.asList(2L, 3L))).build();
    Teacher teacherTwo = Teacher.builder()
        .id(4L)
        .emailAddress("e@f.co.nz")
        .firstName("g")
        .lastName("h")
        .idsFeedbackGiven(new HashSet<Long>(Arrays.asList(5L, 6L))).build();

    @Test
    void getWithOneId() {
        given(repository.findAllById(Flux.just(1L))).willReturn(Flux.just(teacherOne));

        var persons = service.get(Flux.just(1L));

        then(repository).should(times(1)).findAllById(Flux.just(1L));
        then(repository).shouldHaveNoMoreInteractions();
        StepVerifier.create(persons)
            .expectNext(teacherOne)
            .expectComplete()
            .verify();
    }

    @Test
    void getWithNegativeId() {
        var persons = service.get(Flux.just(-1L));

        then(repository).shouldHaveNoInteractions();
        StepVerifier.create(persons)
            .expectError(ConstraintViolationException.class)
            .verify();
    }

    @Test
    void getWithNullId() {
        Long id = null;        

        var persons = service.get(Flux.just(id));

        then(repository).shouldHaveNoInteractions();
        StepVerifier.create(persons)
            .expectError(NullPointerException.class)
            .verify();
    }

    @Test
    void getWithNullPublisher() {
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
        given(repository.findAllByEmailAddress(Flux.just(teacherOne.getEmailAddress()))).willReturn(Flux.empty());
        given(repository.saveAll(Flux.just(teacherOne))).willReturn(Flux.just(teacherOne));

        var persons = service.save(Flux.just(teacherOne));

        then(repository).should(times(1)).findAllByEmailAddress(Flux.just(teacherOne.getEmailAddress()));
        then(repository).should(times(1)).saveAll(Flux.just(teacherOne));
        then(repository).shouldHaveNoMoreInteractions();
        StepVerifier.create(persons)
            .expectNext(teacherOne)
            .expectComplete()
            .verify();
    }

    @Test
    void saveExistingPerson() {
        var teacherOneUpdate = Teacher.builder()
            .id(7L)
            .emailAddress("i@j.co.nz")
            .firstName("k")
            .lastName("l")
            .idsFeedbackGiven(new HashSet<Long>(Arrays.asList(8L, 9L))).build();
        var teacherOneUpdated = Teacher.builder()
            .id(teacherOne.getId())
            .emailAddress(teacherOneUpdate.getEmailAddress())
            .firstName(teacherOneUpdate.getFirstName())
            .lastName(teacherOneUpdate.getLastName())
            .idsFeedbackGiven(teacherOneUpdate.getIdsFeedbackGiven()).build();
        given(repository.findAllByEmailAddress(Flux.just(teacherOneUpdate.getEmailAddress()))).willReturn(Flux.just(teacherOne));
        given(repository.saveAll(Flux.just(teacherOneUpdated))).willReturn(Flux.just(teacherOneUpdated));

        var persons = service.save(Flux.just(teacherOneUpdate));

        then(repository).should(times(1)).findAllByEmailAddress(Flux.just(teacherOneUpdate.getEmailAddress()));
        then(repository).should(times(1)).saveAll(Flux.just(teacherOneUpdated));
        then(repository).shouldHaveNoMoreInteractions();
        StepVerifier.create(persons)
            .expectNext(teacherOneUpdated)
            .expectComplete()
            .verify();
    }

    @Test
    void saveInvalidPerson() {
        var teacherInvalidEmailAddress = Teacher.builder()
            .id(10L)
            .emailAddress("mn")
            .firstName("o")
            .lastName("p")
            .idsFeedbackGiven(new HashSet<Long>(Arrays.asList(11L, 12L))).build();

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
    void saveNullPublisher() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            service.save(null);
        });
    
        then(repository).shouldHaveNoInteractions();
    }
}
