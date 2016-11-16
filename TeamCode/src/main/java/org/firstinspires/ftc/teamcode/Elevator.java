package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Elevator", group = "Mini Op")
public class Elevator extends OpMode {
    DcMotor elevatorMotor;
    TouchSensor upperTouchSensor;
    TouchSensor lowerTouchSensor;

    @Override
    public void init() {
        telemetry.addLine("Initializing!");
        elevatorMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatormotor);
        elevatorMotor.setDirection(DcMotor.Direction.FORWARD);
        elevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorMotor.setPower(0);

        lowerTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchlower);
        upperTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchupper);
    }


    @Override
    public void loop() {
        if (upperTouchSensor.isPressed() || lowerTouchSensor.isPressed()) {

            elevatorMotor.setPower(0);
            telemetry.addLine("Limit Reached - not moving");

        } else {

            elevatorMotor.setPower(this.gamepad2.right_stick_y);
            telemetry.addData("Stick position", this.gamepad2.right_stick_y);

        }
    }
}
