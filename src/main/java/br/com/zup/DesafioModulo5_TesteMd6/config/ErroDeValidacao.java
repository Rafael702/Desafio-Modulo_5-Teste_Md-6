package br.com.zup.DesafioModulo5_TesteMd6.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErroDeValidacao {
    private String campo;
    private String mensagem;
}
