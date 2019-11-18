package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class NumberVariable extends QualtricsSurveyVariable {
    @NotNull
    private Double value;
}
