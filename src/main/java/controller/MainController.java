package controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;

@ViewController("/view/mainFlow.fxml")
public class MainController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane centerPane;

    @PostConstruct
    public void init() throws FlowException {

        context = new ViewFlowContext();

        Flow innerFlow = new Flow(ShopController.class);

        final FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);

        centerPane.getChildren().add(flowHandler.start(
                new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.ZOOM_IN)));
    }
}
