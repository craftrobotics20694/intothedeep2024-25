package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.Learn;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



@Config
@Autonomous(name="haltFunctionTest", group="learn")

public class haltFunctionTest extends LinearOpMode {

    @Override
    public void runOpMode() {
    MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-35, -50, Math.toRadians(90)));

    // Trajectory actions are actions (will go more into these later) that create a trajectory
    // A trajectory is a path that the robot can follow.
    Action trajectoryAction1; // Defining a variable called trajectoryAction

    // Now we get into actually building trajectories
    trajectoryAction1 = drive.actionBuilder(drive.pose) // drive.pose is the starting position you gave the robot

            .waitSeconds(0.1)
                .build();
    while (!isStarted()) {
        if (cameraBlocked == true) {
            Actions.runBlocking(
                    trajectoryAction1
            );
        }
    }
        return cameraBlocked;
}}
