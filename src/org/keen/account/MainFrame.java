package org.keen.account;

import static org.keen.account.AccountCtrl.getAccountList;
import static org.keen.account.AccountCtrl.getLabelList;



import java.time.LocalDate;
import java.util.List;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFrame extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private Stage mainStage;

	@Override
	public void start(Stage stage) throws Exception {
		mainStage = stage;
		stage.setTitle("账 · 生活");
		HBox root = new HBox();
		root.getChildren().addAll(buildLeftPanel(200), buildContentPanel(600));
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add("skin.css");
		stage.setScene(scene);
		stage.show();
	}

	private BorderPane buildLeftPanel(int width) {
		BorderPane v = new BorderPane();
		v.setPrefWidth(width);
		v.setTop(buildLogo(50));
		v.setCenter(buildLabelList());
		return v;
	}

	private FlowPane buildLogo(int height) {
		FlowPane flowPane = new FlowPane();
		flowPane.setPrefHeight(height);
		flowPane.getStyleClass().add("logo");
		return flowPane;
	}

	private ListView<AccountLabel> listView;
	private ListView<AccountLabel> buildLabelList() {
		listView = new ListView<>();
		listView.setCellFactory(cb -> {
			return new DND.LabelListCell();
		});
//		listView.getStyleClass().add("labelList");
		updateLabelList(getLabelList());
		return listView;
	}

	private void updateLabelList(List<AccountLabel> labelList) {
		ObservableList<AccountLabel> items = FXCollections.observableArrayList(labelList);
		this.listView.setItems(items);
	}

	private BorderPane buildContentPanel(int width) {
		BorderPane v = new BorderPane();
		v.setPrefWidth(width);
		v.setTop(buildLabelInfo(50));
		v.setCenter(buildContent());
		return v;
	}

	private FlowPane buildLabelInfo(int height) {
		FlowPane flowPane = new FlowPane();
		flowPane.setPrefHeight(height);
		flowPane.getChildren().add(new Text("标签分类"));
		return flowPane;
	}

	private BorderPane buildContent() {
		BorderPane v = new BorderPane();
		v.setTop(buildInputArea());
		v.setCenter(buildAccountTable());
		return v;
	}

	private Node buildInputArea() {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		// gridPane.setGridLinesVisible(true);
		gridPane.setPadding(new Insets(10, 25, 10, 25));// 上左下右

		gridPane.setHgap(30);
		gridPane.setVgap(10);
		gridPane.add(buildDateCtrl(), 0, 0, 1, 1);
		gridPane.add(buildMoneyCtrl(), 1, 0, 1, 1);
		gridPane.add(buildDescCtrl(), 0, 1, 2, 1);
		gridPane.add(buildAddAccountCtrl(), 2, 1, 1, 1);
		return gridPane;
	}

	private DatePicker date;

	private HBox buildDateCtrl() {// 日期控件
		Label dateLabel = new Label("日期");
		date = new DatePicker();
		HBox dataBox = new HBox(10);
		dataBox.getChildren().addAll(dateLabel, date);
		return dataBox;
	}

	private LocalDate getInputDate() throws InputError {
		LocalDate value = date.getValue();
		if (value == null) {
			throw new InputError("必须输入日期");
		}
		return value;
	}

	private TextField money;
	private ChoiceBox<MoneyDirection> moneyDirection;

	private HBox buildMoneyCtrl() {// 金额控件
		Label moneyLabel = new Label("金额");
		ObservableList<MoneyDirection> items = FXCollections
				.observableArrayList(MoneyDirection.out, MoneyDirection.in);
		moneyDirection = new ChoiceBox<>(items);
		moneyDirection.setValue(MoneyDirection.out);
		money = new TextField();
		HBox moneyBox = new HBox(10);
		HBox box = new HBox(2);
		box.getChildren().addAll(moneyDirection, money);
		moneyBox.getChildren().addAll(moneyLabel, box);
		return moneyBox;
	}

	private double getInputMoney() throws InputError {
		String text = money.getText();
		if (text == null || text.isEmpty()) {
			throw new InputError("必须输入金额");
		}
		try {
			return Double.parseDouble(text);
		} catch (NumberFormatException e) {
			throw new InputError("金额输入有误");
		}
	}

	private String getInputMoneyDirection() {
		return moneyDirection.getValue().toString();
	}

	private TextField desc;

	private Node buildDescCtrl() {// 描述控件
		Label descLabel = new Label("描述");
		desc = new TextField();
		HBox descBox = new HBox(10);
		descBox.setPadding(new Insets(0, 10, 0, 0));
		descBox.getChildren().add(descLabel);
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(descBox);
		borderPane.setCenter(desc);
		return borderPane;
	}

	private String getInputDesc() throws InputError {
		String text = desc.getText();
		if (text == null || text.isEmpty()) {
			throw new InputError("必须输入描述");
		}
		return text;
	}

	private void clearInputArea() {
		// date.setValue(null);
		// moneyDirection.setValue(MoneyDirection.out);
		money.setText("");
		desc.setText("");
	}

	private Button buildAddAccountCtrl() {// 添加按钮
		Button button = new Button("yes");
		button.setOnAction(e -> {
			try {
				Account newAccount = new Account(getInputDate(),
						getInputMoneyDirection(), getInputMoney(),
						getInputDesc());
				addAccountOnTable(newAccount);
				clearInputArea();
			} catch (InputError ex) {
				MessgeDialog.show(mainStage, ex.getMessage());
				return;
			} catch (Exception ex) {
				MessgeDialog.show(mainStage, "添加账目失败");
				return;
			}
		});
		return button;
	}

	private TableView<Account> table;

	private TableView<Account> buildAccountTable() {// 账目表
		table = new TableView<>();
		table.setRowFactory(cb -> {
			return new DND.AccountTableRow();
			
		});
		TableColumn<Account, LocalDate> dateTimeCol = new TableColumn<>("日期");
		dateTimeCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateTimeCol.setMinWidth(150);
		dateTimeCol.setResizable(false);
		dateTimeCol.setCellFactory(cb -> {
			return new DataTimeCellFactory();
		});
		TableColumn<Account, String> directionCol = new TableColumn<>("流向");
		directionCol.setCellValueFactory(new PropertyValueFactory<>(
				"moneyDirection"));
		directionCol.setMinWidth(100);
		TableColumn<Account, Double> moneyCol = new TableColumn<>("金额");
		moneyCol.setCellValueFactory(new PropertyValueFactory<>("money"));
		moneyCol.setMinWidth(100);
		TableColumn<Account, String> descCol = new TableColumn<>("描述");
		descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
		descCol.setMinWidth(100);
		table.getColumns().addAll(dateTimeCol, directionCol, moneyCol, descCol);
		updateAccountTable(getAccountList());
		return table;
	}

	private void updateAccountTable(List<Account> accountList) {
		ObservableList<Account> items = FXCollections.observableArrayList(accountList);
		this.table.setItems(items);
	}

	private void addAccountOnTable(Account newAccount) {
		this.table.getItems().add(newAccount);
	}
	
	

}
