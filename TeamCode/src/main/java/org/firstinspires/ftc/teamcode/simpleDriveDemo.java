package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Drive 2 motors",group = "tesst")
public class simpleDriveDemo extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        telemetry.addLine("Initialized!");
        leftMotor = hardwareMap.dcMotor.get("lmotor");
        rightMotor = hardwareMap.dcMotor.get("rmotor");

        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        ;
        rightMotor.setPower(this.gamepad1.right_stick_y);
        leftMotor.setPower(this.gamepad1.left_stick_y);
    }
}
