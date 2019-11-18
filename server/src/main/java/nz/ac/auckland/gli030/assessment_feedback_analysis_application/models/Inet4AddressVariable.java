package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.net.Inet4Address;
import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class Inet4AddressVariable extends QualtricsSurveyVariable {
    @NotNull
    private Inet4Address value;
}
