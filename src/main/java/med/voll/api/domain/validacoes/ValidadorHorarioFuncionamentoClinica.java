package med.voll.api.domain.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisFechamentoDaClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisFechamentoDaClinica){}
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
    }

}
