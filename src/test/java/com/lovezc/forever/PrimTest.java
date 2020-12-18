package com.lovezc.forever;

import com.lovezc.forever.util.matrix.MGraph;
import com.lovezc.forever.util.matrix.Prim;
import org.junit.Test;

public class PrimTest {

	@Test
	public void prim() {
		char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int MAX = Prim.MAX;

		//邻接矩阵的关系使用二维数组表示,M 这个 表示无穷大的大数，表示两个点不联通
		//     *  | A  |  B  |  C  |  D  |  E  |  F |  G  |
		//     A  | M  |  5  |  7  |  M  |  M  |  M |  2  |
		//     B  | 5  |  M  |  M  |  9  |  M  |  M |  3  |
		//     C  | 7  |  M  |  M  |  M  |  8  |  M |  M  |
		//     D  | M  |  9  |  M  |  M  |  M  |  4 |  M  |
		//     E  | M  |  M  |  8  |  M  |  M  |  5 |  4  |
		//     F  | M  |  M  |  M  |  4  |  5  |  M |  6  |
		//     G  | 2  |  3  |  M  |  M  |  4  |  6 |  M  |
		int[][] weight = new int[][]{
				{MAX, 5, 7, MAX, MAX, MAX, 2},
				{5, MAX, MAX, 9, MAX, MAX, 3},
				{7, MAX, MAX, MAX, 8, MAX, MAX},
				{MAX, 9, MAX, MAX, MAX, 4, MAX},
				{MAX, MAX, 8, MAX, MAX, 5, 4},
				{MAX, MAX, MAX, 4, 5, MAX, 6},
				{2, 3, MAX, MAX, 4, 6, MAX},
		};

		// 生成图
		MGraph mGraph = new MGraph(data.length);

		Prim prim = new Prim();
		prim.createGraph(mGraph, data.length, data, weight);
		prim.primCore(mGraph, 1); // 3 2 4 5 4 7
	}
}
