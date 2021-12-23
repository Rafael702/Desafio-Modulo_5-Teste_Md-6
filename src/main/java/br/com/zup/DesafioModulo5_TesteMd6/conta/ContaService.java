package br.com.zup.DesafioModulo5_TesteMd6.conta;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<Conta> exibirTodasAsContas() {
        return (List<Conta>) contaRepository.findAll();
    }

    public Conta buscarConta(int id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);

        return contaOptional.get();
    }


}
