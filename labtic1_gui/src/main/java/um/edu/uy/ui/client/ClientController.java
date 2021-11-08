package um.edu.uy.ui.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import um.edu.uy.business.dtos.ClientDTO;
import um.edu.uy.service.ClientRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ClientController {

    @Autowired
    private ClientRestService clientRestService;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDocument;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addClient(ActionEvent event) {
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
        txtAddress.getText() == null || txtAddress.getText().equals("") ||
        txtAddress.getText() == null || txtAddress.getText().equals("")) {

            showAlert(
            "Datos faltantes!",
            "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                long document = Long.parseLong(txtDocument.getText());
                String name = txtName.getText();
                String address = txtAddress.getText();

                ClientDTO client = new ClientDTO();
                client.setDocument(document);
                client.setName(name);
                client.setAddress(address);

                try {

                    ResponseEntity response = clientRestService.addClient(client);

                    if (response.getStatusCode() == HttpStatus.OK) {

                        showAlert("Cliente agregado", "Se agrego con exito el cliente!");

                    }

                } catch (HttpClientErrorException error) {
                    if (error.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        showAlert(
                                "Informacion invalida !",
                                "Se encontro un error en los datos ingresados.");
                    } else if (error.getStatusCode() == HttpStatus.CONFLICT) {
                        showAlert(
                                "Documento ya registrado !",
                                "El documento indicado ya ha sido registrado en el sistema.");
                    } else {
                        showAlert(
                                "Error Generico",
                                "Se recibio el siguiente codigo de error: " + error.getStatusCode());
                    }
                }

                close(event);

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }

    private void clean() {
        txtDocument.setText(null);
        txtAddress.setText(null);
        txtName.setText(null);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
