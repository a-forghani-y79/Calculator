package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private boolean isOperatorPressed=false;
    private String operator="";
    private double temp=0.0 , sum=0.0;

    @FXML
    private JFXTextField textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void onClickNumber(ActionEvent event){
        if(event.getSource() instanceof JFXButton) {
            JFXButton btn = (JFXButton)event.getSource();
            if (isOperatorPressed) {
                textField.setText(btn.getText().trim());

            } else {
                textField.setText(textField.getText().trim()+btn.getText().trim());
            }
            isOperatorPressed=false;
        }
    }

    private boolean isDotPressed = false;

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().isKeypadKey()) {
            onPressNumberKey(keyEvent);
        } else if (keyEvent.getCode().toString().equals("BACK_SPACE")) {
            onClickDel();
        } else if (keyEvent.getCode().toString().equals("DELETE")) {
            onClickCE();
        } else {
            onPressDotKey(keyEvent);
            onPressOperatorKey(keyEvent);
        }
        // System.out.println("keyPressed: "+ keyEvent.getCode().toString());
    }

    public void onPressDotKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DECIMAL) {
            if (!isDotPressed) {
                if (isOperatorPressed) {
                    textField.setText("0.");

                } else {
                    textField.setText(textField.getText().trim() + ".");
                }
                isOperatorPressed = false;
            }
            isDotPressed = true;
        }

    }


    public void onPressNumberKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().isKeypadKey()) {
            if (isOperatorPressed) {
                textField.setText(keyEvent.getText().trim());
            } else {
                textField.setText(textField.getText() + keyEvent.getText().trim());
            }
            isOperatorPressed = false;
        }
    }

    public void onPressOperatorKey(KeyEvent keyEvent) {
        if (!textField.getText().isEmpty()) {
            temp = Double.parseDouble(textField.getText());
            switch (operator) {
                case "/":
                    sum /= temp;
                    break;
                case "X":
                    sum *= temp;
                    break;
                case "+":
                    sum += temp;
                    break;
                case "-":
                    sum -= temp;
                    break;
                default:
                    sum = temp;
            }
        }

        if (keyEvent.getCode().toString().equals("ENTER")) {
            textField.setText(String.valueOf(sum));
            operator = "";
        } else {
            textField.setText("");
            isDotPressed = false;
            operator = keyEvent.getText().trim();
        }
        isOperatorPressed = true;
    }

    @FXML
    public void onClickDot() {
        if (!isDotPressed) {
            if (isOperatorPressed) {
                textField.setText("0.");

            } else {
                textField.setText(textField.getText().trim() + ".");
            }
            isOperatorPressed = false;
        }
        isDotPressed = true;


    }

    @FXML
    public void onClickOperator(ActionEvent event) {
        if(event.getSource() instanceof JFXButton) {
            JFXButton btn = (JFXButton)event.getSource();
            if (!textField.getText().isEmpty()){
                temp=Double.parseDouble(textField.getText());
                switch (operator){
                    case "/":
                        sum /= temp;
                        break;
                    case "X":
                        sum *= temp;
                        break;
                    case "+":
                        sum += temp;
                        break;
                    case "-":
                        sum -= temp;
                        break;
                    default:
                        sum = temp;
                    }
            }
            if (btn.getText().equals("=")){
                textField.setText(String.valueOf(sum));
                operator="";
            }else {
                textField.setText("");
                isDotPressed = false;
                operator=btn.getText().trim();
            }
            isOperatorPressed=true;
        }
    }

    @FXML
    public void onClickDel() {
        if (textField.getText().length()>0){
            textField.setText(textField.getText(0,textField.getText().length()-1));
        }
    }

    @FXML
    public void onClickCE() {
        textField.setText("");
        temp=0.0;
        sum=0.0;
        isOperatorPressed=false;
        operator="";
  }

    }











