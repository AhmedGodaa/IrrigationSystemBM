# IrrigationSystem
### Microservices System Sequence

![adf drawio (2)](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/8cd3b579-1d12-48d4-b107-3637f25462ac)



### Secrets
- In Development, it shouldn't upload any credentials or any sensitive data to GitHub like Database credentials, API keys, Firebase, etc.
- We should always use environment variables to store credentials and sensitive data
- This can be done by Docker env vars or kubernetes secrets and workflows credentials of GitHub actions should be stored in GitHub secrets && env vars
- It should be implemented in this way and added to IntelliJ run configurations env vars
![image](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/0f08d676-fc5f-4bc2-a177-e627abf6cf8c)
- But in this POC the credentials will be exposed to simply test the project
### MongoDB:
MongoDB Access Url:  mongodb+srv://banquemisr:nvfYiV4HHsj4FVkE@banquemisr.r01q6gc.mongodb.net/

### Postman Collection:
- Created 2 env vars for localhost & deployed server holding the server url
- All requests have saved response example to show working scenario and negative scenario
Postman Collection with enviroment variables exist here: https://github.com/AhmedGodaa/IrrigationSystemBM/tree/production/postman-collections


![image](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/5983484b-f255-42d2-b0ef-0c18eefd30c7)
![image](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/679577d2-97b3-4035-9a7b-c893a8a5540a)
### AWS:
- If need to showcase of deployment i can do this. i follow this to deploy manual  create ec2 instance allow tcp on application port ssh server and deploy jar file
- If the system was handling with files system I have good experience using spring boot with s3 buket showing security implementation by spring security or pre-signed Url and user file Management System based.
![237761221-01514cf3-d2ec-43d9-92ec-0372e106eaa8](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/cea4a0b7-d2eb-4ba1-a97c-453d83fe4259)
![237762322-31c71c45-54b1-4002-9a18-0cc3586a411f](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/670d295c-05e4-455a-b6e1-e60296f648ff)
![237762702-48cb8630-f4f3-4498-bb2f-f4d86987be68](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/a24f5fb4-95e3-4f3f-9e5e-85d98e1f07ba)
![237762884-a6958467-44f8-4c49-9be1-d5514cde3606](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/73705ac0-2e53-4d54-9219-b72151fb21fd)


### MongoDB:
- Deploying sql database will time consuming but i have good experience with sql databases and i can do it if needed
- I used MongoDB Atlas to deploy the database and i used MongoDB Compass to manage the database
![237761478-9604d86a-ce46-4b2b-99ed-5c20a2e6405e](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/ba03d811-7229-4c88-8e70-0fe9d8f061e1)

### Simple Implementation of Unit Testing
- show simple implementation of unit testing for the controller of the irrigation service
![238797033-b133aefa-26e0-48db-bfd2-9f7824e75f87](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/22180024-0077-413a-9d13-929329c39fde)
### Firebase:
- In alert service i used firebase to send push notifications using FCM (Firebase Cloud Messaging)
![237763628-023da683-ef8e-4001-a615-b2b3bcaa6ead](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/95db9b00-a6df-41d4-82f1-e711a5875e16)

### API First Approach & GitHub Packages
- to prevent existance of duplicate code between the services like models classes "datalayer" it been published as dependency on github packages and it should be consumed at the services
![237807330-fc5d5056-6762-469b-a016-886a830c365e](https://github.com/AhmedGodaa/IrrigationSystemBM-/assets/73083104/ea9c0428-7029-40d4-ae1c-c5b0cd81d4b3)



