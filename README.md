Os quatro arquivos .txt inclusos nesse repositório são integrais para o funcionamento do aplicativo. Essa foi a forma que eu aprendi a usar, e reconheço que tinha formas melhores, mas foi o que deu tempo de aprender.
O grupo é composto apenas por Raul Rodrigues Brasil Palheta Gomes, eu mesmo, e eu fiz tudo que está nesse código, com ajuda de cursos na internet e recursos para prover pedaços de código avançados demais para eu inventar sozinho, mas recontexualizados para o propósito dado.

## TEMPLATE DE ANÁLISE DE REQUISITOS (BÁSICO)
1. Nome do Projeto: 
	**Aplicativo de Gerenciamento de Ferramentas em Java**
	(Tema 37. Controle de Ferramentas)
2. Objetivo do Sistema:
	Gerenciar a disponibilidade de ferramentas e quem as devolveu e removeu do estoque.
3. Requisitos Essenciais (Obrigatórios):
	- Cadastro de entidades principais ✓
	- Consulta por identificador (ID ou nome) ✓
	- Listagem geral ✓
	- Uso da estrutura de dados definida (Veja n°6)
	- Menu com opções básicas ✓
4. Requisitos Importantes (Complementares):
	- Atualização de dados ✓
	- Exclusão de dados ✓
	- Validação de entradas do usuário 
	- Modularização do sistema em classes ✓
5. Requisitos Desejáveis (Extras):
	- Exportação de dados para arquivo ✓
	- Interface gráfica (Swing ou JavaFX) ✓
	- Persistência com arquivos texto (serialização simples) ✓
	- Uso combinado de duas ou mais estruturas de dados (Veja n°6)

6. Estrutura de Dados Utilizada:
	Sobre isso, é um pouco complicado. Eu inicialmente planejei em usar pilhas(stack), mas eu vi a 5.4 e decidi usar arquivos .txt para registrar tudo. Efetivamente, do jeito que eu uso, funciona como uma pilha, manipulando apenas a ocorrência mais recente, mas é tudo
  registrado em arquivos .txt. Eu fiz isso tanto para satisfazer o requisito, tanto porque utilizar as estruturas de dados padrões era frustrante para lidar, e também porque, pensando como um aplicativo feito para vender, seria melhor para o cliente poder
  manipular diretamente as listas de ferramentas e funcionários, através de arquivos acessíveis. Por causa disso, eu não usei diretamente uma estrutura de dados na estrutura principal do aplicativo, mas eu acabei usando arrays em outras partes.
  Eu acredito que a forma que foi implementada ainda conte como uma estrutura de dados, mas acaba que eu usei a minha própia forma de estrutura.

8. Escopo das Funcionalidades:
	Esse aplicativo é capaz de registrar funcionários e ferramentas, registrar suas remoções do estoque e devoluções, e quem fez tal ação, em qual horário, além de mostrar quais ainda estão disponíveis.
9. Casos de Uso Simples:
	Olhar as ferramentas que estão em estoque, ver quem removeu uma ferramenta, adicionar uma nova ferramenta, adicionar um novo funcionário.
