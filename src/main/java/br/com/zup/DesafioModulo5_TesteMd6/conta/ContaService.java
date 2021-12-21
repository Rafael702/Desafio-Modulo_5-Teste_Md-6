package br.com.zup.DesafioModulo5_TesteMd6.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvarConta(Conta conta) {
        contaRepository.save(conta);
        return conta;
    }

}
