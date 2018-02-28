package run;

import controller.MainController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Flow flow = new Flow(MainController.class);

        flowContext = new ViewFlowContext();
        flowContext.register("Stage", primaryStage);
        flowContext.register("1337", 1337);
        FlowHandler flowHandler = flow.createHandler(flowContext);

        StackPane pane = flowHandler.start(new DefaultFlowContainer());
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
