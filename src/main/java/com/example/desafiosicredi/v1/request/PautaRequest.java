package com.example.desafiosicredi.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaRequest {
    @NotBlank(message = "O campo assunto é obrigatório")
    @JsonProperty("assunto")
    private String assunto;
}
