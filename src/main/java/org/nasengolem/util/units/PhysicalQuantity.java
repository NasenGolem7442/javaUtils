package org.nasengolem.util.units;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public abstract class PhysicalQuantity {
    protected final double valueInBaseUnit;

    public PhysicalQuantity(double value, Unit unit) {
        this.valueInBaseUnit = unit.toBaseUnit(value);
    }

    protected abstract Unit getInternalUnit();
    protected abstract int getDefaultDecimalPlaces();

    public double getValue(Unit unit) {
        return unit.fromBaseUnit(valueInBaseUnit);
    }

    public BigDecimal getRoundedValue(Unit unit, int decimalPlaces) {
        BigDecimal value = BigDecimal.valueOf(getValue(unit));
        return value.setScale(decimalPlaces, RoundingMode.HALF_UP);
    }

    public String toString(Unit unit, int decimalPlaces, boolean abbreviateUnitName) {
        BigDecimal value = getRoundedValue(unit, decimalPlaces);

        String unitName;
        if (abbreviateUnitName) {
            unitName = unit.getAbbreviation();
        } else if (Objects.equals(value, BigDecimal.ONE)) {
            unitName = unit.getSingularName();
        } else {
            unitName = unit.getPluralName();
        }

        return getRoundedValue(unit, decimalPlaces) + " " + unitName;
    }

    public String toString(Unit unit, int decimalPlaces) {
        return toString(unit, decimalPlaces, false);
    }

    public String toString(Unit unit) {
        return toString(unit, getDefaultDecimalPlaces());
    }

    @Override
    public String toString() {
        return toString(getInternalUnit());
    }

    public interface Unit {
        double toBaseUnit(double value);
        double fromBaseUnit(double value);
        String getSingularName();
        String getPluralName();
        String getAbbreviation();
    }
}
