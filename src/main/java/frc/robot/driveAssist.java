package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class driveAssist {
    int port1 = 0;
    int port2 = 1;

    private Encoder encoder = new Encoder(port1, port2, false, EncodingType.k4X);

    public driveAssist() {
      
    }

    public double getRaw() {
        return encoder.getRaw();        
    }

    // public double getRawAverage(Encoder encoder, Encoder encoder2) {
    //     return (encoder.getRaw() + encoder.getRaw())/2;        
    // }

    public double getAvgDistance() {
        return getRaw() * 0.0008;
    }

    public void reset() {
        encoder.reset();        
    }
}
