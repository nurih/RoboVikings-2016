package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Disabled
@TeleOp(name = "Viking ZA", group = "Team")

public class VikingZA extends OpMode {
    public DcMotor  leftMotor   = null;
    public DcMotor rightMotor  = null;
    public HardwareMap hwMap  = null;

    @Override
    public void init() {
        leftMotor   = hwMap.dcMotor.get("left motor");
        rightMotor  = hwMap.dcMotor.get("right motor");
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    @Override
    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(- gamepad1.right_stick_y);
    }
}