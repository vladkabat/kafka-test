#!/bin/bash

topic_name=test

./kafka-topics.sh --zookeeper localhost:2181 --delete --topic $topic_name