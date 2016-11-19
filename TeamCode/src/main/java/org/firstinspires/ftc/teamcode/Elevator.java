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
        elevatorMotor.setDirection(DcMotor.Direction.REVERSE);
        elevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorMotor.setPower(0);

        lowerTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchlower);
        upperTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchupper);
    }


    @Override
    public void loop() {
        float powerToUse = this.gamepad2.right_stick_y;
        if (upperTouchSensor.isPressed() || lowerTouchSensor.isPressed()) {
            // go back a bit.
            powerToUse = -powerToUse;
            telemetry.addLine("Limit Reached - reverse powert going more");
        }
        telemetry.addData("Stick position", powerToUse);
        elevatorMotor.setPower(powerToUse);
    }
}
