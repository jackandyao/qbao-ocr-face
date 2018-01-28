package com.qbao.reconginse.util;


public class MathUtil {
	
	/**
	 *  pointsDistance
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double pointsDistance(double[] p1, double[] p2) {
		if (p1 == null || p2 == null || p1.length != p2.length) {
			return -1;
		}
		double distance = 0.0d;
		for (int i = 0; i < p1.length; i++) {
			distance += Math.pow(p1[i] - p2[i], 2);
		}
		return Math.sqrt(distance);
	}

	/**
	 *  pointsDistance x1,y1,x2,y2
	 * @param data
	 * @return
	 */
	public static double pointsDistance(double... data) {
		if (data == null || data.length % 2 != 0) {
			return -1;
		}
		double distance = 0.0d;
		int middle = data.length / 2;
		for (int i = 0; i < middle; i++) {
			distance += Math.pow(data[i] - data[middle + i], 2);
		}
		return Math.sqrt(distance);
	}

	/**
	 *  round
	 * @param digist
	 * @return
	 */
	 
	public static double round(double digist, int number) {
	    return Math.round(digist*Math.pow(10, number))*1.0d/Math.pow(10, number);
         
    }
	 

	/**
	 *  abs
	 * @param digist
	 * @return
	 */
	public static int abs(int digist) {
		return Math.abs(digist);
	}

	public static long abs(long digist) {
		return Math.abs(digist);
	}

	public static float abs(float digist) {
		return Math.abs(digist);
	}

	public static double abs(double digist) {
		return Math.abs(digist);
	}

	 
	 
}