import javafx.scene.control.Button;

public class GameButton extends Button {

    private String color;
    private int x;
    private int y;
    private boolean clicked;
    private boolean valid;

    GameButton() {
        clicked = false;
        valid = false;
    }

    public void validMove() {
        this.valid = true;
    }

    public void notValidMove() {
        this.valid = false;
    }

    public boolean checkMove() {
        return valid;
    }

    public void setColorRed(GameButton button){
        button.setStyle("-fx-background-color: #ff0000; ");
        color = "Red";
    }

    public void setColorBlue(GameButton button){
        button.setStyle("-fx-background-color: #0000CD; ");
        color = "Blue";
    }

    public void setClicked() {
        clicked = true;
    }
    public boolean isClicked() {
        return clicked;
    }

    public void setX(int i) {
        x = i;
    }
    public int getX(){
        return x;
    }

    public void setY(int i) {
        y = i;
    }
    public int getY(){
        return y;
    }

    public String getColor() {
        return color;
    }
}
