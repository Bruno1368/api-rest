package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;

import java.io.IOException;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class MedicoControllerTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJackson;
    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJackson;
    @MockBean
    private MedicoRepository repository; // anotação para evitar interferencias com o banco de dados real


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estão invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {

        var especialidade = Especialidade.CARDIOLOGIA;
        var response = mvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJackson.write(
                        new DadosCadastroMedico("Bruno",
                                null,
                                "54984345127",
                                "123456",
                                especialidade,
                                dadosEndereco()))
                        .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estão validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var especialidade = Especialidade.CARDIOLOGIA;
        var medico = new Medico(new DadosCadastroMedico("Bruno", "bruno5@bru.com", "54984345127", "852654", especialidade, dadosEndereco()));
        var endereco = new Endereco(dadosEndereco());
        var dadosDetalhamentoMedico = new DadosDetalhamentoMedico(null, "Bruno", "bruno5@bru.com", "852654", "54984345127", especialidade, endereco);
        when(repository.save(eq(medico))).thenReturn(medico);
        var response = mvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJackson.write(
                        new DadosCadastroMedico("Bruno", "bruno5@bru.com", "54984345127", "852654", especialidade, dadosEndereco())
                ).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoMedicoJackson.write(dadosDetalhamentoMedico).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }





    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "950434200",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}