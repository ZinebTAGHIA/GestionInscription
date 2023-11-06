# School Management App
This is a web application for managing students, roles, and fields in a school environment.

- Technologies Used
  - Spring Boot
  - MySQL
  - React JS
- Setup
  - Backend (Spring Boot)
    1- Open application.properties located in src/main/resources.
    2- Update the following properties to match your MySQL database configuration:
    
      spring.datasource.url=jdbc:mysql://localhost:3307/school?serverTimezone=UTC
      spring.datasource.username=root
      spring.datasource.password=

    3- You can change the server port in the application.properties file if needed:
    
      server.port = 8082
  - Frontend (React JS)
    For the frontend part, you'll need to set up your React environment separately.
    1- Navigate to the frontend directory
    2- Install dependencies:

      npm install

    3- Start the React app:

      npm start
- API Endpoints
    - Filiere
      POST /api/filieres: Create a new filiere.
      DELETE /api/filieres/{id}: Delete a filiere by ID.
      PUT /api/filieres/{id}: Update a filiere by ID.
      GET /api/filieres: Get all filieres.
      GET /api/filieres/{id}: Get a filiere by ID.
    - Role
      POST /api/roles: Create a new role.
      DELETE /api/roles/{id}: Delete a role by ID.
      PUT /api/roles/{id}: Update a role by ID.
      GET /api/roles: Get all roles.
      GET /api/roles/{id}: Get a role by ID.
    - Student
      POST /api/students: Create a new student.
      DELETE /api/students/{id}: Delete a student by ID.
      PUT /api/students/{id}: Update a student by ID.
      GET /api/students: Get all students.
      GET /api/students/{id}: Get a student by ID.
      GET /api/students/filiere/{id}: Get students by filiere ID.
      
- React Components
  - SharedLayout Component
    The SharedLayout component serves as a layout template for the application. It includes a sidebar, navbar, and content section.
  - Sidebar Component
    The Sidebar component provides a sidebar menu with navigation links to various sections of the application.
  - Dashboard Component
    The Dashboard component serves as the main landing page of the application. It displays links to various sections for managing filieres, roles, and students
  - ListeEtudiants Component
    The ListeEtudiants component is responsible for displaying a list of students, allowing the user to perform operations such as editing and deleting student records.
  - ListeFilieres Component
    The ListeFilieres component is responsible for displaying a list of filieres, allowing the user to perform operations such as editing and deleting filiere records.
  - ListeRoles Component
    The ListeRoles component is responsible for displaying a list of roles, allowing the user to perform operations such as editing and deleting role records.
  - AddEtudiant Component
    The AddEtudiant component allows you to add a new student. It includes a form with fields for entering student details such as name, phone number, email, role, etc. When the form is submitted, it sends a POST request to create a new student.
  - AddFiliere Component
    The AddFiliere component allows you to add a new filiere. It includes a form with fields for entering filiere details such as code and name. When the form is submitted, it sends a POST request to create a new filiere.
  - AddRole Component
    The AddRole component allows you to add a new role. It includes a form with a field for entering the role name. When the form is submitted, it sends a POST request to create a new role.
- Demo
  Here is the YouTube video link for the app demo:
  https://youtu.be/gM5FSeVDph8
