# SpringBoot with Kafka
**What is Kafka?**
1. Open-source event streaming platform

## Kafka Components and Architecture
### Components
1. **Producer** :- Producer is the source of data who publishes messages or events.
2. **Consumer** :- Is a receiver which takes messages or events from producer.
3. **Broker** :- Messages sent from the publisher go through a broker which is a Kafka server. Broker is just a intermediate entity
that helps in message exchange between a producer and consumer.
4. **Cluster** :- One or more Brokers are called Kafka Cluster.
5. **Topic** :- Topics are used to store categorize different type of messages. Listeners can then respond to the messages that belong 
to the topics they are listening on.
6. **Partitions** :- Kafka topics are broken into multiple parts and distribute the parts into different machines. This concept is called
topic partitioning. And each part is called partition in Kafka.
7. **Offset** :- A number is assigned to the message within a partition. This number is called Offset.
8. **Consumer Groups** :- Grouping consumers for dividing the workload of messages to achieve better throughput.
9. **Zookeeper** :- Zookeeper is coordination and to track the status of Kafka cluster nodes. It keeps track of Kafka topics, partitions, offset, etc.

## Producer - Consumer workflow Kafka CLI
### Run Kafka and Zookeeper
Run Kafka and Zoo Keeper using docker compose file [here](docker-compose.yml). Within the docker container go into the opt/kafka<verison>/bin folder. 
And execute the commands to create topic, produce or consume.

### Create Topic
```shell
kakfa-topics.sh --bootstrap-server localhost:9092 --create --topic quickstart --replication-factor 1 --partitions 1 
```
**NOTE** : Either add `--bootstrap-server` or `zookeeper` \
1. Replication Factor: Total number of replicas of topics.  

### List Topics
```shell
kafka-topics.sh --bootstrap-server localhost:9092 --list
```

### Describe Topics
```shell
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic quickstart
```

### Publishing Messages
```shell
kafka-console-producer.sh --broker-list localhost:9092 --topic quickstart
```
-- TODO: add kafka producer image here --

Sending CSV files through CLI
```shell
kafka-console-producer.sh --broker-list localhost:9092 --topic topi < path_to_csv_file/file.csv 
```

### Consuming Messages
```shell
kakfa-console-consumer.sh --topic quickstart --from-beginning --bootstrap-server localhost:9092
```
-- TODO: add kafka consumer image here --