## Spring Boot with Apache KAFKA

To work with Docker on Fedora/CentOS, revert to iptables as firewall backend (instead of nftables)
```shell script
sudo sed -i '/^FirewallBackend/s/=.*/=iptables/' /etc/firewalld/firewalld.conf
```

See :
https://medium.com/big-data-engineering/hello-kafka-world-the-complete-guide-to-kafka-with-docker-and-python-f788e2588cfc

###### Start a cluster
```shell script
cd kafka

# start the cluster
docker-compose up -d

# check the proceses running
docker-compose ps

# destroy the cluster
docker-compose stop
```

###### Create a topic
```shell script
# start a kafka shell
./start-kafka-shell.sh 172.17.0.1

# create topic fmatest
$KAFKA_HOME/bin/kafka-topics.sh --create --topic fmatest \
--partitions 4 --replication-factor 1 \
--bootstrap-server `broker-list.sh`

# describe topic fmatest
$KAFKA_HOME/bin/kafka-topics.sh --describe --topic fmatest \
--bootstrap-server `broker-list.sh`
```

###### Test the topic
```shell script
# initialize a producer and write message 
$KAFKA_HOME/bin/kafka-console-producer.sh --topic=fmatest \
--broker-list=`broker-list.sh`
> Hello topic fmatest

# from another terminal, launch a new kafka shell and initialize a consumer
./start-kafka-shell.sh 172.17.0.1
$KAFKA_HOME/bin/kafka-console-consumer.sh --topic=fmatest \
--from-beginning --bootstrap-server `broker-list.sh`

# At this step, you should receive the previous message
```