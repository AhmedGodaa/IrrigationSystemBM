# IrrigationSystem
### Secrets
- In Development, it shouldn't upload any credentials or any sensitive data to GitHub like Database credentials, API keys, Firebase, etc.
- We should always use environment variables to store credentials and sensitive data
- This can be done by Docker env vars or kubernetes secrets and workflows credentials of GitHub actions should be stored in GitHub secrets && env vars
- It should be implemented in this way and added to IntelliJ run configurations env vars
![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/79a01353-f669-4121-a01c-617cb9ab6c3c)
- But in this POC the credentials will be exposed to simply test the project
### MongoDB:
mongodb+srv://banquemisr:nvfYiV4HHsj4FVkE@banquemisr.r01q6gc.mongodb.net/

### PostMan Collection:
- Created 2 env vars for localhost & deployed server holding the server url
- All requests have saved response example to show working scenario and negative scenario

  ![image](https://github.com/AhmedGodaa/IrrigationSystemBM/assets/73083104/7c401125-9996-4a37-81fa-bca540728abf)
  
### AWS:
- Deployed on AWS EC2 instance i used my free their account to deploy the server and assigned elastic static ip address
- Open port 8444 & 8445 & 8446 for each service in the server to be accessible from outside using TCP protocol
- The deployment is manual no CI/CD pipeline is implemented yet i should use EKS
- If the system was handling with files system I have good experience writing APIs interact with s3 buket showing security implementation by spring security or pre-signed Url and user file Management System

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


