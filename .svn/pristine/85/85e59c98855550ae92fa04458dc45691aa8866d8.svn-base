package com.common.util;

import java.awt.BasicStroke;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class JFreeChartUtils {

	private String title;
	private boolean if3D = false;
	private Plot plot;
	private boolean legend = true;
	private boolean tooltips = true;
	private boolean urls = true;
	
	/**
	 * 实例块，初始化图形报表字体（避免汉字乱码）
	 */
	static {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		standardChartTheme.setExtraLargeFont(new Font("宋书", Font.BOLD, 15));
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 12));
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 12));
		ChartFactory.setChartTheme(standardChartTheme);
	}
	
	/**
	 * 私有构造方法
	 * @param title 图形报表头
	 * @param if3D 是否3D效果
	 */
	private JFreeChartUtils(String title, boolean if3D){
		this.title = title;
		this.if3D = if3D;
	}
	
	/**
	 * 获取JFreeChartUtil对象
	 * @param title 图形报表头
	 */
	public static JFreeChartUtils getInstence(String title){
		return new JFreeChartUtils(title, false);
	}
	
	/**
	 * 获取JFreeChartUtil对象
	 * @param title 图形报表头
	 * @param if3D 是否3D效果
	 */
	public static JFreeChartUtils getInstence(String title, boolean if3D){
		return new JFreeChartUtils(title, if3D);
	}

	/**
	 * 根据给定数据创建饼状图
	 * Function Name: createPieChart
	 * @param dfp 数据集
	 * @return
	 */
	public JFreeChart createPieChart(DefaultPieDataset dfp) {
		JFreeChart jFreeChart = null;
		if (if3D)
			jFreeChart = ChartFactory.createPieChart3D(title, dfp, legend, tooltips, urls);
		else
			jFreeChart = ChartFactory.createPieChart(title, dfp, legend, tooltips, urls);
		PiePlot plot = (PiePlot) jFreeChart.getPlot();
		setPlot(plot);
		String unitSytle = "{0}={1}({2})";
		plot.setStartAngle(90);
		plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle, NumberFormat.getNumberInstance(), new DecimalFormat("0.0%")));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle, NumberFormat.getNumberInstance(), new DecimalFormat("0.0%")));
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle, NumberFormat.getNumberInstance(), new DecimalFormat("0.0%")));
		return jFreeChart;
	}

	/**
	 * 根据给定数据创建折线图
	 * Function Name: createLineChart
	 * @param xVal X轴属性
	 * @param yVal Y轴属性
	 * @param dataset 数据集
	 * @return
	 */
	public JFreeChart createLineChart(String xVal, String yVal, DefaultCategoryDataset dataset) {
		JFreeChart jFreeChart = null;
		if (if3D)
			jFreeChart = ChartFactory.createLineChart3D(title, xVal, yVal, dataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
		else
			jFreeChart = ChartFactory.createLineChart(title, xVal, yVal, dataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
		setPlot(jFreeChart.getPlot());
		return jFreeChart;
	}
	
	/**
	 * 根据给定数据创建区域图
	 * Function Name: createLineChart
	 * @param xVal X轴属性
	 * @param yVal Y轴属性
	 * @param dataset 数据集
	 * @return
	 */
	public JFreeChart createAreaChart(String xVal, String yVal, DefaultCategoryDataset dataset) {
		JFreeChart jFreeChart = ChartFactory.createAreaChart(title, xVal, yVal, dataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
		setPlot(jFreeChart.getPlot());
		return jFreeChart;
	}

	/**
	 * 根据给定数据创建柱状图
	 * Function Name: createBarChart
	 * @param xVal X轴属性
	 * @param yVal Y轴属性
	 * @param dataset 数据集
	 * @return
	 */
	public JFreeChart createBarChart(String xVal, String yVal, DefaultCategoryDataset dataset) {
		JFreeChart jFreeChart = null;
		if (if3D)
			jFreeChart = ChartFactory.createBarChart3D(title, xVal, yVal, dataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
		else
			jFreeChart = ChartFactory.createBarChart(title, xVal, yVal, dataset, PlotOrientation.VERTICAL, legend, tooltips, urls);
		setPlot(jFreeChart.getPlot());
		return jFreeChart;
	}
	
	/**
	 * 根据给定数据创建时序图
	 * Function Name: createTimeSeriesChart
	 * @param xVal X轴属性
	 * @param yVal Y轴属性
	 * @param list 数据集（TimeSeries）
	 * @return
	 */
	public JFreeChart createTimeSeriesChart(String xVal, String yVal, List<TimeSeries> list){
		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
		for(TimeSeries timeSeries : list){
			timeSeriesCollection.addSeries(timeSeries);
		}
		timeSeriesCollection.setDomainIsPointsInTime(true);
		JFreeChart jFreeChart = ChartFactory.createTimeSeriesChart(title, xVal, yVal, timeSeriesCollection, legend, tooltips, urls);
		setPlot(jFreeChart.getPlot());
		return jFreeChart;
	}
	
	/**
	 * 根据给定数据创建时序图
	 * Function Name: createTimeSeriesChart
	 * @param xVal X轴属性
	 * @param yVal Y轴属性
	 * @param timeSeriess 数据集（TimeSeries）
	 * @return
	 */
	public JFreeChart createTimeSeriesChart(String xVal, String yVal, TimeSeries[] timeSeriess){
		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
		for(TimeSeries timeSeries : timeSeriess){
			timeSeriesCollection.addSeries(timeSeries);
		}
		timeSeriesCollection.setDomainIsPointsInTime(false);
		JFreeChart jFreeChart = ChartFactory.createTimeSeriesChart(title, xVal, yVal, timeSeriesCollection, legend, tooltips, urls);
		setPlot(jFreeChart.getPlot());
		return jFreeChart;
	}
	
	private void setPlot(Plot plot){
		this.plot = plot;
		plot.setNoDataMessage("无数据可提供显示！");
		plot.setForegroundAlpha(0.65f);
		plot.setOutlineStroke(new BasicStroke(0));
	}
	
	public Plot getPlot() {
		return plot;
	}
	
	public boolean isLegend() {
		return legend;
	}

	public void setLegend(boolean legend) {
		this.legend = legend;
	}

	public boolean isTooltips() {
		return tooltips;
	}

	public void setTooltips(boolean tooltips) {
		this.tooltips = tooltips;
	}

	public boolean isUrls() {
		return urls;
	}

	public void setUrls(boolean urls) {
		this.urls = urls;
	}

	public static void main(String[] args) {
		/*pie: {
			DefaultPieDataset dfp = new DefaultPieDataset();
			dfp.setValue("A", 45);
			dfp.setValue("B", 25);
			dfp.setValue("C", 5);
			dfp.setValue("D", 35);
			dfp.setValue("E", 15);
			JFreeChartUtils.getInstence("测试图", 1000, 800, null, true).createPieChart(dfp);
		}*/
		
		/*line: {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			dataset.addValue(100, "北京", "苹果");
			dataset.addValue(200, "北京", "梨子");
			dataset.addValue(300, "北京", "葡萄");
			dataset.addValue(500, "上海", "苹果");
			dataset.addValue(200, "上海", "梨子");
			dataset.addValue(400, "上海", "葡萄");
			JFreeChartUtils.getInstence("测试图", 1000, 800, null).createLineChart("水果", "产量", dataset);
		}*/
		
		/*bar: {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			dataset.addValue(100, "北京", "苹果");
			dataset.addValue(200, "北京", "梨子");
			dataset.addValue(300, "北京", "葡萄");
			dataset.addValue(500, "上海", "苹果");
			dataset.addValue(200, "上海", "梨子");
			dataset.addValue(400, "上海", "葡萄");
			JFreeChartUtils.getInstence("测试图", 1000, 800, null).createBarChart("水果", "产量", dataset);
		}*/
		
		timeSeries: {
			TimeSeries timeSeries = new TimeSeries("L&G European Index Trust");
			timeSeries.add(new Month(3, 2001), 181.8D);
			timeSeries.add(new Month(4, 2001), 144.8D);
			timeSeries.add(new Month(5, 2001), 167.8D);
			timeSeries.add(new Month(6, 2001), 181.2D);
			timeSeries.add(new Month(7, 2001), 121.4D);
			timeSeries.add(new Month(8, 2001), 118.7D);
			timeSeries.add(new Month(9, 2001), 124.8D);
			TimeSeries timeSeries1 = new TimeSeries("L&G UK Index Trust");
			timeSeries1.add(new Month(1, 2001), 111.8D);
			timeSeries1.add(new Month(2, 2001), 144.8D);
			timeSeries1.add(new Month(3, 2001), 157.8D);
			timeSeries1.add(new Month(4, 2001), 121.2D);
			timeSeries1.add(new Month(5, 2001), 121.4D);
			timeSeries1.add(new Month(6, 2001), 118.7D);
			timeSeries1.add(new Month(7, 2001), 124.8D);
			List<TimeSeries> list = new ArrayList<TimeSeries>();
			list.add(timeSeries);
			list.add(timeSeries1);
			JFreeChartUtils.getInstence("测试图").createTimeSeriesChart("L", "G", list);
		}
		
		/*line2: {
			XYSeries xySeries = new XYSeries("First");
			xySeries.add(1.0D, 10.0D);
			xySeries.add(2D, 40D);
			xySeries.add(3D, 30D);
			xySeries.add(4D, 50D);
			xySeries.add(5D, 50D);
			xySeries.add(6D, 70D);
			xySeries.add(7D, 70D);
			xySeries.add(8D, 80D);
			xySeries.fireSeriesChanged();

			XYSeries xySeries1 = new XYSeries("Second");
			xySeries.add(1.0D, 5D);
			xySeries.add(2D, 7D);
			xySeries.add(3D, 6D);
			xySeries.add(4D, 8D);
			xySeries.add(5D, 4D);
			xySeries.add(6D, 4D);
			xySeries.add(7D, 2D);
			xySeries.add(8D, 1.0D);
			
			XYSeries xySeries2 = new XYSeries("Third");
			xySeries.add(3D, 4D);
			xySeries.add(4D, 3D);
			xySeries.add(5D, 2D);
			xySeries.add(6D, 3D);
			xySeries.add(7D, 6D);
			xySeries.add(8D, 3D);
			xySeries.add(9D, 4D);
			xySeries.add(10D, 3D);
			
			XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
			xySeriesCollection.addSeries(xySeries);
			xySeriesCollection.addSeries(xySeries1);
			xySeriesCollection.addSeries(xySeries2);
			JFreeChart jFreeChart = ChartFactory.createXYLineChart("Line Chart Demo 2", "X", "Y", xySeriesCollection, PlotOrientation.VERTICAL, true, true, false);
			ChartFrame frame = new ChartFrame("折线图", jFreeChart, true);
			frame.pack();
			frame.setVisible(true);
		}*/

	}
}
