package com.trendyol.customstatemachine.state_machine.state;

import com.trendyol.customstatemachine.annotation.Statable;
import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import com.trendyol.customstatemachine.state_machine.model.TransitionModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.statemachine.action.Action;

@Statable
public class InitialState extends State {

    @Override
    public Action<StateMachineState, StateMachineEvent> startAction() {
        return stateContext -> System.out.println("Here Initial State");
    }

    @Override
    public Action<StateMachineState, StateMachineEvent> exitAction() {
        return stateContext -> {
            //Exit Business
        };
    }

    @Override
    public List<TransitionModel> getTransitions() {

        final var transitions = new ArrayList<TransitionModel>();

        transitions.add(new TransitionModel(getSourceState(), StateMachineState.ORDER_RECEIVED,
                StateMachineEvent.GET_ORDER_RECEIVED));

        return transitions;
    }

    @Override
    public StateMachineState getSourceState() {
        return StateMachineState.INITIAL;
    }
}
