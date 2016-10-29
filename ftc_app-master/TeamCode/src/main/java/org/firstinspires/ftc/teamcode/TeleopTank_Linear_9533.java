/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9bot;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TeleOp Mode", group="9533")
public class TeleopTank_Linear_9533 extends LinearOpMode {

    /* Declare OpMode members. */
    Hardware9533   robot           = new Hardware9533();              // Use a K9'shardware


    private boolean currentButtonState = false;
    private boolean previousButtonState = false;

    private void handleIntake(){
        if(gamepad2.right_bumper) {
            robot.intake.setPower(1);

        } else if(gamepad2.left_bumper) {
            robot.intake.setPower(-1);

        } else {
            robot.intake.setPower(0);

        }
    }
    private void handleElevator(){
        if(gamepad2.right_bumper) {

            robot.elevator.setPower(1);
        } else if(gamepad2.left_bumper) {

            robot.elevator.setPower(-1);
        } else {

            robot.elevator.setPower(0);
        }
    }
    private void handleShooter() {
        if(gamepad2.a) {
            robot.shooterLeft.setPower(1);
            robot.shooterRight.setPower(1);
        } else {
            robot.shooterRight.setPower(0);
            robot.shooterLeft.setPower(0);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            currentButtonState = gamepad1.right_bumper;

            if(currentButtonState != previousButtonState) {
                if(currentButtonState) {
                    robot.invertedDrive = !robot.invertedDrive;
                }
                previousButtonState = currentButtonState;
            }

            if(robot.invertedDrive) {
                telemetry.addData("DriveMode", "Reverse");
            } else {
                telemetry.addData("DriveMode", "Normal");
            }



            handleIntake();
            handleElevator();
            handleShooter();


            robot.DriveRobot(-gamepad1.left_stick_y, -gamepad1.right_stick_y, telemetry);

            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}
