package org.eln.eln3.sim.mna.component;


import org.eln.eln3.sim.mna.SubSystem;
import org.eln.eln3.sim.mna.state.CurrentState;
import org.eln.eln3.sim.mna.state.State;

public class Transformer extends Bipole {

    public Transformer() {}

    public CurrentState aCurrentState = new CurrentState();
    public CurrentState bCurrentState = new CurrentState();

    public Transformer(State aPin, State bPin) {
        super(aPin, bPin);
    }

    double ratio = 1;

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }

    @Override
    public void quitSubSystem() {
        subSystem.states.remove(aCurrentState);
        subSystem.states.remove(bCurrentState);
        super.quitSubSystem();
    }

    @Override
    public void addToSubsystem(SubSystem s) {
        super.addToSubsystem(s);
        s.addState(aCurrentState);
        s.addState(bCurrentState);
    }

    @Override
    public void applyToSubsystem(SubSystem s) {
        s.addToA(bPin, bCurrentState, 1.0);
        s.addToA(bCurrentState, bPin, 1.0);
        s.addToA(bCurrentState, aPin, -ratio);

        s.addToA(aPin, aCurrentState, 1.0);
        s.addToA(aCurrentState, aPin, 1.0);
        s.addToA(aCurrentState, bPin, -1 / ratio);

        s.addToA(aCurrentState, aCurrentState, 1.0);
        s.addToA(aCurrentState, bCurrentState, ratio);
        s.addToA(bCurrentState, aCurrentState, 1.0);
        s.addToA(bCurrentState, bCurrentState, ratio);
    }

    @Override
    public double getCurrent() {
        return 0;
    }
}
