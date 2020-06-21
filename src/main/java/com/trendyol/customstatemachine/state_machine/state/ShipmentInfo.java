package com.trendyol.customstatemachine.state_machine.state;

import com.trendyol.customstatemachine.annotation.Statable;
import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import com.trendyol.customstatemachine.state_machine.model.TransitionModel;
import java.util.List;
import org.springframework.statemachine.action.Action;

@Statable
public class ShipmentInfo extends State {

    @Override
    public Action<StateMachineState, StateMachineEvent> startAction() {
        return stateContext -> {
            System.out.println("SHIPMENT INFO");
          stateContext.getExtendedState().getVariables().put(getSourceState(), "ShipmentInfo");
        };
    }

    @Override
    public Action<StateMachineState, StateMachineEvent> exitAction() {
        return stateContext -> {
            //Exit Business
        };
    }

    @Override
    public List<TransitionModel> getTransitions() {

        return List.of(new TransitionModel(getSourceState(), StateMachineState.SHIPMENT_INFO,
                StateMachineEvent.GET_SHIPMENT_INFO));
    }

    @Override
    public StateMachineState getSourceState() {
        return StateMachineState.SHIPMENT_INFO;
    }
}
