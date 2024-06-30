package org.eln.eln3.sim;

import org.eln.eln3.sim.mna.component.Resistor;


// Despite being in this folder, this is really used for an actual resistor component placed in game.

/**
 * Created by svein on 07/08/15.
 */
/*
public class ResistorProcess implements IProcess {

    ResistorElement element;
    ResistorDescriptor descriptor;
    Resistor resistor;
    ThermalLoad thermal;

    private double lastResistance = -1;

    public ResistorProcess(ResistorElement element, Resistor resistor, ThermalLoad thermal, ResistorDescriptor descriptor) {
        this.element = element;
        this.descriptor = descriptor;
        this.resistor = resistor;
        this.thermal = thermal;
    }

    @Override
    public void process(double time) {
        double newResistance = Math.max(
            MnaConst.noImpedance,
            element.nominalRs * (1 + descriptor.tempCoef * thermal.temperatureCelsius));
        if (element.control != null) {
            newResistance *= (element.control.getNormalized() + 0.01) / 1.01;
        }
        if (newResistance > lastResistance * 1.01 || newResistance < lastResistance * 0.99) {
            resistor.setResistance(newResistance);
            lastResistance = newResistance;
            element.needPublish();
        }
*/
//        /*
//        * https://en.wikipedia.org/wiki/Thermistor
//        *
//        * R = exp[(x - y/2)^(1/3) - (x + y/2)^(1/3)]
//        * y = 1/c*(a - 1/T)
//        * x = sqrt((b/3c)^3 + (y/2)^2)
//        */
//
//        double T = thermal.Tc;
//        double y = 1.0 / descriptor.shC * (descriptor.shA - 1.0/T);
//        double x = Math.sqrt(Math.pow(descriptor.shB / 3.0 / descriptor.shC, 3) + Math.pow(y / 2.0, 2));
//        double R = Math.exp(Math.pow(x - y/2, 1.0/3.0) - Math.pow(x + y/2, 1.0/3.0));
//
//        r.setR(R);
/*
    }
}
*/