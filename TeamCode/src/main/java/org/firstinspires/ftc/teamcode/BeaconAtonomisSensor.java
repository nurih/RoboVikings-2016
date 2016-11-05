package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
@TeleOp(name = "Beacon Color Sensor", group = "Mini Ops")

public class BeaconAtonomisSensor extends OpMode {
    public ColorSensor color = null;

    @Override
    public void init() {

        color = hardwareMap.colorSensor.get("colorsensor1");
        //passive mode
        color.enableLed(false);
    }

    @Override
    public void loop() {
        if (color.red() > 128) {
            //red
            telemetry.addLine("Red Works");
        } else if (color.blue() > 128) {
            //blue
            telemetry.addLine("Blue Works");
        }

    }
}

