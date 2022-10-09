import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {

    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(this.picture);
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= picture.width() || y >= picture.height())
            throw new IllegalArgumentException();
        if (x == 0 || x == picture.width() - 1 || y == 0 || y == picture.height() - 1)
            return 1000;
        Color color = picture.get(x + 1, y);
        int r1 = color.getRed(), b1 = color.getBlue(), g1 = color.getGreen();
        color = picture.get(x - 1, y);
        int r2 = color.getRed(), b2 = color.getBlue(), g2 = color.getGreen();
        double dr = Math.abs(r1 - r2), db = Math.abs(b1 - b2), dg = Math.abs(g1 - g2);
        double dx = dr * dr + db * db + dg * dg;

        color = picture.get(x, y - 1);
        r1 = color.getRed(); b1 = color.getBlue(); g1 = color.getGreen();
        color = picture.get(x, y + 1);
        r2 = color.getRed(); b2 = color.getBlue(); g2 = color.getGreen();
        dr = Math.abs(r1 - r2); db = Math.abs(b1 - b2); dg = Math.abs(g1 - g2);
        double dy = dr * dr + db * db + dg * dg;

        return Math.sqrt(dx + dy);
    }

    // rotate the picture
    private void rotate_picture() {
        Picture tmp = new Picture(picture.height(), picture.width());
        for (int i = 0; i < picture.height(); i++)
            for (int j = 0; j < picture.width(); j++)
                tmp.setRGB(i, j, picture.getRGB(j, i));
        this.picture = tmp;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        rotate_picture();
        int[] seam = findVerticalSeam();
        rotate_picture();
        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] energy = new double[picture.height()][];
        double[][] distance = new double[picture.height()][];
        int[][] trace = new int[picture.height()][];
        /** init. */
        for (int i = 0; i < picture.height(); i++) {
            energy[i] = new double[picture.width()];
            distance[i] = new double[picture.width()];
            trace[i] = new int[picture.width()];
            for (int j = 0; j < picture.width(); j++) {
                energy[i][j] = this.energy(j, i);
                if (i == 0) distance[i][j] = 0;
                else distance[i][j] = Double.POSITIVE_INFINITY;
                trace[i][j] = -1;
            }
        }

        /** go in a row. */
        for (int i = 1; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                for (int nxtcol = j - 1; nxtcol <= j + 1; nxtcol++) {
                    if (nxtcol < 0 || nxtcol >= picture.width()) continue;
                    if (distance[i][nxtcol] > distance[i - 1][j] + energy[i][nxtcol]) {
                        distance[i][nxtcol] = distance[i - 1][j] + energy[i][nxtcol];
                        trace[i][nxtcol] = j;
                    }
                }
            }
        }

        /** find res. */
        int rescol = -1;
        double resdis = Double.POSITIVE_INFINITY;
        for (int i = 0; i < picture.width(); i++) {
            if (distance[picture.height() - 1][i] < resdis) {
                resdis = distance[picture.height()  -1][i];
                rescol = i;
            }
        }
        int[] seam = new int[picture.height()];
        for (int i = picture.height() - 1; i >= 0; i--) {
            seam[i] = rescol;
            rescol = trace[i][rescol];
        }
        return seam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        rotate_picture();
        removeVerticalSeam(seam);
        rotate_picture();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (picture.width() <= 1) throw new IllegalArgumentException();
        if (seam == null) throw new IllegalArgumentException();

        if (seam.length != picture.height()) throw new IllegalArgumentException();
        int preCol = -1;
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= picture.width()) throw new IllegalArgumentException();
            if (preCol != -1 && Math.abs(seam[i] - preCol) > 1) throw new IllegalArgumentException();
            preCol = seam[i];
        }

        Picture newpic = new Picture(picture.width() - 1, picture.height());
        for (int i = 0; i < picture.height(); i++) {
            int removeCol = seam[i];
            int cnt = 0;
            for (int j = 0; j < picture.width(); j++) {
                if (j == removeCol) continue;
                newpic.setRGB(cnt, i, picture.getRGB(j, i));
                cnt++;
            }
        }
        this.picture = newpic;


    }

    //  unit testing (optional)
    public static void main(String[] args) {
        /*SeamCarver carver = new SeamCarver(picture);
        Picture picture1 = carver.picture();
        picture1.set(col, row, color);
        Picture picture2 = carver.picture();*/
    }

}