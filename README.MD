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

### APIs

```
POST /cadastro-pauta: cadastro de novas pautas
    pautaId: obrigatório
    assunto: opcional
   ```
```
POST /abrir-sessao: abre uma sessão de votação para uma pauta.
    pautaId: obrigatório
    duracao: opcional
   ```
```
POST /votar: recebe os votos de associados em um pauta com sessão aberta.
    pautaId: obrigatório
    idAssoc: obrigatório
    voto: obrigatório
```
```
GET /resultado/{id}: retorna o resultado da votação de uma pauta.
   ```
```
GET /pautas: retorna todas as pautas
```

Elas estão expostas pelo endpoint: [APP-SICREDI-AWS](votacao-sicredi-api.sa-east-1.elasticbeanstalk.com).
Exemplo: votacao-sicredi-api.sa-east-1.elasticbeanstalk.com/pautas
### Swagger
* [URL](http://votacao-sicredi-api.sa-east-1.elasticbeanstalk.com/swagger-ui/index.html)
### Kafka
Os resultados são enviados por meio do tópico "resultados". Pode ser visualizada pelo EC2 da AWS.
### Versionamento
Por meio do path e documentação.

<b> OBS: A API de integração de sistemas externos não estava funcionando, por isso optei por não desenvolver.



