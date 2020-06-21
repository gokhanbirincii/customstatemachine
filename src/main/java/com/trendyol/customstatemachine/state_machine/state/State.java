package com.trendyol.customstatemachine.state_machine.state;

import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import com.trendyol.customstatemachine.state_machine.model.TransitionModel;
import java.util.List;
import org.springframework.statemachine.action.Action;

public abstract class State {

    public abstract Action<StateMachineState, StateMachineEvent> startAction();

    public abstract Action<StateMachineState, StateMachineEvent> exitAction();

    public abstract List<TransitionModel> getTransitions();

    public List<TransitionModel> transitionsWrapper() {
        final var transitions = getTransitions();

        transitions.add(new TransitionModel());
        //We can add common state definitions here

        return transitions;
    }

    public abstract StateMachineState getSourceState();

}