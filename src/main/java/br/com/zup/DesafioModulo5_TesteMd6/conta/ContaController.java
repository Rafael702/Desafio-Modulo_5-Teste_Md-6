package br.com.zup.DesafioModulo5_TesteMd6.conta;

import br.com.zup.DesafioModulo5_TesteMd6.conta.dtos.ContaSaidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta cadastrarConta(@RequestBody Conta conta) {
        contaService.salvarConta(conta);
        return conta;
    }

    @GetMapping
    public List<ContaSaidaDTO> exibirTodasAsContas() {

        List<ContaSaidaDTO> listaContaSaidaDTOS = new ArrayList<>();

        for (Conta contaReferencia : contaService.exibirTodasAsContas()) {
            ContaSaidaDTO contaSaidaDTO = modelMapper.map(contaReferencia, ContaSaidaDTO.class);
            listaContaSaidaDTOS.add(contaSaidaDTO);
        }

        return listaContaSaidaDTOS;
    }
}
