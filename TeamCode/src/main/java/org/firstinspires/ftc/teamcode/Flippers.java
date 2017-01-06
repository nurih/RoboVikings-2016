package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Flippers", group = "Mini Op")
public class Flippers extends OpMode {

    private final double startingPosition = 0.25;
    private final int finalPosition = 1;

    public Servo leftFlipper = null;
    public Servo rightFlipper = null;

    private boolean isEnabled = false;

    @Override
    public void init() {

        leftFlipper = TeamShared.getRobotPart(hardwareMap, RobotPart.lflipperservo);
        closeLeft();

        rightFlipper = TeamShared.getRobotPart(hardwareMap, RobotPart.rflipperservo);
        closeRight();
        telemetry.addLine(String.format("Flippers will range from %s to %s", startingPosition, finalPosition));
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            isEnabled = true;
        }

        if (gamepad1.b) {
            isEnabled = false;
            closeRight();
            closeLeft();
        }

        if (isEnabled) {
            if (gamepad1.left_bumper) {
                closeLeft();
            } else {
                openLeft();
            }

            if (gamepad1.right_bumper) {
                closeRight();
            } else {
                openRight();
            }
        }
        telemetry.addLine(String.format("Flippers Enabled %s L %s R %s", isEnabled, gamepad1.left_bumper, gamepad1.right_bumper));
    }

    private void openRight() {
        rightFlipper.setPosition(finalPosition);
    }

    private void closeRight() {
        rightFlipper.setPosition(startingPosition);
    }

    private void openLeft() {
        leftFlipper.setPosition(startingPosition);
    }

    private void closeLeft() {
        leftFlipper.setPosition(finalPosition);
    }
}

