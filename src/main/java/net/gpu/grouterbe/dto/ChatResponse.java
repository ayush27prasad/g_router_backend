package net.gpu.grouterbe.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ChatResponse {
    private String responseMd; // Markdown formatted response
    private String responseGeneratedVia; // Model name that generated the response
}
