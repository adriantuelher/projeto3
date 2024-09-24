# Sistema de reservas de sala

Este sistema tem como finalidade o cadastro e gestão de eventos das salas do Instituto Federal campus Manhuaçu, mas pode ser estendido a outras áreas e unidades. Com o desenvolvimento deste sistema, será possível organizar e controlar os eventos de maneira eficaz, assegurando a disponibilidade das salas e evitando picos ou conflitos de horários entre eles. Além de proporcionar ferramentas para filtragem e consulta de eventos e equipamentos.

## Funcionalidades

1. **Cadastro de Usuario**: Permite cadastro de usuarios, com diferentes tipos e níveis, sendo o nível 1 o mais alto, permitindo ter acesso a mais funcionalidades.
2. **Cadastro de Eventos**: Permite a organização e o planejamento de eventos, com uma interface para o preenchimento de detalhes como data, local e o tipo de evento.
3. **Cadastro de Unidade**: Permite cadastro de unidades de uma instituição, mas apenas usuarios de nível 1 podem cadastrar, editar ou excluir.
4. **Cadastro de Bloco**: Permite cadastro de blocos de uma instituição, mas apenas usuarios de nível 1 podem cadastrar, editar ou excluir.
5. **Cadastro de Espaços**: Permite cadastro de espaços de uma instituição, mas apenas usuarios de nível 1 podem cadastrar, editar ou excluir.
6. **Cadastro de Equipamentos**: Permite cadastro de equipamentos de um espaço, mas apenas usuarios de nível 1 podem cadastrar, editar ou excluir.
7. **Cadastro de Plantas**: Permite cadastro de plantas, ultilizadas em pesquisas e projetos, de um espaço, mas apenas usuarios de nível 1 podem cadastrar, editar ou excluir.
8. **Vizualizar Equipamentos**: Permite que o usuario vizualize todos os equipamentos que existem em um espaço.
9. **Vizualizar Plantas**: Permite que o usuario vizualize todos as plantas, ultilizadas em pesquisas e projetos, que existem em um espaço.
10. **Vizualizar Unidade**: Permite que o usuario vizualize todos as unidades que existem em um campus.
11. **Vizualizar Blocos**: Permite que o usuario vizualize todos os blocos que existem em uma unidade.
12. **Vizualizar Espaços Diponiveis**: Permite que o usuario vizualize todos os espaços que existem e etão disponiveis para futuros eventos.
13. **Vizualizar Eventos**: Permite que o usuario vizualize todos os eventos que existem.

## Tecnologias Utilizadas

- **Linguagem de programação**: Java
- **Framework**: Booststrap
- **Ferramenta**: Eclipse IDE for Enterprise Java and Web Developers
- **Banco de Dados**: MySQL

## Instalação

1. Instale o JDK e o server Apache Tomcat
2. Instale e execute o arquivo Dump20240923.sql que está no repositório para gerar as tabelas do sistema.
3. O arquivo acima irá gerar um usuário padrão root, com a senha 1234.
4. Rode a aplicação via Eclipse ou outra IDE que suporte aplicações Web em Java.
5. A aplicação é executada na porta padrão http://localhost:8080 e genericamente está disponível em http://localhost:8080/atividades
