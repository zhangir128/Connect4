import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeFX extends Application {
	public void start(Stage stage)  {
		stage.setTitle("WELCOME TO CONNECT FOUR");
		stage.setResizable(false);

		//text message
		Text text = new Text();
		text.setText("Welcome to the Connect Four");
		text.setFont(Font.font ("Verdana", 25));
		text.setFill(Color.YELLOW);

		//creating a button to start the game
		Button playButton = new Button("START THE GAME");
		playButton.setPrefSize(200, 50);
		playButton.setStyle("-fx-background-color: #D8BFD8;");
		playButton.setOnAction(e -> new GameFX(stage));

		//container
		VBox vbox = new VBox(70);
		vbox.getChildren().addAll(text, playButton);
		vbox.setAlignment(Pos.CENTER);

		//image
		Image image = new Image("circles.jpg");
		BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround = new Background(bImage);
		vbox.setBackground(bGround);

		Scene scene = new Scene(vbox , 500, 300);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
