package br.com.zup.DesafioModulo5_TesteMd6.conta.dtos;

import br.com.zup.DesafioModulo5_TesteMd6.enums.Status;
import br.com.zup.DesafioModulo5_TesteMd6.enums.Tipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ContaDTO {
    @NotBlank(message = "{validacao.obrigatorio}")
    private String nomeDaConta;
    @Min(value = 2,message = "{validacao.valor.maior}")
    private double valor;
    private LocalDate dataDeVencimento;
    private Tipo tipo;
    private Status status;

}
