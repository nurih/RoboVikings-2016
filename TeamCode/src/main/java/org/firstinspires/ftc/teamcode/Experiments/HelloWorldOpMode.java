package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotPart;
import org.firstinspires.ftc.teamcode.TeamShared;


@TeleOp(name = "Hello World", group = "Demos")
public class HelloWorldOpMode extends OpMode {
    /**
     * User defined init method
     * <p/>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        telemetry.addLine("Starting Op Mode!");
        telemetry.update();
        DcMotor m = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);
    }

    /**
     * User defined loop method
     * <p/>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {

        telemetry.addLine("Looping");

    }
}
