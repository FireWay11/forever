package com.lovezc.forever.util.matrix;

import java.util.Arrays;

/**
 * 普利姆算法
 * @author 30556
 */
public class Prim {

	/**
	 * 表示到不了的边
 	 */
	public static volatile int MAX = Integer.MAX_VALUE;

	public void createGraph(MGraph graph, int vertex, char[] data, int[][] matrix) {
		for (int k = 0; k < vertex; k++) {
			graph.data[k] = data[k];
			for (int j = 0; j < vertex; j++) {
				graph.matrix[k][j] = matrix[k][j];
			}
		}
	}

	public void showGraph(MGraph graph) {
		for (int i = 0; i < graph.vertex; i++) {
			int[] link = graph.matrix[i];
			System.out.println(Arrays.toString(link).replaceAll(Prim.MAX + "","∞"));
		}
	}

	/**
	 * 普利姆核心算法
	 * @param graph
	 * @param v
	 */
	public void primCore(MGraph graph, int v) {
		int[] visited = new int[graph.vertex];

		visited[v] = 1;

		int vertex = graph.vertex;
		int minWeight = Prim.MAX;

		for (int k = 1; k < vertex; k++) {

			int t1 = -1;
			int t2 = -1;
			for (int i =0; i < vertex; i++) {
				for (int j = 0; j < vertex; j++) {
					boolean flag = visited[i] == 1 && visited[j] == 0 && graph.matrix[i][j] < minWeight;
					if (flag) {
						minWeight = graph.matrix[i][j];
						t1 = i;
						t2 = j;
					}
				}
			}

			//找到一条边是最小
			System.out.println("边 <" + graph.data[t1] + "," + graph.data[t2] + "> 权值:" + minWeight);
			visited[t2] = 1;
			minWeight = Prim.MAX;
		}

	}


}
