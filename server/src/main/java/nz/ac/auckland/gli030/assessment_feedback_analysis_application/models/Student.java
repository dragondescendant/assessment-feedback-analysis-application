package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.Set;
import javax.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class Student extends Person {
    @NotNull
    Set<@NotNull @Positive Long> idsFeedbackReceived;
}
