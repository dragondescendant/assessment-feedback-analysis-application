package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.Map;
import javax.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@SuperBuilder
public abstract class QualtricsSurveyVariable {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Map<@NotBlank String, @NotNull String> attributes;
}
