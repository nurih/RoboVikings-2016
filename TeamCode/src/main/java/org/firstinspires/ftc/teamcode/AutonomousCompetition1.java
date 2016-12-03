package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Robovikings on 11/19/2016.
 */

enum Auto {
    START, SHOOT, CHECK, STOP
}

@Autonomous(name = "Autonomous Competition 1", group = "Comp")
public class AutonomousCompetition1 extends OpMode {
    private final int noPower = 0;
    private final double WindupTime = 0.8 ;
    private final double WindupPower = 1;
    public DcMotor winderMotor = null;
    Auto state_s;

    @Override
    public void init() {
        state_s = Auto.START;
        winderMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.windermotor );
        winderMotor.setDirection( DcMotor.Direction.FORWARD );
        winderMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.FLOAT );
        winderMotor.setPower( noPower );
    }

    @Override
    public void loop() {
        switch (state_s) {
            case START:
                resetStartTime();

                state_s = Auto.SHOOT;
                break;
            case SHOOT:
                winderMotor.setPower( WindupPower );

                state_s = Auto.CHECK;
                break;
            case CHECK:
                if (getRuntime() > WindupTime) {
                    state_s = Auto.STOP;
                }

                break;
            case STOP:
                winderMotor.setPower( noPower );
                break;
            default:
                state_s = Auto.STOP;
                break;
        }
        telemetry.addData( "State ", state_s );
        telemetry.addData( "Time ", getRuntime() );
    }
}
