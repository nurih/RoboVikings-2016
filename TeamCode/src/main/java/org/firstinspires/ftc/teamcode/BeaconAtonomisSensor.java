package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "Beacon Color Sensor", group = "Mini Ops")
public class BeaconAtonomisSensor extends OpMode {
    private final int colorThreshold = 128;
    public ColorSensor colorSensor = null;

    @Override
    public void init() {

        colorSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.colorsensor);

        colorSensor.enableLed(false);

        telemetry.addData("Initialized color sensor threshold %s", colorSensor.getManufacturer());
        telemetry.addData("Initialized color sensor threshold %s", colorThreshold);
    }

    @Override
    public void loop() {
        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("Blue", colorSensor.blue());
        if (colorSensor.red() > colorThreshold) {
            //red
            telemetry.addLine("Red Works");
        } else if (colorSensor.blue() > colorThreshold) {
            //blue
            telemetry.addLine("Blue Works");
        }
    }
}

