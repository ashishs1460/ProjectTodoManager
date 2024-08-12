## 🛠️ Setup Instructions to Run Locally

1. **Clone the Backend Repository**:  

2. **Execute SQL Script**:  
- Navigate to `src/main/resources/sql`.
- Execute the `todo.sql` script using your preferred database workbench.

3. **Update Database Configuration**:  
- In the `application.yaml` file, update the database configuration with your local database details.

4. **Configure CORS**:  
- In the `SecurityConfig` class (within the `configuration` package), update the CORS configuration with your frontend local URL.

5. **Run the Backend Application**:  
