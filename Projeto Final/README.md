# Bom dia/ Boa tarde/ Boa noite

Seguem algumas informações sobre o projeto final!

O **Projeto final** tem como objetivo ser uma plataforma de suporte ao Servidor do Crescer-CWI no Discord.

Na plataforma, usuários podem elaborar discussões e formular suas dúvidas em Posts, que ficam disponíveis para que outros usuários e monitores possam engajar.

A proposta do projeto é facilitar com que dúvidas possam ser esclarecidas de forma mais acessível e organizada. Sem estar sujeitas a se perderem em grandes volumes de mensagens em Chats de Texto do Discord.

O **Projeto Final** também permite que monitores acompanhem os debates, façam suas colaborações, validem respostas e acompanhem o ranking de usuários mais ativos na aplicação, que conta com um sistema de Pontos de Reputação.

Usuários que mais engajarem em debates, formularem suas dúvidas e receberem votos positivos em suas participações irão acumular Pontos de Reputação, que serão utilizados para calcular sua posição no Ranking. O **Podium de 5 usuários mais ativos** fica disponível na página principal da plataforma.

---

## Para iniciar a aplicação localmente:

- Criar as Tabelas do banco de dados com o arquivo `scripts.sql` na pasta `db`.
- Instalar as dependências do Maven no arquivo `pom` da pasta `api`.
- Rodar `npm install` no projeto da pasta `app`.
- No projeto de React, acessar:
  - `src/ui/screens/login/login.jsx` e substituir a variável `login.JENKINS` na linha 11 por `login.LOCAL`.
  - `src/api/Projetofinal-api/Projetofinal-api.js` e substituir a variável `api.JENKINS` na linha 9 por `api.LOCAL`.
- Iniciar a API.
- Iniciar o Projeto React.

---

## Acessando a plataforma:

Acessando o menu principal da plataforma e clicando em **Entrar pelo Discord** você será redirecionado para uma página de Login. Lá você deverá realizar o login com suas credenciais do Discord.

Somente serão autenticados usuários que façam parte do Servidor.

A plataforma conta com uma conta de Administração. Com ela é possível acessar a página de administração e delegar ou revogar Monitores na plataforma. Monitores também recebem acesso à página de Administração e a permissão para delegar ou revogar outros monitores. A conta de Administração não pode ter seu Status alterado.

---

## Home:

Ao realizar o Login, o usuário será levado até a Home. No cabeçalho, os elementos em ordem de esquerda para direita são:

- **Logo da Projetofinal** - Ao clicar, retorna o usuário para a Home. Caso já esteja na Home, ele irá recarregar a página para o Usuário.
- **Avatar do Usuário** - Importado direto da conta do Discord. Um contorno violeta indicará que o usuário é um Monitor e um contorno Azul indicará que é um usuário comum.
- **Nome do Usuário** - Importado direto da conta do Discord.
- Sendo Monitor ou Administrador, **Botão de Engrenagens** - Ao clicar, leva até a página de Administração.
- **Sino de notificações** - Ao clicar exibe uma janela com Notificações do usuário. Caso existam Notificações não lidas, uma marcação irá destacar o Sino.
- **Botão de Logout** - Ao clicar, realiza o Logout do usuário e retorna para o Menu.

O Cabeçalho será acessível em todas as páginas da plataforma e é a principal ferramenta de navegação.

No corpo da página:

Do lado Direito é possível encontrar um **Ranking com os 5 usuários mais ativos**. Ordenados por seus Pontos de Reputação. Estes pontos podem ser adquiridos ao engajar em discussões ou criar posts próprios. Também é possível adquirir reputação ao receber votos positivos em suas interações, ou perder reputação ao receber votos negativos. Monitores não são listados no Ranking.

Do lado Esquerdo há uma seção de busca por Discussões. Onde podem ser selecionadas Tags específicas sobre algum assunto e também uma busca por Texto, onde serão pesquisadas Discussões que contenham os elementos escritos em seu Título ou no Corpo da Discussão.

Também há a opção de **Criar uma nova Pergunta**. Onde o usuário será redirecionado para um formulário, descrito na próxima seção.

Abaixo desta seção de busca há uma lista de Posts. Paginados em páginas de 3 elementos. Estes posts são ordenados por Mais Recentes Primeiros, possibilitando que novas discussões tenham visibilidade.

Nos elementos listados são encontrados o Título do Post, as Tags que o representam, o Avatar e nome do autor, um contador de Respostas e um contador de Relevância. Este contador de Relevância representa o somatório de votos positivos, subtraído dos votos negativos daquela publicação.

Ao realizar uma busca, também serão retornados 3 elementos por página. Porém estes estarão ordenados por Relevância, ao invés de recentes primeiro. Assim é possível encontrar os posts mais relevantes para a pesquisa em questão.

Posts feitos por usuários têm elementos coloridos em uma cor azulada. Enquanto Posts feitos por monitores possuem elementos em uma cor violeta.

Clicando em um dos cards listados, o usuário será redirecionado para a página do Post em questão. Descrito mais abaixo.

---

## Formulário de Post:

Clicando no botão de **Criar Pergunta** o usuário é redirecionado para a página do formulário. Lá, qualquer usuário da plataforma pode elaborar um post.

**Elementos do formulário**:

- **Título** - Deve possuir de 10 a 100 caracteres e será exibido no Card da Home como identificador do Post.
- **Tags** - Devem ser selecionadas de 1 a 5 tags. Elas podem ser selecionadas dentre as Tags já existentes, podendo ser filtradas pelo input "Filtro de tags" e também por tags novas que podem ser criadas no próprio formulário, com o botão de Nova Tag. Criando assim uma tag de nome igual ao escrito no Input.
- **Conteúdo** - Representa o corpo do Texto. Deve ter de 10 a 1000 Caracteres e é a descrição principal do post. Aqui o usuário pode trazer uma descrição detalhada do que deseja abordar.
- **Campo de Anexo de imagens** - Aqui o usuário pode anexar arquivos JPEG, JPG e PNG. Podendo ser anexados através do clique ou do arrasto de arquivos para a área demarcada. Arquivos aceitos serão pré-carregados em uma galeria logo acima do campo de anexo. Podendo ser removidas com um clique.
- **Botão de Postar** - Enviará o post para a Home, junto ao usuário.

---

## Página do Post:

Ao clicar em um card de postagem, o usuário será redirecionado para a publicação em questão. Lá, encontrará algo semelhante ao card, porém com mais elementos.

- **Corpo do texto** - Contém o conteúdo principal da postagem.
- **Imagens anexadas** - Caso o Post contenha imagens em anexo, elas ficarão disponibilizadas em uma galeria logo abaixo do Corpo do Texto. Estas imagens podem ser Clicadas, sendo ampliadas na tela para melhor visualização. Clicando fora da imagem, é removida a ampliação.
- **Data e Hora** - Contém a data e a hora da postagem, localizada sob o nome do autor.
- **Botões de Positivo e Negativo** - Caso o usuário não seja o autor do Post, terá a opção de avaliar positivamente ou negativamente o Post através dos botões de símbolos respectivos. Cada usuário só pode avaliar uma vez cada post. O voto será indicado pela cor de destaque no botão. O Usuário pode remover o voto ao clicar novamente no botão em destaque, ou pode inverter seu voto clicando no botão inverso.

Embaixo do Post ficam as respostas. Os cards de resposta são muito semelhantes ao Post, porém não contam com Título ou Tags e também não possuem contador de Respostas. Elas são ordenadas por Relevância, mostrando as mais relevantes primeiro.

Monitores podem escolher Validar respostas de Usuários. Ao visitar um post com uma conta de Monitor, uma opção de verificar resposta será exibida próximo ao avatar do autor. Selecionando, a resposta ficará com fundo esverdeado, constará com a marca de verificação e será ordenada antes das outras respostas. Clicar novamente no ícone de verificação irá revogar a verificação.

Monitores também podem responder Posts. Suas respostas têm um fundo de cor mais escura, com a marca de Monitor. Estas respostas serão exibidas antes de outras respostas. Inclusive antes de respostas verificadas.

Embaixo das respostas há um Formulário de Resposta. Semelhante ao formulário de Post. Porém não conta com Título ou Tags.

---

## Notificações:

O usuário irá receber notificações através do sino do Cabeçalho, que ficará em destaque quando houverem notificações.

Notificações serão enviadas quando:

- Houverem novas respostas no seu Post.
- Houverem novas avaliações no seu Post.
- Houverem novas avaliações nas suas respostas de Posts.

Clicando no sino e exibindo a janela de notificações, o usuário receberá uma lista com botões. Cada botão tem uma breve descrição da notificação. Ao clicar no botão, o usuário será redirecionado para a página do post onde a origem da notificação se encontra. Em seguida, a notificação irá constar como Visualizada e não será listada na janela de notificações.

Também há um botão com ícone de Lixeira. Ao clicar neste botão, a lista de notificações será esvaziada e o ícone deixará de ficar destacado.

---

## Administração:

A página de Administração pode ser acessada por usuários Monitores ou pela conta de Administrador, descrita anteriormente. O acesso a esta página é feito através do botão de engrenagens, no cabeçalho.

Nessa página, há um input de pesquisa para buscar usuários. A busca pode ser feita por nome ou email.

O retorno dessa busca é uma lista paginada de 9 elementos com Cards de Usuários. Estes cards mostram o Avatar e o Nome do Usuário do lado esquerdo. Do lado Direito há dois botões que definem se o usuário é um Monitor (destacado em violeta) ou Usuário (destaque em azul). Selecionar o botão que não está destacado irá trocar o Status daquele usuário no sistema.

Na Lista não é possível encontrar a si mesmo, pois não é permitido que seja alterado o Status do próprio usuário. Também não é exibida a conta de Administração.

---

## Sumário:

Na pasta collection é possível encontrar a coleção do Postman com todos os Endpoints definidos na Api.

Para usar as requisições pelo Postman, é preciso passar um Header de Autorização com o Token da autorização.

Para adquirir o Token, é preciso acessar o LocalStorage do navegador quando Logado na aplicação. Ou interceptar o Token da URL de redirecionamento da Página de Autenticação para a Home, porém este método não é o ideal.

O Header deve ser passado como "authorization" e o token deve ser passado como "Bearer {token}".

Para qualquer problema, dúvida ou qualquer necessidade de contato, estamos disponíveis pelo nosso Linkedin ou Telefone:

- [Henrique Bidarte](https://www.linkedin.com/in/henrique-bidarte-massuquetti-b89006210/) - (51) 999746-2456
- Felipe Bergamo - (51) 99774-9330
- [Héctor Tavares](https://www.linkedin.com/in/hector-tavares/) - (51) 99478-7695

Obrigado pelo Curso, pela Parceria e pela Aventura!

Um Abraço!

- Henrique Bidarte
- Felipe Bergamo
- Héctor Tavares

	

































