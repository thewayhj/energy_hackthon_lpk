package com.lpk.energy.enertalk;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Minwoo on 2017. 4. 7..
 */

@Document(collection = "REAL_TIME_USAGE_TB")
public class UsageItemDo {
    /**
     * timestamp                 timestamp when measurement was made
     * current               electricity current (A)
     * activePower               active power amount (mW)
     * billingActivePower                active power amount for bill charge (mW)
     * apparentPower                 apparent power amount (mW)
     * reactivePower                 reactive power amount (mW)
     * powerFactor               power factor (= active power / apparent power)
     * voltage               voltage amount (V)
     * positiveEnergy                cumulative positive energy (mWh)
     * negativeEnergy                cumulative negative energy (mWh)
     * positiveEnergyReactive                cumulative positive reactive energy (mVarh)
     * negativeEnergyReactive                cumulative negative reactive energy (mVarh)
     */

    String timestamp;
    String current;
    String activePower;
    String billingActivePower;
    String apparentPower;
    String reactivePower;
    String powerFactor;
    String voltage;
    String positiveEnergy;
    String negativeEnergy;
    String positiveEnergyReactive;
    String negativeEnergyReactive;



}
