# Sistema de Gerenciamento de Aluguel de Veículos

Esta tarefa foi concebida no contexto do segundo módulo dedicado à POO, ministrado no Santander Coders pela ADA. 

Uma das maiores dificuldades encontradas durante o desenvolvimento do projeto, embora não fosse obrigatório, foi a criação do frontend em conjunto com o uso do H2 e Spring. Além disso, aplicar de forma eficaz os conceitos do princípio SOLID, que foram aprendidos durante o módulo, representou um desafio significativo.
Nos próximos passos, planejo implementar validação tanto no front-end quanto no back-end(não deu tempo 😭), coletar feedback e incorporá-lo ao código.

##

Este é um sistema de gerenciamento de aluguel de veículos que permite a gestão de veículos e clientes, bem como a realização de aluguéis e devoluções. O sistema segue um conjunto de regras de negócio para garantir seu funcionamento adequado. Abaixo, você encontrará informações sobre os itens obrigatórios e as regras de negócio implementadas neste projeto.

## Itens Obrigatórios

### Veículos
1. **Cadastrar os veículos:** Permite adicionar novos veículos ao sistema, utilizando a placa como identificador de unicidade.

2. **Alterar um veículo cadastrado:** Possibilita a edição das informações de um veículo já registrado.

3. **Buscar um veículo por parte do nome:** Permite a busca de veículos por parte do nome.

### Clientes
4. **Cadastrar o cliente (pessoa física e jurídica):** Permite o registro de clientes, tanto pessoas físicas quanto jurídicas, garantindo que CPF (para pessoas físicas) e CNPJ (para pessoas jurídicas) sejam identificadores únicos.

5. **Alterar o cliente (pessoa física e jurídica):** Permite a atualização das informações de clientes já cadastrados.

### Aluguéis e Devoluções
6. **Alugar um veículo para pessoa física e jurídica:** Permite a realização de aluguéis de veículos tanto para clientes pessoa física quanto jurídica.

7. **Devolver um veículo para pessoa física e jurídica:** Possibilita a devolução de veículos alugados por clientes.

## Regras de Negócio

### RN1: Identificador Único de Veículos
- Os veículos não podem ser repetidos no sistema, e a placa é usada como identificador de unicidade.

### RN2: Tipos de Veículos
- O sistema considera três tipos de veículos: PEQUENO, MÉDIO e SUV.

### RN3: Registros de Aluguéis e Devoluções
- Todos os aluguéis e devoluções devem conter informações de local, data e horário.

### RN4: Cobrança de Diárias
- A cobrança de diárias é realizada em diárias completas, mesmo que o aluguel seja feito em horas quebradas. Por exemplo, uma devolução feita às 15h30 do dia 25 de janeiro resultará em uma diária até o dia 26 de janeiro às 15h30.

### RN5: Veículos Disponíveis
- Veículos que estão alugados não podem estar disponíveis para novos aluguéis.

### RN6: Identificador Único de Clientes
- Não é permitido cadastrar clientes duplicados no sistema, utilizando CPF (para pessoa física) e CNPJ (para pessoa jurídica) como identificadores de unicidade.

### RN7: Regras de Devolução com Desconto
- Cliente pessoa física que ficar com o veículo por mais de 5 diárias terá direito a um desconto de 5%.
- Cliente pessoa jurídica que ficar com o veículo por mais de 3 diárias terá direito a um desconto de 10%.

### Valores Base da Diária por Tipo de Veículo

- PEQUENO: R$ 100,00 por dia.
- MÉDIO: R$ 150,00 por dia.
- SUV: R$ 200,00 por dia.


## Executando o Projeto

Para rodar este projeto, siga as instruções abaixo:

### Frontend (React)

Certifique-se de ter o Node.js instalado em sua máquina. Você pode baixá-lo em [nodejs.org](https://nodejs.org/).

1. Navegue até a pasta do frontend: ADALocateCar-Front
2. Execute o seguinte comando para instalar as dependências do projeto: npm install
3. Após a instalação das dependências, você pode iniciar o aplicativo com o seguinte comando: npm start

O programa React estará disponível no seu navegador em [http://localhost:3000](http://localhost:3000).

### Backend (Java)

Certifique-se de ter o Java instalado em sua máquina. Você pode baixá-lo em [java.com](https://www.java.com/).

1. Navegue até a pasta do backend: ADALocateCar-Back
2. Use o Maven para compilar e executar o projeto. Execute o seguinte comando: mvn spring-boot:run

O backend estará em execução na porta 8080.

Lembre-se de configurar as conexões de banco de dados, se necessário, no arquivo de configuração do backend.

Certifique-se de ter todas as dependências e requisitos instalados antes de iniciar o projeto.

Agora você pode começar a usar o sistema de gerenciamento de aluguel de veículos.

<details>
<summary>Exibir:</summary>

https://github.com/devhebert/SantanderCoders-POOII-AdaLocateCar/assets/101371363/94b90967-44ba-47d6-92d7-4a5377ddd6c1

</details>
