# School Management App
This is a web application for managing students, roles, and fields in a school environment.

## Technologies Used
  - Spring Boot
  - MySQL
  - React JS
  - PrimeReact
## Setup
  ### Backend (Spring Boot)
  1- Open application.properties located in src/main/resources.
    
  2- Update the following properties to match your MySQL database configuration:
    
  ```
          spring.datasource.url=jdbc:mysql://localhost:3307/school?serverTimezone=UTC
    
          spring.datasource.username=root
    
          spring.datasource.password=
  ```

  3- You can change the server port in the application.properties file if needed:
    
  ``` server.port = 8082 ```
    
  ### Frontend (React JS)
    
  For the frontend part, you'll need to set up your React environment separately.
    
  1- Navigate to the frontend directory
    
  2- Install dependencies:

  ```npm install```

  3- Start the React app:

  ```npm start```
    
## API Endpoints
  
  ### Filiere
      
  **POST** /api/filieres: Create a new filiere.
      
  **DELETE** /api/filieres/{id}: Delete a filiere by ID.
      
  **PUT** /api/filieres/{id}: Update a filiere by ID.
      
  **GET** /api/filieres: Get all filieres.
      
  **GET** /api/filieres/{id}: Get a filiere by ID.
      
  ### Role
      
  **POST** /api/roles: Create a new role.
      
  **DELETE** /api/roles/{id}: Delete a role by ID.
      
  **PUT** /api/roles/{id}: Update a role by ID.
      
  **GET** /api/roles: Get all roles.
      
  **GET** /api/roles/{id}: Get a role by ID.
      
  ### Student
      
  **POST** /api/students: Create a new student.
      
  **DELETE** /api/students/{id}: Delete a student by ID.
      
  **PUT** /api/students/{id}: Update a student by ID.
      
  **GET** /api/students: Get all students.
      
  **GET** /api/students/{id}: Get a student by ID.
      
  **GET** /api/students/filiere/{id}: Get students by filiere ID.
      
## React Components
  
  - **SharedLayout Component**
    
    The SharedLayout component serves as a layout template for the application. It includes a sidebar, navbar, and content section.
    
  - **Sidebar Component**
    
    The Sidebar component provides a sidebar menu with navigation links to various sections of the application.
    
  - **Dashboard Component**
    
    The Dashboard component serves as the main landing page of the application. It displays links to various sections for managing filieres, roles, and students
    
  - **ListeEtudiants Component**
    
    The ListeEtudiants component is responsible for displaying a list of students, allowing the user to perform operations such as editing and deleting student records.
    
  - **ListeFilieres Component**
    
    The ListeFilieres component is responsible for displaying a list of filieres, allowing the user to perform operations such as editing and deleting filiere records.
    
  - **ListeRoles Component**
    
    The ListeRoles component is responsible for displaying a list of roles, allowing the user to perform operations such as editing and deleting role records.
    
  - **AddEtudiant Component**
    
    The AddEtudiant component allows you to add a new student. It includes a form with fields for entering student details such as name, phone number, email, role, etc. When the form is submitted, it sends a POST request to create a new student.
    
  - **AddFiliere Component**
    
    The AddFiliere component allows you to add a new filiere. It includes a form with fields for entering filiere details such as code and name. When the form is submitted, it sends a POST request to create a new filiere.
    
  - **AddRole Component**
    
    The AddRole component allows you to add a new role. It includes a form with a field for entering the role name. When the form is submitted, it sends a POST request to create a new role.
    
## Demo
  
  Here is the YouTube video link for the app demo:
  
  https://youtu.be/gM5FSeVDph8

## Screenshots

![Capture d'écran 2023-11-06 225334](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/18f34a72-1dbc-47b1-8dd0-45ae6cab6063)

![Capture d'écran 2023-11-06 224502](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/45eaa83d-0b7b-4005-912b-d521b2dedfb3)

![Capture d'écran 2023-11-06 224551](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/069348b6-7dc5-4ac4-bae2-281f05a1b41e)

![Capture d'écran 2023-11-06 224728](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/e9af49bb-8c3e-4c91-a343-1b3cfc3d351d)

![Capture d’écran (1189)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/6894dc14-97c2-4c2e-8953-860f16233f73)

![Capture d’écran (1190)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/d6a317ae-76b0-41a1-9692-0e7b6d394a66)

![Capture d’écran (1192)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/b911f9a9-7ee9-4318-b6f9-7fa12390fa67)

![Capture d’écran (1191)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/403bbb4a-2644-435c-85cc-db8a40a4ce88)

![Capture d’écran (1193)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/b5b99a1e-f72e-462d-849c-e58558fc67e9)

![Capture d’écran (1196)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/863fbc0f-3d79-4275-9afd-0501bbacd807)

![Capture d’écran (1198)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/43e8b331-2c05-4cb8-ab87-5b7a68ea53b8)

![Capture d’écran (1197)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/6e1cc4eb-448a-4dff-a4bb-c9b97638055a)

![Capture d’écran (1199)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/4b34ad67-3612-4932-80d0-3552e4998e5f)

![Capture d’écran (1200)](https://github.com/ZinebTAGHIA/GestionInscription/assets/102872040/57edef5e-9673-41b0-8b99-dadd29541f0a)
