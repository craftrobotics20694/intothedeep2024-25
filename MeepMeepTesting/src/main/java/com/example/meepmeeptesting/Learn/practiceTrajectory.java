package com.example.meepmeeptesting.Learn;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class practiceTrajectory {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
            .setConstraints(45, 50, Math.toRadians(180), Math.toRadians(180), 15)
            .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -60, Math.toRadians(90)))
//          TODO: See how each type of trajectory works
//              To comment or uncomment selected lines of code
//              Press ctrl or option and / at the same time
//

//             lineTo()
//                .lineToY(35)
//                .turn(Math.toRadians(-90))
//                .lineToX(35)
//                .turn(Math.toRadians(-90))
//                .lineToY(-35)
//                .turn(Math.toRadians(-90))
//                .lineToX(-35)

//             strafeTo()
//                .strafeTo(new Vector2d(0,-45))
//                .waitSeconds(0.5)
//                .strafeTo(new Vector2d(45,0))
//                .waitSeconds(.5)
//                .strafeTo(new Vector2d(0,45))
//                .waitSeconds(.5)
//                .strafeTo(new Vector2d(-45,0))
//                .waitSeconds(.5)
//                .strafeTo(new Vector2d(0,-45))

//              strafeToLinearHeading()
//                .strafeToLinearHeading(new Vector2d(0,0), Math.toRadians(-90))
//                .waitSeconds(.5)
//                .strafeToLinearHeading(new Vector2d(35, -60), Math.toRadians(90))
//                .waitSeconds(.5)
//                .strafeToLinearHeading(new Vector2d(45,-45), Math.toRadians(0))
//                .waitSeconds(.5)
//                .strafeToLinearHeading(new Vector2d(45,45), Math.toRadians(-90))
//                .waitSeconds(.5)
//                .strafeToLinearHeading(new Vector2d(-45,45), Math.toRadians(90))
//                .waitSeconds(.5)
//                .strafeToLinearHeading(new Vector2d(-45, -45), Math.toRadians(-90))
//                .waitSeconds(.5)
//                .strafeToLinearHeading(new Vector2d(-35,-60), Math.toRadians(90))

//              strafeToSplineHeading()
//                .strafeToSplineHeading(new Vector2d(35, -60), Math.toRadians(-90))
//                .waitSeconds(.5)
//                .strafeToSplineHeading(new Vector2d(35,45), Math.toRadians(90))
//                .waitSeconds(.5)
//                .strafeToSplineHeading(new Vector2d(0,0), Math.toRadians(0))
//                .waitSeconds(.5)
//                .strafeToSplineHeading(new Vector2d(-35, 45),Math.toRadians(-90))
//                .waitSeconds(.5)
//                .strafeToSplineHeading(new Vector2d(-35,-60), Math.toRadians(90))

//              splineTo()
//                .splineTo(new Vector2d(0,0), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineTo(new Vector2d(35, -55), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineTo(new Vector2d(35, 55), Math.toRadians(180))
//                .waitSeconds(.5)
//                .splineTo(new Vector2d(-35,55), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineTo(new Vector2d(-35, -60), Math.toRadians(0))

//            splineToConstantHeading()
//                .splineToConstantHeading(new Vector2d(0, 0), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineToConstantHeading(new Vector2d(35, -55), Math.toRadians(180))
//                .waitSeconds(.5)
//                .splineToConstantHeading(new Vector2d(35, 55), Math.toRadians(0))
//                .waitSeconds(.5)
//                .splineToConstantHeading(new Vector2d(-35, 55), Math.toRadians(180))
//                .waitSeconds(.5)
//                .splineToConstantHeading(new Vector2d(-35, -60), Math.toRadians(0))

//            splineToLinearHeading()
//                .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(0)), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineToLinearHeading(new Pose2d(35, -55, Math.toRadians(90)), Math.toRadians(0))
//                .waitSeconds(.5)
//                .splineToLinearHeading(new Pose2d(35, 55, Math.toRadians(180)), Math.toRadians(180))
//                .waitSeconds(.5)
//                .splineToLinearHeading(new Pose2d(-35, 55, Math.toRadians(90)), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineToLinearHeading(new Pose2d(-35, -60, Math.toRadians(90)), Math.toRadians(0))

//            splineToSplineHeading()
//                .splineToSplineHeading(new Pose2d(0, 0, Math.toRadians(90)), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineToSplineHeading(new Pose2d(35, -55, Math.toRadians(0)), Math.toRadians(0))
//                .waitSeconds(.5)
//                .splineToSplineHeading(new Pose2d(35, 55, Math.toRadians(90)), Math.toRadians(90))
//                .waitSeconds(.5)
//                .splineToSplineHeading(new Pose2d(-35, 55, Math.toRadians(270)), Math.toRadians(180))
//                .waitSeconds(.5)
//                .splineToSplineHeading(new Pose2d(-35, -60, Math.toRadians(90)), Math.toRadians(0))

            .build());

        // Getting the image of our play field to be used
        Image img = null;
        try {
            img = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/FIELD_INTOTHEDEEP.png"));
        }
        catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        meepMeep.setBackground(img)
            .setBackgroundAlpha(0.95f)
            .addEntity(myBot)
            .setDarkMode(true)
            .start();
    }
}