package frame;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;



import model.entrate;
import model.uscite;


import DAO.EntrateDAO;
import DAO.UsciteDAO;


public class GraficoProdotto extends ApplicationFrame{


/**
	 * 
	 */
	//    public GraficoProdotti(String title) {
//		super(title);
//		// TODO Auto-generated constructor stub
//	}
	{
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }

    /**
     * A demonstration application showing how to create a simple time series
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     * @throws Exception 
     */
    public void windowClosing(final WindowEvent evt){
    	if(evt.getWindow() == this){
    	dispose();

    	}
    	}
 
    public GraficoProdotto(int id) throws Exception {
        super("Grafico PRODOTTO");
//        GraficoProdotto.this.windowClosing(event);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel(id);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Rapporto Entrata/uscita nel tempo",  // title
            "Date",             // x-axis label
            "Quantita",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));

        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     * @throws Exception 
     */
	private static XYDataset createDataset(int id) throws Exception {

        
        TimeSeries s2 = new TimeSeries("Linea Uscite");
        TimeSeries s1 = new TimeSeries("Linea Entrate");

        boolean controllo=true;

        ArrayList<entrate> datiEntrate= EntrateDAO.getEntrate(id);
        ArrayList<uscite> datiUscite=UsciteDAO.getUscite(id);
        
        
        for(entrate e:datiEntrate){
        		s1.add(new Day(e.getData().getDate(),e.getData().getMonth()+1,e.getData().getYear()+1900),e.getQuantita());
        }
        for(uscite e:datiUscite){
        		s2.add(new Day(e.getData().getDate(),e.getData().getMonth()+1,e.getData().getYear()+1900),e.getQuantita());
        }



        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * @param id 
     *
     * @return A panel.
     * @throws Exception 
     */
    public static JPanel createDemoPanel(int id) throws Exception {
        JFreeChart chart = createChart(createDataset(id));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     * @throws Exception 
     */
    
    public static void visGrafico(int id) throws Exception{
    	
    	GraficoProdotto demo = new GraficoProdotto(id);
    	demo.pack();
    	RefineryUtilities.centerFrameOnScreen(demo);
    	demo.setVisible(true);
    	demo.setDefaultCloseOperation(ApplicationFrame.DISPOSE_ON_CLOSE);

    }
    public static void main(String[] args ) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraficoProdotti demo = new GraficoProdotti(
			                "CIAO");
			        demo.pack();
			        RefineryUtilities.centerFrameOnScreen(demo);
			        demo.setVisible(true);
			        demo.setDefaultCloseOperation(ApplicationFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }


}
