package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Jennifer on 10/28/2016.
 */
@TeleOp(name = "winder" , group = "test")
public class Winder extends OpMode {
    public DcMotor winderMotor = null;
    public TouchSensor limitSwitch1;
    public TouchSensor limitSwitch2;
    public boolean floss = false;
    @Override
    public void init() {
        limitSwitch1 = hardwareMap.touchSensor.get("limitSwitch1");
        limitSwitch2 = hardwareMap.touchSensor.get("limitSwitch2");

        winderMotor = hardwareMap.dcMotor.get("winderMotor");
        winderMotor.setPower(0);
    }

    @Override
    public void loop() {
        if (limitSwitch1.isPressed())
        {
            floss = true;
        }
        if (gamepad1.right_bumper && !limitSwitch1.isPressed() && !floss) {
            winderMotor.setPower(-0.25);
            }
         else if (floss) {
            if (!limitSwitch2.isPressed()) {
                winderMotor.setPower(0.25);
            } else {
                winderMotor.setPower(0);//donuts tho
                floss = false;
            }
        }
        else
        {
            winderMotor.setPower(0);
        }
    }
}
