package net.gpu.grouterbe.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "llms", schema = "public")
@Entity
public class LlmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String modelName;

    @Column(name = "provider_name")
    private String companyName;

    @Column(name = "is_openai_compatible")
    private boolean isOpenAiCompatible;

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "api_key")
    private String apiKey;

}
