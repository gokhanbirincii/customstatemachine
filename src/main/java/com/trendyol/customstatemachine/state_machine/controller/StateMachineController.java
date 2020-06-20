package com.trendyol.customstatemachine.state_machine.controller;

import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.dto.ExecuteResponse;
import com.trendyol.customstatemachine.state_machine.dto.StartResponse;
import com.trendyol.customstatemachine.state_machine.service.StateMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class StateMachineController {

    private final StateMachineService stateMachineService;

    @GetMapping(value = "start")
    public ResponseEntity<StartResponse> start() {
        return ResponseEntity.ok().body(stateMachineService.start());
    }

    @GetMapping(value = "execute")
    public ResponseEntity<ExecuteResponse> execute(@RequestParam("event") StateMachineEvent stateMachineEvent) {
        return ResponseEntity.ok().body(stateMachineService.execute(stateMachineEvent));
    }
}
