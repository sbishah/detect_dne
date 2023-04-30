[![GitHub release](https://img.shields.io/github/actions/workflow/status/sbishah/detect_dne/maven-package-and-publish.yml?style=flat-square)](https://github.com/sbishah/detect_dne/actions/workflows/maven-package-and-publish.yml)
[![GitHub build workflow](https://img.shields.io/github/release/sbishah/detect_dne.svg?style=flat-square)](https://github.com/sbishah/detect_dne/releases/latest)
[![Docker Hub tags](https://img.shields.io/docker/v/sbishah/detect_dne?label=docker&style=flat-square)](https://hub.docker.com/repository/docker/sbishah/detect_dne)

# detect_dne

## What is a DNE sequence?

For a sequence of $n$ integers $a_1, a_2, \.\.\., a_n$ , a DNE sequence is a subsequence $a_i, a_j, a_k$, such that $i < j < k$ and $a_i< a_k< a_j$

## Server

### Usage

#### Deploy

##### Method 1: jar
1. download the latest .jar from [releases](https://github.com/sbishah/detect_dne/releases)
2. run the .jar using `java -jar detect_dne.jar`
3. test the server's health using `curl http://localhost:8081/actuator/health`

##### Method 2: docker
1. pull the latest docker using, `docker pull sbishah/detect_dne`
2. run the docker using `docker run -p 10000:10000 -p 8081:8081 -t sbishah/detect_dne`
3. test the server health using `curl http://localhost:8081/actuator/health`

#### REST API

Two endpoints are exposed:

- `http://localhost:10000/server`
- `http://localhost:8081/actuator/health` _(management)_

1. `/server` (**POST**) performs the DNE detection on the sequence data provided with the request \
parameters:
    1. _`strategy`_ _(optional)_ specifies the DNE detection strategy to run the detection, supports:
        1. `BruteForce` \
        _O(N^3) time complexity, O(1) extra space complexity_
        2. `ReverseSearch` \
        _O(N^2) time complexity, O(1) extra space complexity_ \
        for every entry in reverse:
            1. iterate the entries left to it in a reverse order while holding a count of entries bigger that it
            2. if found an entry less than it and count is bigger than zero, a DNE subsequence is detected
        3. `MinLeftClosestRightSearch` \
        _O(N*LOG(N)) time complexity, O(N) extra space complexity_ \
        for every entry:
            1. find the min entry to the left of it
            2. find the greatest entry that is less than it to the right of it
            3. iterate the entries to detect if a DNE subseuquence is present

    usage examples:
    1. default:
        ```
        > curl -X POST http://localhost:10000/server -H "content-type:application/json" -d '{"seq":[4,1,7,8,7,2]}'
        true
        ```
    2. with _`strategy`_:
        ```
        > curl -X POST http://localhost:10000/server?strategy=BruteForce -H "content-type:application/json" -d '{"seq":[4,1,7,8,7,2]}'
        true
        ```
2. `/actuator/health` (**GET**) performs a health-check and returns the server health \
    usage examples: \
    ```
    > curl -X GET http://localhost:8081/actuator/health
    status":"UP","components":{"diskSpace":{"status":"UP","details":{"total":1081101176832,"free":1021136154624,"threshold":10485760,"exists":true}}}}
    ```

## Contributers
 1. [Bishara Abu Hatoum (bish_a.h@hotmail.com)](https://github.com/sbishah)
