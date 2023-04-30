[![GitHub release](https://img.shields.io/github/actions/workflow/status/sbishah/detect_dne/maven-package-and-publish.yml?style=flat-square)](https://github.com/sbishah/detect_dne/actions/workflows/maven-package-and-publish.yml)
[![GitHub build workflow](https://img.shields.io/github/release/sbishah/detect_dne.svg?style=flat-square)](https://github.com/sbishah/detect_dne/releases/latest)
[![Docker Hub tags](https://img.shields.io/docker/v/sbishah/detect_dne?label=docker&style=flat-square)](https://hub.docker.com/repository/docker/sbishah/detect_dne)

# detect_dne

## Usage

### Method 1: jar
1. download the latest .jar from [releases](https://github.com/sbishah/detect_dne/releases)
2. run the .jar using `java -jar detect_dne.jar`
3. test the server's health using `curl http://localhost:8081/actuator/health`

### Method 2: docker
1. pull the latest docker using, `docker pull sbishah/detect_dne`
2. run the docker using `docker run -p 10000:10000 -p 8081:8081 -t sbishah/detect_dne`
3. test the server's health using `curl http://localhost:8081/actuator/health`

## Contributers
 1. [Bishara Abu Hatoum (bish_a.h@hotmail.com)](https://github.com/sbishah)
