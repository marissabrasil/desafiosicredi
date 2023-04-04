# Desafio Sicredi
## Recursos utilizados
1) Maven
2) Spring boot
2) Swagger
3) Kafka
4) MySQL
5) Logg4j
6) Hibernate/JPA
7) JUnit
8) Mockito

### Bando de dados MySQL
Executar o script no BD MySQL de sua preferência:
```
CREATE DATABASE `base_teste` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

CREATE TABLE `pauta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assunto` varchar(45) NOT NULL,
  `inicio_sessao` varchar(45) DEFAULT NULL,
  `fim_sessao` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `voto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `associado_id` int(11) NOT NULL,
  `pauta_id` int(11) NOT NULL,
  `voto` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
Se necessário, trocar username e password no arquivo application.properties.

### Executar aplicação localmente
Você pode executar a classe main DesafiosicrediApplication utilizando sua IDE.
Ou pela linha de comando utilizando o Maven:
```
mvn spring-boot:run
```

### APIs

```
POST 
    localhost:8080/v1/cadastro-pauta: cadastro de novas pautas
    pautaId: obrigatório
    assunto: opcional
   ```
```
POST 
    localhost:8080/v1/abrir-sessao: abre uma sessão de votação para uma pauta.
    pautaId: obrigatório
    duracao: opcional
   ```
```
POST 
    localhost:8080/v1/votar: recebe os votos de associados em um pauta com sessão aberta.
    pautaId: obrigatório
    idAssoc: obrigatório
    voto: obrigatório
```
```
GET 
    localhost:8080/v1/resultado/{id}: retorna o resultado da votação de uma pauta.
   ```
```
GET 
    localhost:8080/v1/pautas: retorna todas as pautas
```

### Swagger
* http://localhost:8080/swagger-ui/index.html

### Kafka
Os resultados são enviados por meio do tópico "resultados" diretamente pelo log.
### Versionamento
Por meio do path e documentação.

### AWS
* Elas estão expostas pelo endpoint: [votacao-sicredi-api.sa-east-1.elasticbeanstalk.com](votacao-sicredi-api.sa-east-1.elasticbeanstalk.com).
* Exemplo: votacao-sicredi-api.sa-east-1.elasticbeanstalk.com/v1/pautas
* Swagger: [http://votacao-sicredi-api.sa-east-1.elasticbeanstalk.com/swagger-ui/index.html](http://votacao-sicredi-api.sa-east-1.elasticbeanstalk.com/swagger-ui/index.html)

<b> OBS: A API de integração de sistemas externos não estava funcionando, por isso optei por não desenvolver</b>



