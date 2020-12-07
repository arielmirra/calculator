match (n) return n
match (n) detach delete n

-- returns all metrics connected to calculables
MATCH path = (n:Metric)-[*]-(c)
WITH collect(path) as paths
CALL apoc.convert.toTree(paths) yield value
RETURN value;

-- returns all calculables
MATCH path = (n:Calculable)
WITH collect(path) as paths
CALL apoc.convert.toTree(paths) yield value
RETURN value;


-- all nodes and relationships
-- can have custom maxLevel
-- needs a starting point
MATCH (n)
where n.name="Metric 1"
CALL apoc.path.subgraphAll(n, {maxLevel:5}) YIELD nodes, relationships
WITH [node in nodes | node {.*, label:labels(node)[0]}] as nodes,
     [rel in relationships | rel {.*, fromNode:{label:labels(startNode(rel))[0], name:startNode(rel).name}, toNode:{label:labels(endNode(rel))[0], name:endNode(rel).name}}] as rels
WITH {nodes:nodes, relationships:rels} as json
RETURN apoc.convert.toJson(json)
