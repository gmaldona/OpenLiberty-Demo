FROM icr.io/appcafe/open-liberty:full-java11-openj9-ubi
ARG VERSION=1.0
ARG REVISION=SNAPSHOT

COPY --chown=1001:0 src/main/liberty/config/ /config/
COPY --chown=1001:0 target/*.war /config/apps/
RUN configure.sh
USER 1001