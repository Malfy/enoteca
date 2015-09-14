package frame;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

public class GraficoProdotti extends ApplicationFrame{


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
 
    public GraficoProdotti(String title) throws Exception {
        super(title);
//        GraficoProdotto.this.windowClosing(event);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
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
    @SuppressWarnings("deprecation")
	private static XYDataset createDataset() throws Exception {

        TimeSeries s1 = new TimeSeries("Linea Entrate");
        ArrayList<entrate> listaEntrate=EntrateDAO.getAllEntrate();
        for(entrate e:listaEntrate){
//        	s1.add(new Day(e.getData().getDate(),e.getData().getMonth()+1,e.getData().getYear()+1900),e.getQuantita());
        }
        ArrayList<uscite> listaUscite=UsciteDAO.getAllUscite();
        ArrayList<uscite> listaCompattaUscite=new ArrayList<uscite>();
        TimeSeries s2 = new TimeSeries("Linea Uscite");
        boolean controllo=true;

        uscite appoggioU= new uscite();
        uscite appElim=new uscite();
        
        for(int a=0;a<listaUscite.size()-1;a++){
        	appElim=listaUscite.get(a);
        	controllo=true;
        	for(int b=a+1;b<listaUscite.size();b++){
        		if(appElim.getData().equals(listaUscite.get(b).getData()) && listaUscite.get(a)!=null){
        			if(controllo){
        				listaUscite.remove(a);
        				listaCompattaUscite.add(UsciteDAO.getUsciteSommaQuantita(appElim));
        			}
        			listaUscite.remove(b);
        			
        			controllo=false;
        		}

        	}
        	if(controllo && appElim!=null){
        		listaCompattaUscite.add(appElim);
        	}
        	
        }
        for(uscite e:listaCompattaUscite){
        	if(e!=null)
        		s2.add(new Day(e.getData().getDate(),e.getData().getMonth()+1,e.getData().getYear()+1900),e.getQuantita());
        }

//         TimeSeries s3 = new TimeSeries("andres2255");
//        s3.add(new Month(2, 2001), 129.6);
//      
//        s3.add(new Month(10, 2001), 106.1);
//        s3.add(new Month(1, 2002), 111.7);
//        s3.add(new Month(2, 2002), 111.0);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
//         dataset.addSeries(s3);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     * @throws Exception 
     */
    public static JPanel createDemoPanel() throws Exception {
        JFreeChart chart = createChart(createDataset());
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
    
    public static void visGrafico() throws Exception{
    	
    	GraficoProdotti demo = new GraficoProdotti("Grafico rapporto prodotto");
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
