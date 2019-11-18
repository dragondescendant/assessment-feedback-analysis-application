package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.Set;
import javax.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@SuperBuilder
public abstract class Person {
    @Id
    private Long id;
    @Email
    private String emailAddress;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private Set<@NotNull @Positive Long> idsFeedbackGiven;
}
