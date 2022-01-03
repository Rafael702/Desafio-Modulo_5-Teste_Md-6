package br.com.zup.DesafioModulo5_TesteMd6.conta;

import br.com.zup.DesafioModulo5_TesteMd6.conta.dtos.ContaDTO;
import br.com.zup.DesafioModulo5_TesteMd6.conta.dtos.ContaDetailsDTO;
import br.com.zup.DesafioModulo5_TesteMd6.conta.dtos.ContaSaidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ContaDTO cadastrarConta(@RequestBody @Valid ContaDTO contaDTO) {
        Conta conta = modelMapper.map(contaDTO, Conta.class);
        contaService.salvarConta(conta);
        return modelMapper.map(conta, ContaDTO.class);
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Conta pagarConta(@PathVariable int id) {
        Conta contaASerPaga = contaService.atualizarStatusDaConta(id);
        return contaASerPaga;
    }

    @GetMapping("/{id}")
    public ContaDetailsDTO exibirContaCadastrada(@PathVariable int id) {
        Conta conta = contaService.buscarConta(id);
        return modelMapper.map(conta, ContaDetailsDTO.class);
    }
}
