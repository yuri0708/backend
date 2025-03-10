# Sobre
A API faz basicamente o CRUD de algumas classes com autenticação JWT.
# Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.3
- Spring Data JPA
- Spring Validation
- Spring Web
- Spring Security
- PostgreSQL
- JWT
- Lombok
# Instalação 
Instalar uma JDK caso você não tenha.
Caso queira adicionar dependências apenas procurar no google e jogar no arquivo pom.xml
# Como executar o projeto
Eu usei o VSCODE que você apenas baixa as extensões do Spring Boot e abre um painel que já roda o programa todo.
A porta que usei foi a padrão http://localhost:8080
# Endpoints
Todas as rotas precisam de um token JWT de menos de login e register.

/auth/register [POST] : Insere novos usuários na tabela e retorna um token.
/auth/login [POST] : Faz o login de usuários e retorna um token.
/users [GET] : Retorna todos os usuários da tabela.
/users/id [GET] : Retorna o usuário pelo ID.
/users/id [PUT] : Atualiza o cadastro do usuário.
/users/id [DELETE] : Deleta o usuário da base de dados pelo id.

/albums [POST] : Faz o registro de álbums.
/albums [GET] : Retorna todos os álbums da tabela.
/albums/id [GET] : Retorna o álbum pelo ID.
/albums/id [PUT] : Atualiza o cadastro de algum álbum.
/albums/id [DELETE] : Deleta o álbum da base de dados pelo id.

/comments [POST] : Faz o registro de comentários.
/comments [GET] : Retorna todos os comentários da tabela.
/comments/id [GET] : Retorna o comentário pelo ID.
/comments/id [PUT] : Atualiza o cadastro de algum comentário.
/comments/id [DELETE] : Deleta o comentário da base de dados pelo id.

/photos [POST] : Faz o registro de fotos.
/photos [GET] : Retorna todos as fotos da tabela.
/photos/id [GET] : Retorna a foto pelo ID.
/photos/id [PUT] : Atualiza o cadastro de alguma foto.
/photos/id [DELETE] : Deleta a foto da base de dados pelo id.

/posts [POST] : Faz o registro de posts.
/posts [GET] : Retorna todos os posts da tabela.
/posts/id [GET] : Retorna o post pelo ID.
/posts/id [PUT] : Atualiza o cadastro de algum post.
/posts/id [DELETE] : Deleta o post da base de dados pelo id.

/tasks [POST] : Faz o registro de tarefas.
/tasks [GET] : Retorna todos as tarefas da tabela.
/tasks/id [GET] : Retorna a tarefa pelo ID.
/tasks/id [PUT] : Atualiza o cadastro de alguma tarefa.
/tasks/id [DELETE] : Deleta a tarefa da base de dados pelo id.

# Implementação JWT
1. Adicionar a dependência no pom.xml
2. Criar arquivo TokenService para gerar o token.
3. Criar arquivo SecurityFilter para validar o token nas requisições.
4. Criar arquivo SecurityConfig para configurar as permissões e deixar apenas as rotas de login e register sem autenticação.
5. Todas as requisições vão precisar de um token JWT Bearer, de menos a de login e register.

# Melhorias Futuras
- Integração com Front-End
- Documentação dos endpoints
- Criação de testes unitários
- Criação de roles para admins e usuários
- Criação de logout
