package br.com.zup.DesafioModulo5_TesteMd6.contateste;

import br.com.zup.DesafioModulo5_TesteMd6.conta.Conta;
import br.com.zup.DesafioModulo5_TesteMd6.conta.ContaRepository;
import br.com.zup.DesafioModulo5_TesteMd6.conta.ContaService;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ContaServiceTeste {

    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private ContaService contaService;

    private Conta conta;
    private List<Conta> contaList = new ArrayList<>();

    @BeforeEach
    public void setup() {

        conta = new Conta();
        conta.setId(1);
        conta.setNomeDaConta("ENEL");
        conta.setValor(260);
        conta.setTipo(Tipo.LUZ);
        conta.setDataDeVencimento(LocalDate.of(2027, Month.DECEMBER, 24));
        conta.setDataDePagamento(null);
        conta.setStatus(Status.AGUARDANDO);

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
    public void testarStatusDaContaCadastradaComoAguardando() throws Exception {
        LocalDate dataAtual = LocalDate.now();
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(conta);

        Assertions.assertFalse(conta.getDataDeVencimento().isBefore(dataAtual));
        Assertions.assertEquals(conta.getStatus(), Status.AGUARDANDO);

    }

    @Test
    public void testarBuscaDeContaPeloIdCaminhoRuim() {
        Optional<Conta> contaOptional = contaRepository.findById(Mockito.anyInt());

        Mockito.when(contaRepository.findById(Mockito.anyInt())).thenReturn(contaOptional);
        Assertions.assertTrue(contaOptional.isEmpty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () ->
                contaService.buscarConta(Mockito.anyInt()));

        Assertions.assertEquals("Não foi encontrado", exception.getMessage());

    }

    @Test
    public void testarBuscaDeContaPeloIdCaminhoBom() {
        Mockito.when(contaRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(conta));

        Optional<Conta> contaOptional = contaRepository.findById(conta.getId());
        Assertions.assertEquals(conta, contaOptional.get());

    }

    @Test
    public void testarAtualizacaoDeStatusDaContaParaPago(){
        Mockito.when(contaService.buscarConta(1)).thenReturn(conta);

        Conta contaComStatusPago = contaService.atualizarStatusDaConta(1);

        Assertions.assertEquals(conta.getStatus(), Status.PAGO);
    }
}
