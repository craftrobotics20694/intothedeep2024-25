package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(800);

        // Defining our first bot
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        // Building the trajectory for our first bot
//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(20, -56, Math.toRadians(90)))
//                .turn(Math.toRadians(38))
//                .strafeTo(new Vector2d( 0,-34))
//                .waitSeconds(1)
//                .turn(Math. toRadians (-125))
//                .strafeTo(new Vector2d(40, -34))
//                .turn(Math.toRadians(-60))
//                .waitSeconds( 0.2)
//                .strafeTo (new Vector2d( 56, -52))
//                .waitSeconds(1.5)
//                .turn(Math.toRadians(-145))
//                .strafeTo(new Vector2d(0, -34))
//                .waitSeconds(1)
//                .build());

        myBot.runAction(
                myBot.getDrive().actionBuilder(new Pose2d(-28, 59, Math.toRadians(270)))
                        .strafeTo(new Vector2d(0, 34))
                        .waitSeconds(0.1)
                        .strafeToSplineHeading(new Vector2d(-56, 50), Math.toRadians(90))
                        .waitSeconds(.1)
                        .strafeToSplineHeading(new Vector2d(0, 34), Math.toRadians(270))
                        .strafeTo(new Vector2d(-60, 65))
                        .build()
        );

        // Getting the image of our play field to be used
        Image img = null;
        try {
            img = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/FIELD_INTOTHEDEEP.png"));
        }
        catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

