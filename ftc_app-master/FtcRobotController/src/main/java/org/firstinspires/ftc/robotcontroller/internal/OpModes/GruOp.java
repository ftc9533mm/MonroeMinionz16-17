package org.firstinspires.ftc.robotcontroller.internal.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Kerfuffle on 9/17/2016.
 */
public class GruOp extends OpMode{

    private DcMotor leftM, rightM, ballGrab, ballLift, ballThrow;
    //private Servo rot;


    private boolean bgOn = false, bgFor = true;
    //private double throwAngle = 0;


    public void init()
    {
        leftM = hardwareMap.dcMotor.get("left");
        rightM = hardwareMap.dcMotor.get("right");

        //rot = hardwareMap.servo.get("rot");
        ballGrab = hardwareMap.dcMotor.get("ballGrabber");
        //ballThrow = hardwareMap.dcMotor.get("ballThrower");

        leftM.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop()
    {
        float left =  gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;


        if (gamepad1.a)
        {
            bgOn = !bgOn;
        }
        if (gamepad1.b)
        {
            bgFor = !bgFor;
        }


        if (bgOn)
        {
            double pow = -1;
            if (bgFor)
            {
                pow*=-1;
            }
            ballGrab.setPower(pow );
            //ballLift.setPower(1);
            //ballThrow.setPower(1);
        }
        else
        {
            ballGrab.setPower(0);
           // ballLift.setPower(0);
            //ballThrow.setPower(0);
        }


        /*if (gamepad1.dpad_up)
        {
            left *= 1.5f;
            right *= 1.5f;
        }
        if (gamepad1.dpad_down)
        {
            left *= 0.5f;
            right *= 0.5f;
        }*/

        left = Range.clip(left, -1f, 1f);
        right = Range.clip(right, -1f, 1f);

        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);

        leftM.setPower(left);
        rightM.setPower(right);

        telemetry.addData("left: " + left, left);
        telemetry.addData("right: " + right, right);

    }

    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}
