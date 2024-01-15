package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    //jpql selecione todos os medicos from medico m, onde m.ativo = true e m.especialidade = especialidade e m.id não esteja em: na entidade consulta, através do id do medico acessando a propriedade medico da entidade consulta, e que a data seja igual a data da consulta, ou seja, Garante que o médico não tenha uma consulta marcada na data especificad, ordene randomicamente e limite a 1
    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("SELECT m.ativo FROM Medico m WHERE m.id = :idMedico")
    Boolean findAtivoById(Long idMedico);


}
