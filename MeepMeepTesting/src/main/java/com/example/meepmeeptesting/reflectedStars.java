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

public class reflectedStars {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(1000);

        // Defining our first bot
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        // Building the trajectory for our first bot
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -35, Math.toRadians(90)))
                .turn(Math.toRadians(-30))
                .strafeTo(new Vector2d(0, 35))
                .turn(Math.toRadians(-120))
                .strafeTo(new Vector2d(35, -35))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(-40, 10))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(40,10))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(-35,-35))
                .turn(Math.toRadians(-120))
                .waitSeconds(0.2)
                .build());

        // Defining our second bot
        RoadRunnerBotEntity myOtherBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueLight())
                .setConstraints(45, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        // Building the trajectory for our second bot
        myOtherBot.runAction(myOtherBot.getDrive().actionBuilder(new Pose2d(35, 35, Math.toRadians(-90)))
                .turn(Math.toRadians(-30))
                .strafeTo(new Vector2d(0, -35))
                .turn(Math.toRadians(-120))
                .strafeTo(new Vector2d(-35, 35))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(40, -10))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(-40,-10))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(35,35))
                .turn(Math.toRadians(-120))
                .waitSeconds(0.2)
                .build());

        // Getting the image of our play field to be used
        Image img = null;
        try {img = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/FIELD_INTOTHEDEEP.png"));}
        catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        // Customizing window and starting MeepMeep
        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(myOtherBot)
                .start();
    }
}

