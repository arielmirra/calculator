# 3. Implementar el backend en una base de datos orientada a grafos

Fecha de creación: 02/09/2020

## Estado

Aceptado

## Contexto

La naturaleza dinámica de nuestro sistema hace que no se pueda saber previamente 
la cantidad de recursiones que una métrica puede tener, como la cantidad de atributos
que una métrica puede llegar a tener o cómo se va a relacionar con otras.

Además, previamente habíamos decidido utilizar Spring Boot para realizar el backend

## Decisión

Es por ello que decidimos utilizar Neo4j, ya que soporta Spring Boot y es la 
base de datos orientada a grafos más utilizada del ecosistema actual.

## Consecuencias

Los desarrolladores a cargo del backend deberán tener conocimientos de:

- [Cypher Query Language](https://neo4j.com/developer/cypher/)
- [Neo4j y Spring Boot](https://neo4j.com/developer/spring-data-neo4j/)
- [Spring Data Neo4j Reference Documentation](https://docs.spring.io/spring-data/neo4j/docs/5.3.2.RELEASE/reference/html/#reference)
- [Neo4j-OGM Docs](https://neo4j.com/docs/ogm-manual/current)

El backend podrá soportar el dinamismo y complejidad de los modelos que el proyecto quiere poder representar
