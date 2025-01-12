package com.example.sqlite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CurvedBackgroundView extends View {
    private Paint paint;
    private Path path;

    public CurvedBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Khởi tạo Paint
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(android.R.color.white)); // Màu nền trắng
        paint.setAntiAlias(true); // Khử răng cưa

        // Khởi tạo Path
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Reset đường dẫn trước khi vẽ
        path.reset();

        // Tạo đường cong nhẹ nhàng ở phía trên
        path.moveTo(0, height * 0.6f); // Bắt đầu từ bên trái ở 60% chiều cao
        path.quadTo(width / 2, height * 0.3f, width, height * 0.6f); // Đường cong lên giữa và trở lại phía bên phải
        path.lineTo(width, height); // Đường thẳng xuống đáy phải
        path.lineTo(0, height); // Đường thẳng sang đáy trái
        path.close(); // Đóng Path

        // Vẽ Path
        canvas.drawPath(path, paint);
    }

}
