package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Drive", group = "Mini Op")
public class Drive extends OpMode {
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    @Override
    public void init() {
        leftMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);

        rightMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);

        telemetry.addLine("Initializing lmotor and rmotor");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setPower(0);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setPower(0);
    }

    @Override
    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);
    }
}
