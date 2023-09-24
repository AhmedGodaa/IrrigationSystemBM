# IrrigationSystem

## Installation

### Install Kafka bitnami helm chart

- Install Chart

```shell
# Install the Kafka helm chart
helm install my-release oci://registry-1.docker.io/bitnamicharts/kafka
# Get Installed Kafka Helm Chart Password
kubectl get secret my-release-kafka-user-passwords --namespace default -o jsonpath='{.data.client-passwords}' | base64 -d | cut -d , -f 1
``` 

> `📝` **Note:**
>
> Kafka can be accessed by consumers via following DNS name from within your cluster:
> **my-release-kafka.default.svc.cluster.local:9200**

- Provide Spring Project Configurations

```text
# at application.properties
spring.kafka.producer.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS}
logging.level.org.apache.kafka=${KAFKA_LOGGING_LEVEL}
spring.kafka.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS}
spring.kafka.security.protocol=${KAFKA_SECURITY_PROTOCOL}
spring.kafka.properties.sasl.mechanism=${KAFKA_SASL_MECHANISM}
spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required username="${KAFKA_USERNAME}" password="${KAFKA_PASSWORD}";
```

- Example of Configurations values can be found in the configmap and the
  secrets [ConfigMap](kubernetes/development/sensor-service/sensor-service-configmap.yml) - [Secret](kubernetes/development/sensor-service/sensor-service-secret.yml)

### Deploy Application with Kubernetes

> `📝` **Note:**
>
> Application will run the **development** namespace meanwhile the kafka helm chart will be installed in the **default**
> namespace.
> To Switch between namespaces use the following command:
> ```shell
> kubectl config set-context --current --namespace=development
> ```

- Deploy

```shell
kubectl create namespace development

kubectl apply -f kubernetes/development-yamls --recursive
```

- Validate Deployment

```shell
kubectl get deploy,svc,secrets,cm,pod
```

```text
NAME                                       READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/alert-service-deployment   1/1     1            1           2m55s
deployment.apps/irrigation-service         1/1     1            1           2m55s
deployment.apps/sensor-service             1/1     1            1           2m55s

NAME                                 TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
service/alert-service-service        ClusterIP   10.96.132.207   <none>        8080/TCP   2m55s
service/irrigation-service-service   ClusterIP   10.96.192.64    <none>        8080/TCP   2m55s
service/sensor-service-service       ClusterIP   10.96.183.242   <none>        8080/TCP   2m54s

NAME                               TYPE     DATA   AGE
secret/alert-service-secret        Opaque   12     2m55s
secret/irrigation-service-secret   Opaque   1      2m55s
secret/sensor-service-secret       Opaque   1      2m55s

NAME                                     DATA   AGE
configmap/alert-service-configmap        7      2m55s
configmap/irrigation-service-configmap   7      2m55s
configmap/kube-root-ca.crt               1      2m57s
configmap/sensor-service-configmap       7      2m55s

NAME                                            READY   STATUS    RESTARTS      AGE
pod/alert-service-deployment-7bd6d486bb-s9cwh   1/1     Running   0             2m55s
pod/irrigation-service-65f784cc69-2f4b2         1/1     Running   0             2m55s
pod/sensor-service-7c4c89b677-ldhf7             1/1     Running   2 (19s ago)   2m55s
```

### Postman Collection:

- Created 2 env vars for localhost & deployed server holding the server url
- All requests have saved response example to show working scenario and negative scenario
  Postman Collection with enviroment variables exist
  here: https://github.com/AhmedGodaa/IrrigationSystemBM/tree/production/postman-collections

![256684411-5983484b-f255-42d2-b0ef-0c18eefd30c7](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/dbabbc72-3f4d-49a8-8533-d1b0e9a8c25b)
![256684484-679577d2-97b3-4035-9a7b-c893a8a5540a](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/f71c197b-28ce-49ea-8126-8766f95ec9c1)

### MongoDB:

- Deploying sql database will time consuming but i have good experience with sql databases and i can do it if needed
- I used MongoDB Atlas to deploy the database and i used MongoDB Compass to manage the database
  ![256685150-ba03d811-7229-4c88-8e70-0fe9d8f061e1](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/e45d59b6-ba67-4c56-901b-a260f289c832)

### Simple Implementation of Unit Testing

- show simple implementation of unit testing for the controller of the irrigation service
  ![256685249-22180024-0077-413a-9d13-929329c39fde](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/6e344bf6-9ad2-4b0a-97ca-0d534b3d90ea)

### Firebase:

- In alert service i used firebase to send push notifications using FCM (Firebase Cloud Messaging)
  ![256685313-95db9b00-a6df-41d4-82f1-e711a5875e16](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/dc954f9d-3826-4d54-8094-e577af4f7b3e)

### API First Approach & GitHub Packages

- to prevent existance of duplicate code between the services like models classes "datalayer" it been published as
  dependency on github packages and it should be consumed at the services
  ![256685406-ea9c0428-7029-40d4-ae1c-c5b0cd81d4b3](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/41fbcca2-5227-43eb-b72d-c714ec442a33)




