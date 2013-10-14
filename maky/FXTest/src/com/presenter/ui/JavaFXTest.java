package com.presenter.ui;


import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JavaFXTest {

	private final static int APP_WIDTH = 1100;
	private final static int APP_HEIGHT = 800;
	private final static java.awt.Color APP_BACKGROUND = new java.awt.Color(1f, 1f, 1f, 0.5f);

	private static void initAndShowGUI() {
		// This method is invoked on the EDT thread
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("Presenter");
		final JFXPanel fxPanel = new JFXPanel();
		frame.add(fxPanel);
		frame.setAlwaysOnTop(true);
		frame.setBounds((int) (screenSize.getWidth() - APP_WIDTH) / 2, (int) (screenSize.getHeight() - APP_HEIGHT) / 2, APP_WIDTH, APP_HEIGHT);
		frame.setUndecorated(true);
		frame.setBackground(APP_BACKGROUND);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(fxPanel);
			}
		});
	}

	private static void initFX(JFXPanel fxPanel) {
		// This method is invoked on the JavaFX thread
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}

	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.TRANSPARENT);
		final WebView browser = new WebView();
		browser.setMaxSize(0, 0);
		final WebEngine webEngine = browser.getEngine();
		webEngine.getLoadWorker().stateProperty().addListener(
				new ChangeListener<State>() {
					@SuppressWarnings("rawtypes")
					@Override
					public void changed(ObservableValue ov, State oldState, State newState) {

						if (newState == Worker.State.SUCCEEDED) {
							String heightText = webEngine.executeScript("Math.max( document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight );").toString();
							double height = Double.valueOf(heightText.replace("px", ""));
							String widthText = webEngine.executeScript("Math.max( document.body.scrollWidth, document.body.offsetWidth, document.documentElement.clientWidth, document.documentElement.scrollWidth, document.documentElement.offsetWidth );").toString();
							double width = Double.valueOf(widthText.replace("px", ""));
							browser.setLayoutX( (APP_WIDTH - width) / 2);
							browser.setLayoutY( (APP_HEIGHT - height) / 2);
							browser.setMaxSize(width, height);
						}

					}
				});
		
		//FROM code
//		String heightText = webEngine
//				.executeScript(
//						"Math.max( document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight );")
//				.toString();
//		double height = Double.valueOf(heightText.replace("px", ""));
//		String widthText = webEngine
//				.executeScript(
//						"Math.max( document.body.scrollWidth, document.body.offsetWidth, document.documentElement.clientWidth, document.documentElement.scrollWidth, document.documentElement.offsetWidth );")
//				.toString();
//		double width = Double.valueOf(widthText.replace("px", ""));
//		double sceneWidth = Math.min(config.getApplicationWidth() - config.getBorderLeftMax() - config.getBorderRightMax(), width);
//		double sceneHeight = Math.min(config.getApplicationHeight() - config.getBorderTopMax() - config.getBorderBottomMax(), height);
//		double layoutX = Math.max((config.getApplicationWidth() - width) / 2, config.getBorderLeftMax());
//		double layoutY = Math.max((config.getApplicationHeight() - height) / 2, config.getBorderTopMax());
//		// browser.setLayoutX(layoutX);
//		// browser.setLayoutY(layoutY);
//		// //browser.setMaxSize(sceneWidth, sceneHeight);
//		// browser.setMaxSize(width, height);
		
		
		webEngine.load(JavaFXTest.class.getResource("/PlayVideo.html").toExternalForm());
		root.getChildren().add(browser);
		return (scene);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initAndShowGUI();
			}
		});
	}
}