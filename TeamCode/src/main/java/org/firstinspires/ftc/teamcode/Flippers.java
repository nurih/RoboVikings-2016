package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.TelemetryMessage;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Jennifer on 10/7/2016.
 */
@TeleOp(name = "Flippers" , group = "Test")
public class Flippers extends OpMode {

    private final double startingPosition = 0.25;
    private final int finalPosition = 1;
    public Servo servo1 = null;
    public Servo servo2 = null;

    @Override
    public void init() {

        servo1 = hardwareMap.servo.get("flipper1");
        servo1.setPosition(startingPosition);
        servo2 = hardwareMap.servo.get("flipper2");
        servo2.setPosition(startingPosition);

    }

    @Override
    public void loop() {
        telemetry.addLine(String.format("Bumper: " , gamepad1.right_bumper));
        if (gamepad1.right_bumper) {
            servo1.setPosition(finalPosition);
            servo2.setPosition(finalPosition);
        }
        else {
            servo1.setPosition(startingPosition);
            servo1.setPosition(startingPosition);
        }
        }
    }

