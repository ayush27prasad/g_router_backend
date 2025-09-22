package net.gpu.grouterbe.feign;

import net.gpu.grouterbe.dto.ChatResponse;
import net.gpu.grouterbe.exception.ChatException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "llmRouterClient", url = "http://localhost:8000")
public interface LlmRouterClient {

    @PostMapping("/ask")
    ChatResponse ask(@RequestBody Map<String, String> request) throws ChatException;

    @PostMapping("/ask/d2llm")
    ChatResponse askD2llm(@RequestBody Map<String, String> request) throws ChatException;

}

