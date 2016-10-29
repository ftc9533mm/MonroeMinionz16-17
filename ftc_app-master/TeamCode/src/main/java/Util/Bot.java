package Util;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;

/**
 * Created by Kerfuffle on 9/17/2016.
 */
public class Bot {

    private OpMode op;

    private ArrayList<Motor> motors = new ArrayList();

    public Bot(OpMode op)
    {
        this.op = op;
    }


    public void addMotor(Motor motor, String name, float power, Gamepad gp)
    {
        motor.setGamepad(gp);
        motors.add(motor);
    }


    public void loop()
    {
        for (Motor m : motors)
        {
           // m.runMotor();
        }
    }


}
