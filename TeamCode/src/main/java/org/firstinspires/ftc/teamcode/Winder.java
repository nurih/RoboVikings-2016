package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Jennifer on 10/28/2016.
 */
@TeleOp(name = "Winder", group = "test")
public class Winder extends OpMode {
    private final double winderPower = 0.5;
    public DcMotor winderMotor = null;
    public boolean floss = false;

    @Override
    public void init() {
        winderMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.windermotor);
        winderMotor.setDirection(DcMotor.Direction.FORWARD);
        winderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        winderMotor.setPower(0);
    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            winderMotor.setPower(winderPower);

        } else {
            winderMotor.setPower(0);
        }
    }
}
