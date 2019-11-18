package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class TextVariable extends QualtricsSurveyVariable {
    @NotNull
    private String value;
}
