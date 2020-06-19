## Spring Boot with Apache KAFKA

To work with Docker on Fedora/CentOS, revert to iptables as firewall backend (instead of nftables)
See https://fedoraproject.org/wiki/Changes/firewalld_default_to_nftables
```shell script
sudo sed -i '/^FirewallBackend/s/=.*/=iptables/' /etc/firewalld/firewalld.conf
```

See :
https://medium.com/big-data-engineering/hello-kafka-world-the-complete-guide-to-kafka-with-docker-and-python-f788e2588cfc

##### Start a cluster
```shell script
cd kafka

# start the cluster
docker-compose up -d

# check the proceses running
docker-compose ps

# destroy the cluster
docker-compose stop
```

##### Create a topic
```shell script
# start a kafka shell
docker exec -i -t -u root $(docker ps | grep docker_kafka | cut -d' ' -f1) /bin/bash
# $(docker ps | grep docker_kafka | cut -d' ' -f1) - Will return the docker process ID of the Kafka Docker running so you can acces it

# create topic registration
$KAFKA_HOME/bin/kafka-topics.sh --create --topic registration \
--partitions 1 --replication-factor 1 \
--bootstrap-server kafka:9092

# describe topic registration
$KAFKA_HOME/bin/kafka-topics.sh --describe --topic registration \
--bootstrap-server kafka:9092
```

##### Test the topic
```shell script
# initialize a producer and write message 
$KAFKA_HOME/bin/kafka-console-producer.sh --topic=registration \
--broker-list kafka:9092
> Hello topic registration

# from another terminal, launch a new kafka shell and initialize a consumer
docker exec -i -t -u root $(docker ps | grep docker_kafka | cut -d' ' -f1) /bin/bash
$KAFKA_HOME/bin/kafka-console-consumer.sh --topic=registration \
--from-beginning --bootstrap-server kafka:9092

# At this step, you should receive the previous message
```

##### Grade a student (PUT)
* 200 : http://localhost:8080/registration/grade?student_id=6&lesson_id=5&grade=B

The message _The student Arthur Dent obtained B in English for newbie_ will be sent in the topic _registration_ and will be consumed (see RegistrationNotifier class)

