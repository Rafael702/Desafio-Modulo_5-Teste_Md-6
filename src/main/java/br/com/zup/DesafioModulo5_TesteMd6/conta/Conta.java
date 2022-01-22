package br.com.zup.DesafioModulo5_TesteMd6.conta;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Tipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_contas")
@NoArgsConstructor
@Getter
@Setter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeDaConta;
    private double valor;
    private LocalDate dataDeVencimento;
    private LocalDateTime dataDePagamento;
    private Tipo tipo;
    private Status status;

}
