package br.com.zup.DesafioModulo5_TesteMd6.conta.dtos;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Tipo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ContaDetailsDTO {
    private int id;
    private String nomeDaConta;
    private double valor;
    private LocalDate dataDeVencimento;
    private LocalDateTime dataDePagamento;
    private Tipo tipo;
    private Status status;
}
