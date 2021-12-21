package br.com.zup.DesafioModulo5_TesteMd6.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta cadastrarConta(@RequestBody Conta conta) {
        contaService.salvarConta(conta);
        return conta;
    }
}
