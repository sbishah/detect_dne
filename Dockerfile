FROM openjdk:11-jre-slim

WORKDIR detect_dne
COPY target/detect_dne.jar ./

ENV JAVA_OPTS="-Xms2G -Xmx2G"

ENTRYPOINT java $JAVA_OPTS -jar detect_dne.jar