package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private StringBuilder text=new StringBuilder("");
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
                operator=btn.getText().trim();
            }
            isOperatorPressed=true;
        }
    }



    @FXML
    public void onClickDel(ActionEvent event){
        if (textField.getText().length()>0){
            textField.setText(textField.getText(0,textField.getText().length()-1));
        }
    }

    @FXML
    public void onClickCE(ActionEvent event){
        textField.setText("");
        temp=0.0;
        sum=0.0;
        isOperatorPressed=false;
        operator="";
  }


    }












