package com.lovezc.forever.util.matrix;

public class MGraph{
	/**
	 * 节点个数
	 */
	int vertex;
	/**
	 * 存放节点数据
	 */
	char [] data;
	/**
	 * 存放边, 邻接矩阵
	 */
	int [][] matrix;

	public MGraph(int vertex){
		this.vertex = vertex;
		data = new char[vertex];
		matrix = new int[vertex][vertex];
	}
}