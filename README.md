# Adidas Subscription Service

A spring boot application which can be plugged into a clien based service which can be used for logging subscription events, such as - Online Newsletter Subscriptions.
This API also connects with downstream secured enterprise services - Event Service and Email Service. 

**Design Strategy:**

Subscription Service will be a public client facing service, secured via Basic Auth.
This service will be behind a CDN and a Load Balancer service with Autoscaling Enabled (Cloud Platforms). This service will be invoking the two enterprise downstream services and connecting to the Enterprise Database via an Internal gateway and a Load balancer instance.

We will be using a document database which will be running on a high-availibiity (AWS Dynamo if on Cloud Platform).

The two downstream services will have a queueing system assiciated (Kafka or SQS on cloud).
The service will behave in a **consumer-producer model**, the consumer interface will expose a RESTendpoint which will push the request payload to a kafka topic and Producer interface will poll the messages from kafka and process the message - SMTP Email or Audting System.
This will ensure a resilient approach to async calls from up-stream api with 99.9% chance of message prevention.

### Design Flow
![alt text](https://raw.githubusercontent.com/varuneshwarmathur/credit-card-processor/master/swagger.png)

**Tech Stack**
  - Spring Boot 2.0.3.Release
  - Spring Cloud Finchley SR2
  - Security : Basic Auth Spring Security
  - Metrics : Spring Actuator
  - Database : Mongo
  - Circuit Breaker : Netflix Hystrix
  - Loadbalancer: Netflix Eureka
  - API Documenration : Swagger 2.0
  - SpringFox UI
  - Junits
  - Docker
  - Maven 4.0

### Installation
This application is built using maven and all dependencies will be added automatically.
```sh
$ cd eurekserver
$ mvn spring-boot:run
```
```sh
$ cd subscriptionservice
$ mvn spring-boot:run
```
```sh
$ cd eventservice
$ mvn spring-boot:run
```
```sh
$ cd emailservice
$ mvn spring-boot:run
```

### API Curl Requests Sample

**Subscription Service**
```sh
REQUEST::
curl -X POST \
  http://localhost:8080/adidas/v1/newsletter-subscription \
  -H 'authorization: Basic bWF0aHVyOnBhc3N3b3Jk' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: c6b2097a-928d-3c1f-c380-87773e2a4868' \
  -d '{
  "firstName": "varuneshwarMathur22",
  "gender" : "male",
  "emailId":"mathur_varunesh@yahoo.com",
  "dateOfBirth":"19-08-1987",
  "consentGranted":true
}'
```
```sh
RESPONSE::
{
"subscriptionId":"5c446c5d80040774a31a59d1",

"eventServiceResponse":{"headers":{"Content-Type":["application/json;charset=UTF-8"],"Content-Length":["64"],"Date":["Sun, 20 Jan 2019 12:41:01 GMT"]},"body":{},"statusCode":"OK","statusCodeValue":200},

"emailServiceResposne":{"headers":{"Content-Type":["application/json;charset=UTF-8"],"Content-Length":["100"],"Date":["Sun, 20 Jan 2019 12:41:01 GMT"]},"body":{},"statusCode":"OK","statusCodeValue":200}}
```
**Event Service**
```sh
REQUEST::
curl -X POST \
  http://localhost:9090/adidas/v1/event \
  -H 'authorization: Basic bWF0aHVyOnBhc3N3b3Jk' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ac7e6c0b-4c14-9034-103f-4308972c01b0' \
  -d '{
  "firstName": "varuneshwarMathur22",
  "gender" : "male",
  "emailId":"mathur_varunesh@yahoo.com",
  "dateOfBirth":"19-08-1987",
  "consentGranted":true
}'
```
```sh
RESPONSE::
{
    "eventNotification": "Message Queued - RequestID : 2119"
}
```
**Email Service**
```sh
REQUEST::
curl -X POST \
  http://localhost:9091/adidas/v1/send-email \
  -H 'authorization: Basic bWF0aHVyOnBhc3N3b3Jk' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 5c2c4c72-22ae-fdb2-399a-49fafcd10169' \
  -d '{
  "firstName": "varuneshwarMathur22",
  "gender" : "male",
  "emailId":"mathur_varunesh@yahoo.com",
  "dateOfBirth":"19-08-1987",
  "consentGranted":true
}'
```
```sh
RESPONSE::
{
    "emailNotification": "Mail Sent Successfully For Subscription Id : 5c43073f80040740f489c141"
}
```

### SpringFox Swagger Documentation 

Swagger UI will be available on the following link:
```
http://localhost:8080/adidas/v1/swagger-ui.html#/
```
### Application Metrics ###
```
http://localhost:8080/adidas/v1/actuator/info
http://localhost:8080/adidas/v1/actuator/health
```

### Docker
```sh
docker-compose up --build
```
### RestAPI Details
![alt text](https://raw.githubusercontent.com/varuneshwarmathur/credit-card-processor/master/swagger.png)

### Eureka Service Registration
![alt text](https://raw.githubusercontent.com/varuneshwarmathur/credit-card-processor/master/swagger.png)


### CI / CD Approach

Build Pipeline Management : **Concourse CI**
Code Quality Gate: **SonarQube**
Deployment Orchestration Over Cloud: **Kubernetes**
Release / Package Management: **Helm**
KeyStorage: **Hashicorp Vault**

Branching Strategy: **GIT/Bitbucket**
    - Master 
    - Release Branch
    - HotFix-xxidxx Branch
    - Production Tag (post successful release)
 
Automated:    
Each Push of feature branch will trigger the build and deploy to K8 pods in a TEST cluster.
On a successfull merge to master - the DEV instance will be updated.

Manual:
Each deployment to PROD cluster will be managed via managed version bump - (Semver in case of concourse) and a production tag of the artefact will be stored.


Concourse gives an UI which displays the current pipeline executions and their state.
It also checks for the continuous health of the GIT Repos and provides an early feedback on build failures.

Added extention can be the use of Hooks with tools such as Slack - for quick issue restorations.