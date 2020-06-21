package com.trendyol.customstatemachine.state_machine.service;

import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import com.trendyol.customstatemachine.state_machine.dto.ExecuteResponse;
import com.trendyol.customstatemachine.state_machine.dto.StartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateMachineService {

    private final StateMachine<StateMachineState, StateMachineEvent> stateMachine;

    public StartResponse start() {

        stateMachine.start();

        return new StartResponse(stateMachine.getUuid().toString());

    }

    public ExecuteResponse execute(StateMachineEvent event) {

        stateMachine.sendEvent(event);

        final var currentStateValue = stateMachine.getExtendedState().getVariables().get(stateMachine.getState().getId());

        return new ExecuteResponse(stateMachine.getState().getId().name(), currentStateValue.toString());

    }

}
