package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
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
public class Feedback {
    @Id
    private Long id;
    @NotNull
    @Positive
    private Long giverId;
    @NotEmpty
    private Set<@NotNull @Positive Long> receiverIds;
    @NotNull
    private QualtricsSurveyVariable data;
    @NotNull
    private List<@NotNull QualtricsSurveyVariable> metadata;
}
