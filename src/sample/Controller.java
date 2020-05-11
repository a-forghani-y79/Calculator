package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private boolean isOperatorPressed = false;
    private boolean isDotPressed = false;
    private String operator = "";
    private BigDecimal temp, sum;

    @FXML
    private JFXTextField textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void onClickNumber(ActionEvent event) {
        if (event.getSource() instanceof JFXButton) {
            JFXButton btn = (JFXButton) event.getSource();
            if (isOperatorPressed) {
                textField.setText(btn.getText().trim());

            } else {
                textField.setText(textField.getText().trim() + btn.getText().trim());
            }
            isOperatorPressed = false;
        }
    }


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
        boolean flag = false;
        if (!textField.getText().isEmpty()) {
            try {
                temp = new BigDecimal(textField.getText());
            } catch (NumberFormatException e) {
                temp = new BigDecimal(0);
            }
            switch (operator) {
                case "/":
                    try {
                        sum = sum.divide(temp);
                    } catch (ArithmeticException e) {
                        textField.setText(e.getMessage());
                        flag = true;
                    }
                    break;
                case "X":
                    sum = sum.multiply(temp);
                    break;
                case "+":
                    sum = sum.add(temp);
                    break;
                case "-":
                    sum = sum.subtract(temp);
                    break;
                default:
                    sum = new BigDecimal(temp.toString());
            }
        }

        if (keyEvent.getCode().toString().equals("ENTER")) {
            if (flag)
                textField.setText("Divide by Zero");
            else
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
        boolean flag = false;
        if (event.getSource() instanceof JFXButton) {
            JFXButton btn = (JFXButton) event.getSource();
            if (!textField.getText().isEmpty()) {
                try {
                    temp = new BigDecimal(textField.getText());
                } catch (NumberFormatException e) {
                    temp = new BigDecimal(0);
                }
                switch (operator) {
                    case "/":
                        try {
                            sum = sum.divide(temp);
                        } catch (ArithmeticException e) {
                            textField.setText(e.getMessage());
                            flag = true;
                        }
                        break;
                    case "X":
                        sum = sum.multiply(temp);
                        break;
                    case "+":
                        sum = sum.add(temp);
                        break;
                    case "-":
                        sum = sum.subtract(temp);
                        break;
                    default:
                        sum = new BigDecimal(temp.toString());
                }
            }
            if (btn.getText().equals("=")) {
                if (flag)
                    textField.setText("Divide by Zero");
                else
                    textField.setText(String.valueOf(sum));
                operator = "";
            } else {
                textField.setText("");
                isDotPressed = false;
                operator = btn.getText().trim();
            }
            isOperatorPressed = true;
        }
    }

    @FXML
    public void onClickDel() {
        if (textField.getText().length() > 0) {
            textField.setText(textField.getText(0, textField.getText().length() - 1));
        }
    }

    @FXML
    public void onClickCE() {
        textField.setText("");
        temp = new BigDecimal(0);
        sum = new BigDecimal(0);
        isOperatorPressed = false;
        operator = "";
    }

}












