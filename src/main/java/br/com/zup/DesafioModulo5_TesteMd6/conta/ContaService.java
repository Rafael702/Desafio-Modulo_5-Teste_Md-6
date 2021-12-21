package br.com.zup.DesafioModulo5_TesteMd6.conta;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvarConta(Conta conta) {
        conta.setStatus(Status.AGUARDANDO);
        contaRepository.save(conta);
        return conta;
    }

}
