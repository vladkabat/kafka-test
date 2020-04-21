#!/bin/bash

topic_name=test

/usr/hdp/current/kafka-broker/bin/kafka-console-producer.sh --broker-list sandbox-hdp.hortonworks.com:6667 --topic $topic_name