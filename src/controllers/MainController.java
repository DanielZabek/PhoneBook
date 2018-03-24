package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Contact;

public class MainController {

	@FXML
	private Button addButton;
	@FXML
	private Button editButton;
	@FXML
	private Button removeButton;
	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;
	@FXML
	private TextField phoneInput;
	@FXML
	private TableView<Contact> phoneBookTable;
	@FXML
	private TableColumn<Contact, String> firstNameColumn;
	@FXML
	private TableColumn<Contact, String> lastNameColumn;
	@FXML
	private TableColumn<Contact, String> phoneColumn;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	private ObservableList<Contact> data = FXCollections.observableArrayList();
	private BufferedReader reader = null;
	private FileWriter writer = null;
	private String filePath = "src\\file.txt";
	private String[] splitedLine;
	private Contact tmpContact;

	@FXML
	public void initialize() throws IOException {

		ReadFile();

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
		phoneBookTable.setItems(data);
	}

	public void ReadFile() throws IOException {

		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			while (line != null) {
				splitedLine = line.split("\\s");
				data.add(new Contact(splitedLine[0], splitedLine[1], splitedLine[2]));
				line = reader.readLine();
			}
			;

		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public void SaveFile() throws IOException {

		try {
			writer = new FileWriter(filePath);

			for (Contact c : data) {

				writer.write(c.getFirstName() + " " + c.getLastName() + " " + c.getPhoneNumber() + "\r\n");
			}

		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}

	@FXML
	public void addButtonOnAction() throws IOException {

		data.add(new Contact(firstNameInput.getText(), lastNameInput.getText(), phoneInput.getText()));
		SaveFile();
		firstNameInput.setText("");
		lastNameInput.setText("");
		phoneInput.setText("");

	}

	@FXML
	public void editButtonOnAction() throws IOException {

		addButton.setVisible(false);
		removeButton.setVisible(false);

		saveButton.setVisible(true);
		cancelButton.setVisible(true);

		tmpContact = phoneBookTable.getSelectionModel().getSelectedItem();

		firstNameInput.setText(tmpContact.getFirstName());
		lastNameInput.setText(tmpContact.getLastName());
		phoneInput.setText(tmpContact.getPhoneNumber());

	}

	@FXML
	public void saveButtonOnAction() throws IOException {

		for (int i = 0; i < data.size(); i++) {

			if (data.get(i).equals(tmpContact)) {

				data.set(i, new Contact(firstNameInput.getText(), lastNameInput.getText(), phoneInput.getText()));
			}
		}

		SaveFile();

		addButton.setVisible(true);
		removeButton.setVisible(true);

		saveButton.setVisible(false);
		cancelButton.setVisible(false);
	}

	@FXML
	public void cancelButtonOnAction() {
		addButton.setVisible(true);
		removeButton.setVisible(true);

		saveButton.setVisible(false);
		cancelButton.setVisible(false);
	}

	@FXML
	public void removeButtonOnAction() throws IOException {

		Contact contact = phoneBookTable.getSelectionModel().getSelectedItem();
		data.remove(contact);

		SaveFile();

	}

}
