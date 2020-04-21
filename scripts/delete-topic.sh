#!/bin/bash

topic_name=test

/usr/hdp/current/kafka-broker/bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic $topic_name