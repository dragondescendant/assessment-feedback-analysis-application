package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.*;
import javax.validation.constraints.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@SuperBuilder
public class Feedback {
    @Id
    private String id;
    @NotBlank
    private String giverId;
    @NotEmpty
    private Set<@NotBlank String> receiverIds;
    @NotNull
    private QualtricsSurveyVariable data;
    @NotNull
    private List<@NotNull QualtricsSurveyVariable> metadata;
}
