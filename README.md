# ROTAS

## Usuário

Método | Rota | Autenticado? |
-------| ------|--------------|
POST   | /api/v1/users | Não
GET   | /api/v1/users | Sim - *adm*
GET   | /api/v1/users/{id} | Sim
DELETE  | /api/v1/users/{id} | Sim
PATCH  | /api/v1/users/{id} | Sim
POST  | /api/v1/auth | Não

## Ribeirinho
Método | Rota | Autenticado? |
-------| ------|--------------|
POST   | /api/v1/ribeirinho | Sim - *Ribeirinho*
GET   | /api/v1/ribeirinho | Sim - *adm*
GET   | /api/v1/ribeirinho/{id} | Sim - *adm*
GET  | /api/v1/ribeirinho/detalhes | Sim - *Ribeirinho*
PATCH  | /api/v1/ribeirinho/update/{id} | Sim - *Ribeirinho*

## Empresa
Método | Rota | Autenticado? |
-------| ------| -------------|
POST   | /api/v1/empresa | Sim - *Empresa*
GET   | /api/v1/empresa | Sim - *adm*
GET   | /api/v1/empresa/{id} | Sim - *adm*
GET   | /api/v1/empresa/detalhes | Sim - *Empresa*
PATCH  | /api/v1/empresa/update/{id} | Sim - *Empresa*

## Produto
Método | Rota | Autenticado? |
-------| ------ | ----------|
POST   | /api/v1/product | Sim - *Ribeirinho*
GET   | /api/v1/product | Sim - *adm*
GET   | /api/v1/product{id} | Sim - *Ribeirinho*
DELETE  | /api/v1/product/delete/{id} | Sim - *Ribeirinho*
PUT  | /api/v1/product/update/{id} | Sim - *Ribeirinho*

## Estoque
Método | Rota | Autenticado? |
-------| ------ | -------------| 
POST   | /api/v1/stock/add/{productId} | Sim - *Ribeirinho*
POST   | /api/v1/stock/remove/{productId} | Sim - *Ribeirinho*
GET   | /api/v1/stock/detalhes | Sim - *Ribeirinho*
GET   | /api/v1/stock/all | Sim - *adm*




link da apresentação: [https://youtu.be/65HTckl-qQw](https://youtu.be/65HTckl-qQw)


