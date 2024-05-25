# Projeto Java com Maven e MySQL, utilizando Swing como interface gráfica

## Arquitetura
O projeto segue utilizando a arquitetura mvc, apropriando-se de outros pacotes para compor a aplicação de forma reutilizável e de fácil manutenção. Aplicando os conceitos de DRY (dont repeat yourself) na maioria dos casos, e respeitando os princípios SOLID.

## Requisitos

Antes de iniciar, certifique-se de ter os seguintes softwares instalados:

1. [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
2. [Apache Maven](https://maven.apache.org/download.cgi)
3. [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)

## Instalação

### Java 17

1. Acesse o [site de downloads do Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
2. Baixe o instalador apropriado para o seu sistema operacional.
3. Siga as instruções de instalação fornecidas pelo instalador.

Adicione o caminho de instalação do java as variáveis de ambiente do seu sistema: ex: JAVA_HOME = "c://java"

### Apache Maven

1. Acesse o [site de downloads do Apache Maven](https://maven.apache.org/download.cgi).
2. Baixe o arquivo binário zip ou tar.gz.
3. Extraia o conteúdo do arquivo baixado para um diretório de sua escolha.
4. Adicione o diretório `bin` do Maven ao PATH do seu sistema:
   - No Windows:
     ```sh
     setx PATH "%PATH%;C:\caminho\para\maven\bin"
     ```
   - No Linux/Mac:
     ```sh
     export PATH=$PATH:/caminho/para/maven/bin
     ```
5. Verifique a instalação executando o comando:
   ```sh
   mvn -v

### MySql Community Server

1. Instalar a distribuição do MySql community
2. Ao instalar, configurar a senha do MySql como username = "root" & password = "admin"
3. caso queira instalar com a senha diferente, ela pode ser alterada dentro do projeto.
4. Testar a conexão com o banco usando o mysql workbench.
5. Rodar o script para criação do banco, que se encontra na pasta docs > mysql> dev.sql

### Projeto

1. Adicionar as dependencias externas em forma de arquivo no projeto, clicando em cima do projeto > build path > add external archives.
Selecionar a pasta other > e escolher os arquivos mysql e jbcrypt.
2. Instalar o WindowBuilder para melhor manutenção / criação de telas.
Clicando em help > install new software > Add
Selecione o arquivo WindowBuilder dentro da pasta other
3. Rodar com aplicação Java e selecionar a classe Main dentro do package Application.
