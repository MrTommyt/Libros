FROM ubuntu:latest
LABEL authors="kmilo1"

ENTRYPOINT ["top", "-b"]