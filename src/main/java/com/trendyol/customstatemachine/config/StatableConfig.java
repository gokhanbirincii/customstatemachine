package com.trendyol.customstatemachine.config;

import com.google.common.base.CaseFormat;
import com.trendyol.customstatemachine.annotation.Statable;
import com.trendyol.customstatemachine.state_machine.model.TransitionModel;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class StatableConfig {

    private static final String BASE_PACKAGE_PATH = "com.trendyol.customstatemachine";

    @Bean
    @SuppressWarnings("unchecked")
    public List<TransitionModel> transitionModels() {
        Reflections ref = new Reflections(BASE_PACKAGE_PATH);

        final var states = ref.getTypesAnnotatedWith(Statable.class);

        return states.stream().flatMap(stateClazz -> {
            try {
                final var getLatestTransitions = (List<TransitionModel>) stateClazz
                        .getMethod("transitionsWrapper")
                        .invoke(stateClazz.getDeclaredConstructor().newInstance());
                return getLatestTransitions.stream();
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                log.error("error occurred when trigger getTransitions.");
            }
            return Stream.empty();
        }).collect(Collectors.toList());
    }

    @Bean("stateBeanNames")
    public List<String> getStateBeanNames() {

        final var ref = new Reflections(BASE_PACKAGE_PATH);

        final var states = ref.getTypesAnnotatedWith(Statable.class);

        return states.stream().map(state -> CaseFormat.UPPER_CAMEL
                .to(CaseFormat.LOWER_CAMEL, state.getSimpleName())).collect(Collectors.toList());

    }
}
