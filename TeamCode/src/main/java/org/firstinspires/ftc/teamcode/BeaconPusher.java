package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "BeaconPusher", group = "Test")

public class BeaconPusher extends OpMode {

    private final double pos = 0.5;
    private final double change = 0.2;
    Servo beaconPusher = null;

    @Override
    public void init() {
        telemetry.addLine("Initialized!");
        beaconPusher.setPosition(pos);
    }

    /**
     * User defined loop method
     * <p/>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
beaconPusher = hardwareMap.servo.get("BeaconPusher");
        if (this.gamepad1.right_bumper){
            beaconPusher.setPosition(pos+change);
        }
        else if (this.gamepad1.left_bumper) {
            beaconPusher.setPosition(pos-change);
        }
        else {
            beaconPusher.setPosition(pos);
        }
        }
    }

