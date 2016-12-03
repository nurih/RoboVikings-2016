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
        telemetry.addLine( "Initializing!" );
        direction = DcMotorSimple.Direction.FORWARD;

        elevatorMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.elevatormotor );

        elevatorMotor.setDirection( direction );

        elevatorMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE );

        elevatorMotor.setPower( 0 );

        lowerTouchSensor = TeamShared.getRobotPart( hardwareMap, RobotPart.elevatortouchlower );
        upperTouchSensor = TeamShared.getRobotPart( hardwareMap, RobotPart.elevatortouchupper );
    }


    @Override
    public void loop() {
        double powerToUse = 0;
        // decide direction
        if (upperTouchSensor.isPressed()) {
            direction = DcMotorSimple.Direction.REVERSE;
            telemetry.addLine( "Upper Limit Reached - reverse power" );
        } else if (lowerTouchSensor.isPressed()) {
            direction = DcMotorSimple.Direction.FORWARD;
            telemetry.addLine( "Lower Limit Reached - reverse power" );
        }

        // decide power
        if (Math.abs( gamepad2.right_stick_y ) > 0.2) {
            powerToUse = 0.25;
        } else {
            powerToUse = 0;
        }


        telemetry.addData( "Amount Triggered", powerToUse );
        telemetry.addData( "Direction", direction.name() );
        elevatorMotor.setDirection( direction );
        elevatorMotor.setPower( powerToUse );
    }
}
