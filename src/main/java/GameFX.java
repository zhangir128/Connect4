import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static javafx.scene.paint.Color.*;

public class GameFX {

    private boolean draw = false;
    private int playerMove = 1;
    private final List<GameButton> moves = new ArrayList<>();

    public GameFX(Stage stage) {
        //Game Play menu
        Menu gamePlay = new Menu("Game Play");
        MenuItem gameItem = new MenuItem("reverse move");
        gamePlay.getItems().add(gameItem);
        //Themes menu
        Menu themes = new Menu("Themes");
        MenuItem theme1 = new MenuItem("original theme");
        MenuItem theme2 = new MenuItem("theme one");
        MenuItem theme3 = new MenuItem("theme two");
        themes.getItems().addAll(theme1, theme2, theme3);

        //Options menu
        Menu options = new Menu("Options");
        MenuItem optionItem1 = new MenuItem("how to play");
        MenuItem optionItem2 = new MenuItem("new game");
        MenuItem optionItem3 = new MenuItem("exit");
        options.getItems().addAll(optionItem1, optionItem2, optionItem3);

        //Adding to menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(gamePlay, themes, options);

        Text turnInfo = new Text("Player 1 turn to move");
        turnInfo.setFont(Font.font ("Verdana", 15));
        turnInfo.setFill(PURPLE);
        Text moveInfo = new Text("No moves made yet");
        moveInfo.setFont(Font.font ("Verdana", 15));
        moveInfo.setFill(PURPLE);

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        GameButton[][] matrix = new GameButton[7][6];

        //Adding buttons
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                
                GameButton button = new GameButton();
                button.setText("☺");
                button.setFont(new Font(40));
                button.setPrefSize(100, 100);
                button.setStyle("-fx-background-color: #D8BFD8;");
                button.setX(i);
                button.setY(j);

                //mark valid buttons
                if (j == 5) {
                    button.validMove();
                }

                //add the button
                pane.add(button, i, j);
                matrix[i][j] = button;
                
                button.setOnAction(e -> {

                    //check for the win condition
                    if (checkWin(matrix)) {
                        //wait 2 seconds and end the game
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        new WinFX(stage, draw, playerMove);
                    }

                    if (!button.checkMove()) {
                        moveInfo.setText("Player " + playerMove + " moved to\n"
                                + "column: " + (button.getX() + 1) + "\n" + "row: " + (button.getY() + 1) +
                                "\n" + "which is invalid move,\nplease move again");
                    } else {
                        button.setClicked();
                        moves.add(button);

                        if (button.getY() != 0) {
                            matrix[button.getX()][button.getY() - 1].validMove();
                        }

                        moveInfo.setText("Player " + playerMove + " moved to\n"
                                + "column: " + (button.getX() + 1) + "\n" + "row: " + (button.getY() + 1));

                        if (playerMove == 1) {
                            turnInfo.setText("Player 2 turn to move");
                            button.setColorRed(button);
                            button.setDisable(true);
                            playerMove = 2;
                        } else if (playerMove == 2) {
                            turnInfo.setText("Player 1 turn to move");
                            button.setColorBlue(button);
                            button.setDisable(true);
                            playerMove = 1;
                        }
                    }
                });

            }
        }

        // reverse move action
        gameItem.setOnAction(e -> {
            if (moves.size() == 0) {
                moveInfo.setText("No move was made");
            } else {
                GameButton button = moves.get(moves.size() - 1);
                moves.remove(moves.size() - 1);

                button.setStyle("-fx-background-color: #D8BFD8;");
                button.setDisable(false);
                for (int i = 0; i < button.getY(); i++) {
                    matrix[button.getX()][i].notValidMove();
                }
                if (playerMove == 1) {
                    playerMove = 2;
                } else {
                    playerMove = 1;
                }
            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(YELLOW,CornerRadii.EMPTY, Insets.EMPTY)));

        borderPane.setTop(menuBar);
        borderPane.setLeft(turnInfo);
        borderPane.setRight(moveInfo);
        borderPane.setCenter(pane);
        //change the themes
        theme1.setOnAction(e -> borderPane.setBackground(new Background(new BackgroundFill(YELLOW,CornerRadii.EMPTY, Insets.EMPTY))));
        theme2.setOnAction(e -> borderPane.setBackground(new Background(new BackgroundFill(GREEN,CornerRadii.EMPTY, Insets.EMPTY))));
        theme3.setOnAction(e -> {
            turnInfo.setFill(BLUE);
            moveInfo.setFill(RED);
            Image image = new Image("saske.jpg");
            BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background bGround = new Background(bImage);
            borderPane.setBackground(bGround);
        });
        //how to play window
        optionItem1.setOnAction(e -> {
            Stage window = new Stage();
            VBox Vbox = new VBox(20);
            Text text1 = new Text("OBJECTIVE:");
            Text text2 = new Text("To be the first player to connect 4 of the same colored discs in a row (either vertically, horizontally, or diagonally)");
            Text text3 = new Text("HOW TO PLAY:");
            Text text4 = new Text("First, decide who goes first\n" +
                    "Players must alternate turns, and only one disc can be dropped in each turn.\n" +
                    "On your turn, drop one of your colored discs from the top into any of the seven slots.\n" +
                    "The game ends when there is a 4-in-a-row or a stalemate.\n" +
                    "The starter of the previous game goes second on the next game.");
            text1.setFont(Font.font ("Verdana", 20));
            text2.setFont(Font.font ("Verdana", 20));
            text3.setFont(Font.font ("Verdana", 20));
            text4.setFont(Font.font ("Verdana", 20));
            Vbox.getChildren().addAll(text1, text2, text3, text4);
            Vbox.setBackground(new Background(new BackgroundFill(GREEN,CornerRadii.EMPTY, Insets.EMPTY)));
            Vbox.setAlignment(Pos.CENTER);
            Scene windowScene = new Scene(Vbox, 1400, 500);
            window.setScene(windowScene);
            window.show();
        });
        //start a new game
        optionItem2.setOnAction(e -> new GameFX(stage));
        //exit
        optionItem3.setOnAction(e -> Platform.exit());


        Scene scene = new Scene(borderPane);
        stage.setHeight(700);
        stage.setWidth(1150);
        stage.setTitle("CONNECT FOUR");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public boolean checkWin(GameButton[][] matrix) {
        //check horizontal win
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 4; k++) {
                if(matrix[k][i].isClicked()) {
                    if (matrix[k][i].getColor().equals(matrix[k + 1][i].getColor()) && matrix[k + 1][i].getColor().equals(matrix[k + 2][i].getColor()) && matrix[k + 2][i].getColor().equals(matrix[k + 3][i].getColor())) {
                        return true;
                    }
                }
            }
        }
        //check vertical win
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 3; k++) {
                if(matrix[i][k].isClicked()) {
                    if (matrix[i][k].getColor().equals(matrix[i][k + 1].getColor()) && matrix[i][k + 1].getColor().equals(matrix[i][k + 2].getColor()) && matrix[i][k + 2].getColor().equals(matrix[i][k + 3].getColor())) {
                        return true;
                    }
                }
            }
        }
        //check diagonal win
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 3; k++) {
                if(matrix[i][k].isClicked()) {
                    if (matrix[i][k].getColor().equals(matrix[i + 1][k + 1].getColor()) && matrix[i + 1][k + 1].getColor().equals(matrix[i + 2][k + 2].getColor()) && matrix[i + 2][k + 2].getColor().equals(matrix[i + 3][k + 3].getColor())) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int k = 5; k > 2; k--) {
                if(matrix[i][k].isClicked()) {
                    if (matrix[i][k].getColor().equals(matrix[i + 1][k - 1].getColor()) && matrix[i + 1][k - 1].getColor().equals(matrix[i + 2][k - 2].getColor()) && matrix[i + 2][k - 2].getColor().equals(matrix[i + 3][k - 3].getColor())) {
                        return true;
                    }
                }
            }
        }
        //check for draw
        int d = 0;
        for(int i = 0; i < 7; i++) {
            if (matrix[i][0].isClicked()) {
                d++;
            }
        }
        if (d == 7) {
            draw = true;
            return true;
        }
        //no win or draw found
        return false;
    }
}
