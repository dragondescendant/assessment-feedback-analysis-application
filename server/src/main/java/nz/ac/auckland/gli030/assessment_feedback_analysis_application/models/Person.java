package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document
@NoArgsConstructor
@SuperBuilder
public class Person {
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
