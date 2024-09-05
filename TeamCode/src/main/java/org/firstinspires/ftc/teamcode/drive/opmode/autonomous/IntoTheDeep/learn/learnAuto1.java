package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.learn;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// This is the first intro to autonomous
// We will be making the robot move through the use of actions (introduced in RR 1.0).

// The @Config decorator is a part of dashboard and marks the class as a configuration class
// in the dashboard, which is where we see our camera feeds and other dashboard info.
@Config
// The @Autonomous decorator is a part of OpMode and marks the class as an autonomous operation.
// This is what makes the auto show up on the Control Hub so we can run it.
@Autonomous(name="learnAuto1", group="learn") // Name OpModes consistently; keeps good organization
public class learnAuto1 extends LinearOpMode {
    // LinearOpMode is the class that allows us to make op modes that are... linear.
    // 'Extends' implies inheritance of methods(functions) and variables from another class.

    // Now we get into building trajectories and actions for our robot to follow.
    @Override
    public void runOpMode() {
        // This line creates our drive variable and defines it as a member of MecanumDrive
        // MecanumDrive is the class that holds all of the info about our robot
        // This variable is what gives us access to the robot, allowing us to give it instructions.
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-35, -50, Math.toRadians(90)));

        // Trajectory actions are actions (will go more into these later) that create a trajectory
        // A trajectory is a path that the robot can follow.
        Action trajectoryAction1; // Defining a variable called trajectoryAction
        Action trajectoryAction2; // We are defining it as a member of the Action class
        Action trajectoryAction3; // Here we are simply creating the variables, not yet assigning values.

        // Now we get into actually building trajectories
        trajectoryAction1 = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0,0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(180))
                .lineToY(-25)
                .setTangent(Math.toRadians(90))
                .lineToX(25)
                .setTangent(Math.toRadians(0))
                .waitSeconds(0.5)
                .build();

        trajectoryAction2 = drive.actionBuilder(new Pose2d(25, -25, Math.toRadians(90)))
                .strafeTo(new Vector2d(0,0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(180))
                .lineToY(-25)
                .setTangent(Math.toRadians(90))
                .lineToX(25)
                .setTangent(Math.toRadians(0))
                .waitSeconds(0.5)
                .build();

        trajectoryAction3 = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0,0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(180))
                .lineToY(-25)
                .setTangent(Math.toRadians(90))
                .lineToX(25)
                .setTangent(Math.toRadians(0))
                .waitSeconds(0.5)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1,
                        trajectoryAction2,
                        trajectoryAction3
                )
        );
    }
}

/*


*/