package org.firstinspires.ftc.teamcode;

enum AutonomousState {
    START,
    END
}

public class AutonomousStateMachine {
    private AutonomousState _state;

    public AutonomousStateMachine(AutonomousState initialState) {
        _state = initialState;
    }

    public static AutonomousStateMachine create(AutonomousState state) {
        return new AutonomousStateMachine(state);
    }

    public AutonomousState getCurrent() {
        return _state;
    }

    public void set(AutonomousState newState) {
        _state = newState;
    }
}
