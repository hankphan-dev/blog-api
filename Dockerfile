FROM ubuntu:latest
LABEL authors="phank"

ENTRYPOINT ["top", "-b"]