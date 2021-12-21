package br.com.zup.DesafioModulo5_TesteMd6.conta;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvarConta(Conta conta) {
        LocalDate dataDeCadastroDaConta = LocalDate.now();

        if (conta.getDataDeVencimento().isBefore(dataDeCadastroDaConta)) {
            conta.setStatus(Status.VENCIDA);
        } else {
            conta.setStatus(Status.AGUARDANDO);
        }
        contaRepository.save(conta);
        return conta;
    }

    public Iterable<Conta> buscarTodasAsContas() {
        return contaRepository.findAll();
    }

}
