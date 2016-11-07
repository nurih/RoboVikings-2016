package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Elevator", group = "Mini Op")
public class Elevator extends OpMode {
    DcMotor motor;


    @Override
    public void init() {
        telemetry.addLine("Initializing!");
        motor = TeamShared.getRobotPart(hardwareMap,RobotPart.elevatormotor);
        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setPower(0);
    }


    @Override
    public void loop() {

        motor.setPower(this.gamepad2.right_stick_y);
    }
}
