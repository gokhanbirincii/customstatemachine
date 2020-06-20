package com.trendyol.customstatemachine.state_machine.model;

import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransitionModel {

    private StateMachineState sourceState;

    private StateMachineState targetState;

    private StateMachineEvent event;

}