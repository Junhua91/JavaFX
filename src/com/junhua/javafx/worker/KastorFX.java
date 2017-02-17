/*============================================================================
 *
 * Copyright (c) 2000-2017 Smart Trade Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of Smart Trade Technologies
 * Use is subject to license terms. Duplication or distribution prohibited.
 *
 *============================================================================*/

package com.smarttrade.connectivity.kastorfx.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.smarttrade.connectivity.kastorfx.generator.Generator;
import com.smarttrade.connectivity.kastorfx.worker.MessageList;
import com.smarttrade.messages.Message;
import com.smarttrade.messages.structures.CancelMarketDataRequest;
import com.smarttrade.messages.structures.ExecutionReport;
import com.smarttrade.messages.structures.MarketDataRequest;
import com.smarttrade.messages.structures.NewOrderSingle;
import com.smarttrade.messages.structures.OrderCancelReplaceRequest;
import com.smarttrade.messages.structures.OrderCancelRequest;
import com.smarttrade.messages.structures.QuoteRequest;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Test DOCUMENT ME
 *
 * @author jdeng
 */
public class KastorFX extends Application {

    private static BorderPane ROOT_PANE = new BorderPane();
    public Generator generator = new Generator();

    public static List<MessageList> staticList = Arrays.asList(MessageList.values());
    public List<Class<? extends Message>> codecs = new ArrayList<>();

    final Label projectNameLabel = new Label("Project Name: ");
    final Label dictLabel = new Label("Dictionary: ");

    final TextField projectName = new TextField();
    final TextField dictName = new TextField();

    final HBox centerBox = new HBox();
    final VBox buttonBox = new VBox();
    final HBox topBox = new HBox();
    final HBox bottomBox = new HBox();

    final ListView<MessageList> leftListMessage = new ListView<MessageList>();
    final ListView<MessageList> rightListMessage = new ListView<MessageList>();

    ObservableList<MessageList> selectedItems = FXCollections.observableArrayList();
    ObservableList<MessageList> leftItems = FXCollections.observableArrayList();
    ObservableList<MessageList> rightItems = FXCollections.observableArrayList();

    final Button rightBT = new Button(">>");
    final Button leftBT = new Button("<<");
    final Button generateBT = new Button("Generate");

    @Override
    public void start(Stage primaryStage) throws Exception {

        // top
        topBox.getChildren().addAll(projectNameLabel, projectName, dictLabel, dictName);

        // centre
        Collections.sort(staticList);
        leftItems.addAll(staticList);
        leftListMessage.setItems(leftItems);
        rightListMessage.setItems(rightItems);
        buttonBox.getChildren().addAll(rightBT, leftBT);
        centerBox.getChildren().addAll(leftListMessage, buttonBox, rightListMessage);

        //bottom
        bottomBox.getChildren().add(generateBT);

        //the whole view
        ROOT_PANE.setTop(topBox);
        ROOT_PANE.setCenter(centerBox);
        ROOT_PANE.setBottom(bottomBox);

        // set style for the component
        setStyle(primaryStage);

        // show the scene
        final Scene main = new Scene(ROOT_PANE);
        primaryStage.setScene(main);
        main.getStylesheets().add("kastorFX.css");
        primaryStage.show();

        leftListMessage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        rightListMessage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Control
        button1Action(rightBT);
        button2Action(leftBT);
        generateAction(generateBT);
    }

    private void setStyle(Stage primaryStage) {
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setTitle("KastorFX");

        buttonBox.getStyleClass().add("vbox");
        centerBox.getStyleClass().add("hbox");
        topBox.getStyleClass().add("hbox");
        bottomBox.getStyleClass().add("hbox");

        topBox.setMinHeight(66d);
        bottomBox.setMinHeight(66d);
    }

    /****************************** Control **********************************/
    private void button1Action(Button button) {

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                selectedItems = leftListMessage.getSelectionModel().getSelectedItems();
                rightItems.addAll(selectedItems);
                leftItems.removeAll(selectedItems);
                rightListMessage.setItems(rightItems);
            }
        });
    }

    private void button2Action(Button button) {

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                selectedItems = rightListMessage.getSelectionModel().getSelectedItems();
                leftItems.addAll(selectedItems);
                rightItems.removeAll(selectedItems);
                leftListMessage.setItems(leftItems);

            }
        });
    }

    public void generateAction(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                generator.setCodecs(getcodecs());
                generator.setProjectName(getProjetName());
                generator.setFixDictionary(getDictName());
                codecs.clear();
            }
        });
    }

    public List<Class<? extends Message>> getcodecs() {

        for (final MessageList message : rightItems) {

            switch (message) {
                case MarketDataRequest:
                    codecs.add(MarketDataRequest.class);
                    break;
                case ExecutionReport:
                    codecs.add(ExecutionReport.class);
                    break;
                case NewOrderSingle:
                    codecs.add(NewOrderSingle.class);
                    break;
                case OrderCancelReplaceRequest:
                    codecs.add(OrderCancelReplaceRequest.class);
                    break;
                case OrderCancelRequest:
                    codecs.add(OrderCancelRequest.class);
                    break;
                case CancelMarketDataRequest:
                    codecs.add(CancelMarketDataRequest.class);
                    break;
                case QuoteRequest:
                    codecs.add(QuoteRequest.class);
                    break;
                default:
                    break;
            }
        }

        return codecs;
    }

    public String getProjetName() {
        return projectName.getText();
    }

    public String getDictName() {
        return dictName.getText();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
