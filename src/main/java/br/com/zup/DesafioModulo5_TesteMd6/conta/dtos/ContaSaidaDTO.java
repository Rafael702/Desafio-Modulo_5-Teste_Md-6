package br.com.zup.DesafioModulo5_TesteMd6.conta.dtos;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContaSaidaDTO {
    private int id;
    private String nome;
    private double valor;
    private Status status;
}
