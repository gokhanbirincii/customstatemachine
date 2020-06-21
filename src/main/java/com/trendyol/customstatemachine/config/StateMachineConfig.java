package com.trendyol.customstatemachine.config;

import com.trendyol.customstatemachine.state_machine.StateMachineEvent;
import com.trendyol.customstatemachine.state_machine.StateMachineState;
import com.trendyol.customstatemachine.state_machine.model.TransitionModel;
import com.trendyol.customstatemachine.state_machine.state.InitialState;
import com.trendyol.customstatemachine.state_machine.state.State;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


@Configuration
@AllArgsConstructor
@Slf4j
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateMachineState, StateMachineEvent> {

    private static final String INITIAL_STATE_BEAN_NAME = "initialState";

    private final List<TransitionModel> transitionModels;

    private final List<String> stateBeanNames;

    private final InitialState initialState;

    private final ApplicationContext applicationContext;

    @Override
    public void configure(StateMachineStateConfigurer<StateMachineState, StateMachineEvent> states)
            throws Exception {
        var stateConfigurer = states
                .withStates()
                .initial(StateMachineState.INITIAL, initialState.startAction());

        for (var beanName : stateBeanNames) {

            if (beanName.equals(INITIAL_STATE_BEAN_NAME)) {
                continue;
            }

            final var state = applicationContext.getBean(beanName, State.class);
            stateConfigurer.state(state.getSourceState(), state.startAction(), state.exitAction());
        }

        stateConfigurer.end(StateMachineState.END);
    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<StateMachineState, StateMachineEvent> transitions) {
        transitionModels.forEach(transitionModel -> {
            try {
                transitions.withExternal().source(transitionModel.getSourceState())
                        .target(transitionModel.getTargetState())
                        .event(transitionModel.getEvent());
            } catch (Exception e) {
                log.error("Error occurred when prepare transitions.");
            }
        });
    }

}
