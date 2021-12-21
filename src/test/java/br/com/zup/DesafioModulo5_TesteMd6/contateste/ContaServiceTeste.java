package br.com.zup.DesafioModulo5_TesteMd6.contateste;

import br.com.zup.DesafioModulo5_TesteMd6.conta.Conta;
import br.com.zup.DesafioModulo5_TesteMd6.conta.ContaRepository;
import br.com.zup.DesafioModulo5_TesteMd6.conta.ContaService;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
public class ContaServiceTeste {

    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private ContaService contaService;

    private Conta conta;

    @BeforeEach
    public void setup() {

        conta = new Conta();
        conta.setId(1);
        conta.setNomeDaConta("ENEL");
        conta.setValor(260);
        conta.setTipo(Tipo.LUZ);
        conta.setDataDeVencimento(LocalDate.of(2021, Month.DECEMBER, 24));
        conta.setDataDePagamento(null);

    }

    @Test
    public void testarStatusDaContaCadastradaComoVencida() {
        conta = new Conta();
        conta.setDataDeVencimento(LocalDate.of(2021, Month.DECEMBER, 19));
        LocalDate dataAtual = LocalDate.now();
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(conta);

        Conta contaCadastrada = contaService.salvarConta(conta);//PEGAR O SET DO STATUS

        Assertions.assertTrue(contaCadastrada.getDataDeVencimento().isBefore(dataAtual));
        Assertions.assertEquals(contaCadastrada.getStatus(), Status.VENCIDA);
    }

    @Test
    public void testarStatusDaContaCadastradaComoAguardando() {
        LocalDate dataAtual = LocalDate.now();
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(conta);

        Conta contaCadastrada = contaService.salvarConta(conta);

        Assertions.assertFalse(contaCadastrada.getDataDeVencimento().isBefore(dataAtual));
        Assertions.assertEquals(contaCadastrada.getStatus(), Status.AGUARDANDO);
    }
}
