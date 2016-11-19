package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotPart;
import org.firstinspires.ftc.teamcode.TeamShared;

@Disabled
@TeleOp(name = "BeaconPusher", group = "Test")

public class BeaconPusher extends OpMode {
    private final double pos = 0.55;
    private final double change = 0.3;
    //.3 is a test
    Servo beaconPusher = null;
    @Override
    public void init() {
        telemetry.addLine("Initializing!");

        beaconPusher = TeamShared.getRobotPart(hardwareMap, RobotPart.beaconservo) ;

        beaconPusher.setPosition(pos);
        telemetry.addLine("Initialized!");
    }


    @Override
    public void loop() {
        telemetry.addLine("Looping!");
        if (gamepad1.right_bumper) {
            beaconPusher.setPosition(pos - change);
        } else if (gamepad1.left_bumper) {
            beaconPusher.setPosition(pos + change);
        } else {
            beaconPusher.setPosition(pos);
        }
    }
}