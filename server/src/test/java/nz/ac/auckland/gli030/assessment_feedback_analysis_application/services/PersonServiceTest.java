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
    Teacher teacherOne = new Teacher(1L, "a@b.co.nz", "c", "d", new HashSet<Long>(Arrays.asList(2L, 3L)));
    Teacher teacherTwo = new Teacher(4L, "e@f.co.nz", "g", "h", new HashSet<Long>(Arrays.asList(5L, 6L)));

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
        var persons = service.get(Flux.just(null));

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
        var teacherOneUpdate = new Teacher(7L, "i@j.co.nz", "k", "l", new HashSet<Long>(Arrays.asList(8L, 9L))),
            teacherOneUpdated = new Teacher(
                teacherOne.getId(),
                teacherOneUpdate.getEmailAddress(),
                teacherOneUpdate.getFirstName(),
                teacherOneUpdate.getLastName(),
                teacherOneUpdate.getIdsFeedbackGiven());
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
        var teacherInvalidEmailAddress = new Teacher(10L, "mn", "o", "p", new HashSet<Long>(Arrays.asList(11L, 12L)));

        var persons = service.save(Flux.just(teacherInvalidEmailAddress));

        then(repository).shouldHaveNoInteractions();
        StepVerifier.create(persons)
            .expectError(ConstraintViolationException.class)
            .verify();
    }

    @Test
    void saveNullPerson() {
        var persons = service.save(Flux.just(null));
        
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
