package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Elevator", group = "Mini Op")
public class Elevator extends OpMode {
    DcMotor elevatorMotor;
    TouchSensor upperTouchSensor;
    TouchSensor lowerTouchSensor;

    DcMotorSimple.Direction direction;

    @Override
    public void init() {
        telemetry.addLine("Initializing!");
        direction = DcMotorSimple.Direction.FORWARD;

        elevatorMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatormotor);

        elevatorMotor.setDirection(direction);

        elevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorMotor.setPower(0);

        lowerTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchlower);
        upperTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchupper);
    }


    @Override
    public void loop() {
        float powerToUse = 0;
        if (upperTouchSensor.isPressed()) {
            direction = DcMotorSimple.Direction.FORWARD;
            telemetry.addLine("Limit Reached - reverse powert going more");
        }
        else if (lowerTouchSensor.isPressed())
        {
            direction = DcMotorSimple.Direction.REVERSE;
            telemetry.addLine("Limit Reached - reverse powert going more");
        }
        else if( this.gamepad2.left_trigger > 0.2){
            powerToUse = this.gamepad2.left_trigger;
        }


        telemetry.addData("Amount Triggered", powerToUse);
        elevatorMotor.setPower(powerToUse);
    }
}
