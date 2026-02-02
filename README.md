# Hungarian Algorithm Solver

A Java Swing application that implements the Hungarian algorithm to solve the assignment problem. The application provides a graphical user interface for users to input a cost matrix and find the optimal assignment with the minimum possible cost.

## Features

- **Graphical User Interface (GUI):** A user-friendly interface built with Java Swing for easy interaction.
- **Dynamic Cost Matrix:** Users can specify the dimensions (rows and columns) of the cost matrix.
- **Optimal Assignment Calculation:** Implements the Hungarian algorithm to find the most efficient assignment solution.
- **Results Display:** Shows the final pairings and the calculated total minimum cost.
- **Login System:** Includes a fully functional (though currently bypassed) login screen for user authentication.

## How to Run the Project

The project is a standard Java application and can be run from the command line.

1.  **Compile the source code:**
    Navigate to the project's root directory and compile the Java files into the `bin` directory.
    ```bash
    javac -d bin src/invetigaciónDeOperaciones/*.java src/module-info.java
    ```

2.  **Run the application:**
    Once compiled, run the main class from the `bin` directory.
    ```bash
    java -cp bin invetigaciónDeOperaciones.Main
    ```

## Project Structure

The source code is located in the `src/invetigaciónDeOperaciones/` directory and is organized as follows:

- **`Main.java`**: The entry point for the application. It initializes and runs the main user interface.

- **`Menu.java`**: The core component of the application. It handles the main UI, including the creation of the cost matrix table, user input, and orchestrating the execution of the Hungarian algorithm.

- **`HungarianSolver.java`**: Contains the logic for the Hungarian algorithm itself. It processes the cost matrix to find the optimal assignment.

- **`LoginPage.java`**: Implements the user login window. It authenticates users against a predefined set of credentials.

- **`IDandPassword.java`**: A simple data class that provides hardcoded username and password information for the `LoginPage`.