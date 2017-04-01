package activity;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MainActivity {
	/**
	 * 读取一张图片的RGB值
	 * 
	 * @throws Exception
	 */
	public void getImagePixel(String image) throws Exception {
		//定义代表RGB的数组
		int[] rgb = new int[3];
		//获取文件对象
		File file = new File(image);
		//判断文件是否存在
		if(!file.exists()){
			System.out.println("不存在该文件！");
			System.exit(0);
		}
		//创建流空对象
		BufferedImage bi = null;
		try {
			//吧图片流读取到创建的流对象
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取图片的长和宽
		int width = bi.getWidth();
		int height = bi.getHeight();
		//创建流中的坐标
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		//输出结果
		System.out.println("width=" + width + ",height=" + height + ".");
		System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
			}
		}
	}

	/**
	 * 返回屏幕色彩值
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws AWTException
	 */
	public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
		Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
		rb = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
		Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
		System.out.println(di.width);
		System.out.println(di.height);
		Rectangle rec = new Rectangle(0, 0, di.width, di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		int pixelColor = bi.getRGB(x, y);

		return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		int x = 0;
		MainActivity rc = new MainActivity();
		x = rc.getScreenPixel(100, 345);
		System.out.println(x + " - ");
		rc.getImagePixel("ico.png");//输入图片位置，可读取jpg和png
	}

}
