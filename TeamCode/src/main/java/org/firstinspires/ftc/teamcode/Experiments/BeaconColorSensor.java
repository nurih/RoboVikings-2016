package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.RobotPart;
import org.firstinspires.ftc.teamcode.TeamShared;

@Disabled
@TeleOp(name = "Beacon Color Sensor", group = "Test")
public class BeaconColorSensor extends OpMode {


    public ColorSensor colorSensor = null;

    @Override
    public void init() {

        colorSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.colorsensor);

        colorSensor.enableLed(false);

        telemetry.addData("Initialized color sensor", colorSensor.getManufacturer());

    }

    @Override
    public void loop() {

        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("Blue", colorSensor.blue());
        if (colorSensor.red() > 0) {
            //red
            telemetry.addLine("Red Works");
        } else if (colorSensor.blue() > 0) {
            //blue
            telemetry.addLine("Blue Works");
        }
    }
}

