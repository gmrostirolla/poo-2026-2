FROM mcr.microsoft.com/devcontainers/java:25-bookworm

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        graphviz \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
