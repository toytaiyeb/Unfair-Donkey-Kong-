public class FloorHole {


    private double x;
    private double y;
    private double halfHeight = 0.075;
    private double halfWidth = 0.015;

    /* Constructor: ladder at x, y
     * @param double x, double y
     * @return n/a
     */
    public FloorHole(double x, double y) {
        this.x = x;
        this.y = y;
    }



    /* Description: draws ladder
     * @param n/a
     * @return n/a
     */
    public void draw() {
        PennDraw.picture(x, y, "floorhole.png", 0.2* 256, 15);
    }

    /* Description: returns y
     * @param n/a
     * @return double y
     */
    public double getY() {
        return y;
    }

    /* Description: returns x
     * @param n/a
     * @return double x
     */
    public double getX() {
        return x;
    }







}
