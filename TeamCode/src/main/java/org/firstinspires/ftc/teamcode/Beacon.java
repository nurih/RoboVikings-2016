package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Beacon Pusher Auto")
public class Beacon extends OpMode {

    private final double initialPosition = 0.55;
    private final double change = 0.3;
    private ColorSensor colorSensor = null;
    private Servo beaconPusher = null;
    private int blueThreshold =4;
    private int redThreshold = 5;

    @Override
    public void init() {

        telemetry.addLine("Initializing color sensor");

        colorSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.colorsensor);

        colorSensor.enableLed(false);

        telemetry.addData("Initialized color sensor", colorSensor.getManufacturer());

        beaconPusher = TeamShared.getRobotPart(hardwareMap, RobotPart.beaconservo);

        beaconPusher.setPosition(initialPosition);
        telemetry.addLine("Initialized servo");
    }

    @Override
    public void loop() {

        if (colorSensor.blue() > blueThreshold) {
            //blue
            telemetry.addLine("Seeing Blue");
            beaconPusher.setPosition(initialPosition - change);
        } else if (colorSensor.red() > redThreshold) {
            // red
            telemetry.addLine("Seeing Red");
            beaconPusher.setPosition(initialPosition + change);
        } else {
            telemetry.addLine(String.format("Blue %s | Red %s", colorSensor.blue(), colorSensor.red()));
            beaconPusher.setPosition(initialPosition);
        }
    }
}

