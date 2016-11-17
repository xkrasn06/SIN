/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sin;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 *
 * @author Milan
 */
public class MyAdjustmentListener implements AdjustmentListener {
    private final PaintPanel Panel;
    public MyAdjustmentListener(PaintPanel p) {
       this.Panel = p; 
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Panel.updateValues();
    }
    
}
