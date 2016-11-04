package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
/**
 * Created by Robovikings on 10/29/2016.
 */
public class BeaconAtonomisSensor extends OpMode {
    public ColorSensor color = null;
    @Override
    public void init() {
        color = hardwareMap.colorSensor.get("colorsensor1");
    }

    @Override
    public void loop() {
        if (color.red() > 1) {
            //red
        } else if (color.blue() > 1) {
            //blue
            }
        }
    }

