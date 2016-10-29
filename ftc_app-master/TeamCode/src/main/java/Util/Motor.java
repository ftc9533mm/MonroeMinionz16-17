package Util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Kerfuffle on 9/17/2016.
 */
public class Motor {

    private OpMode op;

    private DcMotor motor;
    private String name;
    private Gamepad gp;

    public Motor(OpMode op, DcMotor motor, String name)
    {
        this.motor = motor;
        this.name = name;
        this.op = op;

        motor = op.hardwareMap.dcMotor.get(name);
    }




    public DcMotor getMotor()
    {
        return motor;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        motor = op.hardwareMap.dcMotor.get(name);
    }
    public void setGamepad(Gamepad gp)
    {
        this.gp = gp;
    }
    public void runMotor(float power)
    {
        motor.setPower(power);
    }

}
