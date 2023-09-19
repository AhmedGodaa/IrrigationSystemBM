# IrrigationSystem

### Deploy Application with Kubernetes ~~without helm~~

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

```

### Microservices System Sequence

![256683967-8cd3b579-1d12-48d4-b107-3637f25462ac](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/7ee2357b-4873-4e4e-a6b1-d952474e8171)

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




