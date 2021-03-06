package br.com.zup.DesafioModulo5_TesteMd6.contateste;

import br.com.zup.DesafioModulo5_TesteMd6.componentes.Conversor;
import br.com.zup.DesafioModulo5_TesteMd6.conta.Conta;
import br.com.zup.DesafioModulo5_TesteMd6.conta.ContaController;
import br.com.zup.DesafioModulo5_TesteMd6.conta.ContaService;
import br.com.zup.DesafioModulo5_TesteMd6.conta.dtos.ContaDTO;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Tipo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@WebMvcTest({ContaController.class, Conversor.class})
public class ContaControllerTeste {

    @MockBean
    private ContaService contaService;

    @Autowired
    private MockMvc mockMvc;

    private Conta conta;
    private List<Conta> contas;
    private ContaDTO contaDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        conta = new Conta();
        conta.setId(1);
        conta.setNomeDaConta("ENEL");
        conta.setValor(260);
        conta.setTipo(Tipo.LUZ);
        conta.setDataDeVencimento(LocalDate.of(2021, Month.DECEMBER, 24));
        conta.setDataDePagamento(null);
        conta.setStatus(Status.AGUARDANDO);

        contas = Arrays.asList(conta);
    }

    @Test
    public void testarCadastroDeContas() throws Exception {
        Mockito.when(contaService.salvarConta(Mockito.any(Conta.class))).thenReturn(conta);
        String json = objectMapper.writeValueAsString(conta);

        ResultActions resultadoDaRequisicao =
                mockMvc.perform(MockMvcRequestBuilders.post("/contas/cadastro")
                                .content(json).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().is(201))
                        .andExpect(MockMvcResultMatchers.jsonPath("$").isMap());

        System.out.println(json);

    }

    @Test
    public void testarBuscaDeTodasAsContas() throws Exception {
        Mockito.when(contaService.exibirTodasAsContas()).thenReturn(contas);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/contas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testarBuscaDeTodasAsContasConstandoOCampoIDCaminhoBom() throws Exception {
        Mockito.when(contaService.exibirTodasAsContas()).thenReturn(contas);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/contas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(conta.getId()));
    }

//    @Test
//    public void testarPagamentoDeConta() throws Exception {
//        Mockito.when(contaService.atualizarStatusDaConta(Mockito.anyInt())).thenReturn(conta);
//        conta = new Conta();
//
//        conta = contaService.atualizarStatusDaConta(Mockito.anyInt());
//        String json = objectMapper.writeValueAsString(conta);
//
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/contas/1")
//                        .content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
//                        .value(Status.PAGO));
//
//        System.out.println(json);
//
//    }

    @Test
    public void testarValidacoesNoCadastroDasContasNomeNulo() throws Exception {
        Conta conta = new Conta();
        conta.setNomeDaConta(null);
        Mockito.when(contaService.salvarConta(Mockito.any(Conta.class))).thenReturn(conta);
        String json = objectMapper.writeValueAsString(conta);

        ResultActions resultadoDaRequisicao =
                mockMvc.perform(MockMvcRequestBuilders.post("/contas/cadastro")
                                .content(json).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().is(422));

    }

    @Test
    public void testarValidacoesNoCadastroDasContasValorMenorOuIgualAZero() throws Exception {
        Conta conta = new Conta();
        conta.setValor(-1);
        Mockito.when(contaService.salvarConta(Mockito.any(Conta.class))).thenReturn(conta);
        String json = objectMapper.writeValueAsString(conta);

        ResultActions resultadoDaRequisicao =
                mockMvc.perform(MockMvcRequestBuilders.post("/contas/cadastro")
                                .content(json).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().is(422));

    }


}
