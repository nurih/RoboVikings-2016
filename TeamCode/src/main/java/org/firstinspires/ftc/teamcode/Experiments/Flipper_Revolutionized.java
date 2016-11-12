package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.TelemetryMessage;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "Flipper Revolutionized, hey everybody", group = "Test")
public class Flipper_Revolutionized extends OpMode {

    private final double startingPosition = 0.0;
    private final int finalPosition = 1;
    public Servo servo1 = null;
    public Servo servo2 = null;

    @Override
    public void init() {
        servo1 = hardwareMap.servo.get("flipper1");

        servo1.setPosition(startingPosition);
        servo2 = hardwareMap.servo.get("flipper2");
        servo2.setPosition(finalPosition);

    }

    @Override
    public void loop() {
        telemetry.addLine(String.format("Bumper: %s", gamepad2.right_bumper));
        if (gamepad2.left_bumper) {
            servo1.setPosition(finalPosition);
            servo2.setPosition(startingPosition);
        } else {
            servo1.setPosition(startingPosition);
            servo2.setPosition(finalPosition);
        }
    }
}
