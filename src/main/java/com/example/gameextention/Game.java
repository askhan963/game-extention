package com.example.gameextention;

import com.example.gameextention.logic.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {
    private GameBoard gameBoard;
    private Hero hero;
    Button[][] buttons = new Button[SIZE_X][SIZE_Y];
    private List<Button> directionButtons;
    private List<Button> specialDirectionButtons;
    private static final int SIZE_X = 5;
    private static final int SIZE_Y = 5;
    private Scene scene;
    private Stage stage;
    private static final double BUTTON_SIZE = 70;
    private static final double SCENE_WIDTH = 400;
    private static final double SCENE_HEIGHT = 550;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.hero = new Mage(this);
        this.gameBoard = new GameBoard(SIZE_X, SIZE_Y);

        // TODO: Task 1.1, Initialization of the gameBoard visualization.
        GridPane gridPane = new GridPane();
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setTextAlignment(TextAlignment.CENTER);
                buttons[i][j].setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
                gridPane.add(buttons[i][j], i, j);
            }
        }
        // TODO: Task 1.2, Movement buttons configuration.
        directionButtons = new ArrayList<>();
        directionButtons.add(new Button("Up"));
        directionButtons.add(new Button("Down"));
        directionButtons.add(new Button("Left"));
        directionButtons.add(new Button("Right"));
        HBox directionButtonsContainer = new HBox();
        directionButtonsContainer.getChildren().addAll(directionButtons);
        applyStyleForButtons(directionButtons);
        applyStyleForButtons(directionButtons);
        addDirectionButtonsFunctionality();

        // TODO: Task 1.3, Ability buttons configuration.
        specialDirectionButtons = new ArrayList<>();
        specialDirectionButtons.add(new Button("Special Up"));
        specialDirectionButtons.add(new Button("Special Down"));
        specialDirectionButtons.add(new Button("Special Left"));
        specialDirectionButtons.add(new Button("Special Right"));
        HBox specialDirectionButtonsContainer = new HBox();
        specialDirectionButtonsContainer.getChildren().addAll(specialDirectionButtons);
        applyStyleForButtons(specialDirectionButtons);
        addSpecialDirectionButtonsFunctionality();


        // TODO: Task 2.1, Add an HBox for the movement buttons, an HBox for the ability buttons and add them to a VBox. Add the VBox to the gridPane.

        VBox vBox = new VBox();
        vBox.getChildren().addAll(directionButtonsContainer, specialDirectionButtonsContainer);
        gridPane.add(vBox, SIZE_X, SIZE_Y);

        scene = new Scene(gridPane, SCENE_WIDTH * 3, SCENE_HEIGHT * 1.3);
        stage.setScene(scene);
        stage.setTitle("Game");
        stage.setResizable(false);
        stage.show();
        updateUI();
    }

    /**
     * Applies custom (inline) css to the buttons.
     *
     * @param buttonsList List of the buttons without styles.
     */
    private void applyStyleForButtons(List<Button> buttonsList) {
        // TODO: Task 1.2, Movement buttons configuration.
        for (Button button : buttonsList) {

            button.setStyle("-fx-background-color: #3f51b5;    -fx-border-insets: 5px;   -fx-background-insets: 5px; -fx-text-fill: white; -fx-font-size: 20px;");
        }

    }

    /**
     * Adds functionality to specified direction buttons.
     * When a direction button is clicked, it will call the move method with the corresponding direction and then update the UI.
     */
    private void addDirectionButtonsFunctionality()  {
        // TODO: Task 1.2, Movement buttons configuration.
        if (directionButtons != null) {
            for (Button button : directionButtons) {
                button.setOnAction(event -> {
                    String buttonText = button.getText();
                    switch (buttonText) {
                        case "Up":
                            // Move the player up
                            try {
                                move('U');
                            } catch (Exception e) {
                                try {
                                    throw    new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            break;
                        case "Down":
                            // Move the player down
                            try {
                                move('D');
                            } catch (Exception e) {
                                try {
                                    throw  new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            break;
                        case "Left":
                            // Move the player left
                            try {
                                move('L');
                            } catch (Exception e) {
                                try {
                                    throw   new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }

                            break;
                        case "Right":
                            // Move the player right
                            try {
                                move('R');
                            } catch (Exception e) {
                                try {
                                    throw   new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid Move");
                            break;
                    }
                    updateUI();
                });
            }
        }

    }

    /**
     * Adds functionality to specified hero's special ability buttons.
     * When a hero's special ability button is clicked, it will call the hero's special ability method with the corresponding direction and then update the UI.
     */
    private void addSpecialDirectionButtonsFunctionality() {
        // TODO: Task 1.3, Ability buttons configuration.
        if (specialDirectionButtons != null) {
            for (Button button : specialDirectionButtons) {
                button.setOnAction(event -> {
                    String buttonText = button.getText();

                    switch (buttonText) {
                        case "Special Up":

                            try {
                                hero.useSpecialPower('U');
                            } catch (Exception e) {
                                try {
                                    throw    new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            break;
                        case "Special Down":
                            // Move the player down
                            try {
                                hero.useSpecialPower('D');
                            } catch (Exception e) {
                                try {
                                    throw   new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            break;
                        case "Special Left":
                            // Move the player left
                            try {
                                hero.useSpecialPower('L');
                            } catch (Exception e) {
                                try {
                                    throw   new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            break;
                        case "Special Right":
                            // Move the player right
                            try {
                                hero.useSpecialPower('R');
                            } catch (Exception e) {
                                try {
                                    throw   new IllegalMoveException("Invalid call");
                                } catch (IllegalMoveException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid Move");
                            break;
                    }

                    updateUI();
                    if (isWon()) {
                        informationAlert("You won!", "You won the game!");
                        restart();
                    }
                });
            }
        }
    }





    /**
     * Updates the current game status by updating text of the buttons in the
     * gridPane.
     */
    private void updateUI() {
        for (int i = 0; i < gameBoard.getSizeX(); i++) {
            for (int j = 0; j < gameBoard.getSizeY(); j++) {
                // TODO: Task 1.1
                // Uncomment this line, once you have implemented Task 1
                buttons[i][j].setText(String.valueOf(gameBoard.get(i, j)));
            }
        }
    }

    /**
     * Moves the character on the gameBoard in the indicated direction.
     *
     * @param direction Direction to move.
     * @throws IllegalMoveException Thrown if unsupported move has been detected.
     */
    public void move(char direction) throws IllegalMoveException {
        int deltaX = 0;
        int deltaY = 0;
        if (direction == 'L') {
            deltaX = -1;
            if (this.hero.getPosX() == 0) {
                throw new IllegalMoveException("IllegalMoveException: Cannot move left, out of bounds!");
            }
        } else if (direction == 'R') {
            deltaX = 1;
            if (this.hero.getPosX() == this.gameBoard.getSizeX() - 1) {
                throw new IllegalMoveException("IllegalMoveException: Cannot move right, out of bounds!");
            }
        } else if (direction == 'U') {
            deltaY = -1;
            if (this.hero.getPosY() == 0) {
                throw new IllegalMoveException("IllegalMoveException: Cannot move up, out of bounds!");
            }
        } else if (direction == 'D') {
            deltaY = 1;
            if (this.hero.getPosY() == this.gameBoard.getSizeY() - 1) {
                throw new IllegalMoveException("IllegalMoveException: Cannot move down, out of bounds!");
            }
        }
        if (this.gameBoard.get(this.hero.getPosX() + deltaX, this.hero.getPosY() + deltaY) == 'M') {
            informationAlert("You died!", "A monster has killed you!");
            restart();
        }
        this.gameBoard.set(this.hero.getPosX(), this.hero.getPosY(), '_');
        this.hero.setPosX(this.hero.getPosX() + deltaX);
        this.hero.setPosY(this.hero.getPosY() + deltaY);
        this.gameBoard.set(this.hero.getPosX(), this.hero.getPosY(), 'H');
        if (isWon()) {
            informationAlert("You won!", "You won the game!");
            restart();
        }
    }

    private void restart() {
        stage.close();
        Game game = new Game();
        game.start(stage);
    }

    /**
     * Shows user information alert.
     *
     * @param title   Title of the alert window.
     * @param content Content text of the alert.
     */
    private void informationAlert(String title, String content) {
        // TODO: Task 2.2, Displaying information alerts.
        Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
        winAlert.setTitle(title);
        winAlert.setHeaderText(content);
        winAlert.showAndWait();
        restart();
    }

    /**
     * Verify if the hero reached the goal.
     *
     * @return Result of the verification.
     */
    public boolean isWon() {
        return gameBoard.get(gameBoard.getSizeX() - 1, gameBoard.getSizeY() - 1) == 'H' && this.hero.getPosX() == gameBoard.getSizeX() - 1 && this.hero.getPosY() == gameBoard.getSizeY() - 1;
    }

    // Required for logic.
    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    // Required for logic.
    public Hero getHero() {
        return this.hero;
    }

}
