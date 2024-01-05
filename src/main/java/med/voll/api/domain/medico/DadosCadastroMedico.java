package med.voll.api.domain.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank(message = "o nome não deve estar em branco")
        String nome,
        @NotBlank(message = "o email não deve estar em branco")
        @Email(message = "Formato do email é inválido")
        String email,
        @NotBlank(message = "o telefone não deve estar em branco")
        String telefone,
        @NotBlank(message = "o crm não deve estar em branco")
        @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")
        String crm,
        @NotNull(message = "a especialidade não deve estar em branco")
        Especialidade especialidade,
        @NotNull(message = "o endereço não deve estar em branco")
        @Valid
        DadosEndereco endereco

) {
}
