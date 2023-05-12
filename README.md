# IrrigationSystem
### System Sequence

![adf drawio (2)](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/b30ccc22-5173-4733-b3bc-d1fbfdeb59f1)



### Secrets
- In Development, it shouldn't upload any credentials or any sensitive data to GitHub like Database credentials, API keys, Firebase, etc.
- We should always use environment variables to store credentials and sensitive data
- This can be done by Docker env vars or kubernetes secrets and workflows credentials of GitHub actions should be stored in GitHub secrets && env vars
- It should be implemented in this way and added to IntelliJ run configurations env vars
![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/79a01353-f669-4121-a01c-617cb9ab6c3c)
- But in this POC the credentials will be exposed to simply test the project
### MongoDB:
MongoDB Access Url:  mongodb+srv://banquemisr:nvfYiV4HHsj4FVkE@banquemisr.r01q6gc.mongodb.net/

### Postman Collection:
- Created 2 env vars for localhost & deployed server holding the server url
- All requests have saved response example to show working scenario and negative scenario
Postman Collection with enviroment variables exist here: https://github.com/AhmedGodaa/IrrigationSystemBM/tree/production/postman-collections

  ![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/7c401125-9996-4a37-81fa-bca540728abf)
  
### AWS:
- If need to showcase of deployment i can do this. i follow this to deploy manual  create ec2 instance allow tcp on application port ssh server and deploy jar file
- If the system was handling with files system I have good experience using spring boot with s3 buket showing security implementation by spring security or pre-signed Url and user file Management System based.

![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/01514cf3-d2ec-43d9-92ec-0372e106eaa8)
![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/31c71c45-54b1-4002-9a18-0cc3586a411f)
![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/48cb8630-f4f3-4498-bb2f-f4d86987be68)
![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/a6958467-44f8-4c49-9be1-d5514cde3606)

### MongoDB:
- Deploying sql database will time consuming but i have good experience with sql databases and i can do it if needed
- I used MongoDB Atlas to deploy the database and i used MongoDB Compass to manage the database

  ![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/9604d86a-ce46-4b2b-99ed-5c20a2e6405e)
  
### Firebase:
- In alert service i used firebase to send push notifications using FCM (Firebase Cloud Messaging)

  ![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/023da683-ef8e-4001-a615-b2b3bcaa6ead)
### DataLayer - GitHub Package 
- to prevent existance of duplicate code between the services like models classes "datalayer" it been published as dependency on github packages and it should be consumed at the services
![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/fc5d5056-6762-469b-a016-886a830c365e)


