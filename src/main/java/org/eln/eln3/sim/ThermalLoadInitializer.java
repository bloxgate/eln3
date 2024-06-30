package org.eln.eln3.sim;

import org.eln.eln3.Eln3;
import org.eln.eln3.sim.process.destruct.ThermalLoadWatchDog;

public class ThermalLoadInitializer {

    public double maximumTemperature, minimumTemperature;
    double heatingTao;
    double conductionTao;

    double Rs, Rp, C;

    public ThermalLoadInitializer(double maximumTemperature, double minimumTemperature, double heatingTao, double conductionTao) {
        this.conductionTao = conductionTao;
        this.minimumTemperature = minimumTemperature;
        this.heatingTao = heatingTao;
        this.maximumTemperature = maximumTemperature;
    }

    public void setMaximalPower(double power) {
        C = power * heatingTao / (maximumTemperature);
        Rp = maximumTemperature / power;
        Rs = conductionTao / C / 2;

        ThermalValidator.checkThermalLoad(Rs, Rp, C);
    }

    public void applyTo(ThermalLoad load) {
        load.set(Rs, Rp, C);
    }

    public void applyTo(ThermalLoadWatchDog doggy) {
        doggy.setThermalLoad(this);
    }

    public ThermalLoadInitializer copy() {
        ThermalLoadInitializer thermalLoad = new ThermalLoadInitializer(maximumTemperature, minimumTemperature, heatingTao, conductionTao);
        thermalLoad.Rp = Rp;
        thermalLoad.Rs = Rs;
        thermalLoad.C = C;
        return thermalLoad;
    }
}
