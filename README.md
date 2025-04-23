🧘 Dosha Assessment Application
A simple Java Swing-based desktop application to assess the dominant Dosha (Vata, Pitta, or Kapha) of a user based on a short quiz. The application includes user login, quiz-based evaluation, and stores results in a MySQL database.

🚀 Features
🔐 User Login System with hardcoded credentials.

📋 Dosha Quiz with multiple questions and radio button-based selection.

💾 MySQL Integration to store and retrieve user results.

🖥️ Built using Java Swing for GUI development.

🛠️ Tech Stack
Java

Java Swing (GUI)

MySQL (Database)

JDBC (Database connectivity)

🧪 How It Works
Login: Users enter a username and password to begin.

Assessment: A 3-question dosha assessment is conducted using radio buttons.

Submission: Based on answers, the dominant dosha is determined.

Storage: Result is stored in a MySQL database under the user's name.

📂 File Structure
Main.java – Entry point of the application.

Login.java – Manages the user login UI and validation.

Evaluation.java – Contains the dosha quiz interface and logic.

DatabaseManager.java – Handles database connectivity and result storage.

