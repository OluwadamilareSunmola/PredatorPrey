# Predator-Prey Simulator

This is a 2D predator-prey ecosystem simulator built using Java and JavaFX. The simulator models a simple predator-prey dynamic with up to 200 entities, using multi-threading for efficient movement and interaction between entities. The GUI allows for real-time adjustments to simulation parameters such as simulation speed.

## Features

- **Multi-threaded simulation**: Each entity's movement is handled in a separate thread for improved performance.
- **Custom physics engine**: Entities move within the bounds of the screen, and predators hunt prey.
- **Rule-based behavior**: 
  - **Predators** (red) will hunt and eat prey.
  - **Prey** (green) will attempt to move randomly.
- **Real-time control**: Adjust the simulation speed and start/stop the simulation with the GUI control panel.
- **Extensible design**: The system is designed to be extensible, allowing for easy additions of new features like reproduction, energy depletion, and advanced behaviors.

## Getting Started

### Prerequisites

- [Java JDK 8 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- [JavaFX SDK](https://openjfx.io/) (if using a version of Java without built-in JavaFX support)

### Installing

1. Clone the repository to your local machine:

    ```bash
    git clone https://github.com/yourusername/PredatorPreySimulator.git
    ```

2. Ensure you have JavaFX correctly configured in your development environment.

3. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).

4. Run the `PredatorPreySimulator.java` file to start the simulation.

### Usage

- **Start Simulation**: Press the "Start" button to begin the simulation.
- **Stop Simulation**: Press the "Stop" button to pause the simulation.
- **Adjust Speed**: Use the slider to adjust the simulation speed in real-time.

## Future Enhancements

- **Reproduction cycles**: Entities reproduce under certain conditions.
- **Energy depletion**: Predators lose energy over time and must eat prey to survive.
- **Advanced behaviors**: Implement flocking behavior for prey and hunting strategies for predators.
- **OpenGL support**: Future versions will use OpenGL for improved graphics performance.

## Screenshots

*(Include screenshots or gifs of the simulation in action)*

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- JavaFX for the graphical user interface.
- OpenJDK for the development of Java.
