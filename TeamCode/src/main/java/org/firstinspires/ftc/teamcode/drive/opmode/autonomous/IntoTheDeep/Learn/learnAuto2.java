package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.Learn;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

// This is the second intro to autonomous
// We will be adding claw and lift functionality
// You will find this is actually easier than you'd expect because of RoadRunner

@Config
@Autonomous(name = "learnAuto2", group = "learn")
public class learnAuto2 extends LinearOpMode {

    // Here we make a class for our Claw
    public static class Claw {
        private final Servo clawLeft, clawRight; // Define a variable called claw that is of the Servo class

        // This is the constructor for our Claw class
        public Claw(HardwareMap hardwareMap) {
            // We locate the servo in the hardware map and assign it to the
            //   claw variable we created early
            clawLeft = hardwareMap.get(Servo.class, "clawLeft");
            clawRight = hardwareMap.get(Servo.class, "clawRight");
        }

        // This is a method called CloseClaw that inherits from the Action class
        // We'll be able to use these methods on any instance our our Claw class
        public class CloseClaw implements Action {
            // We override the run function from the Action class
            //   and specify what we want to do when the function is run
            //   this allows us to call the function as an action
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                clawLeft.setPosition(0); // Setting the position of the left side servo
                clawRight.setPosition(0); // Setting the position of the right side servo
                return false;
            }
        }
        // This is where we create the action called closeClaw()
        //   so when we call the closeClaw() Action it will
        //   return the result of the CloseClaw() function
        //   which basically just runs the run() function inside it
        public Action closeClaw() {
            return new CloseClaw();
        }

        // This is the OpenClaw function
        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                clawLeft.setPosition(1); // Setting the position of the left side servo
                clawRight.setPosition(1); // Setting the position of the right side servo
                return false;
            }
        }
        // Create openClaw() action
        public Action openClaw() {
            return new OpenClaw();
        }
    }

    // Override the runOpMode function from LinearOpMode
    @Override
    public void runOpMode() {
        // Initialize drive variable as a member of MecanumDrive
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-35, -60, Math.toRadians(90)));

        // Initialized claw variable as a member of the Claw class we made above
        Claw claw = new Claw(hardwareMap);

        // Create action variables
        Action trajectoryAction1;
        Action trajectoryAction2;

        // Define action variables
        trajectoryAction1 = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(-90))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .lineToY(-34)
                .turn(Math.toRadians(90))
                .lineToX(-60)
                .turn(Math.toRadians(-90))
                .lineToY(34)
                .turn(Math.toRadians(-90))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .lineToY(60)
                .turn(Math.toRadians(90))
                .lineToX(-35)
                .turn(Math.toRadians(90))
                .build();

        trajectoryAction2 = drive.actionBuilder(new Pose2d(60, -35, Math.toRadians(90)))
                .turn(Math.toRadians(90))
                .lineToX(34)
                .turn(Math.toRadians(-90))
                .lineToY(34)
                .turn(Math.toRadians(90))
                .lineToX(60)
                .turn(Math.toRadians(-90))
                .lineToY(-34)
                .turn(Math.toRadians(-90))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .lineToY(-60)
                .turn(Math.toRadians(90))
                .lineToX(35)
                .turn(Math.toRadians(90))
                .build();

        // This stops everything the robot does until the start button is pressed
        waitForStart();

        // This runs our actions
        Actions.runBlocking(
                new SequentialAction(
                        claw.closeClaw(),
                        claw.openClaw(),
                        claw.closeClaw(),
                        claw.openClaw()
            ));
    }
}
