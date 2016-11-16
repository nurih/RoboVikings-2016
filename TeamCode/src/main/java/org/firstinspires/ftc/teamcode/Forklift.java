package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Jason on 10/28/2016.
 */
@TeleOp(name = "Forklift", group = "Mini Op")
public class Forklift extends OpMode {

    DcMotor motor;
    /**
     * User defined init method
     * <p/>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        telemetry.addLine("Initializing!");
        motor = TeamShared.getRobotPart(hardwareMap, RobotPart.forkliftmotor);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0);
    }

    /**
     * User defined loop method
     * <p/>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {

        motor.setPower(this.gamepad2.left_stick_y);
    }
}
