package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Conveyor_Belt", group = "test")
public class Conveyor_Belt extends OpMode{
    DcMotor CBMotor = null;


    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        telemetry.addLine("Hey everybody");
        CBMotor = hardwareMap.dcMotor.get("CBMotor");
        CBMotor.setPower(gamepad1.left_stick_x);
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {

    }
}
