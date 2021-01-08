# MetricsIngestion
Containerized API Service for upload and retrieving systems metrics

Overview:
  The containerized API service that accepts a json object info about the sender host's CPU and memory utilization in percentage. Then it appends the sender's IP address. If another json object is recieved from the same host then the service returns the maximum value of the cpu and memory utilization fields. The other service returns the list of system metrics based on the IP addresses.
  
  **Note: This code has been tested on ubuntu platform and temporarily hosted on 13.127.37.228** 

Tools Used:
  - Java
  - Springboot
  - Docker
  
Tools Required:
  - Docker engine
  - Docker compose
  
Running the container:
  `docker-compose up`
  To run the same in detached mode -d flag
  `docker-compose up -d`

Specifications:
 - Exposed port: 8080
 - docker volume path on host: ./metrics-data/

API endpoints:
  i) http://'host_address':8080/metrics
  ii) http://'host_address':8080/report
  iii) http://'host_address':8080/reportall

1. Ingestion:
    Method: Post
    Use case example:
      `curl -XPOST -H "Content-Type: application/json" --data '{"percentage_cpu_used": 89, "percentage_memory_used": 87}' http://127.0.0.1:8080/metrics`
      
      **Note: use the same key as above while creating the json object**
 
2. Data retrieval:
     Method: Get
     Use case example:
         `curl -XGET -H "Content-Type: application/json" http://127.0.0.1:8080/report`
