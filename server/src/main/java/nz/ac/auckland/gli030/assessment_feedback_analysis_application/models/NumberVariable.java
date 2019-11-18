package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class NumberVariable extends QualtricsSurveyVariable {
    @NotNull
    private Double value;
}
