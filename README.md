# Teste CELK - Projeto Spring Boot e AngulaJS

#### Pre-requisitos

- Java 8
- Maven 3

#### Instruções para execução

##### Do terminal 

    $ mvnw spring-boot:run
    ou 
    $ mvn spring-boot:run 

##### Heroku Deploy

No diretório raiz do projeto:

    $ git init
    $ echo target > .gitignore
    $ git add .
    $ git commit -m"initial commit"
    $ heroku login
    $ heroku create
    $ git push heroku master
    $ heroku open

##### Observações
- Foi utilizado a biblioteca SpringFox para gerar o Swagger da aplicação.

# Avaliação Técnica

##### OBJETIVO:

Criar cadastro de unidades federativas do Brasil (estados).
##### JUSTIFICATIVA:

A equipe de atendimento precisa informar no cadastro de cada paciente a unidade
federativa. Porém, previamente, será necessário cadastrar os estados.

##### USER STORY: 
Eu como administrador do sistema desejo cadastrar os estados no sistema para
que esses dados sejam usados posteriormente pelos atendentes no cadastro de
pacientes.
* Critério de aceite 1: Cadastrar uma unidade federativa por vez com data e hora do
registro;
* Critério de aceite 2: Apresentar a lista das unidades federativas na mesma tela,
abaixo do cadastro;
* Critério de aceite 3: Permitir o nome completo e sigla das unidades federativas com
data e hora atualizadas;

##### TECNOLOGIAS:
* Preferencialmente em JavaEE. Springboot como segunda alternativa;
* Bootstrap;
* AngularJS / Angular 1.7;
* Hibernate/JPAD;
* TypeScript;
* SCSS;
* Seguir o style: https://github.com/toddmotto/angularjs-styleguide;
* Teste unitário.

##### DEPLOY DA APLICAÇÃO:
Disponibilizar o site em qualquer portal de hospedagem. Sugestão: Heroku, netlify ou
similar.

##### FONTES:
Publicar o código no gitlab ou github.