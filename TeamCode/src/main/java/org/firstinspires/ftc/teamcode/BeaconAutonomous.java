package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "Beacon Autonomous")
public class BeaconAutonomous extends OpMode {
    private final double initialPosition = 0.5;
    private final double change = 0.3;
    private ColorSensor colorSensor = null;
    private Servo beaconPusher = null;
    private TouchSensor Bumper;
    private int blueThreshold = 1;
    private int redThreshold = 2;
    private Alliance team = Alliance.Blue;
    private BeaconState currentState;
    private OpticalDistanceSensor follower;

    @Override
    public void init() {

        telemetry.addLine("Initializing color sensor");

        colorSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.colorsensor);

        colorSensor.enableLed(false);

        telemetry.addData("Initialized color sensor", colorSensor.getManufacturer());

        beaconPusher = TeamShared.getRobotPart(hardwareMap, RobotPart.beaconservo);

        beaconPusher.setPosition(initialPosition);
        telemetry.addLine("Initialized servo");
        currentState = BeaconState.START;
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
            telemetry.addLine(String.format("Colors below thresholds Blue %s | Red %s", colorSensor.blue(), colorSensor.red()));
            beaconPusher.setPosition(initialPosition);
        }
        switch (currentState) {
            case START:
                resetStartTime();
                currentState = BeaconState.DRIVE;
                break;
            case DRIVE:
                if (follower.getLightDetected() >= 0.5 || Bumper.isPressed()) {

                }
        }
    }

    enum BeaconState {
        START, DRIVE, FOLLOW, PRESS
    }
}

