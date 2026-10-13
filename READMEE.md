# Course Registration System

## 1. Setup and Execution Instructions

### 1.1 Prerequisites

Install the tools:

- JDK 17 or later
- Apache Maven 3.x
- Git
- Docker
- Jenkins
- Minikube
- kubectl

project structure
<img width="303" height="352" alt="image" src="https://github.com/user-attachments/assets/3eb6d8fe-ce1b-4ae3-a53f-61c8473d346e" />


### 1.2 Clone the Repository
```
git clone 
cd course-registration 

```

<img width="970" height="295" alt="Screenshot from 2026-10-13 09-31-42" src="https://github.com/user-attachments/assets/fa9c1e93-2284-4689-90c9-4709e5647327" />
<img width="1097" height="730" alt="Screenshot from 2026-10-13 10-02-02" src="https://github.com/user-attachments/assets/e56f405c-5e63-47b5-9e5a-53aaf083650d" />

final git log
<img width="1075" height="227" alt="Screenshot from 2026-10-13 11-40-59" src="https://github.com/user-attachments/assets/111f605a-59d1-4034-aedf-26f463df2925" />

---
### Task 2
Build and Test

Clean the project and execute the complete test suite:
```
mvn clean test
```

Package the application:
```
mvn package
```

The generated JAR is located under:
```
course-registration-app/target/
```

JUnit XML reports are generated under:
```
course-registration-app/target/surefire-reports/
```

parent pom.xml
The parent POM defines shared project properties, manages the JUnit dependency version, and declares the child module.

child modules are defined within <module> tags
<img width="993" height="807" alt="Screenshot from 2026-10-13 09-55-30" src="https://github.com/user-attachments/assets/31c37c5a-b70d-478f-a39d-0d8531ecffc8" />
<img width="993" height="807" alt="Screenshot from 2026-10-13 09-55-35" src="https://github.com/user-attachments/assets/ceab7633-cd28-4f46-8ff5-d84cf053afc3" />

child `pom.xml`

`<parent>` is defined to import the common definitions
<img width="968" height="575" alt="Screenshot from 2026-10-13 09-55-08" src="https://github.com/user-attachments/assets/f373da62-f86c-4136-a10f-f033214f5d09" />

build commands output:
```
mvn clean package
mvn test
```

<img width="1108" height="828" alt="Screenshot from 2026-10-13 09-57-06" src="https://github.com/user-attachments/assets/1e5b957a-6eea-49bf-9cc5-260590396ea7" />
<img width="1108" height="828" alt="Screenshot from 2026-10-13 09-57-11" src="https://github.com/user-attachments/assets/acd7e7df-949b-49ed-9383-cecb63467109" />

project structure:
<img width="1255" height="833" alt="Screenshot from 2026-10-13 09-59-25" src="https://github.com/user-attachments/assets/7d888c6f-45e5-4761-877e-10f3e8bea2d6" />
<img width="1255" height="833" alt="Screenshot from 2026-10-13 09-59-29" src="https://github.com/user-attachments/assets/0a5283b6-12c5-4bb9-9c67-2ca6a4312dd7" />


---
### Task 3
Diagnose and fix the capacity boundary defect

modified the RegistrationService class to correctly handle the edge case of near maximum capacity
<img width="803" height="453" alt="Screenshot from 2026-10-13 10-17-08" src="https://github.com/user-attachments/assets/da32fd06-dba5-4fa2-9d82-b0bc84c21906" />

added related tests to verify
one seat remaining
<img width="797" height="764" alt="Screenshot from 2026-10-13 10-40-34" src="https://github.com/user-attachments/assets/927372f7-d1fb-46c2-a2e6-2af0df743338" />

over capacity registering (should fail)
<img width="797" height="764" alt="Screenshot from 2026-10-13 10-40-50" src="https://github.com/user-attachments/assets/cd7c3b90-d0e3-4207-96b6-6ef71b6759fa" />
<img width="797" height="764" alt="Screenshot from 2026-10-13 10-40-53" src="https://github.com/user-attachments/assets/df3bd885-f8c9-4595-9873-940baa7fa498" />

after running mvn clean test
<img width="965" height="802" alt="Screenshot from 2026-10-13 10-41-23" src="https://github.com/user-attachments/assets/584b3b4d-8826-470b-937c-2f0ee17b1a78" />

---
### Task 4
Course withdrawal

first, modified the existing classes and added extra methods to allow for withdrawal logic

added removeRegistration method in Student class
<img width="1029" height="761" alt="Screenshot from 2026-10-13 10-55-09" src="https://github.com/user-attachments/assets/b634e314-9382-4c09-aa8f-5228c3633933" />

added decrement in Course class
<img width="1094" height="182" alt="Screenshot from 2026-10-13 11-02-00" src="https://github.com/user-attachments/assets/a7201708-feff-4324-90dc-f597c288e464" />

finally, added withdraw method in RegistrationService class
<img width="1095" height="768" alt="Screenshot from 2026-10-13 11-01-34" src="https://github.com/user-attachments/assets/8c42d3a8-ea86-4d5f-89f1-65eee97add64" />

also added testing separately in a new WithdrawTest class
<img width="1104" height="850" alt="Screenshot from 2026-10-13 11-06-34" src="https://github.com/user-attachments/assets/6cc0c3cb-64cf-44a1-b1f0-460cb7284db6" />
<img width="1104" height="850" alt="Screenshot from 2026-10-13 11-06-40" src="https://github.com/user-attachments/assets/611904b5-34d9-4310-9325-3ae8d7cec4f8" />
<img width="1104" height="850" alt="Screenshot from 2026-10-13 11-06-46" src="https://github.com/user-attachments/assets/d6218fe8-8992-43eb-b816-eb1a80f5d80f" />

verified with tests
<img width="1104" height="850" alt="Screenshot from 2026-10-13 11-06-54" src="https://github.com/user-attachments/assets/12ade2cf-c17e-40fe-a1ce-fee8a370a880" />

---
### Task 5
Jenkins Declarative Pipeline

defined pipeline using Jenkinsfile
The pipeline outlines the various stages that occur. It ensures the project has to pass the required tests before running builds and installing/deploying the project.
<img width="1102" height="755" alt="Screenshot from 2026-10-13 11-24-38" src="https://github.com/user-attachments/assets/aeb7b963-77d7-474b-a60d-b939245dd70f" />
<img width="1102" height="755" alt="Screenshot from 2026-10-13 11-24-42" src="https://github.com/user-attachments/assets/942b0b33-ef09-4830-9753-a40189d1887d" />

added credentials to Jenkins
<img width="1290" height="838" alt="image" src="https://github.com/user-attachments/assets/bad60bcf-f400-490c-8867-a75fa612cc5b" />

set up polling within Jenkins. Every set period of time, it checks the remote repository and runs a build in case there were any changes made.
<img width="1301" height="697" alt="Screenshot from 2026-10-13 12-39-16" src="https://github.com/user-attachments/assets/0bcc0125-698b-4b86-9bd5-735a90bd4329" />

successfully built the project within jenkins
<img width="1506" height="424" alt="Screenshot from 2026-10-13 12-39-27" src="https://github.com/user-attachments/assets/83c2afaf-eeef-45f4-953e-031b8ed97981" />

---
### Task 6
Deploy and Manage the Application using Minikube

first created the config files, deployment and service.yaml
<img width="1102" height="755" alt="Screenshot from 2026-10-13 11-31-13" src="https://github.com/user-attachments/assets/4b836463-5b08-4593-9dcb-11346948109b" />
<img width="1101" height="430" alt="Screenshot from 2026-10-13 11-31-20" src="https://github.com/user-attachments/assets/ad591ef7-6743-41a9-bdac-b014cda02c0d" />

started minikube, applied configs and deployed successfully
<img width="1104" height="648" alt="Screenshot from 2026-10-13 11-33-22" src="https://github.com/user-attachments/assets/c79fad78-66e5-4625-bcac-5a1955aed50e" />

scaled up deployment, and checked ability to recover from crashes/delting
<img width="1106" height="732" alt="Screenshot from 2026-10-13 11-35-40" src="https://github.com/user-attachments/assets/bd955a87-2043-419f-b258-195c2de2e715" />

checked the container is accessible
<img width="1851" height="993" alt="Screenshot from 2026-10-13 11-36-49" src="https://github.com/user-attachments/assets/666cccc9-befa-4393-a447-41b33dc79756" />
