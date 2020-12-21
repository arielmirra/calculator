//package com.calculator.model;
//
//import org.springframework.data.neo4j.annotation.Depth;
//import org.springframework.data.neo4j.annotation.Query;
//import org.springframework.data.neo4j.repository.Neo4jRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface MetricRepository extends Neo4jRepository<Metric, Long> {
//
//    @Depth(4)
//    Optional<Metric> findBy_id(Long _id);
//
//    @Query("MATCH path = (n:Metric{name: $name})-[*]-(c) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
//    Metric findByName(String name);
//
//    @Query("MATCH path = (n:Metric)-[*]-(c: Calculable) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
//    List<Object> fetchAllComplete();
//
//    @Query("MATCH path = (n:Metric) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
//    List<Object> fetchAll();
//}
