package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Jennifer on 10/8/2016.
 */
@TeleOp(name = "basicDriveTrain" , group = "Test")
public class basicDriveTrain extends OpMode {
    public DcMotor  leftMotor   = null;
    public DcMotor rightMotor  = null;
    @Override
    public void init() {
        leftMotor   = hardwareMap.dcMotor.get("lmotor");
        rightMotor  = hardwareMap.dcMotor.get("rmotor");

        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    @Override
    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(- gamepad1.right_stick_y);
    }
}
