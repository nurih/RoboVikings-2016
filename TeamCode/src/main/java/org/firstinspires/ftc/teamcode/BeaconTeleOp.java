package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Beacon TeleOp")
public class BeaconTeleOp extends OpMode {

    private final double initialPosition = 0.5;
    private final double change = 0.3;
    ;
    private Servo beaconPusher = null;

    @Override
    public void init() {
        beaconPusher = TeamShared.getRobotPart( hardwareMap, RobotPart.beaconservo );

        beaconPusher.setPosition( initialPosition );
        telemetry.addLine( "Initialized Beacon Servo" );
    }

    @Override
    public void loop() {

        if (gamepad2.x) {
            //blue
            telemetry.addLine( "Beacon Pusher - X Pressed" );
            beaconPusher.setPosition( initialPosition - change );
        } else if (gamepad2.b) {
            // red
            telemetry.addLine( "Beacon Pusher - B pressed" );
            beaconPusher.setPosition( initialPosition + change );
        } else {
            telemetry.addLine( "Beacon Pusher - resting" );
            beaconPusher.setPosition( initialPosition );
        }

    }
}
