package br.com.zup.DesafioModulo5_TesteMd6.conta.dtos;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Tipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ContaDTO {
    private String nomeDaConta;
    private double valor;
    private LocalDate dataDeVencimento;
    private LocalDateTime dataDePagamento;
    private Tipo tipo;
    private Status status;

}
