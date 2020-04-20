#!/bin/bash

topic_name=test

./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic $topic_name