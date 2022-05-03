package it.cnr.istc.icv.engine;

import java.awt.Color;
import java.awt.Paint;

public class PaintSupplier
{
    private Paint[] paintSequence;
    int curIdx;
    
    public PaintSupplier()
    {
        paintSequence = createPaintSequence();
        curIdx = 0;
    }
    
    public Paint getNextPaint()
    {
        return paintSequence[curIdx++ % paintSequence.length];
    }
    
    public static Paint[] createPaintSequence()
    {
        return new Paint[] {
                new Color(0xFF, 0x55, 0x55),
                new Color(0x55, 0x55, 0xFF),
                new Color(0x55, 0xFF, 0x55),
                new Color(0xFF, 0xFF, 0x55),
                new Color(0xFF, 0x55, 0xFF),
                new Color(0x55, 0xFF, 0xFF),
                Color.pink,
                Color.gray,
                new Color(0xc0, 0x00, 0x00),
                new Color(0x00, 0x00, 0xC0),
                new Color(0x00, 0x80, 0x00),
                new Color(0xC0, 0xC0, 0x00),
                new Color(0xC0, 0x00, 0xC0),
                new Color(0x00, 0xC0, 0xC0),
                Color.darkGray,
                new Color(0xFF, 0x40, 0x40),
                new Color(0x40, 0x40, 0xFF),
                new Color(0x40, 0xFF, 0x40),
                new Color(0xFF, 0xFF, 0x40),
                new Color(0xFF, 0x40, 0xFF),
                new Color(0x40, 0xFF, 0xFF),
                Color.lightGray,
                new Color(0x80, 0x00, 0x00),
                new Color(0x00, 0x00, 0x80),
                new Color(0x00, 0x80, 0x00),
                new Color(0x80, 0x80, 0x00),
                new Color(0x80, 0x00, 0x80),
                new Color(0x00, 0x80, 0x80),
                new Color(0xFF, 0x80, 0x80),
                new Color(0x80, 0x80, 0xFF),
                new Color(0x80, 0xFF, 0x80),
                new Color(0xFF, 0xFF, 0x80),
                new Color(0xFF, 0x80, 0xFF),
                new Color(0x80, 0xFF, 0xFF)
        };
    }

}
