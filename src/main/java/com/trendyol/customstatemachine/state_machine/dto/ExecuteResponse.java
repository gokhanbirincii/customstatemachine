package com.trendyol.customstatemachine.state_machine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExecuteResponse {

    private String currentState;
    private String currentStateValue;

}
