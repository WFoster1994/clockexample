package will.example.clockexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DialView extends View {

    private float angle;
    private Paint paint;

    public DialView(Context context) {
        super(context);

        //Setting the angle
        angle = 0;

        //Initialising the paint
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(150);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //Task 1 - Fill the background of the canvas
        canvas.drawRGB(248, 232, 198);

        /*Task 2 - Compute the width and height of the canvas.
        This will change depending on the orientation.
         */
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int radius;

        if(width > height){
            radius = height/2;
        } else {
            radius = width/2;
        }

        //Task 3 - Draw the Circles (Clock face)
        paint.setColor(Color.rgb(126, 79, 43));
        canvas.drawCircle(width/2, height/2, radius, paint);

        paint.setColor(Color.rgb(224, 153, 78));
        canvas.drawCircle(width/2, height/2, radius/2, paint);

        paint.setColor(Color.rgb(207, 69, 56));
        canvas.drawCircle(width/2, height/2, radius/4, paint);

        //Task 4 - Compute the new angle
        angle += 1;
        if (angle > 360)
            angle = 0;

        //Task 5 - Draw the line from the centre to the rim
        float radians = (float) (angle * (180/Math.PI));
        float stopX = (float) (width/2 + radius * Math.sin(radians));
        float stopY = (float) (height/2 - radius * Math.cos(radians));
        paint.setColor(Color.rgb(132, 175, 166));
        canvas.drawLine(width/2, height/2, stopX, stopY, paint );
    }

    /* This method will tell the system to redraw the DialView as soon as
    time is found to perform the task. This is slow and continuous, and must be
    called from a UI thread.
     */
    public void update() {
        invalidate();
    }
}
