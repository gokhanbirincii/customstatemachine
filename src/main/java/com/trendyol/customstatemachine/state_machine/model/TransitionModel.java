package com.trendyol.customstatemachine.state_machine.model;

import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransitionModel {

    private StateMachineState sourceState;

    private StateMachineState targetState;

    private StateMachineEvent event;

}