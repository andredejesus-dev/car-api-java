<div align="center">

<!-- TITULO ANIMADO EM SVG (RODA DIRETO NO GITHUB) -->
<svg width="100%" height="80" viewBox="0 0 650 80" xmlns="http://www.w3.org/2000/svg">
  <style>
    .title-text {
      font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
      font-weight: 800;
      font-size: 26px;
      fill: #0969da;
      stroke: #0969da;
      stroke-width: 1px;
      stroke-dasharray: 400;
      stroke-dashoffset: 400;
      animation: writeText 3.5s ease-in-out infinite alternate;
    }
    @keyframes writeText {
      0% {
        stroke-dashoffset: 400;
        fill-opacity: 0;
      }
      60% {
        fill-opacity: 0.1;
      }
      100% {
        stroke-dashoffset: 0;
        fill-opacity: 1;
      }
    }
  </style>
  <text x="50%" y="50%" dominant-baseline="middle" text-anchor="middle" class="title-text">
    CONSULTA TABELA FIPE - JAVA 24
  </text>
</svg>

<p>Aplicacao desktop desenvolvida em Java 24 para consulta dinamica de valores de veiculos em tempo real utilizando a API REST Parallelum FIPE.</p>

<!-- DEMONSTRACAO DA APLICACAO EM GIF -->
<img src="demo.gif" alt="Demonstracao do App" width="700">

</div>

---

## Visao Geral

O projeto consiste em uma interface grafica interativa construida com **Java Swing** que realiza chamadas HTTP assincronas para a API da Tabela FIPE. A interface funciona com carregamento em cascata: ao selecionar uma marca, os modelos sao atualizados automaticamente; ao escolher o modelo, os anos disponiveis sao carregados.

---

## Fluxo de Integracao da API

O diagrama abaixo descreve o ciclo de requisiscoes HTTP executadas pela aplicacao:

```mermaid
graph TD
    A[Inicio: Execucao da Interface] -->|1. HTTP GET /marcas| B[Preenche Combo de Marcas]
    B -->|2. Evento: Seleção de Marca| C[HTTP GET /marcas/ID/modelos]
    C -->|3. Preenche Combo de Modelos| D[Aguardando Acao]
    D -->|4. Evento: Seleção de Modelo| E[HTTP GET /marcas/ID/modelos/ID/anos]
    E -->|5. Preenche Combo de Anos| F[Aguardando Clique]
    F -->|6. Clique em Buscar Preço| G[HTTP GET /marcas/ID/modelos/ID/anos/ID]
    G -->|7. Retorno do JSON| H[Exibe Resultado Formatado no JTextArea]
    Tecnologias UtilizadasComponenteTecnologiaFuncaoLinguagemJava 24Versao principal de desenvolvimentoInterface GraficaJava Swing / AWTLayout e componentes (GridBagLayout, JComboBox, JTextArea)Cliente HTTPjava.net.http.HttpClientRequisicoes REST HTTP nativasProcessamento JSONorg.jsonParsing de respostas JSON da APIGerenciador de BuildApache MavenGerenciamento do ciclo de vida e dependenciasEstrutura do RepositorioPlaintextcar-api-java/
├── src/
│   └── main/
│       └── java/
│           ├── InterfaceCarros.java   # Construcao da GUI e logica das chamadas HTTP
│           └── Principal.java         # Ponto de entrada (Main) com SwingUtilities
├── .gitignore
├── demo.gif                           # Demonstracao animada da aplicacao
├── pom.xml                            # Configuracoes de dependencias Maven
└── README.md                          # Documentacao do projeto
Como Executar o ProjetoPre-requisitosJDK 24 instalado.Maven instalado e configurado nas variaveis de ambiente.Passo a PassoClone o repositorio:Bashgit clone [https://github.com/andredejesus-dev/car-api-java.git](https://github.com/andredejesus-dev/car-api-java.git)
cd car-api-java
Compile o projeto e baixe as dependencias necessarias via Maven:Bashmvn clean compile
Execute a aplicacao:Bashmvn exec:java -Dexec.mainClass="Principal"

---

### Passo 3: Como publicar diretamente no seu repositório

1. Abra a página principal do seu projeto: `[https://github.com/andredejesus-dev/car-api-java](https://github.com/andredejesus-dev/car-api-java)`
2. No canto superior direito da lista de arquivos, clique em **Add file** > **Create new file**.
3. Digite `README.md` no campo do nome do arquivo.
4. Cole o código acima no campo de texto e clique no botão verde **Commit changes...** no fi
