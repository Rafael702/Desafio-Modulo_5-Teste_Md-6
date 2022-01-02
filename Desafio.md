**Gerenciador de contas.** 

Faça uma API para gerenciar as terríveis contas que devemos pagar todos os meses. Essa API deve funcionar como um organizador, dessa forma ela vai permitir cadastrar, visualizar, atualizar e deletar contas do sistema (nosso famoso CRUD).

Cadastrando a conta: 

Toda conta do sistema deve ter:

tipo: ENUM

nome:String

valor:double

dataDeVencimento: LocalDate

dataDePagamento: LocalDateTime

status: ENUM
Para cadastrar uma conta a requisição deve seguir o seguinte padrão: 
Endpoint: /contasVerbo HTTP: 

POST corpo:

{   

"nome": "ENEL",  

 "valor": 190.00,   

"tipo": "LUZ",  

 "dataDeVencimento": "2021-11-27"

} 

a resposta deve ser: STATUS 201

corpo:

{   

"id": 1,  

 "nome": "ENEL",  

 "valor": 190.00,   

"tipo": "LUZ",  

 "dataDeVencimento": "2021-11-27",   

"dataDePagamento": null  ,

 "status": "Aguardando"

}



 Os Enum;
O enum de **Status** deve conter as opções; **AGUARDANDO, PAGO e VENCIDA**;
O enum de **Tipo** deve conter as opções; **LUZ, AGUA, COMIDA, LASER e OUTROS**

Entrega Mínima:
O sistema deve permitir que uma conta seja cadastrada no sistema. Sempre que uma conta com a data de vencimento for anterior ao dia do cadastro será preenchido AUTOMATICAMENTE pelo sistema o STATUS VENCIDA, em todos os outros casos o STATUS padrão para as contas novas cadastradas é de AGUARDANDO. 

O Sistema também deve permitir visualizar a lista com todas as contas. Essa lista deve conter apenas os campos ID, Nome, Valor, Status. Por fim, o sistema deve permitir fazer uma requisição para informar que a conta foi paga. Essa requisição deve seguir o seguinte padrão. 
Endpoint: /contas/{id}

Verbo HTTP: PUT

{   

"status":"PAGO"

}

a resposta: 

Status 200

{ 

  "id": 1,

   "nome": "ENEL",

   "valor": 190.00, 

  "tipo": "LUZ", 

  "dataDeVencimento": "2021-11-27",

   "dataDePagamento": “2021-11-10 10:00:00”,

   "status": "PAGO"

}

IMPORTANTE: SEMPRE QUE UMA CONTA FOR PAGA O CAMPO dateDePagamento DEVE SER PREENCHIDA **PELO SISTEMA** 
