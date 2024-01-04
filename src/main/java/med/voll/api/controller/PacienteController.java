package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCadastroPaciente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        System.out.println(dados);

    }
}
