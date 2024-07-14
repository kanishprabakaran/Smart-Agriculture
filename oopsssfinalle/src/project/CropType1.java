package project;

import java.util.Arrays;
import java.util.List;

public enum CropType1 {
    WHEAT("CLAY", "LOAM", "SILT"),
    CORN("LOAM", "SILT"),
    BARLEY("CLAY", "LOAM", "SILT"),
    SOYBEAN("SAND", "LOAM", "SILT");

    private List<String> compatibleSoilTypes;

    CropType1(String... compatibleSoilTypes) {
        this.compatibleSoilTypes = Arrays.asList(compatibleSoilTypes);
    }

    public boolean isCompatibleWith(SoilType soilType) {
        return compatibleSoilTypes.contains(soilType.name());
    }
}
