# Hibernate Tutorial
## How to differentiate Entity types and Value types? p2c4
Shared references - If runtime shared references exist it is an entity-type(BID), if not value-type(ADDRESS).

Lifecycle dependencies - value-type is bound to an entity type.

Identity - there is a primary key for entity-type

## hsql clients
java -cp hsqldb.jar org.hsqldb.util.DatabaseManager

java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing

##
With :mem: you define a database which is only accessible within the running java vm. 
This database resides in memory and cannot be accessed externally via host/port jdbc access.

##


