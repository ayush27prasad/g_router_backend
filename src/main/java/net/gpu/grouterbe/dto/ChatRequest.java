package net.gpu.grouterbe.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ChatRequest {

    private Long modelId;
    private String query;

}
