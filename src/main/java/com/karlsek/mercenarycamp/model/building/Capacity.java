package com.karlsek.mercenarycamp.model.building;

public class Capacity {

    private int totalCapacity;
    private int totalNumberOfUnits;
    private int totalNumberOfReservedSlots;

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getTotalNumberOfUnits() {
        return totalNumberOfUnits;
    }

    public void setTotalNumberOfUnits(int totalNumberOfUnits) {
        this.totalNumberOfUnits = totalNumberOfUnits;
    }

    public int getTotalNumberOfReservedSlots() {
        return totalNumberOfReservedSlots;
    }

    public void setTotalNumberOfReservedSlots(int totalNumberOfReservedSlots) {
        this.totalNumberOfReservedSlots = totalNumberOfReservedSlots;
    }

}
