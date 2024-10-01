import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PredatorPreySimulator extends Application {

    private List<Entity> entities = new ArrayList<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private boolean running = true;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        HBox controlPanel = new HBox(10);
        
        Slider speedSlider = new Slider(1, 10, 1);
        speedSlider.setShowTickMarks(true);
        speedSlider.setShowTickLabels(true);
        Label speedLabel = new Label("Speed:");
        
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        
        controlPanel.getChildren().addAll(speedLabel, speedSlider, startButton, stopButton);
        root.setBottom(controlPanel);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Predator-Prey Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create 200 entities (100 prey, 100 predators)
        createEntities(root, 100, 100);
        
        // Start simulation loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateEntities(speedSlider.getValue());
            }
        };
        
        startButton.setOnAction(e -> running = true);
        stopButton.setOnAction(e -> running = false);

        timer.start();
    }

    private void createEntities(BorderPane root, int preyCount, int predatorCount) {
        for (int i = 0; i < preyCount; i++) {
            Entity prey = new Entity(false);
            entities.add(prey);
            root.getChildren().add(prey.getCircle());
        }

        for (int i = 0; i < predatorCount; i++) {
            Entity predator = new Entity(true);
            entities.add(predator);
            root.getChildren().add(predator.getCircle());
        }
    }

    private void updateEntities(double speedMultiplier) {
        if (running) {
            for (Entity entity : entities) {
                if (!entity.isAlive()) continue;

                Entity closestPrey = null;
                if (entity.isPredator()) {
                    closestPrey = findClosestPrey(entity);
                }

                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() {
                        entity.move(speedMultiplier, closestPrey);
                        return null;
                    }
                };
                executorService.submit(task);
            }
        }
    }

    private Entity findClosestPrey(Entity predator) {
        Entity closestPrey = null;
        double minDistance = Double.MAX_VALUE;

        for (Entity entity : entities) {
            if (!entity.isPredator() && entity.isAlive()) {
                double distance = Math.sqrt(Math.pow(predator.getX() - entity.getX(), 2)
                        + Math.pow(predator.getY() - entity.getY(), 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPrey = entity;
                }
            }
        }

        return closestPrey;
    }

    @Override
    public void stop() throws Exception {
        executorService.shutdown();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
