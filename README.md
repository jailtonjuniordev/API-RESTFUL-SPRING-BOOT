# API RESTful de Cadastro de Produtos e Usuários

Este é um projeto acadêmico de uma API RESTful desenvolvida em Spring Boot para cadastro de produtos e usuários. Utiliza PostgreSQL como banco de dados para armazenar os dados.
Funcionalidades

    Cadastro, atualização, remoção e consulta de produtos.
    Cadastro, atualização, remoção e consulta de usuários.
    Restrição de acesso: determinados usuários possuem permissões limitadas em certas operações.

### Tecnologias Utilizadas

    Spring Boot
    PostgreSQL
    Hibernate
    Spring Security

### Pré-requisitos

    Java JDK 17 ou superior
    PostgreSQL
    Maven

### Configuração do Banco de Dados

    Instale o PostgreSQL em sua máquina, se ainda não o tiver feito.
    Crie um banco de dados chamado api-restful-springboot.
    Configure as credenciais de acesso ao banco de dados no arquivo application.properties.

### Como Executar

    Clone este repositório.
    Navegue até o diretório do projeto.
    Execute o comando mvn spring-boot:run para iniciar o servidor.

### Endpoints
Produtos

    GET /products: Retorna todos os produtos cadastrados.
    GET /products/{id}: Retorna o produto com o ID especificado.
    POST /products: Cria um novo produto.
    PUT /products/{id}: Atualiza o produto com o ID especificado.
    DELETE /products/{id}: Remove o produto com o ID especificado.

Categorias

    GET /category/: Retorna todas as categorias cadastrados.
    GET /category/{id}: Retorna a categoria com o ID especificado.
    POST /category/: Cria uma nova categoria.
    PUT /category/{id}: Atualiza a categoria com o ID especificado.
    DELETE /category/{id}: Remove a categoria com o ID especificado.

Usuários

    GET /users/: Retorna todos os usuários cadastrados.
    GET /users/{id}: Retorna o usuário com o ID especificado.
    POST /users/: Cria um novo usuário.
    PUT /users/{id}: Atualiza o usuário com o ID especificado.
    DELETE /users/{id}: Remove o usuário com o ID especificado.

### Autenticação e Autorização

A autenticação é realizada utilizando JWT (JSON Web Token). As credenciais de acesso são enviadas através de um token JWT no cabeçalho da requisição.
Exemplo de Autenticação

### Restrições de Acesso

As restrições de acesso são configuradas de acordo com o papel do usuário. Alguns usuários podem ter permissões limitadas em certas operações.
Contribuição

Este projeto está licenciado sob a MIT License.
