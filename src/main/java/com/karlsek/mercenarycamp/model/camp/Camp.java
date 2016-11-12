package com.karlsek.mercenarycamp.model.camp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Camp {

    private Long enteredBuilding;

    public Long getEnteredBuilding() {
        return enteredBuilding;
    }

    public void setEnteredBuilding(Long enteredBuilding) {
        this.enteredBuilding = enteredBuilding;
    }
}
