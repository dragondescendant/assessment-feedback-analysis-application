package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.Date;
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
public class DateTimeVariable extends QualtricsSurveyVariable {
    @NotNull
    private Date value;
}
