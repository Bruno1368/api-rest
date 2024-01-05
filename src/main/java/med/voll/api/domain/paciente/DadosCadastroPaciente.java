package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank(message = "o nome não deve estar em branco")
        String nome,
        @NotBlank(message = "o email não deve estar em branco")
        @Email(message = "Formato do email é inválido")
        String email,
        @NotBlank(message = "o telefone não deve estar em branco")
        String telefone,
        @NotBlank(message = "o cpf não deve estar em branco")
        String cpf,
        @NotNull(message = "o endereço não deve estar em branco")
        @Valid
        DadosEndereco endereco) {
}
