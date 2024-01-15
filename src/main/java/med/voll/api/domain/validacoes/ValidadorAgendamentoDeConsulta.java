package med.voll.api.domain.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

//interface não precisa de anotação, spring carrega automaticamente
public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
