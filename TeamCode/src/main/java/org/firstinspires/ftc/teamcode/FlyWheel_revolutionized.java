package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FlyWheel_Revolutionized", group = "test")
public class FlyWheel_revolutionized extends OpMode {
    DcMotor FwMotor1 = null;
    DcMotor FwMotor2 = null;

    /**
     *
     *
     * User defined init method
     * <p/>
     * This method will be called once when the INIT button is pressed.
     */

    @Override
    public void init() {
        FwMotor1 = hardwareMap.dcMotor.get("LeftMotor");
        FwMotor2 = hardwareMap.dcMotor.get("RightMotor");
        FwMotor1.setPower(0);
        FwMotor2.setPower(0);
    }

    /**
     * User defined loop method
     * <p/>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        if(gamepad1.y) {
            FwMotor1.setPower(1);
            FwMotor2.setPower(-1);
        }
        else if(gamepad1.x) {
            FwMotor1.setPower(-1);
            FwMotor2.setPower(1);
        }
        else

        {
            FwMotor1.setPower(0);
            FwMotor2.setPower(0);
        }
    }
}

