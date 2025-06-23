# Zeelus

**Plataforma de Gestão para Acompanhantes de Pessoas Atípicas**

Zeelus é uma plataforma digital que oferece suporte completo para acompanhantes informais de pessoas atipicas, promovendo bem-estar, organização, capacitação e comunidade em um só lugar.

## ✨ Visão Geral

Cuidar de quem amamos pode ser desafiador. Pensando nisso, desenvolvemos a Zeelus, uma plataforma web com foco em facilitar o cotidiano dos acompanhantes através de recursos como:

- Assistente virtual
- Agenda interativa
- Diário de monitoramento
- Fórum para tirar duvidas
- Conteúdos de capacitação

## 🎯 Objetivos

- Ser uma rede de apoio empática para acompanhantes de pessoas atipicas
- Oferecer ferramentas práticas de organização e suporte
- Promover a capacitação com treinamentos basicos

## 🚀 Funcionalidades

### Conta e Autenticação
- Cadastro e login de acompanhantes
- Recuperação de senha

### Monitoramento e Organização
- **Registros**: possibilidade de anotar/registrar os ultimos ocorridos
- **Calendario** com notificações
- **Anamnese do acompanhado**

### Comunidade e Bem-Estar
- Fórum com troca de experiências entre acompanhantes
- Pop-ups de motivação

### Recursos Educacionais
- Conteúdos de primeiros socorros em formatos acessíveis
- Treinamento e materiais integrados ao plano premium

### Monetização
- Plano premium, disponibilizando funcionalidades que só podem ser acessadas caso tenha o plano

## 🔧 Tecnologias Utilizadas

- **React** — Interface modular, dinâmica e com foco em performance
- **Java + Spring Boot** — Backend robusto, seguro e escalável
- **PostgresSQL** — Banco de dados relacional confiável e amplamente usado

## 🌱 Escalabilidade

- **Médio prazo**: expansão da base de usuários e novos treinamentos
- **Longo prazo**: criação de marketplace de acompanhantes

## 💡 Diferenciais

- Foco exclusivo em acompanhantes de pessoas atipicas
- Plataforma acessível com suporte em Libras

## 📊 Dados Relevantes

- 18,6 milhões de pessoas com deficiência no Brasil (PNAD, 2022)
- 55% estão no mercado informal
- Cuidadores enfrentam desafios emocionais, físicos e financeiros

## 🌍 Alinhamento com os ODS da ONU

- ODS 3 – Saúde e Bem-Estar  
- ODS 8 – Trabalho Decente e Crescimento Econômico  
- ODS 10 – Redução das Desigualdades

## 🧭 Missão

Oferecer suporte e ferramentas essenciais aos acompanhantes, tornando a experiência de cuidar mais leve, humana e empática.

---

## 📚 Documentação da API (Swagger)

Para acessar a documentação interativa da API, siga os passos abaixo:

1. Certifique-se de que a aplicação está rodando
2. Acesse a interface do Swagger UI através da URL:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

Através desta interface você poderá:
- Visualizar todos os endpoints disponíveis
- Testar as requisições diretamente pelo navegador
- Ver os modelos de dados e respostas
- Entender os parâmetros necessários para cada rota

---

## **Como Executar o Projeto**

1. Clone o repositório:

   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```

2. Configure o banco de dados no arquivo `application.properties` ou `application.yml`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   security.token.secret=chave_token
   ```

3. Configure e execute os serviços com **Docker Compose**:

   Crie um arquivo `docker-compose.yml` com o seguinte conteúdo:

   ```yaml
   version: "3.8"

   services:
     postgree:
       image: postgres
       container_name: nome_container
       ports:
         - "5432:5432"
       environment:
         - POSTGRES_USER=seu_usuario
         - POSTGRES_PASSWORD=sua_senha
         - POSTGRES_DB=nome_banco
   ```

   Em seguida, execute o comando:

   ```bash
   docker-compose up -d
   ```

4. Compile e execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

5. Acesse a API na URL:

   ```
   http://localhost:8080
   ```

---

## 👥 Desenvolvedores

- [Andrey Sebastian Justino](https://www.linkedin.com/in/andrey-sebastian-justino/) - Product Owner e Desenvolvedor Full-Stack
- [Laysla Rayssa Alves dos Santos](https://www.linkedin.com/in/laysla-alves-16350b274/) - Scrum Master e Desenvolvedora Front-End
- [Livia Ribeiro Alvarenga](https://www.linkedin.com/in/livia-ribeiro-alvarenga-800813242/) - Marketing e Desenvolvedora Front-End
- [Lucas Padula Alves](https://www.linkedin.com/in/lucas-padula-alves-b72ba52b6/) - Database Manager e Desenvolvedor Back-End
- [Noah Cardozo da Silva](https://www.linkedin.com/in/noah-cardozo-714224352/) - Designer e Desenvolvedor Front-End
- [Rudney Lopes de Souza Junior](https://www.linkedin.com/in/rudneyjr/) - Financeiro e Desenvolvedor Back-End
- [William Ferreira dos Santos](https://www.linkedin.com/in/william-ferreira-911884269/) - Designer UI/UX e Desenvolvedor Full-Stack

---

> *"Cuidar de quem amamos começa com o cuidado de quem cuida."* 💙  
