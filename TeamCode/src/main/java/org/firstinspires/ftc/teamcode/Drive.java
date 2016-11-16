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


        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setPower(0);

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setPower(0);

        telemetry.addLine(String.format("Initializing %s  and %s",RobotPart.lmotor, RobotPart.rmotor));
    }

    @Override
    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);
    }
}
