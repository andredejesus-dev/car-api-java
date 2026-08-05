<div align="center">

![Titulo Animado](https://readme-typing-svg.demolab.com?font=Segoe+UI&weight=800&size=26&pause=1000&color=0969DA&center=true&vCenter=true&width=700&height=50&lines=CONSULTA+TABELA+FIPE+-+JAVA+24;JAVA+SWING+%2B+API+REST)

Aplicacao desktop desenvolvida em Java 24 para consulta dinamica de valores de veiculos em tempo real utilizando a API REST Parallelum FIPE.

![Java 24](https://img.shields.io/badge/Java-24-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue?style=for-the-badge)
![API FIPE](https://img.shields.io/badge/API-Parallelum%20FIPE-green?style=for-the-badge)

</div>

---

## Visao Geral

O projeto consiste em uma interface grafica interativa construida com **Java Swing** que realiza chamadas HTTP assincronas para a API da Tabela FIPE. A interface funciona com carregamento em cascata: ao selecionar uma marca, os modelos sao atualizados automaticamente; ao escolher o modelo, os anos disponiveis sao carregados.

---

## Fluxo de Integracao da API

1. **Execucao da Interface:** Envia `HTTP GET /marcas` e preenche o combo de marcas.
2. **Selecao de Marca:** Envia `HTTP GET /marcas/{ID}/modelos` e atualiza o combo de modelos.
3. **Selecao de Modelo:** Envia `HTTP GET /marcas/{ID}/modelos/{ID}/anos` e atualiza o combo de anos.
4. **Clique em Buscar Preco:** Envia `HTTP GET /marcas/{ID}/modelos/{ID}/anos/{ID}`.
5. **Exibicao:** Faz o parsing do JSON retornado e exibe o valor final no painel.

---

## Tecnologias Utilizadas

| Componente | Tecnologia | Funcao |
| :--- | :--- | :--- |
| **Linguagem** | Java 24 | Versao principal de desenvolvimento |
| **Interface Grafica** | Java Swing / AWT | Layout e componentes (`GridBagLayout`, `JComboBox`, `JTextArea`) |
| **Cliente HTTP** | `java.net.http.HttpClient` | Requisicoes REST HTTP nativas |
| **Processamento JSON** | `org.json` | Parsing de respostas JSON da API |
| **Gerenciador de Build** | Apache Maven | Gerenciamento do ciclo de vida e dependencias |

---

## Estrutura do Repositorio

```text
car-api-java/
├── src/
│   └── main/
│       └── java/
│           ├── InterfaceCarros.java   # Construcao da GUI e logica das chamadas HTTP
│           └── Principal.java         # Ponto de entrada (Main) com SwingUtilities
├── .gitignore
├── pom.xml                            # Configuracoes de dependencias Maven
└── README.md                        # Documentacao do projeto
```

Pre-requisitos
JDK 24 instalado.

Maven instalado e configurado nas variaveis de ambiente.

##Passo a Passo para Executar
---
Clone o repositorio:

Bash
git clone https://github.com/andredejesus-dev/car-api-java.git
cd car-api-java
Compile o projeto e baixe as dependencias necessarias via Maven:

Bash
mvn clean compile
Execute a aplicacao:

Bash
mvn exec:java -Dexec.mainClass="Principal"
---

## Fluxograma de Execucao do Codigo

``
       +------------------------------------+
       |       Inicio (Principal.java)      |
       +------------------------------------+
                         |
                         v
       +------------------------------------+
       |   SwingUtilities.invokeLater()     |
       |  (Inicializa GUI InterfaceCarros)  |
       +------------------------------------+
                         |
                         v
       +------------------------------------+
       |        HTTP GET /marcas            |
       |    (Preenche Combo de Marcas)      |
       +------------------------------------+
                         |
                         v
       +------------------------------------+
       |   Aguardando Interacao do Usuario  |
       +------------------------------------+
          /              |              \
         /               |               \
   Alterou Marca   Alterou Modelo    Clicou Buscar
        /                |                 \
       v                 v                  v
+--------------+  +--------------+  +---------------+
|   HTTP GET   |  |   HTTP GET   |  |   HTTP GET    |
|   /modelos   |  |    /anos     |  | /veiculo_info |
+--------------+  +--------------+  +---------------+
       |                 |                  |
       v                 v                  v
+--------------+  +--------------+  +---------------+
|   Preenche   |  |   Preenche   |  |  Parse JSON   |
| Combo Modelo |  |  Combo Anos  |  |   e Exibicao  |
+--------------+  +--------------+  +---------------+
       \                 |                 /
        \                |                /
         +---------------+---------------+
                         |
                         v
       +------------------------------------+
       |    Retorna ao Estado de Espera     |
       +------------------------------------+
