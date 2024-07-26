package org.nasengolem.util.units;

public class Length extends PhysicalQuantity {
    private static final Unit INTERNAL_UNIT = Unit.METRE;
    private static final int DEFAULT_DECIMAL_PLACES = 2;

    public Length(double value, Unit unit) {
        super(value, unit);
    }

    @Override
    protected Unit getInternalUnit() {
        return INTERNAL_UNIT;
    }

    @Override
    protected int getDefaultDecimalPlaces() {
        return DEFAULT_DECIMAL_PLACES;
    }

    public enum Unit implements PhysicalQuantity.Unit {
        METRE("metre", "metres", "m"
                , (double metres) -> metres
                , (double metres) -> metres),
        KILOMETRE("kilometre", "kilometres", "km"
                , (double kilometres) -> 1000 * kilometres
                , (double metres) -> metres / 1000),
        CENTIMETRE("centimetre", "centimetres", "cm"
                , (double centimetres) -> centimetres / 100
                , (double metres) -> 100 * metres),
        MILLIMETRE("millimetre", "millimetres", "mm"
                , (double millimetres) -> millimetres / 1000
                , (double metres) -> 1000 * metres),
        INCH("inch", "inches", "in"
                , (double inches) -> 0.0254 * inches
                , (double metres) -> metres / 0.0254),
        FOOT("foot", "feet", "ft"
                , (double feet) -> 0.3048 * feet
                , (double metres) -> metres / 0.3048),
        YARD("yard", "yards", "yd"
                , (double yards) -> 0.9144 * yards
                , (double metres) -> metres / 0.9144),
        MILE("mile", "miles", "mi"
                , (double miles) -> 1609.344 * miles
                , (double metres) -> metres / 1609.344),
        NAUTICAL_MILE("nautical mile", "nautical miles", "nmi"
                , (double nauticalMiles) -> 1852 * nauticalMiles
                , (double metres) -> metres / 1852);

        private final String singularName;
        private final String pluralName;
        private final String abbreviation;
        private final ToMetreConverter toMetreConverter;
        private final FromMetreConverter fromMetreConverter;

        @FunctionalInterface
        public interface ToMetreConverter {
            double toMetre(double length);
        }

        @FunctionalInterface
        public interface FromMetreConverter {
            double fromMetre(double length);
        }

        Unit(String singularName, String pluralName, String abbreviation, ToMetreConverter toMetreConverter
                , FromMetreConverter fromMetreConverter) {
            this.singularName = singularName;
            this.pluralName = pluralName;
            this.abbreviation = abbreviation;
            this.toMetreConverter = toMetreConverter;
            this.fromMetreConverter = fromMetreConverter;
        }

        @Override
        public double toBaseUnit(double value) {
            return toMetreConverter.toMetre(value);
        }

        @Override
        public double fromBaseUnit(double value) {
            return fromMetreConverter.fromMetre(value);
        }

        @Override
        public String getSingularName() {
            return singularName;
        }

        @Override
        public String getPluralName() {
            return pluralName;
        }

        @Override
        public String getAbbreviation() {
            return abbreviation;
        }
    }
}