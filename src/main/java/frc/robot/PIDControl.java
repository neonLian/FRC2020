/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class PIDControl {

    private double P = 0.05;
    private double I = 0.001;
    private double D = 0.03;
    private double derivative, integral, error, previous_error = 0;

    public PIDControl(double p, double i, double d)
    {
        P = p;
        I = i;
        D = d;
    }

    public double next(double error) 
    {
        integral += error * 0.2;
        derivative = (error - previous_error) / .02;
        previous_error = error;
        return P * error + I * integral + D * derivative;
    }

    public double get()
    {
        return P * error + I * integral + D * derivative;
    }

    public void reset()
    {
        error = 0;
        previous_error = 0;
        integral = 0;
    }

    public double getP() { return P; }
    public double getI() { return I; }
    public double getD() { return D; }
    public void setP(double p) { P = p; }
    public void setI(double i) { I = i; }
    public void setD(double d) { D = d; }


}
