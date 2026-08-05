Markdown<div align="center">

<!-- TITULO ANIMADO VIA TYPING SVG (RODA NATIVO NO GITHUB) -->
<a href="https://git.io/typing-svg">
  <img src="https://readme-typing-svg.demolab.com?font=Segoe+UI&weight=800&size=28&pause=1000&color=0969DA&center=true&vCenter=true&width=600&height=50&lines=CONSULTA+TABELA+FIPE+-+JAVA+24;JAVA+SWING+%2B+API+REST" alt="Typing SVG" />
</a>

<p>Aplicacao desktop desenvolvida em Java 24 para consulta dinamica de valores de veiculos em tempo real utilizando a API REST Parallelum FIPE.</p>

<!-- BADGES INTERATIVOS -->
![](https://img.shields.io/badge/Java-24-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![](https://img.shields.io/badge/GUI-Java%20Swing-blue?style=for-the-badge)
![](https://img.shields.io/badge/API-Parallelum%20FIPE-green?style=for-the-badge)

</div>

---

## Visao Geral

O projeto consiste em uma interface grafica interativa construida com **Java Swing** que realiza chamadas HTTP assincronas para a API da Tabela FIPE. A interface funciona com carregamento em cascata: ao selecionar uma marca, os modelos sao atualizados automaticamente; ao escolher o modelo, os anos disponiveis sao carregados.

---

## Fluxo de Integracao da API

```mermaid
graph TD
    A[Inicio: Execucao da Interface] -->|1. HTTP GET /marcas| B[Preenche Combo de Marcas]
    B -->|2. Evento: Selecao de Marca| C[HTTP GET /marcas/ID/modelos]
    C -->|3. Preenche Combo de Modelos| D[Aguardando Acao]
    D -->|4. Evento: Selecao de Modelo| E[HTTP GET /marcas/ID/modelos/ID/anos]
    E -->|5. Preenche Combo de Anos| F[Aguardando Clique]
    F -->|6. Clique em Buscar Preco| G[HTTP GET /marcas/ID/modelos/ID/anos/ID]
    G -->|7. Retorno do JSON| H[Exibe Resultado Formatado no JTextArea]
Tecnologias UtilizadasComponenteTecnologiaFuncaoLinguagemJava 24Versao principal de desenvolvimentoInterface GraficaJava Swing / AWTLayout e componentes (GridBagLayout, JComboBox, JTextArea)Cliente HTTPjava.net.http.HttpClientRequisicoes REST HTTP nativasProcessamento JSONorg.jsonParsing de respostas JSON da APIGerenciador de BuildApache MavenGerenciamento do ciclo de vida e dependenciasEstrutura do RepositorioPlaintextcar-api-java/
├── src/
│   └── main/
│       └── java/
│           ├── InterfaceCarros.java   # Construcao da GUI e logica das chamadas HTTP
│           └── Principal.java         # Ponto de entrada (Main) com SwingUtilities
├── .gitignore
├── pom.xml                            # Configuracoes de dependencias Maven
└── README.md                          # Documentacao do projeto
Como Executar o ProjetoPre-requisitosJDK 24 instalado.Maven instalado e configurado nas variaveis de ambiente.Passo a PassoClone o repositorio:Bashgit clone [https://github.com/andredejesus-dev/car-api-java.git](https://github.com/andredejesus-dev/car-api-java.git)
cd car-api-java
Compile o projeto e baixe as dependencias necessarias via Maven:Bashmvn clean compile
Execute a aplicacao:Bashmvn exec:java -Dexec.mainClass="Principal"
