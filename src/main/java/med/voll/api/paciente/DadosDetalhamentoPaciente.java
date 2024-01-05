package med.voll.api.paciente;

public record DadosDetalhamentoPaciente(Long id, String nome, String telefone, Boolean ativo) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getAtivo());
    }
}
