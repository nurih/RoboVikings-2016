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

    @Override
    public void init() {

        leftFlipper = TeamShared.getRobotPart(hardwareMap, RobotPart.lflipperservo);
        leftFlipper.setPosition(startingPosition);

        rightFlipper = TeamShared.getRobotPart(hardwareMap, RobotPart.rflipperservo);
        rightFlipper.setPosition(startingPosition);

    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            leftFlipper.setPosition(finalPosition);
            rightFlipper.setPosition(finalPosition);
        } else {
            leftFlipper.setPosition(startingPosition);
            leftFlipper.setPosition(startingPosition);
        }
    }
}

