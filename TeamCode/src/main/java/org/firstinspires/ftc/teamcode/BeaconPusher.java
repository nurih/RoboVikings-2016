package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
//Wow look! a BaconPusher
@TeleOp(name = "BeaconPusher", group = "Test")

public class BeaconPusher extends OpMode {
    private final double pos = 0.55;
    private final double change = 0.3;
    //.3 is a test
    Servo beaconPusher = null;
//( ͡° ͜ʖ ͡°) le lenny face
    @Override
    public void init() {
        telemetry.addLine("Initializing!");

        beaconPusher = hardwareMap.servo.get("BeaconPusher");

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

