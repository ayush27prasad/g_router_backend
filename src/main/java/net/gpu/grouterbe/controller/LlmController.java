package net.gpu.grouterbe.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gpu.grouterbe.db.LlmEntity;
import net.gpu.grouterbe.db.LlmRepository;
import net.gpu.grouterbe.dto.ChatRequest;
import net.gpu.grouterbe.dto.ChatResponse;
import net.gpu.grouterbe.feign.LlmRouterClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/llm")
public class LlmController {

    private final LlmRepository llmRepository;
    private final LlmRouterClient llmRouterClient;

    @GetMapping("/")
    public ResponseEntity<List<LlmEntity>> list() {
        return ResponseEntity.ok(llmRepository.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<LlmEntity> process(@RequestBody LlmEntity llmEntityRequest) {
        llmEntityRequest.setOpenAiCompatible(true);
        LlmEntity savedEntity = llmRepository.save(llmEntityRequest);
        return ResponseEntity.ok(savedEntity);
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest chatRequest) {

        if (chatRequest.getModelId() != null) {
            Map<String, String> request = new HashMap<>();

            LlmEntity requestedLlm =
                    llmRepository.findById(chatRequest.getModelId())
                            .orElseThrow(() -> new RuntimeException("Model doesn't exist in db!"));

            request.put("query", chatRequest.getQuery());
            request.put("model_name", chatRequest.getQuery());
            request.put("api_key", chatRequest.getQuery());
            request.put("base_url", chatRequest.getQuery());

            log.info("Request body - D2LLM: {}", request);
            ChatResponse chatResponse = llmRouterClient.askD2llm(request);
            return ResponseEntity.ok(chatResponse);

        } else {
            Map<String, String> request = new HashMap<>();
            request.put("query", chatRequest.getQuery());

            log.info("Request body - router: {}", request);
            ChatResponse chatResponse = llmRouterClient.ask(request);

            return ResponseEntity.ok(chatResponse);
        }

    }


}