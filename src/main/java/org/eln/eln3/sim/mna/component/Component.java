package org.eln.eln3.sim.mna.component;


import org.eln.eln3.sim.mna.RootSystem;
import org.eln.eln3.sim.mna.SubSystem;
import org.eln.eln3.sim.mna.state.State;

public abstract class Component {

    SubSystem subSystem;

    public IAbstractor abstractedBy;

    public Component() {}

    public void addToSubsystem(SubSystem s) {
        this.subSystem = s;
    }

    public SubSystem getSubSystem() {
        if (isAbstracted()) return abstractedBy.getAbstractorSubSystem();
        return subSystem;
    }

    public abstract void applyToSubsystem(SubSystem s);

    public abstract State[] getConnectedStates();

    public boolean canBeReplacedByInterSystem() {
        return false;
    }

    public void breakConnection() {
    }

    public void returnToRootSystem(RootSystem root) {
        root.addComponents.add(this);
    }

    public void dirty() {
        if (abstractedBy != null) {
            abstractedBy.dirty(this);
        } else if (getSubSystem() != null) {
            getSubSystem().invalidate();
        }
    }

    public void quitSubSystem() {
        subSystem = null;
    }

    public boolean isAbstracted() {
        return abstractedBy != null;
    }

    public void onAddToRootSystem() {}

    public void onRemoveFromRootSystem() {}

    public String toString() {
        return "(" + this.getClass().getSimpleName() + ")";
    }
}
