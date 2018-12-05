#define r_limit 255
#define v_limit 255
void Mandelbrot(double a, double b, double& x, double& y, int& nRepeat)
{
    x = 0.0;
    y = 0.0;
    for (nRepeat = 1; nRepeat <  r_limit; nRepeat ++)
    {
        double xn = x;
        x = x * x - y * y + a;
        y = 2 * xn * y + b;
        if ((x*x + y*y) >= v_limit*v_limit)
            break;
    }
}

void CMandelbrotSetView::DrawMandelbrotSet() 
{
        
    CClientDC dc(this);
    for (double a = -3.0; a <= 3.0; a += 0.005)
    for (double b = -2.0; b <= 2.0; b += 0.005)
    {
        double x, y;
        int nRepeat;
        Mandelbrot(a, b, x, y, nRepeat);
        COLORREF clrref = RGB(255-nRepeat, sqrt(x*x), sqrt(y*y));
        int xx = 600 + a * 200;
        int yy = 400 - b * 200;
        dc.SetPixel(xx, yy, clrref);
    }
}