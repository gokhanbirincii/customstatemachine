package com.trendyol.customstatemachine.state_machine.state;

import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import com.trendyol.customstatemachine.state_machine.model.TransitionModel;
import java.util.List;
import org.springframework.statemachine.action.Action;

public interface State {

    Action<StateMachineState, StateMachineEvent> startAction();

    Action<StateMachineState, StateMachineEvent> exitAction();

    List<TransitionModel> getTransitions();

    default List<TransitionModel> transitionsWrapper() {
        final var transitions = getTransitions();

        transitions.add(new TransitionModel());
        //We can add common state definitions here

        return transitions;
    }

    StateMachineState getSourceState();

}