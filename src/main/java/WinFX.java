import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinFX {

    public WinFX(Stage stage, boolean draw, int player) {
        VBox box = new VBox(70);
        box.setAlignment(Pos.CENTER);
        Text message = new Text();
        message.setFont(Font.font ("Verdana", 25));
        message.setFill(Color.WHITE);
        if (draw) {
            message.setText("DRAW");
        } else if (player == 1){
            message.setText("Player 2 Won");
        } else {
            message.setText("Player 1 Won");
        }

        //creating a button to start new game
        Button playButton = new Button("PLAY AGAIN");
        playButton.setPrefSize(200, 50);
        playButton.setStyle("-fx-background-color: #D8BFD8;");
        playButton.setOnAction(e -> new GameFX(stage));

        //creating a button to exit
        Button exit = new Button("EXIT");
        exit.setPrefSize(200, 50);
        exit.setStyle("-fx-background-color: #D8BFD8;");
        exit.setOnAction(e -> Platform.exit());

        box.getChildren().addAll(message, playButton, exit);
        //background image
        Image image = new Image("wall.jpg");
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImage);
        box.setBackground(bGround);

        Scene scene = new Scene(box);

        stage.setHeight(350);
        stage.setWidth(500);
        stage.setTitle("GAME FINISHED");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
