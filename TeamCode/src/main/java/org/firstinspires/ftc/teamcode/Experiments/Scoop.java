package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotPart;
import org.firstinspires.ftc.teamcode.TeamShared;

@Disabled
@TeleOp(name = "Scoop", group = "Mini Ops")
public class Scoop extends OpMode {
    private final double startingPosition = 0;
    private final int finalPosition = 1;

    public Servo scoopServo = null;

    @Override
    public void init() {
        scoopServo = TeamShared.getRobotPart(hardwareMap, RobotPart.scoopservo);
        scoopServo.setPosition(startingPosition);
    }

    @Override
    public void loop() {
        if (gamepad2.right_bumper) {
            scoopServo.setPosition(finalPosition);
        } else {
            scoopServo.setPosition(startingPosition);
        }
    }
}
