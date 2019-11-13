package nz.ac.auckland.gli030.assessment_feedback_analysis_application.models;

import java.util.Map;
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
public class FeedbackMetadata {
    @Id
    private Long id;
    private String name;
    private String description;
    private Map<String, String> attributes;
}
