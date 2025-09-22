package net.gpu.grouterbe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ChatResponse {

    @JsonProperty("response")
    private String response; // Markdown formatted response

    @JsonProperty("response_generated_via")
    private String responseGeneratedVia; // Model name that generated the response

}
