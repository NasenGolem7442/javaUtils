package org.nasengolem.units;

public class Temperature extends PhysicalQuantity {
    private static final PhysicalQuantity.Unit INTERNAL_UNIT = Unit.KELVIN;
    private static final int DEFAULT_DECIMAL_PLACES = 1;

    public Temperature(double value, Temperature.Unit unit) {
        super(value, unit);
    }

    @Override
    protected PhysicalQuantity.Unit getInternalUnit() {
        return INTERNAL_UNIT;
    }

    @Override
    protected int getDefaultDecimalPlaces() {
        return DEFAULT_DECIMAL_PLACES;
    }

    public enum Unit implements PhysicalQuantity.Unit {
        KELVIN("Kelvin", "Kelvin", "K"
                , (double kelvinTemp) -> kelvinTemp
                , (double kelvinTemp) -> kelvinTemp),
        CELSIUS("degree Celsius", "degrees Celsius", "°C"
                , (double celsiusTemp) -> celsiusTemp + 273.15
                , (double kelvinTemp) -> kelvinTemp - 273.15),
        FAHRENHEIT("degree Fahrenheit", "degrees Fahrenheit", "°F"
                , (double fahrenheitTemp) -> (fahrenheitTemp - 32) * 5 / 9 + 273.15
                , (double kelvinTemp) -> (kelvinTemp - 273.15) * 9 / 5 + 32),
        RAKINE("degree Rankine", "degrees Rankine", "°R"
                , (double rankineTemp) -> rankineTemp * 5 / 9
                , (double kelvinTemp) -> kelvinTemp * 9 / 5);

        private final String singularName;
        private final String pluralName;
        private final String abbreviation;
        private final ToKelvinConverter toKelvinConverter;
        private final FromKelvinConverter fromKelvinConverter;

        @FunctionalInterface
        public interface ToKelvinConverter {
            double toKelvin(double temperature);
        }

        @FunctionalInterface
        public interface FromKelvinConverter {
            double fromKelvin(double temperature);
        }

        Unit(String singularName, String pluralName, String abbreviation, ToKelvinConverter toKelvinConverter
                , FromKelvinConverter fromKelvinConverter) {
            this.singularName = singularName;
            this.pluralName = pluralName;
            this.abbreviation = abbreviation;
            this.toKelvinConverter = toKelvinConverter;
            this.fromKelvinConverter = fromKelvinConverter;
        }

        @Override
        public double toBaseUnit(double value) {
            return toKelvinConverter.toKelvin(value);
        }

        @Override
        public double fromBaseUnit(double value) {
            return fromKelvinConverter.fromKelvin(value);
        }

        public String getSingularName() {
            return singularName;
        }

        @Override
        public String getPluralName() {
            return pluralName;
        }

        public String getAbbreviation() {
            return abbreviation;
        }
    }
}
