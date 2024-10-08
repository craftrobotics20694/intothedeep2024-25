package org.firstinspires.ftc.teamcode.drive.opmode.visionIntoTheDeep;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class itdCameraPipeline extends OpenCvPipeline {

    private final Mat grayscale = new Mat();
    //This is the mat that receives the camera output after a Grayscale filter is applied

    private final Mat clean = new Mat();
    //Diagnostic matrix to adjust camera.


    public enum sampleAngleStage {
    farLeft,
    middleLeft,
    nearLeft,
    Center,
    nearRight,
    middleRight,
    farRight,
    angleUNKNOWN,
    //Creating enum's to pull from later for various functions
    }

    private Mat farLeftMat = new Mat();
    private Mat middleLeftMat = new Mat();
    private Mat nearLeftMat = new Mat();
    private Mat centerMat = new Mat();
    private Mat nearRightMat = new Mat();
    private Mat middleRightMat = new Mat();
    private Mat farRightMat = new Mat();
    //Individual matrices to detect how far left/right on the camera the sample is,
    // and by proxy what angle we need to turn the robot to face the sample head on.

    private double farLeftAvg, middleLeftAvg, nearLeftAvg, centerAvg, nearRightAvg, middleRightAvg, farRightAvg;
    //Collects the average of how many pixels have turned white after grayscale filter is applied.

    private final Rect farLeftRect = new Rect(0,0,183,720);
    private final Rect middleLeftRect = new Rect(183,0,183,720);
    private final Rect nearLeftRect = new Rect(366,0,183,720);
    private final Rect centerRect = new Rect(548,0,183,720);
    private final Rect nearRightRect = new Rect(731,0,183,720);
    private final Rect middleRightRect = new Rect(914,0,183,720);
    private final Rect farRightRect = new Rect(1097,0,183,720);


    public enum sampleHeightStage {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        heightUNKNOWN,
    }
    // Creating bars to locate sample positions

    private Mat aMat = new Mat();
    private Mat bMat = new Mat();
    private Mat cMat = new Mat();
    private Mat dMat = new Mat();
    private Mat eMat = new Mat();
    private Mat fMat = new Mat();
    private Mat gMat = new Mat();
    //Individual matrices to detect how high on the camera the sample is,
    // and by proxy how far ahead of the robot it is.
    private double aAvg, bAvg, cAvg, dAvg, eAvg, fAvg, gAvg;
    //Collects the average of how many pixels have turned white after grayscale filter is applied.

    private final Rect aRect = new Rect(0,0,1280,103);
    private final Rect bRect = new Rect(0,104,1280,103);
    private final Rect cRect = new Rect(0,207,1280,103);
    private final Rect dRect = new Rect(0,310,1280,101);
    private final Rect eRect = new Rect(0,411,1280,103);
    private final Rect fRect = new Rect(0,514,1280,103);
    private final Rect gRect = new Rect(0,617,1280,103);

    @Override
    public Mat processFrame(Mat input) {

        Imgproc.cvtColor(input, grayscale, Imgproc.COLOR_RGB2GRAY);
        //converts image to grayscale
        input.copyTo(grayscale);
            // Process each region directly without redundant copying
            Mat aMat = grayscale.submat(aRect); // Get submat for 'A' region
            Scalar avgIntensityA = Core.mean(aMat); // Calculate average intensity
            aAvg = avgIntensityA.val[0]; // Store the average intensity

            Mat bMat = grayscale.submat(bRect); // Get submat for 'B' region
            Scalar avgIntensityB = Core.mean(bMat);
            bAvg = avgIntensityB.val[0];

            Mat cMat = grayscale.submat(cRect);
            Scalar avgIntensityC = Core.mean(cMat);
            cAvg = avgIntensityC.val[0];

            // Continue for other regions...
            Mat dMat = grayscale.submat(dRect);
            Scalar avgIntensityD = Core.mean(dMat);
            dAvg = avgIntensityD.val[0];

            Mat eMat = grayscale.submat(eRect);
            Scalar avgIntensityE = Core.mean(eMat);
            dAvg = avgIntensityE.val[0];

            Mat fMat = grayscale.submat(fRect);
            Scalar avgIntensityF = Core.mean(fMat);
            fAvg = avgIntensityF.val[0];

            Mat gMat = grayscale.submat(gRect);
            Scalar avgIntensityG = Core.mean(gMat);
            gAvg = avgIntensityG.val[0];

            // Repeat for other regions like nearLeft, farRight, etc.
            Mat farLeftMat = grayscale.submat(farLeftRect);
            Scalar avgIntensityFarLeft = Core.mean(farLeftMat);
            farLeftAvg = avgIntensityFarLeft.val[0];

            Mat middleLeftMat = grayscale.submat(middleLeftRect);
            Scalar avgIntensityMiddleLeft = Core.mean(middleLeftMat);
            middleLeftAvg = avgIntensityMiddleLeft.val[0];

            Mat nearLeftMat = grayscale.submat(nearLeftRect);
            Scalar avgIntensityNearLeft = Core.mean(nearLeftMat);
            nearLeftAvg = avgIntensityNearLeft.val[0];

            Mat centerMat = grayscale.submat(centerRect);
            Scalar avgIntensityCenter = Core.mean(centerMat);
            centerAvg = avgIntensityCenter.val[0];

            Mat nearRightMat = grayscale.submat(nearRightRect);
            Scalar avgIntensityNearRight = Core.mean(nearRightMat);
            nearRightAvg = avgIntensityNearRight.val[0];

            Mat middleRightMat = grayscale.submat(middleRightRect);
            Scalar avgIntensityMiddleRight = Core.mean(middleRightMat);
            middleRightAvg = avgIntensityMiddleRight.val[0];

            Mat farRightMat = grayscale.submat(farRightRect);
            Scalar avgIntensityFarRight = Core.mean(farRightMat);
            farRightAvg = avgIntensityFarRight.val[0];



        /*Imgproc.rectangle(grayscale, aRect, new Scalar(255.0, 255.0, 0.0), 2);
        Imgproc.rectangle(grayscale, bRect, new Scalar(255.0, 255.0, 0.0), 2);
        Imgproc.rectangle(grayscale, cRect, new Scalar(255.0, 255.0, 0.0), 2);
        Imgproc.rectangle(grayscale, dRect, new Scalar(255.0, 255.0, 0.0), 2);
        Imgproc.rectangle(grayscale, eRect, new Scalar(255.0, 255.0, 0.0), 2);
        Imgproc.rectangle(grayscale, fRect, new Scalar(255.0, 255.0, 0.0), 2);
        Imgproc.rectangle(grayscale, gRect, new Scalar(255.0, 255.0, 0.0), 2);

        Imgproc.rectangle(grayscale, farLeftRect, new Scalar(255.0,0.0,255.0),2);
        Imgproc.rectangle(grayscale, middleLeftRect, new Scalar(255.0,0.0,255.0),2);
        Imgproc.rectangle(grayscale, nearLeftRect, new Scalar(255.0,0.0,255.0),2);
        Imgproc.rectangle(grayscale, centerRect, new Scalar(255.0,0.0,255.0),2);
        Imgproc.rectangle(grayscale, nearRightRect, new Scalar(255.0,0.0,255.0),2);
        Imgproc.rectangle(grayscale, middleRightRect, new Scalar(255.0,0.0,255.0),2);
        Imgproc.rectangle(grayscale, farRightRect, new Scalar(255.0,0.0,255.0),2);*/
        // Creates rectangles on the driver hub vision screen for each matrix to better diagnose issues,
        // and visualize what stage is active.

        double[] angleAverages = {farLeftAvg, middleLeftAvg, nearLeftAvg, centerAvg, nearRightAvg, middleRightAvg, farRightAvg};
        String[] angle = {"farLeft", "middleLeft", "nearLeft", "center", "nearRight", "middleRight", "farRight"};
        // converts the avg's into strings to be analyzed in the integar array below

        int minAngleIndex = 0;
        for (int i = 1; i < angleAverages.length; i++) {
            if (angleAverages[i] < angleAverages[minAngleIndex]) {
                minAngleIndex = i;
            }
        }

        double[] sampleHeightAverages = {aAvg, bAvg, cAvg, dAvg, eAvg, fAvg, gAvg};
        String[] sampleHeight = {"A", "B", "C", "D", "E", "F", "G"};
        // converts the avg into strings to be analyzed in the integer array below

        int minHeightIndex = 0;
        for (int i = 1; i < sampleHeightAverages.length; i++) {
            if (sampleHeightAverages[i] < sampleHeightAverages[minHeightIndex]) {
                minHeightIndex = i;
            }
        }
        // the index starts at zero assuming whatever aAvg's value is as the minimum, and resets each time it gathers a new minimum
        // the for loop goes over all the averages checking if that average is lower than the previous minimum found
        // the loop terminates at G and will start over again to return a new value.


        String getAngle = angle[minAngleIndex];
        String getSampleHeight = sampleHeight[minHeightIndex];

        Imgproc.putText(grayscale, getSampleHeight, new Point(25, 100), Imgproc.FONT_HERSHEY_SIMPLEX, 3.0, new Scalar(255.0, 255.0, 0.0));
        // displays the measured Sample Location on screen.
        Imgproc.putText(grayscale, getAngle, new Point(100, 100), Imgproc.FONT_HERSHEY_SIMPLEX,3.0, new Scalar(255.0,0.0,255.0));
        // displays the measured angle on screen.
        return grayscale;
    }}