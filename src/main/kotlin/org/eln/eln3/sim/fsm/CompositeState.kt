package org.eln.eln3.sim.fsm

open class CompositeState : StateMachine(), State {
    override fun enter() {
        reset()
    }

    override fun leave() {
        stop()
    }

    override fun state(time: Double): State? {
        process(time)
        return null
    }
}
